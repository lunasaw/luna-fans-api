package io.github.lunasaw.pay.constant;

/**
 * @Package: io.github.lunasaw.pay.constant
 * @ClassName: TencentPayConstant
 * @Author: luna
 * @CreateTime: 2020/8/16 14:17
 * @Description:
 */
public class TencentPayConstant {

    public static final String HOST         = "https://api.mch.weixin.qq.com";

    /** 创建订单 */
    public static final String CREATE_ORDER = "/pay/unifiedorder";

    /** 查询订单 */
    public static final String QUERY_ORDER  = "/pay/orderquery";

    /** 订单关闭 */
    public static final String CLOSE_ORDER  = "/pay/closeorder";

}
