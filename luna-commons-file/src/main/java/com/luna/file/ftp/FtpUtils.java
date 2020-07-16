package com.luna.file.ftp;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/4/28 20:33
 */
public class FtpUtils {

    /**
     * 设置超时时间
     *
     * @param defaultTimeout 超时时间
     * @param connectTimeout 超时时间
     * @param dataTimeout 超时时间
     */
    private static void setTimeout(FTPClient ftpClient, int defaultTimeout, int connectTimeout, int dataTimeout) {
        ftpClient.setDefaultTimeout(defaultTimeout);
        ftpClient.setConnectTimeout(connectTimeout);
        ftpClient.setDataTimeout(dataTimeout);
    }

    /**
     * 连接到ftp
     */
    public static void connect(FTPClient ftpClient, String host, int port, String username, String password,
        String ftpBasePath)
        throws IOException {
        try {
            ftpClient.connect(host, port);
        } catch (UnknownHostException e) {
            throw new IOException("Can't find FTP server :" + host);
        }

        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            disconnect(ftpClient);
            throw new IOException("Can't connect to server :" + host);
        }

        if (!ftpClient.login(username, password)) {
            disconnect(ftpClient);
            throw new IOException("Can't login to server :" + host);
        }

        // set data transfer mode.
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        // Use passive mode to pass firewalls.
        ftpClient.enterLocalPassiveMode();

        initFtpBasePath(ftpClient, ftpBasePath);
    }

    /**
     * 连接ftp时保存刚登陆ftp时的路径
     */
    private static String initFtpBasePath(FTPClient ftpClient, String ftpBasePath) throws IOException {
        if (StringUtils.isEmpty(ftpBasePath)) {
            synchronized (FtpUtils.class) {
                if (StringUtils.isEmpty(ftpBasePath)) {
                    return ftpClient.printWorkingDirectory();
                }
            }
        }
        return ftpBasePath;
    }

    /**
     * ftp是否处于连接状态，是连接状态返回<tt>true</tt>
     *
     * @return boolean 是连接状态返回<tt>true</tt>
     */
    public static boolean isConnected(FTPClient ftpClient) {
        return ftpClient.isConnected();
    }

    /**
     * 上传文件到对应目录下
     *
     * @param fileName 文件名
     * @param inputStream 文件输入流
     * @param uploadDir 上传文件的父路径
     * @return java.lang.String
     */
    public static String uploadFile(FTPClient ftpClient, String fileName, InputStream inputStream, String uploadDir,
        String ftpBasePath)
        throws IOException {
        changeWorkingDirectory(ftpClient, ftpBasePath);
        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd");
        makeDirs(ftpClient, uploadDir);
        storeFile(ftpClient, fileName, inputStream);
        return uploadDir + "/" + fileName;
    }

    /**
     * 根据uploadFile返回的路径，从ftp下载文件到指定输出流中
     *
     * @param ftpRealFilePath 方法uploadFile返回的路径
     * @param outputStream 输出流
     */
    public static void downloadFileFromDailyDir(FTPClient ftpClient, String ftpRealFilePath, OutputStream outputStream,
        String ftpBasePath) throws IOException {
        changeWorkingDirectory(ftpClient, ftpBasePath);
        ftpClient.retrieveFile(ftpRealFilePath, outputStream);
    }

    /**
     * 获取ftp上指定文件名到输出流中
     *
     * @param ftpFileName 文件在ftp上的路径 如绝对路径 /home/ftpuser/123.txt 或者相对路径 123.txt
     * @param out 输出流
     */
    public static void retrieveFile(FTPClient ftpClient, String ftpFileName, OutputStream out) throws IOException {
        try {
            FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
            if (fileInfoArray == null || fileInfoArray.length == 0) {
                throw new FileNotFoundException("File '" + ftpFileName + "' was not found on FTP server.");
            }

            FTPFile fileInfo = fileInfoArray[0];
            if (fileInfo.getSize() > Integer.MAX_VALUE) {
                throw new IOException("File '" + ftpFileName + "' is too large.");
            }

            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException(
                    "Error loading file '" + ftpFileName + "' from FTP server. Check FTP permissions and path.");
            }
            out.flush();
        } finally {
            closeStream(out);
        }
    }

    /**
     * 将输入流存储到指定的ftp路径下
     *
     * @param ftpFileName 文件在ftp上的路径 如绝对路径 /home/ftpuser/123.txt 或者相对路径 123.txt
     * @param in 输入流
     */
    private static void storeFile(FTPClient ftpClient, String ftpFileName, InputStream in) throws IOException {
        try {
            if (!ftpClient.storeFile(ftpFileName, in)) {
                throw new IOException(
                    "Can't upload file '" + ftpFileName + "' to FTP server. Check FTP permissions and path.");
            }
        } finally {
            closeStream(in);
        }
    }

    /**
     * 根据文件ftp路径名称删除文件
     *
     * @param ftpFileName 文件ftp路径名称
     */
    public static void deleteFile(FTPClient ftpClient, String ftpFileName) throws IOException {
        if (!ftpClient.deleteFile(ftpFileName)) {
            throw new IOException("Can't remove file '" + ftpFileName + "' from FTP server.");
        }
    }

    /**
     * 上传文件到ftp
     *
     * @param ftpPathName 上传到ftp文件路径名称
     * @param localFile 本地文件路径名称
     */
    public static void upload(FTPClient ftpClient, String ftpPathName, String fileName, File localFile)
        throws IOException {

        if (!localFile.exists()) {
            throw new IOException("Can't upload '" + localFile.getAbsolutePath() + "'. This file doesn't exist.");
        }
        ftpPathName = ftpPathName + fileName;
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(localFile));
            if (!ftpClient.storeFile(ftpPathName, in)) {
                throw new IOException(
                    "Can't upload file '" + ftpPathName + "' to FTP server. Check FTP permissions and path.");
            }
        } finally {
            closeStream(in);
        }
    }

    /**
     * 将本地目录里面的文件上传到ftp的文件夹内,远程目录不存在则创建目录
     *
     * @param remotePath ftp上文件夹路径名称
     * @param localPath 本地上传的文件夹路径名称
     */
    public static void uploadDir(FTPClient ftpClient, String remotePath, String localPath) throws IOException {
        localPath = localPath.replace("\\\\", "/");
        File file = new File(localPath);
        if (file.exists()) {
            if (!ftpClient.changeWorkingDirectory(remotePath)) {
                ftpClient.makeDirectory(remotePath);
                ftpClient.changeWorkingDirectory(remotePath);
            }
            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    if (f.isDirectory() && !f.getName().equals(".") && !f.getName().equals("..")) {
                        uploadDir(ftpClient, remotePath + "/" + f.getName(), f.getPath());
                    } else if (f.isFile()) {
                        upload(ftpClient, remotePath, "" + "/" + f.getName(), f);
                    }
                }
            }
        }
    }

    /**
     * 下载ftp文件到本地上
     *
     * @param ftpFileName ftp文件路径名称带文件名
     * @param localFile 本地文件路径名称带文件名
     */
    public static void download(FTPClient ftpClient, String ftpFileName, File localFile) throws IOException {
        OutputStream out = null;
        try {
            FTPFile[] fileInfoArray = ftpClient.listFiles(ftpFileName);
            if (fileInfoArray == null || fileInfoArray.length == 0) {
                throw new FileNotFoundException("File " + ftpFileName + " was not found on FTP server.");
            }

            FTPFile fileInfo = fileInfoArray[0];
            if (fileInfo.getSize() > Integer.MAX_VALUE) {
                throw new IOException("File " + ftpFileName + " is too large.");
            }

            out = new BufferedOutputStream(new FileOutputStream(localFile));
            if (!ftpClient.retrieveFile(ftpFileName, out)) {
                throw new IOException(
                    "Error loading file " + ftpFileName + " from FTP server. Check FTP permissions and path.");
            }
            out.flush();
        } finally {
            closeStream(out);
        }
    }

    /**
     * 改变工作目录
     *
     * @param dir ftp服务器上目录
     * @return boolean 改变成功返回true
     */
    public static boolean changeWorkingDirectory(FTPClient ftpClient, String dir) {
        if (!ftpClient.isConnected()) {
            return false;
        }
        try {
            return ftpClient.changeWorkingDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 下载ftp服务器下文件夹到本地
     *
     * @param remotePath ftp上文件夹路径名称
     * @param localPath 本地上传的文件夹路径名称
     */
    public static void downloadDir(FTPClient ftpClient, String remotePath, String localPath) throws IOException {
        localPath = localPath.replace("\\\\", "/");
        File file = new File(localPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FTPFile[] ftpFiles = ftpClient.listFiles(remotePath);
        for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
            FTPFile ftpFile = ftpFiles[i];
            if (ftpFile.isDirectory() && !ftpFile.getName().equals(".") && !ftpFile.getName().equals("..")) {
                downloadDir(ftpClient, remotePath + "/" + ftpFile.getName(), localPath + "/" + ftpFile.getName());
            } else {
                download(ftpClient, remotePath + "/" + ftpFile.getName(),
                    new File(localPath + "/" + ftpFile.getName()));
            }
        }
    }

    /**
     * 列出ftp上文件目录下的文件
     *
     * @param filePath ftp上文件目录
     * @return java.util.List<java.lang.String>
     */
    public List<String> listFileNames(FTPClient ftpClient, String filePath) throws IOException {
        FTPFile[] ftpFiles = ftpClient.listFiles(filePath);
        List<String> fileList = new ArrayList<>();
        if (ftpFiles != null) {
            for (int i = 0; i < ftpFiles.length; i++) {
                FTPFile ftpFile = ftpFiles[i];
                if (ftpFile.isFile()) {
                    fileList.add(ftpFile.getName());
                }
            }
        }

        return fileList;
    }

    /**
     * 发送ftp命令到ftp服务器中
     *
     * @param args ftp命令
     */
    public static void sendSiteCommand(FTPClient ftpClient, String args) throws IOException {
        if (!ftpClient.isConnected()) {
            ftpClient.sendSiteCommand(args);
        }
    }

    /**
     * 获取当前所处的工作目录
     *
     * @return java.lang.String 当前所处的工作目录
     */
    public static String printWorkingDirectory(FTPClient ftpClient) {
        if (!ftpClient.isConnected()) {
            return "";
        }
        try {
            return ftpClient.printWorkingDirectory();
        } catch (IOException e) {
            // do nothing
        }

        return "";
    }

    /**
     * 切换到当前工作目录的父目录下
     *
     * @return boolean 切换成功返回true
     */
    public static boolean changeToParentDirectory(FTPClient ftpClient) {

        if (!ftpClient.isConnected()) {
            return false;
        }

        try {
            return ftpClient.changeToParentDirectory();
        } catch (IOException e) {
            // do nothing
        }

        return false;
    }

    /**
     * 返回当前工作目录的上一级目录
     *
     * @return java.lang.String 当前工作目录的父目录
     */
    public static String printParentDirectory(FTPClient ftpClient) {
        if (!ftpClient.isConnected()) {
            return "";
        }

        String w = printWorkingDirectory(ftpClient);
        changeToParentDirectory(ftpClient);
        String p = printWorkingDirectory(ftpClient);
        changeWorkingDirectory(ftpClient, w);

        return p;
    }

    /**
     * 创建目录
     *
     * @param pathname 路径名
     * @return boolean 创建成功返回true
     */
    public boolean makeDirectory(FTPClient ftpClient, String pathname) throws IOException {
        return ftpClient.makeDirectory(pathname);
    }

    /**
     * 创建多个目录
     *
     * @param pathname 路径名
     */
    public static void makeDirs(FTPClient ftpClient, String pathname) throws IOException {
        pathname = pathname.replace("\\\\", "/");
        String[] pathnameArray = pathname.split("/");
        for (String each : pathnameArray) {
            if (StringUtils.isNotEmpty(each)) {
                ftpClient.makeDirectory(each);
                ftpClient.changeWorkingDirectory(each);
            }
        }
    }

    /**
     * 关闭流
     *
     * @param stream 流
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }

    /**
     * 关闭ftp连接
     */
    public static void disconnect(FTPClient ftpClient) {
        if (null != ftpClient && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException ex) {
                // do nothing
            }
        }
    }
}
