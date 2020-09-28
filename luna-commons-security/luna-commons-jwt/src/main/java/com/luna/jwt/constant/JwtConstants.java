package com.luna.jwt.constant;

/**
 * @Package: com.luna.jwt.constant
 * @ClassName: JwtConstant
 * @Author: luna
 * @CreateTime: 2020/9/28 15:21
 * @Description:
 */
public interface JwtConstants {

    // 有效期为
    Long JWT_TTL = 3600000L;
    // 60 * 60 *1000 一个小时

    // Jwt令牌信息
    String JWT_KEY = "luna";

}
