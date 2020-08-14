package com.luna.message.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.luna.message.dto.SmsDTO;
import com.luna.tencent.api.TencentMessageApi;
import com.luna.tencent.dto.message.SendStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/5/14 20:46
 */
public class TencentSmsMessage {

    private static final Logger log = LoggerFactory.getLogger(TencentSmsMessage.class);

    /**
     * 发送短信结果解析
     * 
     * @return 包含对应手机号的发送信息结果集 1 成功 0 失败
     */
    public static ArrayList<SendStatusDTO> sendMsg(String id, String key, SmsDTO smsDTO) {
        log.info("sendMsgReq start id={}, key={}, smsDTO={}", id, key, smsDTO);
        String s = null;
        try {
            s = TencentMessageApi.sendMsgReq(id, key, smsDTO.getPhones(), smsDTO.getAppid(), smsDTO.getSign(),
                smsDTO.getTemplateId(), smsDTO.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSON.parseObject(s);
        String state = JSON.parseObject(jsonObject.get("Response").toString()).get("SendStatusSet").toString();
        List<String> datas = JSON.parseArray(state, String.class);
        ArrayList<SendStatusDTO> list = Lists.newArrayList();
        for (String data : datas) {
            SendStatusDTO sendStatusDTO = JSON.parseObject(data, SendStatusDTO.class);
            list.add(sendStatusDTO);
        }
        return list;
    }
}
