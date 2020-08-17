package com.luna.tencent.pay.nortify;

import com.luna.common.http.HttpUtils;
import com.luna.tencent.pay.api.TencentPayApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private TencentPayNotifyService tencentPayNotifyService;

    @Autowired
    private RabbitTemplate          rabbitTemplate;

    @Autowired
    private Environment             env;

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

}
