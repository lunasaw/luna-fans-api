package com.luna.baidu.api;

import cn.hutool.core.io.FileUtil;
import com.google.common.collect.Lists;
import com.luna.baidu.entity.Body;
import com.luna.baidu.entity.Face;
import com.luna.baidu.entity.Word;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.entity.Location;
import com.luna.common.exception.base.BaseException;

import javax.imageio.ImageIO;
import java.awt.*;
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
public class PaintImage {

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
            Location location = new Location();
            location.setWidth(face.getWidth());
            location.setTop(face.getTop());
            location.setLeft(face.getLeft());
            location.setHeight(face.getHeight());
            list.add(location);
        }

        BufferedImage location = location(list, image);

        /** 保存图片 */
        File file = new File(savePath);
        try {
            return ImageIO.write(location, FileUtil.getType(file), file);
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
            Location location = new Location();
            location.setWidth(word.getWidth());
            location.setTop(word.getTop());
            location.setLeft(word.getLeft());
            location.setHeight(word.getHeight());
            list.add(location);
        }

        BufferedImage location = location(list, image);
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
        /** 标记出人脸 */
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, e.getMessage());
        }
        ArrayList<Location> list = Lists.newArrayList();
        for (Body body : bodies) {
            Location location = new Location();
            location.setHeight(body.getHeight());
            location.setLeft(body.getLeft());
            location.setTop(body.getTop());
            location.setWidth(body.getWidth());
        }
        BufferedImage location = location(list, image);

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
    public static BufferedImage location(List<Location> lists, BufferedImage image) {
        Graphics2D g = (Graphics2D)image.getGraphics();
        BasicStroke thinStroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
        g.setStroke(thinStroke);
        g.setColor(Color.RED);
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
}
