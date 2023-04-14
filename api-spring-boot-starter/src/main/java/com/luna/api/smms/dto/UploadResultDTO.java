package com.luna.api.smms.dto;

/**
 * @author luna@mac
 * @className UploadResultDTO.java
 * @description TODO
 * @createTime 2021年03月28日 15:10:00
 */
public class UploadResultDTO {

    /**
     * Image URL.
     */
    private Integer width;

    /**
     * Height.
     */
    private String  height;

    /**
     * Filename.
     */
    private String  filename;

    /**
     * Store name.
     */
    private String  storename;

    /**
     * Image Size.
     */
    private Integer size;
    /**
     * Image Path.
     */
    private String  path;

    /**
     * Image Deletion HASH.
     */
    private String  hash;

    /**
     * Image URL.
     */
    private String  url;

    /**
     * Image Deletion Link.
     */
    private String  delete;

    /**
     * Image Page Link.
     */
    private String  upagerl;


    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getUpagerl() {
        return upagerl;
    }

    public void setUpagerl(String upagerl) {
        this.upagerl = upagerl;
    }
}
