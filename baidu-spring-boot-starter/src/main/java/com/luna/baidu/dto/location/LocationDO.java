package com.luna.baidu.dto.location;

/**
 * @author Luna@win10
 * @date 2020/4/29 14:37
 */
public class LocationDO {

    private double top;

    private double left;

    private double width;

    private double height;

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
        return "Body{" +
            "top=" + top +
            ", left=" + left +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
