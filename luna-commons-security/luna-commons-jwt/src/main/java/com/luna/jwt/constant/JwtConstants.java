package com.luna.jwt.constant;

/**
 * @Package: com.luna.jwt.constant
 * @ClassName: JwtConstant
 * @Author: luna
 * @CreateTime: 2020/9/28 15:21
 * @Description:
 */
public interface JwtConstants {

    /** Jwt 有效期 60 * 60 *1000 一个小时 */
    Long   JWT_TTL     = 3600000L;

    /** Jwt 密钥 */
    String JWT_KEY     = "luna";

    /** 发送者 */
    String JWT_ADMIN   = "luna";

    /** 主题 */
    String JWT_SUBJECT = "subject";

}
