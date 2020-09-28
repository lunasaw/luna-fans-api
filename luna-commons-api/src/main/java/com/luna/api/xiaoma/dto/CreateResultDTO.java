package com.luna.api.xiaoma.dto;

/**
 * @Package: com.luna.api.xiaoma.dto
 * @ClassName: CreateResultDTO
 * @Author: luna
 * @CreateTime: 2020/9/28 11:21
 * @Description:
 */
public class CreateResultDTO {

    /** 今天通过接口已创建的短链接数量 */
    private String   nLinksToday;

    /** 短链接 */
    private LinkDTO  link;

    /** 链接分组 */
    private GroupDTO group;

    public String getnLinksToday() {
        return nLinksToday;
    }

    public void setnLinksToday(String nLinksToday) {
        this.nLinksToday = nLinksToday;
    }

    public LinkDTO getLink() {
        return link;
    }

    public void setLink(LinkDTO link) {
        this.link = link;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }
}
