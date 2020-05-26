package com.luna.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

/**
 * 图片文件，与 byte[] 互转
 */
public class ImageUtils {

    /**
     * 图片转字节
     * 
     * @param imgFile
     * @return
     */
    public static byte[] getBytes(String imgFile) {
        // 将图片文件转化为字节数组

        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 字节转图片
     * 
     * @param data
     * @param path
     */
    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] bytes = ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Camera Roll\\github.png");
        ImageUtils.byte2image(bytes, "D:\\a.jpg");
    }
}