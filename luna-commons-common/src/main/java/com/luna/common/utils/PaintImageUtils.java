package com.luna.common.utils;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.entity.Body;
import com.luna.common.entity.Face;
import com.luna.common.entity.Location;
import com.luna.common.entity.Word;
import com.luna.common.exception.base.BaseException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/5/3 10:15
 */
public class PaintImageUtils {

    /**
     * 框出图片中的人脸
     *
     * @param filePath 图片路径
     * @throws Exception
     */
    public static boolean paintFace(String filePath, List<Face> faces, String savePath) {
        /** 标记出人脸 */
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
        ArrayList<Location> list = Lists.newArrayList();
        for (Face face : faces) {
            Location location = face;
            list.add(location);
        }

        BufferedImage location = location(list, image, Color.RED, 2.0f);
        BufferedImage circle = circle(list, location, Color.RED, 2.0f);
        /** 保存图片 */
        File file = new File(savePath);
        try {
            return ImageIO.write(circle, FileUtil.getType(file), file);
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
    }

    /**
     * 框出图片中的文字
     *
     * @param filePath 图片路径
     * @throws Exception
     */
    public static boolean paintWords(String filePath, List<Word> words, String savePath) {
        /** 标记出人脸 */
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }

        ArrayList<Location> list = Lists.newArrayList();
        for (Word word : words) {
            Location location = word;
            list.add(location);
        }

        BufferedImage location = location(list, image, Color.RED, 2.0f);
        /** 保存图片 */
        File file = new File(savePath);
        try {
            return ImageIO.write(location, FileUtil.getType(file), file);
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
    }

    /**
     * 框出图片中的人体
     *
     * @param filePath 图片路径
     * @throws Exception
     */
    public static boolean paintBody(String filePath, List<Body> bodies, String savePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
        ArrayList<Location> list = Lists.newArrayList();
        for (Body body : bodies) {
            Location location = body;
            list.add(location);
        }
        BufferedImage location = location(list, image, Color.RED, 2.0f);

        /** 保存图片 */
        File file = new File(savePath);
        try {
            return ImageIO.write(location, FileUtil.getType(file), file);
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
    }

    /**
     * 画图
     *
     * @param lists
     * @param image
     * @return
     */
    public static BufferedImage location(List<Location> lists, BufferedImage image, Color color, Float wide) {
        Graphics2D g = (Graphics2D)image.getGraphics();
        BasicStroke thinStroke = new BasicStroke(wide, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
        g.setStroke(thinStroke);
        g.setColor(color);
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;

        for (Location location : lists) {

            bgWidth = (int)location.getWidth();
            bgTop = (int)location.getTop();
            bgLeft = (int)location.getLeft();
            bgHeight = (int)location.getHeight();

            /** 美化：调整真实坐标 */
            bgLeft -= bgWidth / 6;
            bgTop -= bgHeight / 6;
            bgWidth += bgWidth / 3;
            bgHeight += bgHeight / 3;

            /** 画方框 */
            Rectangle2D rect = new Rectangle2D.Double(bgLeft, bgTop, bgWidth, bgHeight);
            g.draw(rect);
        }
        g.dispose();
        return image;
    }

    /**
     * 画圆
     *
     * @param lists
     * @param image
     * @param color
     * @param wide
     * @return
     */
    public static BufferedImage circle(List<Location> lists, BufferedImage image, Color color, Float wide) {
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(color);
        g.setStroke(new BasicStroke(wide));
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;

        for (Location list : lists) {
            bgWidth = (int)list.getWidth();
            bgTop = (int)list.getTop();
            bgLeft = (int)list.getLeft();
            bgHeight = (int)list.getHeight();

            /** 美化：调整真实坐标 */
            bgLeft -= bgWidth / 6;
            bgTop -= bgHeight / 6;
            bgWidth += bgWidth / 3;
            bgHeight += bgHeight / 3;
            /** 画方框的内切椭圆 */
            Rectangle2D rect = new Rectangle2D.Double(bgLeft + bgWidth * 0.1, bgTop + bgHeight * 0.1, bgWidth * 0.8,
                bgHeight * 0.8);
            Ellipse2D ellipse = new Ellipse2D.Double();
            ellipse.setFrame(rect);
            g.draw(ellipse);

            /** 画四个角 */
            g.drawLine(bgLeft, bgTop, bgLeft, bgTop + bgWidth / 3);
            g.drawLine(bgLeft, bgTop, bgLeft + bgHeight / 3, bgTop);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft + bgWidth / 3, bgTop + bgHeight);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth, bgTop + bgWidth / 3);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth * 2 / 3, bgTop);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth * 2 / 3, bgTop + bgHeight);
        }
        g.dispose();
        return image;
    }

    public static BufferedImage angle(List<Location> lists, BufferedImage image, Color color, Float wide) {
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(color);
        g.setStroke(new BasicStroke(wide));
        int bgWidth = 0, bgTop = 0, bgLeft = 0, bgHeight = 0;
        for (Location list : lists) {
            bgWidth = (int)list.getWidth();
            bgTop = (int)list.getTop();
            bgLeft = (int)list.getLeft();
            bgHeight = (int)list.getHeight();

            /** 美化：调整真实坐标 */
            bgLeft -= bgWidth / 6;
            bgTop -= bgHeight / 6;
            bgWidth += bgWidth / 3;
            bgHeight += bgHeight / 3;

            /** 画四个角 */
            g.drawLine(bgLeft, bgTop, bgLeft, bgTop + bgWidth / 3);
            g.drawLine(bgLeft, bgTop, bgLeft + bgHeight / 3, bgTop);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft, bgTop + bgHeight, bgLeft + bgWidth / 3, bgTop + bgHeight);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth, bgTop + bgWidth / 3);
            g.drawLine(bgLeft + bgWidth, bgTop, bgLeft + bgWidth * 2 / 3, bgTop);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth, bgTop + bgHeight * 2 / 3);
            g.drawLine(bgLeft + bgWidth, bgTop + bgHeight, bgLeft + bgWidth * 2 / 3, bgTop + bgHeight);
        }
        g.dispose();
        return image;
    }
}
