package com.luna.media.javacv;

import cn.hutool.core.io.FileUtil;
import cn.hutool.setting.dialect.Props;
import com.luna.media.config.JavaCvConfigValue;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

/**
 * Javacv 工具类
 * 
 * @author Luna@win10
 * @date 2020/5/16 10:12
 */
public class JavaCvUtils {

    /** 转换器 */
    public static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

    private static final Logger                   log       = LoggerFactory.getLogger(JavaCvUtils.class);

    public static CascadeClassifier               faceCascade;

    public static CascadeClassifier getFaceCascade() {
        if (faceCascade == null) {
            if (JavaCvConfigValue.getFrontalFace() != null) {
                String path = FileUtil.getAbsolutePath(JavaCvConfigValue.getFrontalFace());
                faceCascade = new CascadeClassifier(path);
            } else {
                Props props = new Props("application.properties");
                String absolutePath =
                    FileUtil.getAbsolutePath(String.valueOf(props.get("luna.face.frontalFace")), JavaCvUtils.class);
                faceCascade = new CascadeClassifier(absolutePath);
            }
        }
        return faceCascade;
    }

    /**
     * 加载图像并显示在画布框中。
     * 如果映像无法加载，应用程序将使用代码1退出。
     *
     * @return loaded image
     */
    public Mat loadAndShowOrExit(File file) {
        return loadAndShowOrExit(file, IMREAD_COLOR);
    }

    /**
     * 加载图像并在画布框架中显示。 如果不能加载映像，应用程序将以代码1退出。
     *
     * @param flags 指定加载图像颜色类型的标志:
     * <ul>
     * <li>`>0` 返回一个3通道彩色图像</li>
     * <li>`=0` 返回一个灰度图像</li>
     * <li>`<0` Return the loaded image as is. Note that in the current implementation
     * the alpha channel, if any, is stripped from the output image. For example, a 4-channel
     * RGBA image is loaded as RGB if the `flags` is greater than 0.</li>
     * </ul>
     * Default is gray scale.
     * @return loaded image
     */
    public static Mat loadAndShowOrExit(File file, Integer flags) {
        Mat image = loadOrExit(file, flags);
        show(image, file.getName());
        return image;
    }

    /**
     * 加载一个图像。
     * 如果映像无法加载，应用程序将使用代码1退出。
     *
     * @return loaded image
     */
    public static Mat loadOrExit(File file) {
        return loadOrExit(file, IMREAD_COLOR);
    }

    /**
     * Load an image. If image cannot be loaded the application will exit with code 1.
     *
     * @param flags Flags specifying the color type of a loaded image:
     * <ul>
     * <li>`>0` Return a 3-channel color image</li>
     * <li>`=0` Return a gray scale image</li>
     * <li>`<0` Return the loaded image as is. Note that in the current implementation
     * the alpha channel, if any, is stripped from the output image. For example, a 4-channel
     * RGBA image is loaded as RGB if the `flags` is greater than 0.</li>
     * </ul>
     * Default is gray scale.
     * @return loaded image
     */
    public static Mat loadOrExit(File file, Integer flags) {
        Mat image = imread(file.getAbsolutePath(), flags);
        if (image.empty()) {
            log.info("Couldn't load image: " + file.getAbsolutePath());
            System.exit(1);
        }
        return image;
    }

    /**
     * 在窗口中显示图像。
     * 关闭窗口将退出应用程序。
     */
    public static void show(Mat mat, String title) {
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        CanvasFrame canvas = new CanvasFrame(title, 1);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.showImage(converter.convert(mat));
    }

    /** Show image in a window. Closing the window will exit the application. */
    public static void show(BufferedImage image, String title) {
        CanvasFrame canvas = new CanvasFrame(title, 1);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.showImage(image);
    }

    /**
     * Save the image to the specified file.
     *
     * The image format is chosen based on the filename extension (see `imread()` in OpenCV documentation for the list
     * of extensions).
     * Only 8-bit (or 16-bit in case of PNG, JPEG 2000, and TIFF) single-channel or
     * 3-channel (with ‘BGR’ channel order) images can be saved using this function.
     * If the format, depth or channel order is different, use Mat::convertTo() , and cvtColor() to convert it before
     * saving.
     *
     * @param file file to save to. File name extension decides output image format.
     * @param image image to save.
     */
    public void save(File file, Mat image) {
        imwrite(file.getAbsolutePath(), image);
    }

}
