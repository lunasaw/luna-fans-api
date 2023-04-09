package io.github.lunasaw.pay.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import io.github.lunasaw.pay.dto.NotifyResultDTO;
import io.github.lunasaw.pay.nortify.TencentPayNotifyService;

/**
 * 描述
 *
 * @author luna
 * @version 1.0
 * @package io.github.lunasaw.pay.mq
 * @since 1.0
 */
@Component
@ConditionalOnProperty(prefix = "spring.wechat", name = "pay-mq.queue")
@RabbitListener(queues = "${spring.wechat.pay-mq.queue}")
public class OrderMessageListener {

    @Autowired
    private TencentPayNotifyService tencentPayNotifyService;

    @RabbitHandler
    public void handlerData(String msg) {
        NotifyResultDTO notifyResultDTO = tencentPayNotifyService.analysisNotify(msg);
    }
}
