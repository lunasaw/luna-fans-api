package com.luna.baidu.dto.face;

import java.util.List;

/**
 * @author luna@mac
 * 2021年05月07日 20:48
 */
public class UserInfoListDTO {

    private List<UserInfoResultDTO> userList;

    public List<UserInfoResultDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfoResultDTO> userList) {
        this.userList = userList;
    }
}
