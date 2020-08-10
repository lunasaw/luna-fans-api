package com.luna.baidu.dto;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: BodyAttributesDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 19:44
 * @Description:
 */
public class BodyAttributesDTO {

    /** 是否是正常人体 */
    private BodyScoreNameDTO orientation;

    /** 是否是正常人体 */
    private BodyScoreNameDTO isHuman;

    /** 有无带帽子 */
    private BodyScoreNameDTO headwear;

    /** 上身服饰纹理 纯色、图案、碎花、条纹或格子 */
    private BodyScoreNameDTO upperWearTexture;

    /** 无手提物、有手提物、不确定 */
    private BodyScoreNameDTO carryingTtem;

    /** 是否戴口罩 无口罩、戴口罩、不确定（背面或者头部被截断的人体，一般为不确定） */
    private BodyScoreNameDTO faceMask;

    /** 下半身服饰 长裤、短裤、长裙、短裙、不确定 */
    private BodyScoreNameDTO lowerWear;

    /** 是否有交通工具 无交通工具、骑摩托车、骑自行车、骑三轮车 */
    private BodyScoreNameDTO vehicle;

    /** 上身服饰细分类 T恤、无袖、衬衫、西装、毛衣、夹克、羽绒服、风衣、外套 */
    private BodyScoreNameDTO upperWearFg;

    /** 下半身衣着颜色 红、橙、黄、绿、蓝、紫、粉、黑、白、灰、棕、不确定 */
    private BodyScoreNameDTO lowerColor;

    /** 是否打伞 未打伞、打伞 */
    private BodyScoreNameDTO umbrella;

    /** 上方截断 无上方截断、有上方截断 */
    private BodyScoreNameDTO upperCut;

    /** 下方截断 无下方截断、有下方截断 */
    private BodyScoreNameDTO lowerCut;

    /** 是否是正常人体 */
    private BodyScoreNameDTO upperWear;

    /** 是否是正常人体 */
    private BodyScoreNameDTO cellphone;

    /** 性别 男性、女性 */
    private BodyScoreNameDTO gender;

    /** 年龄阶段 幼儿、青少年、青年、中年、老年 */
    private BodyScoreNameDTO age;

    /** 是否背包 */
    private BodyScoreNameDTO bag;

    /** 是否吸烟 */
    private BodyScoreNameDTO smoke;

    /** 上半身衣着颜色 红、橙、黄、绿、蓝、紫、粉、黑、白、灰、棕 */
    private BodyScoreNameDTO upperColor;

    /** 是否遮挡 无遮挡、轻度遮挡、重度遮挡 */
    private BodyScoreNameDTO occlusion;

    /** 是否带眼镜 */
    private BodyScoreNameDTO glasses;

    public BodyScoreNameDTO getOrientation() {
        return orientation;
    }

    public void setOrientation(BodyScoreNameDTO orientation) {
        this.orientation = orientation;
    }

    public BodyScoreNameDTO getIsHuman() {
        return isHuman;
    }

    public void setIsHuman(BodyScoreNameDTO isHuman) {
        this.isHuman = isHuman;
    }

    public BodyScoreNameDTO getHeadwear() {
        return headwear;
    }

    public void setHeadwear(BodyScoreNameDTO headwear) {
        this.headwear = headwear;
    }

    public BodyScoreNameDTO getUpperWearTexture() {
        return upperWearTexture;
    }

    public void setUpperWearTexture(BodyScoreNameDTO upperWearTexture) {
        this.upperWearTexture = upperWearTexture;
    }

    public BodyScoreNameDTO getCarryingTtem() {
        return carryingTtem;
    }

    public void setCarryingTtem(BodyScoreNameDTO carryingTtem) {
        this.carryingTtem = carryingTtem;
    }

    public BodyScoreNameDTO getFaceMask() {
        return faceMask;
    }

    public void setFaceMask(BodyScoreNameDTO faceMask) {
        this.faceMask = faceMask;
    }

    public BodyScoreNameDTO getLowerWear() {
        return lowerWear;
    }

    public void setLowerWear(BodyScoreNameDTO lowerWear) {
        this.lowerWear = lowerWear;
    }

    public BodyScoreNameDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(BodyScoreNameDTO vehicle) {
        this.vehicle = vehicle;
    }

    public BodyScoreNameDTO getUpperWearFg() {
        return upperWearFg;
    }

    public void setUpperWearFg(BodyScoreNameDTO upperWearFg) {
        this.upperWearFg = upperWearFg;
    }

    public BodyScoreNameDTO getLowerColor() {
        return lowerColor;
    }

    public void setLowerColor(BodyScoreNameDTO lowerColor) {
        this.lowerColor = lowerColor;
    }

    public BodyScoreNameDTO getUmbrella() {
        return umbrella;
    }

    public void setUmbrella(BodyScoreNameDTO umbrella) {
        this.umbrella = umbrella;
    }

    public BodyScoreNameDTO getUpperCut() {
        return upperCut;
    }

    public void setUpperCut(BodyScoreNameDTO upperCut) {
        this.upperCut = upperCut;
    }

    public BodyScoreNameDTO getLowerCut() {
        return lowerCut;
    }

    public void setLowerCut(BodyScoreNameDTO lowerCut) {
        this.lowerCut = lowerCut;
    }

    public BodyScoreNameDTO getUpperWear() {
        return upperWear;
    }

    public void setUpperWear(BodyScoreNameDTO upperWear) {
        this.upperWear = upperWear;
    }

    public BodyScoreNameDTO getCellphone() {
        return cellphone;
    }

    public void setCellphone(BodyScoreNameDTO cellphone) {
        this.cellphone = cellphone;
    }

    public BodyScoreNameDTO getGender() {
        return gender;
    }

    public void setGender(BodyScoreNameDTO gender) {
        this.gender = gender;
    }

    public BodyScoreNameDTO getAge() {
        return age;
    }

    public void setAge(BodyScoreNameDTO age) {
        this.age = age;
    }

    public BodyScoreNameDTO getBag() {
        return bag;
    }

    public void setBag(BodyScoreNameDTO bag) {
        this.bag = bag;
    }

    public BodyScoreNameDTO getSmoke() {
        return smoke;
    }

    public void setSmoke(BodyScoreNameDTO smoke) {
        this.smoke = smoke;
    }

    public BodyScoreNameDTO getUpperColor() {
        return upperColor;
    }

    public void setUpperColor(BodyScoreNameDTO upperColor) {
        this.upperColor = upperColor;
    }

    public BodyScoreNameDTO getOcclusion() {
        return occlusion;
    }

    public void setOcclusion(BodyScoreNameDTO occlusion) {
        this.occlusion = occlusion;
    }

    public BodyScoreNameDTO getGlasses() {
        return glasses;
    }

    public void setGlasses(BodyScoreNameDTO glasses) {
        this.glasses = glasses;
    }
}
