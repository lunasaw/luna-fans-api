package com.luna.file.file;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;

import java.io.*;

public class FileEditUtil {

    public static Integer writeToFile(String filePath,String content){
        File file = new File(filePath);
        FileOutputStream fos = null;
        OutputStream out = null;
        BufferedWriter bw = null;

        if (!file.exists()) {
            try {
                file.createNewFile();
                out = new FileOutputStream(file);
                bw = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
                bw.write(content);

                return 0;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            } finally {
                try {
                    bw.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public static String  readFile(String filePath){
        String content = null;
        File file = new File(filePath);
        try {
            FileInputStream in = new FileInputStream(file);
            // size 为字串的长度 ，这里一次性读完
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            content = new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 用字节文件读取文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] fileToByteArray(String filePath){
        byte[] data = new byte[0];
        try {
            InputStream in = new FileInputStream(filePath);
            data = toByteArray(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件路径不存在");
        }
        return data;
    }

    public static byte[] toByteArray(InputStream in)  {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        try {
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件路径不存在");
        }
        return out.toByteArray();
    }

    /**
     * 获得指定文件的byte数组
     */
    private byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static String  readFile(File file){
        String content = null;
        try {
            FileInputStream in = new FileInputStream(file);
            // size 为字串的长度 ，这里一次性读完
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            content = new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     根据byte数组，生成文件
     */
    public static void byteToFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+File.separator+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
