package com.luna.commons.javacv;

import javax.swing.JFrame;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_objdetect;


/**
 * 截图或视频录制
 * @author Luna@win10
 * @date 2020/5/16 10:12
 */
public class VideoRecording {

    /**
     * 不预览获取摄像头捕捉信息
     * 本机摄像头默认0，这里使用javacv的抓取器，至于使用的是ffmpeg还是opencv，请自行查看源码
     * 
     * @throws Exception
     * @throws InterruptedException
     */
    public static void getCamera(Integer number, String outFolderPath, Integer size, String prefix, String type)
        throws Exception {
        if (number == null) {
            number = 0;
        }
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(number);
        grabber.start(); // 开始获取摄像头数据
        int tag = 1;
        while (tag <= size) {
            Mat mat = JavaCvUtils.converter.convertToMat(grabber.grabFrame());
            opencv_imgcodecs.imwrite(outFolderPath + prefix + tag + type, mat);
            tag++;
        }
        grabber.stop();
    }

    /**
     * 预览摄像
     * 本机摄像头默认0，这里使用javacv的抓取器，至于使用的是ffmpeg还是opencv，请自行查看源码
     *
     * @param number
     * @throws Exception
     */
    public static void getScreenshots(Integer number) throws Exception {
        if (number == null) {
            number = 0;
        }
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(number);
        grabber.start(); // 开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("摄像头");
        // 新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        while (true) {
            if (!canvas.isDisplayable()) {
                grabber.stop();
                // 停止抓取
                System.exit(2);
                // 退出
                break;
            }
            canvas.showImage(grabber.grab());
            // 获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
            Thread.sleep(50);
            // 50毫秒刷新一次图像
        }
    }

    /**
     * 按帧录制本机摄像头视频（边预览边录制，停止预览即停止录制）
     *
     * @author eguid
     * @param outputFile -录制的文件路径，也可以是rtsp或者rtmp等流媒体服务器发布地址
     * @param frameRate - 视频帧率
     * @throws Exception
     * @throws InterruptedException
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     */
    public static void recordCamera(String outputFile, double frameRate)
        throws Exception {
        Loader.load(opencv_objdetect.class);
        FrameGrabber grabber = FrameGrabber.createDefault(0);
        // 本机摄像头默认0，这里使用javacv的抓取器，至于使用的是ffmpeg还是opencv，请自行查看源码
        grabber.start();
        // 转换器
        IplImage grabbedImage = JavaCvUtils.converter.convert(grabber.grab());
        // 抓取一帧视频并将其转换为图像，至于用这个图像用来做什么？加水印，人脸识别等等自行添加
        int width = grabbedImage.width();
        int height = grabbedImage.height();

        FrameRecorder recorder = FrameRecorder.createDefault(outputFile, width, height);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        // avcodec.AV_CODEC_ID_H264，编码
        recorder.setFormat("flv");
        // 封装格式，如果是推送到rtmp就必须是flv封装格式
        recorder.setFrameRate(frameRate);

        recorder.start();
        // 开启录制器
        long startTime = 0;
        long videoTS = 0;
        CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma() / grabber.getGamma());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        // 不知道为什么这里不做转换就不能推到rtmp
        while (frame.isVisible() && (grabbedImage = JavaCvUtils.converter.convert(grabber.grab())) != null) {
            Frame rotatedFrame = JavaCvUtils.converter.convert(grabbedImage);
            frame.showImage(rotatedFrame);
            if (startTime == 0) {
                startTime = System.currentTimeMillis();
            }
            videoTS = 1000 * (System.currentTimeMillis() - startTime);
            recorder.setTimestamp(videoTS);
            recorder.record(rotatedFrame);
            Thread.sleep(40);
        }
        frame.dispose();
        recorder.stop();
        recorder.release();
        grabber.stop();
    }

}