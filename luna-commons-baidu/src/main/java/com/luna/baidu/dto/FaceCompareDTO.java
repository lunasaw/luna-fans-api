package com.luna.baidu.dto;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: FaceCompareDTO
 * @Author: luna
 * @CreateTime: 2020/8/2 18:05
 * @Description:
 */
public class FaceCompareDTO {

    private String image;

    private String image_type;

    private String face_type;

    public FaceCompareDTO() {}

    public FaceCompareDTO(String image, String image_type, String face_type) {
        this.image = image;
        this.image_type = image_type;
        this.face_type = face_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getFace_type() {
        return face_type;
    }

    public void setFace_type(String face_type) {
        this.face_type = face_type;
    }
}
