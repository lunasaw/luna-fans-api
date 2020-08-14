package com.luna.baidu.dto.goods;

/**
 * @Package: com.luna.baidu.dto.goods
 * @ClassName: GoodsInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/14 16:39
 * @Description:
 */
public class GoodsInfoDTO {

    /**
     * 相似分数
     */
    private String       score;

    /**
     * 类别
     */
    private String       root;

    /**
     * 关键字
     */
    private String       keyword;

    /**
     * 百度百科联系
     */
    private BaiKeInfoDTO baikeInfo;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BaiKeInfoDTO getBaikeInfo() {
        return baikeInfo;
    }

    public void setBaikeInfo(BaiKeInfoDTO baikeInfo) {
        this.baikeInfo = baikeInfo;
    }
}
