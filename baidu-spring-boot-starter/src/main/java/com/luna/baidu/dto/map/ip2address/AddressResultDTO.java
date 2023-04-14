package com.luna.baidu.dto.map.ip2address;

import lombok.Data;

/**
 * @author luna@mac
 * @className AddressResultDTO.java
 * @description TODO
 * @createTime 2021年03月29日 20:17:00
 */
@Data
public class AddressResultDTO {

    private String            address;

    private AddressContentDTO content;

    @Data
    public static class AddressContentDTO {

        private AddressDetailDTO addressDetail;

        private LocationDTO      point;

        private String           address;
    }

    @Data
    public static class AddressDetailDTO {

        /**
         * 城市
         */
        private String city;

        /**
         * 百度城市代码
         */
        private String cityCode;

        /**
         * 省份
         */
        private String province;
    }

    @Data
    public static class LocationDTO {

        /** 当前城市中心点经度 */
        private String x;

        /** 当前城市中心点纬度 */
        private String y;
    }

}
