package com.luna.tencent.dto.face;

/**
 * @author luna
 * 2021/6/14
 */
public class FaceInfosDTO {

    private String                x;

    private String                y;

    private String                width;

    private String                height;

    private FaceAttributesInfoDTO faceAttributesInfoDTO;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public FaceAttributesInfoDTO getFaceAttributesInfoDTO() {
        return faceAttributesInfoDTO;
    }

    public void setFaceAttributesInfoDTO(FaceAttributesInfoDTO faceAttributesInfoDTO) {
        this.faceAttributesInfoDTO = faceAttributesInfoDTO;
    }
}

class FaceAttributesInfoDTO {

    /**
     * 性别[0~49]为女性，[50，100]为男性，越接近0和100表示置信度越高。NeedFaceAttributes 不为 1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */

    private Long                      Gender;

    /**
     * 年龄 [0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */

    private Long                      Age;

    /**
     * 微笑[0(normal，正常)~50(smile，微笑)~100(laugh，大笑)]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */

    private Long                      Expression;

    /**
     * 是否有眼镜 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */

    private Boolean                   Glass;

    /**
     * 上下偏移[-30,30]，单位角度。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     */

    private Long                      Pitch;

    /**
     * 左右偏移[-30,30]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     */
    private Long                      Yaw;

    /**
     * 平面旋转[-180,180]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-20,20]的图片。
     */

    private Long                      Roll;

    /**
     * 魅力[0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */

    private Long                      Beauty;

    /**
     * 是否有帽子 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */

    private Boolean                   Hat;

    /**
     * 是否有口罩 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */

    private Boolean                   Mask;

    /**
     * 头发信息，包含头发长度（length）、有无刘海（bang）、头发颜色（color）。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */

    private FaceHairAttributesInfoDTO Hair;

    /**
     * 双眼是否睁开 [true,false]。只要有超过一只眼睛闭眼，就返回false。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */

    private Boolean                   EyeOpen;

    /**
     * Get 性别[0~49]为女性，[50，100]为男性，越接近0和100表示置信度越高。NeedFaceAttributes 不为 1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @return Gender 性别[0~49]为女性，[50，100]为男性，越接近0和100表示置信度越高。NeedFaceAttributes 不为 1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public Long getGender() {
        return this.Gender;
    }

    /**
     * Set 性别[0~49]为女性，[50，100]为男性，越接近0和100表示置信度越高。NeedFaceAttributes 不为 1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @param Gender 性别[0~49]为女性，[50，100]为男性，越接近0和100表示置信度越高。NeedFaceAttributes 不为 1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public void setGender(Long Gender) {
        this.Gender = Gender;
    }

    /**
     * Get 年龄 [0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @return Age 年龄 [0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public Long getAge() {
        return this.Age;
    }

    /**
     * Set 年龄 [0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @param Age 年龄 [0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public void setAge(Long Age) {
        this.Age = Age;
    }

    /**
     * Get 微笑[0(normal，正常)~50(smile，微笑)~100(laugh，大笑)]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @return Expression 微笑[0(normal，正常)~50(smile，微笑)~100(laugh，大笑)]。NeedFaceAttributes 不为1 或检测超过 5
     * 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public Long getExpression() {
        return this.Expression;
    }

    /**
     * Set 微笑[0(normal，正常)~50(smile，微笑)~100(laugh，大笑)]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @param Expression 微笑[0(normal，正常)~50(smile，微笑)~100(laugh，大笑)]。NeedFaceAttributes 不为1 或检测超过 5
     * 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public void setExpression(Long Expression) {
        this.Expression = Expression;
    }

    /**
     * Get 是否有眼镜 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @return Glass 是否有眼镜 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public Boolean getGlass() {
        return this.Glass;
    }

    /**
     * Set 是否有眼镜 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @param Glass 是否有眼镜 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public void setGlass(Boolean Glass) {
        this.Glass = Glass;
    }

    /**
     * Get 上下偏移[-30,30]，单位角度。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     *
     * @return Pitch 上下偏移[-30,30]，单位角度。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     */
    public Long getPitch() {
        return this.Pitch;
    }

    /**
     * Set 上下偏移[-30,30]，单位角度。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     *
     * @param Pitch 上下偏移[-30,30]，单位角度。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     */
    public void setPitch(Long Pitch) {
        this.Pitch = Pitch;
    }

    /**
     * Get 左右偏移[-30,30]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     *
     * @return Yaw 左右偏移[-30,30]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     */
    public Long getYaw() {
        return this.Yaw;
    }

    /**
     * Set 左右偏移[-30,30]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     *
     * @param Yaw 左右偏移[-30,30]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-10,10]的图片。
     */
    public void setYaw(Long Yaw) {
        this.Yaw = Yaw;
    }

    /**
     * Get 平面旋转[-180,180]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-20,20]的图片。
     *
     * @return Roll 平面旋转[-180,180]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-20,20]的图片。
     */
    public Long getRoll() {
        return this.Roll;
    }

    /**
     * Set 平面旋转[-180,180]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-20,20]的图片。
     *
     * @param Roll 平面旋转[-180,180]，单位角度。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 建议：人脸入库选择[-20,20]的图片。
     */
    public void setRoll(Long Roll) {
        this.Roll = Roll;
    }

    /**
     * Get 魅力[0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @return Beauty 魅力[0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public Long getBeauty() {
        return this.Beauty;
    }

    /**
     * Set 魅力[0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     *
     * @param Beauty 魅力[0~100]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     */
    public void setBeauty(Long Beauty) {
        this.Beauty = Beauty;
    }

    /**
     * Get 是否有帽子 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return Hat 是否有帽子 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public Boolean getHat() {
        return this.Hat;
    }

    /**
     * Set 是否有帽子 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param Hat 是否有帽子 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setHat(Boolean Hat) {
        this.Hat = Hat;
    }

    /**
     * Get 是否有口罩 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return Mask 是否有口罩 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public Boolean getMask() {
        return this.Mask;
    }

    /**
     * Set 是否有口罩 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param Mask 是否有口罩 [true,false]。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setMask(Boolean Mask) {
        this.Mask = Mask;
    }

    /**
     * Get 头发信息，包含头发长度（length）、有无刘海（bang）、头发颜色（color）。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return Hair 头发信息，包含头发长度（length）、有无刘海（bang）、头发颜色（color）。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public FaceHairAttributesInfoDTO getHair() {
        return this.Hair;
    }

    /**
     * Set 头发信息，包含头发长度（length）、有无刘海（bang）、头发颜色（color）。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param Hair 头发信息，包含头发长度（length）、有无刘海（bang）、头发颜色（color）。NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setHair(FaceHairAttributesInfoDTO Hair) {
        this.Hair = Hair;
    }

    /**
     * Get 双眼是否睁开 [true,false]。只要有超过一只眼睛闭眼，就返回false。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return EyeOpen 双眼是否睁开 [true,false]。只要有超过一只眼睛闭眼，就返回false。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public Boolean getEyeOpen() {
        return this.EyeOpen;
    }

    /**
     * Set 双眼是否睁开 [true,false]。只要有超过一只眼睛闭眼，就返回false。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param EyeOpen 双眼是否睁开 [true,false]。只要有超过一只眼睛闭眼，就返回false。 NeedFaceAttributes 不为1 或检测超过 5 张人脸时，此参数仍返回，但不具备参考意义。
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setEyeOpen(Boolean EyeOpen) {
        this.EyeOpen = EyeOpen;
    }
}

class FaceHairAttributesInfoDTO {

    /**
     * 0：光头，1：短发，2：中发，3：长发，4：绑发
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    private Long Length;

    /**
     * 0：有刘海，1：无刘海
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    private Long Bang;

    /**
     * 0：黑色，1：金色，2：棕色，3：灰白色
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    private Long Color;

    /**
     * Get 0：光头，1：短发，2：中发，3：长发，4：绑发
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return Length 0：光头，1：短发，2：中发，3：长发，4：绑发
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public Long getLength() {
        return this.Length;
    }

    /**
     * Set 0：光头，1：短发，2：中发，3：长发，4：绑发
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param Length 0：光头，1：短发，2：中发，3：长发，4：绑发
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setLength(Long Length) {
        this.Length = Length;
    }

    /**
     * Get 0：有刘海，1：无刘海
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return Bang 0：有刘海，1：无刘海
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public Long getBang() {
        return this.Bang;
    }

    /**
     * Set 0：有刘海，1：无刘海
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param Bang 0：有刘海，1：无刘海
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setBang(Long Bang) {
        this.Bang = Bang;
    }

    /**
     * Get 0：黑色，1：金色，2：棕色，3：灰白色
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @return Color 0：黑色，1：金色，2：棕色，3：灰白色
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public Long getColor() {
        return this.Color;
    }

    /**
     * Set 0：黑色，1：金色，2：棕色，3：灰白色
     * 注意：此字段可能返回 null，表示取不到有效值。
     *
     * @param Color 0：黑色，1：金色，2：棕色，3：灰白色
     * 注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setColor(Long Color) {
        this.Color = Color;
    }
}
