package com.luna.common.utils.img;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(ResultCode.PARAMETER_INVALID, "Exception: " + e.getMessage());
        }
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(ResultCode.PARAMETER_INVALID, "Exception: " + e.getMessage());
        }
    }
}