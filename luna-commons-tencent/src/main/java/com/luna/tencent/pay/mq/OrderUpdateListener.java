package com.luna.tencent.pay.mq;

import com.luna.tencent.pay.nortify.TencentPayNotifyService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述
 *
 * @author luna
 * @version 1.0
 * @package com.luna.tencent.pay.mq
 * @since 1.0
 */
@Component
@RabbitListener(queues = "${mq.pay.queue.order}")
public class OrderUpdateListener {

    @Autowired
    private TencentPayNotifyService tencentPayNotifyService;

    @RabbitHandler
    public void handlerData(String msg) {
        tencentPayNotifyService.analysisNotify(msg);
    }
}
