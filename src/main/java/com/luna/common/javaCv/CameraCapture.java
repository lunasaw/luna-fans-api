package com.luna.common.javaCv;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.opencv.imgproc.Imgproc.COLOR_BGRA2GRAY;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luna.common.baidu.Config.GetBaiduKey;

/**
 * 人脸识别调试
 * 
 * @author Luna@win10
 * @date 202015:37
 */
public class CameraCapture {

    public static void main(String[] args) {
        try {
            TurnOnTheCamera();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void TurnOnTheCamera() throws Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.setImageWidth(300);
        grabber.setImageHeight(300);
        CanvasFrame canvas = new CanvasFrame("摄像头");
        // 新建一个窗口
        canvas.setLayout(null);
        canvas.setAlwaysOnTop(true);
        Font font = new Font("微软雅黑", Font.BOLD, 15);
        JButton jButton = new JButton("确认上传");
        JButton jButton1 = new JButton("继续拍照");
        jButton1.setEnabled(false);
        JButton jButton2 = new JButton("关闭");
        jButton.setFont(font);
        jButton1.setFont(font);
        jButton2.setFont(font);
        jButton.setBounds(0, 300, 100, 50);
        jButton1.setBounds(120, 300, 100, 50);
        jButton2.setBounds(240, 300, 100, 50);
        canvas.add(jButton);
        canvas.add(jButton1);
        canvas.add(jButton2);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        jButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        jButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        camerThread camerThread = new camerThread(canvas, grabber);
        camerThread.start();
    }
}

class camerThread extends Thread {
    private static final Logger       log   = LoggerFactory.getLogger(GetBaiduKey.class);

    static int                        x     = 0, y = 0, width = 0, height = 0;
    private volatile static boolean   stop  = true;
    private volatile static boolean   stop3 = true;
    private static OpenCVFrameGrabber grabber;
    int                               i     = 0;
    private CanvasFrame               canvas;

    public camerThread(CanvasFrame canvas, OpenCVFrameGrabber grabber) {
        // TODO Auto-generated constructor stub
        camerThread.grabber = grabber;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stu
        try {
            grabber.start(); // 开始获取摄像头数据
            while (stop) {
                if (stop3) {
                    canvas.setSize(500, 500);
                    Frame f = detectFace1(grabber.grab());
                    canvas.showImage(f);
                } else {
                    // Frame hh= detectFace1(grabber.grab());
                    canvas.showImage(grabber.grab());
                    stop3 = true;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Frame detectFace1(Frame frame) throws InterruptedException, Exception {
        String path = "C:\\Users\\improve\\Pictures\\javaCv\\luna1.jpg";
        Mat imread = opencv_imgcodecs.imread(path);
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        Mat mat = converter.convertToMat(frame);
        Mat videoMatGray = new Mat();
        cvtColor(mat, videoMatGray, COLOR_BGRA2GRAY);
        RectVector faces = new RectVector();
        // 创建用来装检测出来的人脸的容器
        JavaCvUtils.getFaceCascade().detectMultiScale(videoMatGray, faces);
        // 检测人脸，grayscr为要检测的图片，faces用来存放检测结果
        for (int i = 0; i < faces.size(); i++) {
            // 遍历检测出来的人脸
            if (0 == i) {
                Rect face_i = faces.get(i);
                rectangle(mat, face_i, new Scalar(0, 255, 0, 1));
                x = face_i.tl().x();
                y = face_i.tl().y();
                width = face_i.width();
                height = face_i.height();
                log.info("脸长：" + face_i.width() + ",脸宽:" + face_i.height());
                if (0 != x && 0 != width && 0 != height && 0 != y) {
                    Rect rect = new Rect(x, y, width, height);
                    Mat sub = new Mat(mat, rect);
                    // 截取的人脸
                    Size size = new Size(200, 200);
                    Mat resize = new Mat();
                    resize(sub, resize, size);
                    if (CheckFace.cmpPic(resize, imread)) {
                        stop3 = false;
                        log.info("成功");
                        Thread.sleep(50);
                    } else {
                        log.info("失败");
                    }
                }
            }
        }
        Frame frame1 = converter.convert(mat);
        System.gc();
        return frame1;
    }

}