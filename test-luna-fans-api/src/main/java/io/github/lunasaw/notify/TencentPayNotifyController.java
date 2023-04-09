package io.github.lunasaw.notify;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import io.github.lunasaw.pay.api.TencentPayApi;
import io.github.lunasaw.pay.entity.TencentPayEntity;
import io.github.lunasaw.pay.properties.TencentPayConfigProperties;
import io.github.lunasaw.pay.properties.TencentPayMqConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.BaseException;
import com.luna.common.net.base.HttpBaseUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: io.github.lunasaw.pay.nortify
 * @ClassName: TencentPayNotify
 * @Author: luna
 * @CreateTime: 2020/8/16 15:32
 * @Description:
 */
@RestController
@RequestMapping("/pay")
public class TencentPayNotifyController {
    @Autowired
    private RabbitTemplate          rabbitTemplate;

    @Autowired
    private TencentPayMqConfigProperties tencentPayMqConfigProperties;

    @Autowired
    private TencentPayConfigProperties   configValue;

    private static final Logger     log = LoggerFactory.getLogger(TencentPayNotifyController.class);

    /**
     * 支付回调同步接口
     * 
     * @param request
     */
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        String data = getRequest(request);
        // Mq监听处理
        rabbitTemplate.convertAndSend(tencentPayMqConfigProperties.getExchange(),
            tencentPayMqConfigProperties.getRouting(),
            data);
        return TencentPayApi.retrunOrder("SUCCESS", "OK");
    }

    /**
     * 解析请求体
     *
     * @param httpRequest
     * @return
     */
    public static String getRequest(HttpServletRequest httpRequest) {
        try {
            return HttpBaseUtils.readWithString(httpRequest.getInputStream(), "UTF-8");
        } catch (IOException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 订单创建=>延时处理
     * 
     * @param tencentPayEntity
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/createOrder")
    public String delay(@RequestBody TencentPayEntity tencentPayEntity, HttpServletRequest httpServletRequest) {
        tencentPayEntity.setSpbillCreateIp(httpServletRequest.getRemoteHost());
        String aNative = TencentPayApi.createNative(configValue, tencentPayEntity);
        rabbitTemplate.convertAndSend("orderDelayQueue", (Object)tencentPayEntity.getOutTradeNo(),
            new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("300000");
                    return message;
                }
            });
        log.info("delay: \"订单创建时间:\" + DateUtil.getTime()");
        return aNative;
    }

}
