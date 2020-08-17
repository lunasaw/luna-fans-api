package com.luna.tencent.pay.nortify;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.text.ConvertUtil;
import com.luna.tencent.pay.api.TencentPayApi;
import com.luna.tencent.pay.dto.NotifyResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Package: com.luna.tencent.pay.nortify
 * @ClassName: TencentPayNotifyService
 * @Author: luna
 * @CreateTime: 2020/8/16 16:51
 * @Description:
 */
@Service
public class TencentPayNotifyService {

    private static final Logger log = LoggerFactory.getLogger(TencentPayNotifyService.class);

    /**
     * 微信支付回调数据处理
     * 
     * @param data
     * @return
     */
    public String analysisNotify(String data) {
        log.info("analysisNotify start data={}", data);
        try {
            Map<String, String> map = ConvertUtil.xmlToMap(data);
            NotifyResultDTO notifyResultDTO = JSON.parseObject(JSON.toJSONString(map), NotifyResultDTO.class);
            if ("SUCCESS".equals(notifyResultDTO.getReturnCode())) {
                // TODO 通讯成功,进行订单处理
                log.info("analysisNotify success notifyResultDTO={}", JSON.toJSONString(notifyResultDTO));
                return TencentPayApi.retrunOrder("SUCCESS", "OK");
            } else {
                // TODO 关闭订单 取消回滚
                log.info("analysisNotify failed notifyResultDTO={},data={}", JSON.toJSONString(notifyResultDTO), data);
                return TencentPayApi.retrunOrder("FAIL", "");
            }
        } catch (Exception e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }
}
