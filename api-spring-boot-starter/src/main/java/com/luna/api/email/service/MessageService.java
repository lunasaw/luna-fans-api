package com.luna.api.email.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.luna.api.config.MailSendProperties;
import com.luna.api.email.constant.MessageTypeConstant;
import com.luna.api.email.dto.EmailSmallDTO;
import com.luna.api.email.dto.MessageDTO;
import com.luna.api.email.dto.SendKeyMessageDTO;
import com.luna.api.email.dto.TemplateDTO;
import com.luna.api.email.entity.TemplateDO;
import com.luna.api.email.exception.MessageException;
import com.luna.api.email.warpper.MailWrapper;
import com.luna.api.email.mapper.TemplateDAO;
import com.luna.common.dto.constant.ResultCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Luna
 * @Description 消息service
 * @date 2019年10月4日 下午10:11:46
 */
@Service
public class MessageService {

    @Autowired
    private MailSendProperties              mailSendProperties;

    @Autowired
    private MailProperties                  mailProperties;

    /**
     * 发消息用线程池
     */
    private static final ThreadPoolExecutor SEND_MESSAGE_THREAD_POOL_EXECUTOR =
        new ThreadPoolExecutor(1, Integer.MAX_VALUE, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("send-message-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private TemplateService                 templateService;
    @Autowired
    private MailWrapper                     mailWrapper;

    private TemplateDTO getTemplate(String messageType, Long templateId) {
        checkParam(messageType);
        return Optional.ofNullable(templateService.getTemplateById(templateId)).orElseThrow(
            () -> new MessageException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID));
    }

    private void checkParam(String messageType) {
        if (!MessageTypeConstant.isLegal(messageType)) {
            throw new MessageException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
    }

    public void asyncSendMessage(EmailSmallDTO messageDTO) {
        if (CollectionUtils.isEmpty(messageDTO.getTargetList())) {
            return;
        }
        TemplateDTO templateDTO = getTemplate(messageDTO.getMessageType(), messageDTO.getTemplateId());

        // 异步提交任务
        SEND_MESSAGE_THREAD_POOL_EXECUTOR.execute(new MessageTask(messageDTO, templateDTO, mailWrapper));
    }

    public void asyncSendMessage(SendKeyMessageDTO sendKeyMessageDTO) {
        if (CollectionUtils.isEmpty(sendKeyMessageDTO.getSendModelDTOS())) {
            return;
        }
        checkParam(sendKeyMessageDTO.getMessageType());

        List<String> targetList = sendKeyMessageDTO.getSendModelDTOS().stream().map(sendModelDTO -> {
            if (StringUtils.equals(sendKeyMessageDTO.getMessageType(), MessageTypeConstant.EMAIL)) {
                return sendModelDTO.getEmail();
            } else if (StringUtils.equals(sendKeyMessageDTO.getMessageType(), MessageTypeConstant.MOBILE)) {
                return sendModelDTO.getPhone();
            }
            return null;
        }).collect(Collectors.toList());

        CollectionUtils.filter(targetList, PredicateUtils.notNullPredicate());

        if (CollectionUtils.isEmpty(targetList)) {
            return;
        }

        extracted(targetList, sendKeyMessageDTO.getMessageType(), sendKeyMessageDTO.getTemplateId(),
            sendKeyMessageDTO.getPlaceholderContent(), mailProperties.getUsername(), mailSendProperties.getNick());
    }

    private void extracted(List<String> targetList, String messageType, Long templateId, Map<String, String> placeholderContent, String fromEmail,
        String nick) {
        EmailSmallDTO emailSmallDTO = new EmailSmallDTO();
        emailSmallDTO.setTargetList(targetList);
        emailSmallDTO.setMessageType(messageType);
        emailSmallDTO.setTemplateId(templateId);
        emailSmallDTO.setPlaceholderContent(placeholderContent);
        emailSmallDTO.setFromMail(fromEmail);
        emailSmallDTO.setNickName(nick);
        asyncSendMessage(emailSmallDTO);
    }

    public void sendSimpleMessage(String subject, String content) {
        SEND_MESSAGE_THREAD_POOL_EXECUTOR
            .execute(() -> mailWrapper.sendSimpleMessage(mailProperties.getUsername(), mailSendProperties.getNick(), subject, content));
    }
}
