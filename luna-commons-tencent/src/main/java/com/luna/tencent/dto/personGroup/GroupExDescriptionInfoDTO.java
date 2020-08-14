package com.luna.tencent.dto.personGroup;

/**
 * @Package: com.luna.dto.dto
 * @ClassName: GroupExDescriptionInfoDTO
 * @Author: luna
 * @CreateTime: 2020/8/6 15:06
 * @Description:
 */
public class GroupExDescriptionInfoDTO {

    private Integer groupExDescriptionIndex;

    private String  groupExDescription;

    public Integer getGroupExDescriptionIndex() {
        return groupExDescriptionIndex;
    }

    public void setGroupExDescriptionIndex(Integer groupExDescriptionIndex) {
        this.groupExDescriptionIndex = groupExDescriptionIndex;
    }

    public String getGroupExDescription() {
        return groupExDescription;
    }

    public void setGroupExDescription(String groupExDescription) {
        this.groupExDescription = groupExDescription;
    }
}
