package com.luna.baidu.dto.map;

import java.util.Map;
import java.util.HashMap;

/**
 * @author weidian
 */

public enum ErrorCode {
    SERVER_ERROR(1, "服务器内部错误", "具体失败消息会在message提示"),
    LOCATION_PERMISSION_DENIED(2, "高级权限才允许使用location字段", "用户需开通高级权限功能"),
    MISSING_REQUIRED_PARAM(10, "缺少必要的请求参数", "缺少参数的时候会抛出该异常"),
    INVALID_PARAM_FORMAT(11, "请求参数格式错误", "请求参数类型错误时会抛出该异常， 如字符长度、数据类型、数据格式不规范等"),
    AUTHENTICATION_FAILED(20, "身份验证失败", "ak 无效时会抛出该异常"),
    TIMESTAMP_EXPIRED(21, "timestamp超出有效时间范围", "timestamp 与服务端的当前时间相差超过1分钟"),
    INVALID_LOCATION_CODE(40, "查询的区域编码无效", "查询的区域编码与约定的编码不符"),
    INVALID_COORDINATE_RANGE(41, "查询的经纬度值范围无效", "查询的经纬度超出了正常的经纬度范围"),
    UNSUPPORTED_DATA_TYPE(42, "不支持的数据类型", "查询的数据类型不支持"),
    UNSUPPORTED_LANGUAGE_TYPE(43, "不支持的语言类型", "查询的语言类型不支持"),
    NO_DATA_COVERAGE(44, "经纬度所在地区无数据覆盖", "输入的经纬度区域无数据覆盖"),
    SERVICE_DISABLED(45, "服务被禁用，请确认是否开通高级权限", "查询的数据类型与用户类型不匹配，请确认是否开通高级权限"),
    SERVER_BUSY(50, "服务繁忙", "其他类型错误"),
    INVALID_AK_PARAM(101, "AK参数不存在", "请求消息没有携带AK参数"),
    INVALID_MCODE_PARAM(102, "MCODE参数不存在，mobile类型mcode参数必需", "对于Mobile类型的应用请求需要携带mcode参数，该错误码代表服务器没有解析到mcode"),
    APP_NOT_EXIST(200, "APP不存在，AK有误请检查再重试", "根据请求的AK，找不到对应的APP"),
    APP_DISABLED_BY_USER(201, "APP被用户自己禁用，请在控制台解禁", ""),
    APP_DELETED_BY_ADMIN(202, "APP被管理员删除", "恶意APP被管理员删除"),
    INVALID_APP_TYPE(203, "APP类型错误",
        "当前API控制台支持Server(类型1), Mobile(类型2, 新版控制台区分为Mobile_Android(类型21)及Mobile_IPhone（类型22））及Browser（类型3），除此之外其他类型认为是APP类型错误"),
    APP_IP_CHECK_FAILED(210, "APP IP校验失败", "在申请SERVER类型应用的时候选择IP校验，需要填写IP白名单，如果当前请求的IP地址不在IP白名单或者不是0.0.0.0/0就认为IP校验失败"),
    APP_SN_CHECK_FAILED(211, "APP SN校验失败", "SERVER类型APP有两种校验方式IP校验和SN校验，当用户请求的SN和服务端计算出来的SN不相等的时候提示SN校验失败"),
    APP_REFERER_CHECK_FAILED(220, "APP Referer校验失败", "浏览器类型的APP会校验referer字段是否存且切在referer白名单里面，否则返回该错误码"),
    APP_MCODE_CHECK_FAILED(230, "APP Mcode码校验失败", "服务器能解析到mcode，但和数据库中不一致，请携带正确的mcode"),
    APP_SERVICE_DISABLED(240, "APP 服务被禁用", "用户在API控制台中创建或设置某APP的时候禁用了某项服务，若需开通权限，可进入API控制台为AK勾选对应服务"),
    USER_NOT_EXIST(250, "用户不存在", "根据请求的user_id, 数据库中找不到该用户的信息，请携带正确的user_id"),
    USER_DISABLED_BY_SELF(251, "用户被自己删除", "该用户处于未激活状态"),
    USER_DELETED_BY_ADMIN(252, "用户被管理员删除", "恶意用户被加入黑名单"),
    SERVICE_NOT_EXIST(260, "服务不存在", "服务器解析不到用户请求的服务名称"),
    SERVICE_DISABLED_DOWN(261, "服务被禁用", "该服务已下线，请在控制台确认是否有该服务的权限"),
    PERMANENT_QUOTA_EXCEEDED(301, "永久配额超限，限制访问", "配额超限，可在控制台购买提升配额"),
    DAILY_QUOTA_EXCEEDED(302, "天配额超限，限制访问", "配额超限，可在控制台购买提升配额"),
    CONCURRENCY_CONTROL_LIMITED(401, "当前并发量已经超过约定并发配额，限制访问", "并发控制超限，可在控制台购买提升并发"),
    TOTAL_CONCURRENCY_CONTROL_LIMITED(402, "当前并发量已经超过约定并发配额，并且服务总并发量也已经超过设定的总并发配额，限制访问", "并发控制超限，可在控制台购买提升并发");

    private final int                            code;
    private final String                         desc;
    private final String                         detail;

    private static final Map<Integer, ErrorCode> codeMap = new HashMap<>();

    static {
        for (ErrorCode errorCode : values()) {
            codeMap.put(errorCode.code, errorCode);
        }
    }

    ErrorCode(int code, String desc, String detail) {
        this.code = code;
        this.desc = desc;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getDetail() {
        return detail;
    }

    public static ErrorCode fromCode(int code) {
        return codeMap.get(code);
    }
}