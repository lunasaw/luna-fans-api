package com.luna.ali.dto;

/**
 * @Package: com.luna.ali.entity
 * @ClassName: AliPayGoodsDetailDTO
 * @Author: luna
 * @CreateTime: 2020/8/18 15:33
 * @Description:
 */
public class AliPayGoodsDetailDTO {
    /** 商品的编号 必填 */
    private String goods_id;
    /** 商品名称 必填 */
    private String goods_name;
    /** 商品数量 必填 */
    private String quantity;
    /** 商品单价，单位为元 必填 */
    private String price;
    /** 商品类目 可选 */
    private String goods_category;
    /** 商品类目树，从商品类目根节点到叶子节点的类目id组成，类目id值使用|分割 可选 */
    private String categories_tree;
    /** 商品描述信息 可选 */
    private String body;
    /** 商品的展示地址 可选 */
    private String show_url;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoods_category() {
        return goods_category;
    }

    public void setGoods_category(String goods_category) {
        this.goods_category = goods_category;
    }

    public String getCategories_tree() {
        return categories_tree;
    }

    public void setCategories_tree(String categories_tree) {
        this.categories_tree = categories_tree;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShow_url() {
        return show_url;
    }

    public void setShow_url(String show_url) {
        this.show_url = show_url;
    }
}
