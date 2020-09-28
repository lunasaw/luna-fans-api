package com.luna.api.xiaoma.dto;

import java.util.List;

/**
 * @Package: com.luna.api.xiaoma.dto
 * @ClassName: GroupListDTO
 * @Author: luna
 * @CreateTime: 2020/9/28 10:40
 * @Description:
 */
public class GroupListDTO {

    /** 用户的链接分组 */
    private List<GroupDTO> groups;

    /** 用户创建或加入的协作空间 */
    private List<WorksPlaceDTO> workspaces;

    public List<GroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDTO> groups) {
        this.groups = groups;
    }

    public List<WorksPlaceDTO> getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces(List<WorksPlaceDTO> workspaces) {
        this.workspaces = workspaces;
    }
}
