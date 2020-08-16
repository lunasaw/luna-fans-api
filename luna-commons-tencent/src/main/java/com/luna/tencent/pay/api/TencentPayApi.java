package com.luna.tencent.pay.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.http.HttpUtils;
import com.luna.common.utils.md5.SignUtil;
import com.luna.common.utils.text.ConvertUtil;
import com.luna.common.utils.text.RandomStrUtil;
import com.luna.common.utils.text.StringUtils;
import com.luna.tencent.config.TencentPayConfigValue;
import com.luna.tencent.pay.constant.TencentPayConstant;
import com.luna.tencent.pay.dto.QueryResultDTO;
import com.luna.tencent.pay.entity.TencentPayEntity;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.luna.tencent.pay.api
 * @ClassName: TencentPayApi
 * @Author: luna
 * @CreateTime: 2020/8/16 14:28
 * @Description:
 */
public class TencentPayApi {

    private static final Logger log = LoggerFactory.getLogger(TencentPayApi.class);

    /**
     * 生成支付订单二维码
     * 
     * @param configValue 配置项目
     * @param payEntity 订单实体
     * @return 支付二维码
     * @throws Exception
     */
    public static String createNative(TencentPayConfigValue configValue, TencentPayEntity payEntity) {
        log.info("createNative start configValue={},payEntity={}", JSON.toJSONString(configValue),
            JSON.toJSONString(payEntity));
        HashMap<String, String> paramMap = Maps.newHashMap();

        // 2.设置参数值(根据文档来写)
        paramMap.put("appid", configValue.getAppid());
        paramMap.put("mch_id", configValue.getPartner());
        paramMap.put("nonce_str", RandomStrUtil.generateNonceStr());
        paramMap.put("body", payEntity.getBody());
        paramMap.put("out_trade_no", payEntity.getOutTradeNo());
        paramMap.put("total_fee", payEntity.getTotalFee());
        paramMap.put("spbill_create_ip", payEntity.getSpbillCreateIp());
        paramMap.put("notify_url", configValue.getNotifyurl());
        paramMap.put("trade_type", payEntity.getTradeType());

        String code_url = null;
        try {
            String body = SignUtil.generateSignedXml(paramMap, configValue.getPartnerkey());
            HttpResponse httpResponse =
                HttpUtils.doPost(TencentPayConstant.HOST, TencentPayConstant.CREATE_ORDER, ImmutableMap.of(), null,
                    body);
            String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
            code_url = ConvertUtil.xmlToMap(s).get("code_url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("createNative success configValue={},payEntity={},code_url={}", JSON.toJSONString(configValue),
            JSON.toJSONString(payEntity), code_url);
        return code_url;
    }

    /**
     * 支付状态查询
     *
     * @param configValue 配置信息
     * @param outTradeNo 订单号
     * @return
     * @throws Exception
     */
    public static QueryResultDTO queryStatus(TencentPayConfigValue configValue, String outTradeNo)
        throws Exception {
        log.info("queryStatus start out_trade_no={}", outTradeNo);
        Map<String, String> paramMap = new HashMap<>();

        // 2.设置参数值(根据文档来写)
        paramMap.put("appid", configValue.getAppid());
        paramMap.put("mch_id", configValue.getPartner());
        paramMap.put("nonce_str", RandomStrUtil.generateNonceStr());
        paramMap.put("out_trade_no", outTradeNo);

        String body = SignUtil.generateSignedXml(paramMap, configValue.getPartnerkey());
        HttpResponse httpResponse =
            HttpUtils.doPost(TencentPayConstant.HOST, TencentPayConstant.QUERY_ORDER, ImmutableMap.of(), null, body);
        String s = HttpUtils.checkResponseAndGetResult(httpResponse, true);
        Map<String, String> map = ConvertUtil.xmlToMap(s);
        QueryResultDTO queryResultDTO = JSON.parseObject(JSON.toJSONString(map), QueryResultDTO.class);
        return queryResultDTO;
    }

    /**
     *
     * @param returnCode SUCCESS/FAIL
     * SUCCESS表示商户接收通知成功并校验成功
     * @param returnMsg 返回信息，如非空，为错误原因：
     * 签名失败
     * 参数格式校验错误
     */
    public static String retrunOrder(String returnCode, String returnMsg) throws Exception {
        log.info("retrunOrder start returnCode={},returnMsg={}", returnCode, returnMsg);
        Map<String, String> paramMap = new HashMap<>();
        if (StringUtils.isEmpty(returnCode)) {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "返回状态码不能为空");
        }
        paramMap.put("return_code", returnCode);
        if (StringUtils.isNotEmpty(returnMsg)) {
            paramMap.put("return_msg", returnMsg);
        }
        return ConvertUtil.mapToXml(paramMap);
    }
}
