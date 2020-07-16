package com.luna.message.api.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.CommonUtils;
import com.luna.message.api.constant.MessageTypeConstant;
import com.luna.message.api.constant.TargetKeyConstant;
import com.luna.message.api.constant.TargetTypeConstant;
import com.luna.message.api.dao.TemplateDAO;
import com.luna.message.api.entity.MessageDO;
import com.luna.message.api.wrapper.MailWrapper;
import com.luna.message.api.wrapper.SmsWrapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @author Tony
 * @date 2019年10月4日 下午10:11:46
 */
@Component
public class MessageService {

    /** 发消息用线程池 */
    private static ThreadPoolExecutor sendMessageThreadPoolExecutor =
        new ThreadPoolExecutor(1, Integer.MAX_VALUE, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryBuilder().setNameFormat("send-message-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    @Autowired
    private TemplateDAO               templateDAO;
    @Autowired
    private MailWrapper               mailWrapper;
    @Autowired
    private SmsWrapper                smsWrapper;

    public void asyncSendMessage(MessageDO messageDO, String email, String phone) {
        if (MapUtils.isEmpty(messageDO.getTargetMap())) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        if (TargetTypeConstant.isLegal(messageDO.getTargetType()) == false) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }
        if (MessageTypeConstant.isLegal(messageDO.getMessageType()) == false) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
        }

        if (StringUtils.equals(TargetTypeConstant.EMAIL, messageDO.getTargetType())) {
            if (messageDO.getTargetMap().containsKey(TargetKeyConstant.EMAIL) == false) {
                throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
            }
            if (CommonUtils.isEmailAddress(messageDO.getTargetMap().get(TargetKeyConstant.EMAIL)) == false) {
                throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
            }
        } else if (StringUtils.equals(TargetTypeConstant.MOBILE, messageDO.getTargetType())) {
            if (messageDO.getTargetMap().containsKey(TargetKeyConstant.MOBILE) == false) {
                throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
            }
            if (CommonUtils.isMobilePhoneNumber(messageDO.getTargetMap().get(TargetKeyConstant.MOBILE)) == false) {
                throw new BaseException(ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID);
            }
        }
        // 异步提交任务
        sendMessageThreadPoolExecutor
            .execute(new MessageTask(messageDO, templateDAO, mailWrapper, smsWrapper, email, phone));
    }
}
