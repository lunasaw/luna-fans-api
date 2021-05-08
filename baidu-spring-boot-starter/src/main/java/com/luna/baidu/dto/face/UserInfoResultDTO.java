package com.luna.baidu.dto.face;

/**
 * @author luna@mac
 * 2021年05月07日 20:47
 */
public class UserInfoResultDTO {

    private String userInfo;

    private String groupId;

    private String userId;

    private Double score;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
