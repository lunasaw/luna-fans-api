package com.luna.baidu.dto;

/**
 * @Package: com.luna.baidu.dto
 * @ClassName: VeinDTO
 * @Author: luna
 * @CreateTime: 2020/8/10 16:32
 * @Description:
 */
public class VeinDTO {

    private String      name;

    private EventKeyDTO key_doc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventKeyDTO getKey_doc() {
        return key_doc;
    }

    public void setKey_doc(EventKeyDTO key_doc) {
        this.key_doc = key_doc;
    }
}
