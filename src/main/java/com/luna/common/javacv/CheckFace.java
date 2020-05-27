package com.luna.common.javacv;

import static org.bytedeco.flandmark.global.flandmark.flandmark_detect;
import static org.bytedeco.flandmark.global.flandmark.flandmark_init;
import static org.bytedeco.opencv.global.opencv_core.NORM_MINMAX;
import static org.bytedeco.opencv.global.opencv_core.normalize;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bytedeco.flandmark.FLANDMARK_Model;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.luna.common.baidu.Config.GetBaiduKey;
import com.luna.common.utils.StringUtils;

/**
 * @author Luna@win10
 * @date 2020/5/27 15:37
 * 人脸检测工具
 */
public class CheckFace {
    private static final Logger log = LoggerFactory.getLogger(GetBaiduKey.class);

    private static FLANDMARK_Model loadFLandmarkModel(final File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("FLandmark model file does not exist: " + file.getAbsolutePath());
        }

        final FLANDMARK_Model model = flandmark_init("flandmark_model.dat");
        if (model == null) {
            throw new IOException("Failed to load FLandmark model from file: " + file.getAbsolutePath());
        }

        return model;
    }

    private static void detectFaceInImage(final Mat orig,
        Mat input,
        CascadeClassifier faceCascade,
        FLANDMARK_Model model,
        int[] bbox,
        double[] landmarks) throws Exception {

        RectVector faces = new RectVector();
        faceCascade.detectMultiScale(input, faces);

        long nFaces = faces.size();
        System.out.println("Faces detected: " + nFaces);
        if (nFaces == 0) {
            throw new Exception("No faces detected");
        }

        for (int iface = 0; iface < nFaces; ++iface) {
            Rect rect = faces.get(iface);

            bbox[0] = rect.x();
            bbox[1] = rect.y();
            bbox[2] = rect.x() + rect.width();
            bbox[3] = rect.y() + rect.height();

            flandmark_detect(new IplImage(input), bbox, model, landmarks);

            // display landmarks
            rectangle(orig,
                new Point((int)model.bb().get(0), (int)model.bb().get(1)),
                new Point((int)model.bb().get(2), (int)model.bb().get(3)), new Scalar(0, 0, 255, 128), 3, 4, 0);
            circle(orig,
                new Point((int)landmarks[0], (int)landmarks[1]), 3,
                new Scalar(0, 0, 255, 128), CV_FILLED, 8, 0);
            for (int i = 2; i < 2 * model.data().options().M(); i += 2) {
                circle(orig, new Point((int)(landmarks[i]), (int)(landmarks[i + 1])), 3,
                    new Scalar(255, 0, 0, 128), CV_FILLED, 8, 0);
            }
        }
    }

    /**
     * 人脸检测矩形勾画
     * 
     * @param image
     * @return
     */
    public static Mat detectFace(Mat image) {
        Mat grayscr = new Mat();
        opencv_imgproc.cvtColor(image, grayscr, CV_BGR2GRAY);
        // 摄像头是彩色图像，所以先灰度化下
        equalizeHist(grayscr, grayscr);
        CascadeClassifier faceCascade = JavaCvUtils.getFaceCascade();
        RectVector faces = new RectVector();
        faceCascade.detectMultiScale(image, faces);
        // 均衡化直方图
        for (int i = 0; i < faces.size(); i++) {
            Rect face_i = faces.get(i);
            rectangle(image, face_i, new Scalar(10, 0, 255, 1), 3, 4, 0);
        }
        return image;
    }

    /**
     * 图像人脸检测
     * 
     * @param inPath
     * @param outPah
     */
    public static void chackFace(String inPath, String outPah) {
        try {
            Mat image = imread(new File(inPath).getCanonicalPath());
            Mat mat = detectFace(image);
            if (StringUtils.isEmpty(outPah)) {
                outPah = inPath;
            }
            VideoUtil.doExecuteFrame(JavaCvUtils.converter.convert(mat), outPah);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 人脸检测展示
     * 
     * @param inPath
     * \
     */
    public static void chackFaceAndShow(String inPath) {
        try {
            Mat image = imread(new File(inPath).getCanonicalPath());
            CheckFace.checkFace(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 人脸检测 灰度,矩形 frame显示
     * 
     * @param image
     */
    public static void checkFace(Mat image) {
        try {
            final File flandmarkModelFile = new File("flandmark_model.dat");
            final FLANDMARK_Model model = loadFLandmarkModel(flandmarkModelFile);
            Mat imageBW = new Mat();
            cvtColor(image, imageBW, CV_BGR2GRAY);
            final int[] bbox = new int[4];
            final double[] landmarks = new double[2 * model.data().options().M()];
            detectFaceInImage(image, imageBW, JavaCvUtils.getFaceCascade(), model, bbox, landmarks);
            JavaCvUtils.show(image, "Example 1 - output");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean cmpPic(Mat src1, Mat src2) throws InterruptedException {
        Mat r_hist = new Mat();
        Mat g_hist = new Mat();
        Mat hsrc = new Mat();
        Mat hsrc1 = new Mat();
        System.out.println("\n==========直方图比较==========");
        // 相关性阈值，应大于多少，越接近1表示越像，最大为1
        double HISTCMP_CORREL_THRESHOLD = 0.90;
        // 卡方阈值，应小于多少，越接近0表示越像
        double HISTCMP_CHISQR_THRESHOLD = 0.5;
        // 交叉阈值，应大于多少，数值越大表示越像
        double HISTCMP_INTERSECT_THRESHOLD = 2.5;
        // 巴氏距离阈值，应小于多少，越接近0表示越像
        double HISTCMP_BHATTACHARYYA_THRESHOLD = 0.2;
        // HSV
        int channels[] = {0, 1};
        int h_bins = 50;
        int s_bins = 60;
        int v_bins = 60;
        int histSize[] = {h_bins, s_bins, v_bins};
        float range[] = {0, 255, 0, 180};

        cvtColor(src2, hsrc, CV_BGR2HSV);
        cvtColor(src1, hsrc1, CV_BGR2HSV);
        calcHist(hsrc, 1, channels, new Mat(), r_hist, 1, histSize, range, true, false);
        calcHist(hsrc1, 1, channels, new Mat(), g_hist, 1, histSize, range, true, false);
        normalize(r_hist, r_hist, 0, 1, NORM_MINMAX, -1, new Mat());
        normalize(g_hist, g_hist, 0, 1, NORM_MINMAX, -1, new Mat());
        double result0, result1, result2, result3;
        result0 = compareHist(g_hist, r_hist, HISTCMP_CORREL);
        result1 = compareHist(g_hist, r_hist, HISTCMP_CHISQR);
        result2 = compareHist(g_hist, r_hist, HISTCMP_INTERSECT);
        result3 = compareHist(g_hist, r_hist, HISTCMP_BHATTACHARYYA);
        log.info("相关性（度量越高，匹配越准确 [基准：" + HISTCMP_CORREL_THRESHOLD + "]）,当前值:" + result0);
        log.info("卡方（度量越低，匹配越准确 [基准：" + HISTCMP_CHISQR_THRESHOLD + "]）,当前值:" + result1);
        log.info("交叉核（度量越高，匹配越准确 [基准：" + HISTCMP_INTERSECT_THRESHOLD + "]）,当前值:" + result2);
        log.info("巴氏距离（度量越低，匹配越准确 [基准：" + HISTCMP_BHATTACHARYYA_THRESHOLD + "]）,当前值:" + result3);
        // 一共四种方式，有三个满足阈值就算匹配成功
        int count = 0;
        if (result0 > HISTCMP_CORREL_THRESHOLD) {
            count++;
        }
        if (result1 < HISTCMP_CHISQR_THRESHOLD) {
            count++;
        }
        if (result2 > HISTCMP_INTERSECT_THRESHOLD) {
            count++;
        }
        if (result3 < HISTCMP_BHATTACHARYYA_THRESHOLD) {
            count++;
        }
        if (count >= 1) {
            // 这是相似的图像
            log.info("----------------------------====================-----相似");
            return true;
        }
        return false;
    }
}
