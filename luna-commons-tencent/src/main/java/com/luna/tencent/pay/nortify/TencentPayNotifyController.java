package com.luna.tencent.pay.nortify;

import com.luna.common.http.HttpUtils;
import com.luna.common.utils.text.DateUtil;
import com.luna.tencent.config.TencentPayConfigValue;
import com.luna.tencent.pay.api.TencentPayApi;
import com.luna.tencent.pay.entity.TencentPayEntity;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Package: com.luna.tencent.pay.nortify
 * @ClassName: TencentPayNotify
 * @Author: luna
 * @CreateTime: 2020/8/16 15:32
 * @Description:
 */
@RestController
@RequestMapping("/pay")
public class TencentPayNotifyController {
    @Autowired
    private RabbitTemplate        rabbitTemplate;

    @Autowired
    private Environment           env;

    @Autowired
    private TencentPayConfigValue configValue;

    /**
     * 支付回调同步接口
     * 
     * @param request
     */
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        String data = HttpUtils.getRequest(request);
        // Mq监听处理
        rabbitTemplate.convertAndSend(env.getProperty("mq.pay.exchange.order"), env.getProperty("mq.pay.routing.key"),
            data);
        return TencentPayApi.retrunOrder("SUCCESS", "OK");
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
        System.out.println("订单创建时间:" + DateUtil.getTime());
        return aNative;
    }

}
