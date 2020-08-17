package com.luna.tencent.pay.nortify;

import com.alibaba.fastjson.JSON;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.text.ConvertUtil;
import com.luna.tencent.pay.api.TencentPayApi;
import com.luna.tencent.pay.dto.NotifyResultDTO;
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

    /**
     * 微信支付回调数据处理
     * 
     * @param data
     * @return
     */
    public String analysisNotify(String data) {
        try {
            Map<String, String> map = ConvertUtil.xmlToMap(data);
            NotifyResultDTO notifyResultDTO = JSON.parseObject(JSON.toJSONString(map), NotifyResultDTO.class);
            /** 作相应处理后返回数据 */
            String success = TencentPayApi.retrunOrder("SUCCESS", "OK");
            return success;
        } catch (Exception e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }
}
