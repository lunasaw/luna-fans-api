package com.luna.common.entity;

/**
 * @author Luna@win10
 * @date 2020/4/29 14:37
 */
public class Face {

    private String faceToken;

    /** 人脸区域离上边界的距离 */
    private double top;

    /** 人脸区域离左边界的距离 */
    private double left;

    /** 矩形框的宽度 */
    private double width;

    /** 矩形框的高度 */
    private double height;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
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
        return "Face{" +
            "faceToken='" + faceToken + '\'' +
            ", top=" + top +
            ", left=" + left +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
