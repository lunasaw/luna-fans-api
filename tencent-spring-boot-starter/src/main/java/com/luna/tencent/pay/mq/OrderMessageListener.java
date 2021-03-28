package com.luna.tencent.pay.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luna.tencent.pay.dto.NotifyResultDTO;
import com.luna.tencent.pay.nortify.TencentPayNotifyService;

/**
 * 描述
 *
 * @author luna
 * @version 1.0
 * @package com.luna.tencent.pay.mq
 * @since 1.0
 */
@Component
@RabbitListener(queues = "${mq.pay.queue}")
public class OrderMessageListener {

    @Autowired
    private TencentPayNotifyService tencentPayNotifyService;

    @RabbitHandler
    public void handlerData(String msg) {
        NotifyResultDTO notifyResultDTO = tencentPayNotifyService.analysisNotify(msg);
    }
}
