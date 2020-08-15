package com.luna.tencent.dto.card;

/**
 * @Package: com.luna.dto.dto
 * @ClassName: IdCardPictureCheckInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/6 14:18
 * @Description:
 */
public class IdCardPictureCheckInfoDTO {

    private String Result;

    private String Description;

    private Float  Sim;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Float getSim() {
        return Sim;
    }

    public void setSim(Float sim) {
        Sim = sim;
    }
}
