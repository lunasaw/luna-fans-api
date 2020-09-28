package com.luna.api.xiaoma.dto;

/**
 * @Package: com.luna.api.xiaoma.dto
 * @ClassName: Group
 * @Author: luna
 * @CreateTime: 2020/9/28 10:40
 * @Description:
 */
public class GroupDTO {

    /** sid */
    private String  sid;
    /** 分组名称 */
    private String  name;
    /** 该分组下的链接数量 */
    private Integer nLinks;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getnLinks() {
        return nLinks;
    }

    public void setnLinks(Integer nLinks) {
        this.nLinks = nLinks;
    }
}
