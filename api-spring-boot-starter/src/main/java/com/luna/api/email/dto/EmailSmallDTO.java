package com.luna.api.email.dto;

import com.luna.api.email.constant.MessageTypeConstant;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * @author Luna
 */
@Data
public class EmailSmallDTO {

    /** 目标列表，邮箱或手机 */
    private List<String>        targetList;

    /** 消息类型，见{@link MessageTypeConstant} */
    @NotBlank
    private String              messageType;

    /** 模版消息id */
    @Min(1)
    private Long                templateId;

    /** 发送人昵称 */
    private String              nickName;

    /**
     * 发送人账户
     */
    private String              fromMail;
    /**
     * 发送人密码
     */
    private String              fromMailPassword;

    /** 主题 */
    @NotEmpty
    private String              subject;

    /** 内容 */
    private String              content;

    /** 加密抄送 */
    private String[]            bcc;

    /** 抄送人 */
    private String[]            cc;

    /** 占位内容 */
    private Map<String, String> placeholderContent;

    /** 附件地址<名称,路径> */
    private Map<String, String> pathMap;

    public EmailSmallDTO(String fromMail, String fromMailPassword, String subject, String content) {
        this.fromMail = fromMail;
        this.fromMailPassword = fromMailPassword;
        this.subject = subject;
        this.content = content;
    }

    public EmailSmallDTO() {}
}
