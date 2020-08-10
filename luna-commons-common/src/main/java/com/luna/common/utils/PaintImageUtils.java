package com.luna.common.utils;

import cn.hutool.core.io.FileUtil;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.entity.Location;
import com.luna.common.exception.base.BaseException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/5/3 10:15
 */
public class PaintImageUtils {

    /**
     *
     *
     * @param filePath 图片路径
     * @throws Exception
     */

    /**
     * 框出图片中的人脸
     * 
     * @param filePath 图片路径
     * @param locations 位置信息
     * @param savePath 勾画类别
     * 1 画方框
     * 2 画圆圈
     * 3 方框.内切圆
     * @param type
     * @return
     */
    public static boolean paintImage(String filePath, List<Location> locations, String savePath, Integer type) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }

        if (type == 0) {
            image = angle(locations, image, Color.RED, 2.0f);
        }

        if (type == 1) {
            image = location(locations, image, Color.RED, 2.0f);
        }

        if (type == 2) {
            image = circle(locations, image, Color.RED, 2.0f);
        }

        if (type == 3) {
            image = location(locations, image, Color.RED, 2.0f);
            image = circle(locations, image, Color.RED, 2.0f);
        }

        /** 保存图片 */
        File file = new File(savePath);
        try {
            return ImageIO.write(image, FileUtil.getType(file), file);
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
