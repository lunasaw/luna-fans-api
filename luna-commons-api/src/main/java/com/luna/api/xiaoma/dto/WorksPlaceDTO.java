package com.luna.api.xiaoma.dto;

import java.util.List;

/**
 * @Package: com.luna.api.xiaoma.dto
 * @ClassName: WorksPlaces
 * @Author: luna
 * @CreateTime: 2020/9/28 11:04
 * @Description:
 */
public class WorksPlaceDTO {

    /** 空间名称 */
    private String      name;
    /** 用户在该空间的角色（创建者为creator，协作者为collaborator） */
    private String      role;
    /** 该空间下的链接分组 */
    private List<GroupDTO> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }
}
