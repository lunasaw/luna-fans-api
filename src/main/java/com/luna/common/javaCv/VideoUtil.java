package com.luna.common.javaCv;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

public class VideoUtil {

    /**
     * 获取视频开始第几帧
     * 
     * @param path
     * @param firstPath
     * @param type
     * @param integer
     * @throws Exception
     */
    public static void getVideoFirstImg(String path, String firstPath, String type, Integer integer) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(path);
        // 开始播放
        fFmpegFrameGrabber.start();
        // 指定第几帧
        fFmpegFrameGrabber.setFrameNumber(integer);
        // 获取指定第几帧的图片
        Frame frame = fFmpegFrameGrabber.grabImage();
        // 文件绝对路径+名字
        File outPut = new File(firstPath);
        ImageIO.write(FrameToBufferedImage(frame), type, outPut);
    }

    /**
     * 获取视频倒数第几帧
     * 
     * @param path
     * @param lastPath
     * @param type
     * @param integer
     * @throws Exception
     */
    public static void getVideoLastImg(String path, String lastPath, String type, Integer integer) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(path);
        // 开始播放
        fFmpegFrameGrabber.start();
        // 获取视频总帧数
        int ftp = fFmpegFrameGrabber.getLengthInFrames();
        // 指定第几帧
        fFmpegFrameGrabber.setFrameNumber(ftp - integer);
        // 获取指定第几帧的图片
        Frame frame = fFmpegFrameGrabber.grabImage();
        File outPut = new File(lastPath);
        ImageIO.write(FrameToBufferedImage(frame), type, outPut);
    }

    /**
     * Frame 转 BufferedImage
     *
     * @param frame
     * @return
     */
    public static BufferedImage FrameToBufferedImage(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }

    /**
     * 旋转角度的
     * 
     * @param src
     * @param angle
     * @return
     */
    public static IplImage rotate(IplImage src, int angle) {
        IplImage img = IplImage.create(src.height(), src.width(), src.depth(), src.nChannels());
        opencv_core.cvTranspose(src, img);
        opencv_core.cvFlip(img, img, angle);
        return img;
    }

    /**
     * frame 转 图片
     * 
     * @param f
     * @param path 转换图片地址
     */
    public static void doExecuteFrame(Frame f, String path) {
        if (null == f || null == f.image) {
            return;
        }
        Mat mat = JavaCvUtils.converter.convertToMat(f);
        opencv_imgcodecs.imwrite(path, mat);
    }

}
