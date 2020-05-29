package com.luna.commons.javacv;

import com.luna.commons.baidu.Config.GetBaiduKey;
import com.luna.commons.ffmpeg.FfmpegUtil;
import com.luna.commons.file.FileUtils;
import com.luna.commons.utils.StringUtils;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 视频处理工具
 * 
 * @author Luna@win10
 * @date 2020/5/16 10:12
 */
public class VideoUtil {

    private static final Logger log = LoggerFactory.getLogger(GetBaiduKey.class);

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
        fFmpegFrameGrabber.stop();
        fFmpegFrameGrabber.release();
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
        fFmpegFrameGrabber.stop();
        fFmpegFrameGrabber.release();
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

    /**
     * 视频文件指定时间段的帧截取
     *
     * @param start
     * @param end
     */
    public static void videoIntercept(String inputPath, String outPrefix, String outPutPath, String outType,
        Integer frameNumber, String start, String end) {
        if (!FileUtils.isFileExists(inputPath)) {
            return;
        }
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(inputPath);
        int videoTime = FfmpegUtil.getTimelen(start);
        int videoTime1 = FfmpegUtil.getTimelen(end);
        try {
            fFmpegFrameGrabber.start();
            // 开始视频提取帧
            int second = 0;
            int tag = 1;
            int tmmp = frameNumber;
            int lengthInFrames = fFmpegFrameGrabber.getLengthInFrames();
            for (int i = 0; i < lengthInFrames; i++) {
                // 捕捉帧
                Frame frame = fFmpegFrameGrabber.grabImage();
                double frameRate = fFmpegFrameGrabber.getFrameRate();
                // 第几秒
                if (i % frameRate == 0) {
                    second++;
                }
                // 来到范围内
                if (second > videoTime && second <= videoTime1) {
                    if (tmmp > 0) {
                        doExecuteFrame(frame, outPrefix, outType, outPutPath, tag);
                        tag++;
                        tmmp--;
                    }
                    if (tmmp == 0) {
                        videoTime++;
                        tmmp = frameNumber;
                    }
                }
            }
            fFmpegFrameGrabber.stop();
        } catch (IOException e) {
            log.error("视频抽帧异常", e, inputPath);
        } finally {
            try {
                fFmpegFrameGrabber.stop();
                fFmpegFrameGrabber.release();
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Frame图片输出
     *
     * @param frame
     * @param outPrefix 输出前缀
     * @param outType 输出类型
     * @param outPutPath 输出路径
     * @param index
     */
    public static void doExecuteFrame(Frame frame, String outPrefix, String outType, String outPutPath, int index) {
        if (frame == null || frame.image == null) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        String fileName = outPutPath + outPrefix + index + "." + outType;
        BufferedImage bi = converter.getBufferedImage(frame);
        File output = new File(fileName);
        try {
            ImageIO.write(bi, outType, output);
        } catch (IOException e) {
            log.error("图片输出异常", e, outPrefix);
        }
    }

    /**
     * 获取视频长度 -秒
     * 
     * @param videoPath
     * @return
     */
    public static Double getVedioTime(String videoPath) {
        if (FileUtils.isFileExists(videoPath)) {
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoPath);
            try {
                fFmpegFrameGrabber.start();
                int lengthInFrames = fFmpegFrameGrabber.getLengthInFrames();
                double videoFrameRate = fFmpegFrameGrabber.getVideoFrameRate();
                return lengthInFrames / videoFrameRate;
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 图片合成视频
     * 
     * @param savePath 保存视频路径
     * @param type 保存视频类型
     * @param times 保存时间
     * @param imgType 输入图片类型
     * @param prefix 输入图片前缀
     * @param imgPath 图片文件夹
     * @param width 图片宽度
     * @param height 图片高度
     * @param frameRate 帧率
     * @throws FrameRecorder.Exception
     */
    public static void createVideo(String savePath, String type, Integer times, String imgType, String prefix,
        String imgPath,
        Integer width, Integer height,
        Integer frameRate) throws FrameRecorder.Exception {
        // 视频宽高最好是按照常见的视频的宽高 16：9 或者 9：16
        if (width == null) {
            width = 1600;
        }
        if (height == null) {
            height = 900;
        }
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(savePath, width, height);
        // 设置视频编码层模式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        // 设置默认视频为25帧每秒
        Integer frameRateLo = 25;
        if (frameRate != null) {
            frameRateLo = frameRate;
        }
        recorder.setFrameRate(frameRateLo);

        // 设置视频图像数据格式
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        if (StringUtils.isEmpty(type)) {
            recorder.setFormat("mp4");
        } else {
            recorder.setFormat(type);
        }
        try {
            recorder.start();
            Java2DFrameConverter converter = new Java2DFrameConverter();
            // 录制一个5秒的视频
            int tag = 1;
            for (int i = 0; i < times; i++) {
                for (Integer integer = 0; integer < frameRateLo; integer++) {
                    File file = new File(imgPath + prefix + tag + "." + imgType);
                    tag++;
                    BufferedImage read = ImageIO.read(file);
                    recorder.record(converter.getFrame(read));
                }
            }
            log.info("视频生成成功", savePath);
        } catch (Exception e) {
            log.error("视频生成失败", savePath);
            e.printStackTrace();
        } finally {
            // 最后一定要结束并释放资源
            recorder.stop();
            recorder.release();
        }
    }

    /**
     * 音频视频合并
     *
     * @param videoPath 视频路径
     * @param videoType 视频类型 默认mp4
     * @param audioPath 音频路径
     * @param outPut 输出路径 同输入类型
     * @return
     * @throws Exception
     */
    public static boolean mergeAudioAndVideo(String videoPath, String videoType, String audioPath, String outPut)
        throws Exception {
        boolean isCreated = true;
        File file = new File(videoPath);
        if (!file.exists()) {
            return false;
        }
        FrameRecorder recorder = null;
        FrameGrabber grabber1 = null;
        FrameGrabber grabber2 = null;
        try {
            // 抓取视频帧
            grabber1 = new FFmpegFrameGrabber(videoPath);
            // 抓取音频帧
            grabber2 = new FFmpegFrameGrabber(audioPath);
            grabber1.start();
            grabber2.start();
            // 创建录制
            recorder = new FFmpegFrameRecorder(outPut,
                grabber1.getImageWidth(), grabber1.getImageHeight(),
                grabber2.getAudioChannels());

            if (StringUtils.isEmpty(videoType)) {
                recorder.setFormat("mp4");
            } else {
                recorder.setFormat(videoType);
            }
            recorder.setFrameRate(grabber1.getFrameRate());
            recorder.setSampleRate(grabber2.getSampleRate());
            recorder.start();

            Frame frame1;
            Frame frame2;
            // 先录入视频
            while ((frame1 = grabber1.grabFrame()) != null) {
                recorder.record(frame1);
            }
            // 然后录入音频
            while ((frame2 = grabber2.grabFrame()) != null) {
                recorder.record(frame2);
            }
            grabber1.stop();
            grabber2.stop();
            recorder.stop();
            log.info("音视频合并生成成功", outPut);
        } catch (Exception e) {
            log.error("音视频合并生成成功", outPut);
            e.printStackTrace();
        } finally {
            try {
                if (recorder != null) {
                    recorder.release();
                }
                if (grabber1 != null) {
                    grabber1.release();
                }
                if (grabber2 != null) {
                    grabber2.release();
                }
            } catch (FrameRecorder.Exception e) {
                e.printStackTrace();
            }
        }
        return isCreated;
    }
}
