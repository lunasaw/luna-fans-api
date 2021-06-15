package com.luna.tencent.response.card;

/**
 * @author luna
 * 2021/6/14
 */
public class IdCardPictureCheckInfoResponse {

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
