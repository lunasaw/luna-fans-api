package com.luna.message.emailmodel;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;
import com.luna.common.utils.text.StringUtils;
import com.luna.message.api.constant.EmailContentsConstant;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.dto.EmailDTO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Package: com.luna.message.emailmodel
 * @ClassName: EmailModelBuild
 * @Author: luna
 * @CreateTime: 2020/8/8 20:45
 * @Description:
 */
public class EmailModelBuild {

    private static final Logger log = LoggerFactory.getLogger(EmailModelBuild.class);

    /**
     * 模板加载
     *
     * @param emailDTO 填充信息
     * @return
     * @throws IOException
     */
    public static String buildContentOne(EmailDTO emailDTO) {
        log.info("build model emailDTO={}", JSON.toJSONString(emailDTO));

        String modelName = emailDTO.getModelName();
        if (StringUtils.isEmpty(modelName)) {
            modelName = MessageTypeConstant.EMAIL_MODEL;
        }
        String model = getModel(modelName);
        Map<String, String> contents = emailDTO.getContents();
        String contentText = StringUtils.EMPTY;
        // 版权链接地址
        String copyRight = StringUtils.EMPTY;
        // 版权地址
        String path = StringUtils.EMPTY;
        // 头部颜色
        String emailHeadColor = StringUtils.EMPTY;
        // 昵称
        String outName = StringUtils.EMPTY;
        // 版权名称
        if (contents.containsKey(EmailContentsConstant.COPY_RIGHT_NAME)) {
            copyRight = contents.get(EmailContentsConstant.COPY_RIGHT_NAME) + EmailContentsConstant.COPY_RIGHT_VALUE;
        } else {
            copyRight = EmailContentsConstant.COPY_RIGHT_NAME_VALUE + EmailContentsConstant.COPY_RIGHT_VALUE;
        }

        // 版权地址
        if (contents.containsKey(EmailContentsConstant.COPY_RIGHT_SRC)) {
            path = contents.get(EmailContentsConstant.COPY_RIGHT_SRC);
        } else {
            path = EmailContentsConstant.COPY_RIGHT_SRC_VALUE;
        }

        // 默认绿色
        if (contents.containsKey(EmailContentsConstant.EMAIL_HEAD_COLOR)) {
            emailHeadColor = contents.get(EmailContentsConstant.EMAIL_HEAD_COLOR);
        } else {
            emailHeadColor = EmailContentsConstant.EMAIL_HEAD_COLOR_VALUE;
        }

        String emailContentBeforeOuterUser = StringUtils.EMPTY;
        // 用户信息之前
        if (contents.containsKey(EmailContentsConstant.EMAIL_CONTENT_BEFORE_OUTER_USER)) {
            emailContentBeforeOuterUser = contents.get(EmailContentsConstant.EMAIL_CONTENT_BEFORE_OUTER_USER);
        }

        // 昵称
        if (contents.containsKey(EmailContentsConstant.EMAIL_CONTENT_USER_NAME)) {
            outName = contents.get(EmailContentsConstant.EMAIL_CONTENT_USER_NAME);
        }

        // 用户信息之后
        String emailContentAfterOuterUser = StringUtils.EMPTY;
        if (contents.containsKey(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER)) {
            emailContentAfterOuterUser = contents.get(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER);
        }

        // 用户信息之后的链接
        String emailContentAfterOuterUserSrc = StringUtils.EMPTY;
        if (contents.containsKey(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER_SRC)) {
            emailContentAfterOuterUserSrc = contents.get(EmailContentsConstant.EMAIL_CONTENT_AFTER_OUTER_USER_SRC);
        }

        String date = DateFormatUtils.format(new Date(), DatePattern.NORM_DATETIME_MINUTE_PATTERN);
        // 填充html模板中的参数
        String htmlText =
            MessageFormat.format(model, emailHeadColor,
                emailContentBeforeOuterUser, outName,
                emailDTO.getEmailSmallDTO().getContent(), emailContentAfterOuterUserSrc, emailContentAfterOuterUser,
                date, path, copyRight);
        return htmlText;
    }

    /**
     * 读取模板信息
     *
     * @param modelName
     * @return
     */
    private static String getModel(String modelName) {
        log.info("read model modelName={}", modelName);
        // 加载邮件html模板
        BufferedReader fileReader = null;
        StringBuffer buffer = null;
        try {
            InputStream resourceAsStream =
                EmailModelBuild.class.getClassLoader().getResourceAsStream("static/" + modelName);
            fileReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            buffer = new StringBuffer();
            String line = StringUtils.EMPTY;
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            log.error("读取文件失败，fileName={}", modelName, e);
            throw new FileException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                log.error("读取文件失败，fileName={}", modelName, e);
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
