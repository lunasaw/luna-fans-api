package com.luna.ali.service;

import java.util.Map;

import com.luna.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.luna.ali.alipay.factory.PayCheckFactory;
import com.luna.ali.api.AlipayApi;
import com.luna.ali.config.AlipayConfigProperties;
import com.luna.ali.dto.*;
import com.luna.common.dto.constant.ResultCode;

/**
 * @ClassName: AlipayService
 * @Author: luna
 * @CreateTime: 2020/8/18 12:35
 * @Description:
 */
@Service
public class AlipayService {

    private static final Logger log = LoggerFactory.getLogger(AlipayService.class);

    @Autowired
    private AlipayConfigProperties alipayConfigProperties;

    /**
     * 支付接口 网页端自动跳转
     * 
     * @param alipayOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public String createOrder(AlipayOrderDTO alipayOrderDTO) {
        log.info("createOrder start alipayOrderDTO={}", JSON.toJSONString(alipayOrderDTO));
        try {
            return AlipayApi.pagePay(alipayConfigProperties, alipayOrderDTO);
        } catch (AlipayApiException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 交易查询
     * 
     * @param queryOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public QueryOrderResultDTO queryOrder(QueryOrderDTO queryOrderDTO) {
        log.info("queryOrder start queryOrderDTO={}", JSON.toJSONString(queryOrderDTO));
        try {
            String response = JSON.parseObject(AlipayApi.payQuery(alipayConfigProperties, queryOrderDTO))
                .getString("alipay_trade_query_response");
            log.info("queryOrder success response={}", response);
            return JSON.parseObject(response, QueryOrderResultDTO.class);
        } catch (AlipayApiException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 订单同步消息
     * 
     * @param data
     */
    public void notifyOrder(Map<String, String[]> data) {
        log.info("notifyOrder start data={}", JSON.toJSONString(data));
        Map<String, String> reload = PayCheckFactory.reload(data);
        String s = JSON.toJSONString(reload);
        QueryOrderResultDTO queryOrderResultDTO = JSON.parseObject(s, QueryOrderResultDTO.class);
        // TODO 订单处理
        log.info("notifyOrder get data success queryOrderResultDTO={}", JSON.toJSONString(queryOrderResultDTO));
    }

    /**
     * 订单关闭
     * 
     * @param closeOrderDTO
     * @return
     */
    public String closeOrder(CloseOrderDTO closeOrderDTO) {
        log.info("closeOrder start closeOrderDTO={}", JSON.toJSONString(closeOrderDTO));
        try {
            String s = AlipayApi.payClose(alipayConfigProperties, closeOrderDTO);
            log.info("closeOrder success s={}", JSON.toJSONString(s));
            return s;
        } catch (AlipayApiException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 退款
     * 
     * @param refundAmountDTO
     * @return
     */
    public String refundAmount(RefundAmountDTO refundAmountDTO) {
        log.info("refundAmount start refundAmountDTO={}", JSON.toJSONString(refundAmountDTO));
        try {
            String s = AlipayApi.payRefund(alipayConfigProperties, refundAmountDTO);
            log.info("closeOrder refundAmount s={}", JSON.toJSONString(s));
            return s;
        } catch (AlipayApiException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 退款查询
     * 
     * @param refundQueryDTO
     * @return
     */
    public String refundQuery(RefundQueryDTO refundQueryDTO) {
        log.info("refundQuery start refundQueryDTO={}", JSON.toJSONString(refundQueryDTO));
        try {
            String s = AlipayApi.payRefundQuery(alipayConfigProperties, refundQueryDTO);
            log.info("refundQuery result s={}", JSON.toJSONString(s));
            return s;
        } catch (AlipayApiException e) {
            throw new BaseException(ResultCode.ERROR_SYSTEM_EXCEPTION, e.getMessage());
        }
    }
}
