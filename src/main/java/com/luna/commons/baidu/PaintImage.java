package com.luna.commons.baidu;

import com.luna.commons.baidu.entity.Body;
import com.luna.commons.baidu.entity.Face;
import com.luna.commons.baidu.entity.Word;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/5/3 10:15
 */
public class PaintImage {

    /**
     * 框出图片中的人脸
     *
     * @param filePath 图片路径
     * @throws Exception
     */
    public static void paint(String filePath, List<Face> faces, String savePath) throws Exception {
        /** 标记出人脸 */
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        Graphics2D g = (Graphics2D)image.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke thinStroke = new BasicStroke(1.0f);
        BasicStroke fatStroke = new BasicStroke(6.0f);
        g2.setStroke(thinStroke);
        Color c = g.getColor();
        g.setColor(Color.RED);
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;

        for (Face faceTemp : faces) {

            bgWidth = (int)faceTemp.getWidth();
            bgTop = (int)faceTemp.getTop();
            bgLeft = (int)faceTemp.getLeft();
            bgHeight = (int)faceTemp.getHeight();

            /** 美化：调整真实坐标 */
            bgLeft -= bgWidth / 6;
            bgTop -= bgHeight / 6;
            bgWidth += bgWidth / 3;
            bgHeight += bgHeight / 3;

            /** 画方框 */
            Rectangle2D rect = new Rectangle2D.Double(bgLeft, bgTop, bgWidth, bgHeight);
            g.draw(rect);
            /** 画方框的内切椭圆 */
            rect = new Rectangle2D.Double(bgLeft + bgWidth * 0.1, bgTop + bgHeight * 0.1, bgWidth * 0.8,
                bgHeight * 0.8);
            Ellipse2D ellipse = new Ellipse2D.Double();
            ellipse.setFrame(rect);
            g.draw(ellipse);

            /** 画四个角 */
            g2.setStroke(fatStroke);
            g.drawLine(bgLeft, bgTop, bgLeft, bgTop + bgWidth / 3);
            g.drawLine(bgLeft, bgTop, bgLeft + bgHeight / 3, bgTop);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft + bgWidth / 3, bgTop + bgHeight);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth, bgTop + bgWidth / 3);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth * 2 / 3, bgTop);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth * 2 / 3, bgTop + bgHeight);
            g2.setStroke(thinStroke);
        }

        g.setColor(c);

        /** 保存图片 */
        File file = new File(savePath);
        ImageIO.write(image, "jpg", file);
    }

    /**
     * 框出图片中的文字
     *
     * @param filePath 图片路径
     * @throws Exception
     */
    public static void paintWords(String filePath, List<Word> words, String savePath) throws Exception {
        /** 标记出人脸 */
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        Graphics2D g = (Graphics2D)image.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke thinStroke = new BasicStroke(1.0f);
        BasicStroke fatStroke = new BasicStroke(6.0f);
        g2.setStroke(thinStroke);
        Color c = g.getColor();
        g.setColor(Color.RED);
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;

        for (Word word : words) {

            bgWidth = (int)word.getWidth();
            bgTop = (int)word.getTop();
            bgLeft = (int)word.getLeft();
            bgHeight = (int)word.getHeight();

            /** 美化：调整真实坐标 */
            bgLeft -= bgWidth / 6;
            bgTop -= bgHeight / 6;
            bgWidth += bgWidth / 3;
            bgHeight += bgHeight / 3;

            /** 画方框 */
            Rectangle2D rect = new Rectangle2D.Double(bgLeft, bgTop, bgWidth, bgHeight);
            g.draw(rect);
        }
        g.setColor(c);

        /** 保存图片 */
        File file = new File(savePath);
        ImageIO.write(image, "jpg", file);
    }

    /**
     * 框出图片中的人体
     *
     * @param filePath 图片路径
     * @throws Exception
     */
    public static void paintBody(String filePath, List<Body> bodies, String savePath) throws Exception {
        /** 标记出人脸 */
        BufferedImage image = ImageIO.read(new FileInputStream(filePath));
        Graphics2D g = (Graphics2D)image.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        BasicStroke thinStroke = new BasicStroke(1.0f);
        BasicStroke fatStroke = new BasicStroke(6.0f);
        g2.setStroke(thinStroke);
        Color c = g.getColor();
        g.setColor(Color.RED);
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;

        for (Body body : bodies) {

            bgWidth = (int)body.getWidth();
            bgTop = (int)body.getTop();
            bgLeft = (int)body.getLeft();
            bgHeight = (int)body.getHeight();

            // /** 美化：调整真实坐标 */
            // bgLeft -= bgWidth / 6;
            // bgTop -= bgHeight / 6;
            // bgWidth += bgWidth / 3;
            // bgHeight += bgHeight / 3;

            /** 画方框 */
            Rectangle2D rect = new Rectangle2D.Double(bgLeft, bgTop, bgWidth, bgHeight);
            g.draw(rect);
        }
        g.setColor(c);

        /** 保存图片 */
        File file = new File(savePath);
        ImageIO.write(image, "jpg", file);
    }

}
