package com.luna.file.fastDFS;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 描述
 *
 * @author luna
 * @version 1.0
 * @package com\luna\file\fastDFS
 * @since 1.0
 */
public class FastDFSClient {

    private static final Logger log = LoggerFactory.getLogger(FastDFSClient.class);

    static {
        // 从classpath下获取文件对象获取路径
        String path = new ClassPathResource("config/fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "配置文件获取失败:" + e.getMessage());
        }
    }

    /**
     * 图片上传
     * 
     * @param file
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 参数1 字节数组
            // 参数2 扩展名(不带点)
            // 参数3 元数据( 文件的大小,文件的作者,文件的创建时间戳)
            NameValuePair[] meta_list =
                new NameValuePair[] {new NameValuePair(file.getAuthor()), new NameValuePair(file.getName())};

            String[] strings = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);

            return strings;
            // strings[0]==group1 strings[1]=M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "图片上传失败:" + e.getMessage());
        }
    }

    /**
     * 图片下载
     * 
     * @param groupName 组名
     * @param remoteFileName 远程路径
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            // 3.创建trackerclient对象
            TrackerClient trackerClient = new TrackerClient();
            // 4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            // 5.创建stroageserver 对象
            // 6.创建storageclient 对象
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 7.根据组名 和 文件名 下载图片

            // 参数1:指定组名
            // 参数2 :指定远程的文件名
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            return byteArrayInputStream;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "图片下载失败:" + e.getMessage());
        } finally {
            try {
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "系统异常,请重试:" + e.getMessage());
            }
        }
    }

    /**
     * 图片删除
     * 
     * @param groupName 组名
     * @param remoteFileName 远程路径
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        try {
            // 3.创建trackerclient对象
            TrackerClient trackerClient = new TrackerClient();
            // 4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            // 5.创建stroageserver 对象
            // 6.创建storageclient 对象
            StorageClient storageClient = new StorageClient(trackerServer, null);
            int i = storageClient.delete_file(groupName, remoteFileName);
            if (i == 0) {
                log.info("删除成功,groupName={},remoteFileName={}", groupName, remoteFileName);
            } else {
                throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "系统异常,请重试:" + e.getMessage());
        }
    }

    /**
     * 根据组名获取组的信息
     * 
     * @param groupName
     * @return
     */
    public static StorageServer getStorages(String groupName) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            // 4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();

            // 参数1 指定traqckerserver 对象
            // 参数2 指定组名
            StorageServer group1 = trackerClient.getStoreStorage(trackerServer, groupName);
            return group1;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "根据组名获取组的信息失败:" + e.getMessage());
        }
    }

    /**
     * 根据文件名和组名获取文件的信息
     * 
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            // 4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, null);

            // 参数1 指定组名
            // 参数2 指定文件的路径
            FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "根据文件名和组名获取文件的信息失败:" + e.getMessage());
        }
    }

    /**
     * 根据文件名和组名 获取组信息的数组信息
     * 
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) {
        try {
            // 3.创建trackerclient对象
            TrackerClient trackerClient = new TrackerClient();
            // 4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();

            ServerInfo[] group1s = trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
            return group1s;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "根据文件名和组名 获取组信息的数组信息失败:" + e.getMessage());
        }
    }

    /**
     * 获取tracker 的ip和端口的信息
     * http://47.92.89.8:8080
     * 
     * @return
     */
    public static String getTrackerUrl() {
        try {
            // 3.创建trackerclient对象
            TrackerClient trackerClient = new TrackerClient();
            // 4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            // tracker 的ip的信息
            String hostString = trackerServer.getInetSocketAddress().getHostString();

            // http://47.92.89.8:8080/group1/M00/00/00/wKjThF1aW9CAOUJGAAClQrJOYvs424.jpg img
            int g_tracker_http_port = ClientGlobal.getG_tracker_http_port();
            return "http://" + hostString + ":" + g_tracker_http_port;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, "获取tracker 的ip和端口的信息失败:" + e.getMessage());
        }
    }
}
