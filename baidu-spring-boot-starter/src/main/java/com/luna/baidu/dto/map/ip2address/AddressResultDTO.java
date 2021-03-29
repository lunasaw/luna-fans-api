package com.luna.baidu.dto.map.ip2address;

/**
 * @author luna@mac
 * @className AddressResultDTO.java
 * @description TODO
 * @createTime 2021年03月29日 20:17:00
 */
public class AddressResultDTO {

    private String            address;

    private AddressContentDTO content;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AddressContentDTO getContent() {
        return content;
    }

    public void setContent(AddressContentDTO content) {
        this.content = content;
    }
}
