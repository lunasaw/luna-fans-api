package com.luna.api.email.dto;

import com.luna.api.email.constant.MessageTypeConstant;
import com.luna.common.text.CharsetUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.mail.internet.InternetAddress;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Luna
 */
@Data
@Slf4j
public class EmailSmallDTO {

    /** 目标列表，邮箱或手机 */
    private List<String>        targetList;

    /** 消息类型，见{@link MessageTypeConstant} */
    @NotBlank
    private String              messageType;

    /** 模版消息id */
    @Min(1)
    private Long                templateId;

    /**
     * 发送人昵称
     */
    private Personal            fromUser;

    /**
     * 邮件回复的收件人
     */
    private Personal            replyTo;
    /**
     * 发送人密码
     */
    private String              fromMailPassword;

    /** 主题 */
    @NotEmpty
    private String              subject;

    /**
     * 发送时间
     */
    private Date                sentDate;

    /** 内容 */
    private Content             content;

    /** 加密抄送 */
    private List<Personal>      bcc;

    /** 抄送人 */
    private List<Personal>      cc;

    /** 占位内容 */
    private Map<String, String> placeholderContent;

    /** 附件地址<名称,路径> */
    private Map<String, String> pathMap;

    @Data
    public static class Content {
        /**
         * 内容
         */
        @NotEmpty
        private String  txt;
        /**
         * html
         */
        private Boolean html;

        public Content(String txt) {
            this.txt = txt;
            this.html = false;
        }

        public Content(String txt, Boolean html) {
            this.txt = txt;
            this.html = html;
        }
    }

    @Data
    public static class Personal {
        /**
         * 昵称(可选) 在 Java 中，InternetAddress 类用于表示电子邮件地址。InternetAddress 类中的 personal 属性表示邮件地址中的名称或昵称。
         * 注意，在设置 personal 属性时，需要使用 RFC 2047 规定的编码方式对包含非 ASCII 字符的名称进行编码。可以使用 MimeUtility.encodeText() 方法进行编码
         */
        private String nickName;

        /**
         * 账户
         */
        private String address;

        public Personal(String address) {
            this.address = address;
        }

        public Personal(String address, String nickName) {
            this.nickName = nickName;
            this.address = address;
        }
    }

    public EmailSmallDTO() {}
}
