package com.luna.baidu.enums.voice;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {
    OPEN_API_REQUEST_LIMIT_REACHED(4, "Open api request limit reached", "集群超限额", ""),
    NO_PERMISSION_TO_ACCESS_DATA(6, "No permission to access data", "对控制台内app进行编辑，添加语音权限", ""),
    IAM_CERTIFICATION_FAILED(14, "IAM Certification failed", "IAM鉴权失败，建议用户参照文档自查生成sign的方式是否正确，或换用控制台中ak sk的方式调用", ""),
    OPEN_API_DAILY_REQUEST_LIMIT_REACHED(17, "Open api daily request limit reached", "每天流量超限额", ""),
    OPEN_API_QPS_REQUEST_LIMIT_REACHED(18, "Open api qps request limit reached", "并发超限额", ""),
    OPEN_API_TOTAL_REQUEST_LIMIT_REACHED(19, "Open api total request limit reached", "请求总量超限额", ""),
    INVALID_PARAMETER(100, "Invalid parameter", "无效参数", ""),
    ACCESS_TOKEN_INVALID_OR_NO_LONGER_VALID(110, "Access token invalid or no longer valid", "Access Token失效", ""),
    ACCESS_TOKEN_EXPIRED(111, "Access token expired", "Access token过期", ""),

    INPUT_ERROR(3300, "输入错误", "输入参数不正确", "请仔细核对文档及参照 demo，核对输入参数"),
    POOR_AUDIO_QUALITY(3301, "音频质量过差", "音频质量过差", "请上传清晰的音频"),
    AUTHENTICATION_FAILED(3302, "鉴权失败", "token字段校验失败", "请使用正确的API_KEY 和 SECRET_KEY生成。 或并发、调用量超出限额。 或音频采样率不正确（可尝试更换为16k采样率）。 或者自训练平台 lm_id 不属于该账号"),
    SERVER_BUSY(3303, "百度服务器后端繁忙", "百度服务器后端繁忙", "有可能是原始音频质量过差。可以请将 api 返回结果和原始音频反馈至论坛或者 QQ 群"),
    USER_REQUEST_EXCEEDED(3304, "用户请求超限", "用户的请求并发超限", "请降低识别 api 请求频率 （并发以 appId 计算，移动端如果共用则累计）"),
    USER_DAILY_REQUEST_EXCEEDED(3305, "用户请求超限", "用户的日 pv（日请求量）超限", "请开通付费，购买调用量资源（账号内所有应用 APPID 共用调用量限额）"),
    SERVER_ERROR(3307, "语音服务器后端识别出错问题", "语音服务器后端识别出错问题", "有可能是原始音频质量过差。可以将 api 返回结果和原始音频反馈至工单、论坛或者 QQ 群"),
    AUDIO_TOO_LONG(3308, "音频过长", "音频过长", "音频时长不超过 60s，请将音频时长截取为 60s 以下，特别是 amr 格式"),
    AUDIO_DATA_PROBLEM(3309, "音频数据问题", "服务端无法将音频转为 pcm 格式，可能是长度问题，音频格式问题等。", "请将输入的音频时长截取为 60s 以下，并核对下音频的编码，采样率 16000，单声道，小端序，16bits。"),
    AUDIO_FILE_TOO_LARGE(3310, "输入的音频文件过大 或 len 参数过大", "文件内容过大，音频时长不能超过 60s", "文件内容过大，音频时长不能超过 60s"),
    RATE_NOT_SUPPORTED(3311, "采样率 rate 参数不在选项里", "", "目前 rate 参数支持 16000、8000，填写其他值即会有此错误。"),
    FORMAT_NOT_SUPPORTED(3312, "音频格式 format 参数不在选项里", "", "目前格式仅仅支持 pcm，wav 或 amr，如填写 mp3 即会有此错误"),
    SERVER_TIMEOUT(3313, "服务端问题", "语音服务器解析超时", "请将 api 返回结果反馈至工单、论坛或者 QQ 群"),
    AUDIO_TOO_SHORT(3314, "用户输入错误", "音频长度过短", "用户的 len 参数小于等于 4"),
    SERVER_PROCESS_TIMEOUT(3315, "服务端问题", "语音服务器处理超时", "请将 api 返回结果反馈至工单、论坛或者 QQ 群"),
    PCM_CONVERSION_FAILED(3316, "用户输入错误", "音频转为 pcm 失败", "使用 pcm 格式，或者确认 wav 和 amr 的采样率 16000，单声道。 wav 是否是 pcm 编码，小端序，16bits");

    private int                            code;
    private String                         name;
    private String                         desc;
    private String                         comment;
    private static Map<Integer, ErrorCode> codeMap = new HashMap<>();

    static {
        for (ErrorCode errorCode : ErrorCode.values()) {
            codeMap.put(errorCode.code, errorCode);
        }
    }

    ErrorCode(int code, String name, String desc, String comment) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.comment = comment;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getComment() {
        return comment;
    }

    public static ErrorCode getByCode(int code) {
        return codeMap.get(code);
    }
}