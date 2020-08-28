package com.luna.api.jd.dto;

/**
 * @Package: com.luna.api.xuexiaoyi.dto
 * @ClassName: SearchJDDTO
 * @Author: luna
 * @CreateTime: 2020/8/26 20:03
 * @Description:
 */
public class SearchJDDTO {

    /** 图片 */
    private String img;
    /** 加个 */
    private String price;
    /** 标题 */
    private String title;

    public SearchJDDTO() {}

    public SearchJDDTO(String img, String price, String title) {
        this.img = img;
        this.price = price;
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
