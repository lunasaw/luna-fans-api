package com.luna.common.javacv;

import javax.swing.*;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 * @author Luna@win10
 * @date 2020/5/27 15:37
 */
public class CheckFace2Video {

    /**
     * 获取摄像 调用人脸检测 返回显示
     * 
     * @throws Exception
     */
    public static void cameraJavaCv() throws Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start(); // 开始获取摄像头数据
        CanvasFrame canvas = new CanvasFrame("人脸检测");
        // 新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
            if (!canvas.isEnabled()) {
                // 窗口是否关闭
                grabber.stop();// 停止抓取
                System.exit(0);
                // 退出
            }
            Frame frame = grabber.grab();
            Mat scr = JavaCvUtils.converter.convertToMat(frame);
            // 将获取的frame转化成mat数据类型
            Mat mat = CheckFace.detectFace(scr);
            // 人脸检测
            frame = JavaCvUtils.converter.convert(mat);
            // 将检测结果重新的mat重新转化为frame
            canvas.showImage(frame);
            // 获取摄像头图像并放到窗口上显示，frame是一帧视频图像
            Thread.sleep(50);
            // 50毫秒刷新一次图像
        }
    }

}
