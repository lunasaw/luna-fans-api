package com.luna.baidu.dto.goods;

/**
 * @Package: com.luna.baidu.dto.goods
 * @ClassName: GoodsInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 16:36
 * @Description:
 */
public class BaiKeInfoDTO {

    /**
     * 描述
     */
    private String description;

    /**
     * 百度百科
     */
    private String baikeUrl;

    /**
     * 图片链接
     */
    private String imageUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaikeUrl() {
        return baikeUrl;
    }

    public void setBaikeUrl(String baikeUrl) {
        this.baikeUrl = baikeUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
