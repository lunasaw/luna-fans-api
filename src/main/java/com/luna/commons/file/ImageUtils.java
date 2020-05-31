package com.luna.commons.file;

import com.luna.commons.baidu.Config.GetBaiduKey;
import com.luna.commons.dto.constant.ResultCode;
import com.luna.commons.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import javax.imageio.stream.FileImageOutputStream;

/**
 * 图片文件，与 byte[] 互转
 */
public class ImageUtils {

    private static final Logger log = LoggerFactory.getLogger(GetBaiduKey.class);

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
            log.info("Make Picture success,Please find image in " + path);
        } catch (Exception ex) {
            throw new FileException(ResultCode.PARAMETER_INVALID, "Exception: " + ex);
        }
    }
}