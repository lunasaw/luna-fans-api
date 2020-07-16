package com.luna.baidu.entity;

/**
 * @author Luna@win10
 * @date 2020/5/3 9:59
 */
public class Word {

    private String wold;

    /** 表示定位位置的长方形左上顶点的水平坐标 */
    private double top;

    /** 表示定位位置的长方形左上顶点的垂直坐标 */
    private double left;

    /** 表示定位位置的长方形的宽度 */
    private double width;

    /** 表示定位位置的长方形的高度 */
    private double height;

    public String getWold() {
        return wold;
    }

    public void setWold(String wold) {
        this.wold = wold;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Word{" +
            "wold='" + wold + '\'' +
            ", top=" + top +
            ", left=" + left +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
