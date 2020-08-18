package com.luna.ali.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.luna.ali.alipay.factory.PayCheckFactory;
import com.luna.ali.alipay.factory.PayRootChainFactory;
import com.luna.ali.config.AlipayConfigValue;
import com.luna.ali.dto.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 支付宝支付接口
 * 
 * @author Luna@win10
 * @date 2020/4/26 15:40
 */
public class AlipayApi {

    /**
     * 网页支付
     * 
     * @param alipayConfigValue
     * @param alipayOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public static String pagePay(AlipayConfigValue alipayConfigValue, AlipayOrderDTO alipayOrderDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigValue.getAppId(), alipayConfigValue.getPrivateKey(),
                alipayConfigValue.getPublicKey())
            .pagePay(alipayOrderDTO.getSubject(), alipayOrderDTO.getOutTradeNo(), alipayOrderDTO.getTotalAmount())
            .builder()
            .pay(alipayConfigValue.getReturnUrl(), alipayConfigValue.getNotifyUrl());
    }

    /**
     * 交易查询
     * 
     * @param alipayConfigValue
     * @param queryOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payQuery(AlipayConfigValue alipayConfigValue, QueryOrderDTO queryOrderDTO)
        throws AlipayApiException {
        AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
        queryModel.setOutTradeNo(queryOrderDTO.getOutTradeNo());
        queryModel.setTradeNo(queryOrderDTO.getTradeNo());
        queryModel.setQueryOptions(queryOrderDTO.getQueryOptions());
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigValue.getAppId(), alipayConfigValue.getPrivateKey(),
                alipayConfigValue.getPublicKey())
            .queryPay(queryModel)
            .builder()
            .query();
    }

    /**
     * 关闭交易
     * 
     * @param alipayConfigValue
     * @param closeOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payClose(AlipayConfigValue alipayConfigValue, CloseOrderDTO closeOrderDTO)
        throws AlipayApiException {
        AlipayTradeCloseModel closeModel = new AlipayTradeCloseModel();
        closeModel.setOutTradeNo(closeOrderDTO.getOutTradeNo());
        closeModel.setTradeNo(closeOrderDTO.getTradeNo());
        closeModel.setOperatorId(closeOrderDTO.getTradeNo());
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigValue.getAppId(), alipayConfigValue.getPrivateKey(),
                alipayConfigValue.getPublicKey())
            .closePay(closeModel)
            .builder()
            .close();
    }

    /**
     * 交易退款
     * 
     * @param alipayConfigValue
     * @param refundAmountDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payRefund(AlipayConfigValue alipayConfigValue, RefundAmountDTO refundAmountDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigValue.getAppId(), alipayConfigValue.getPrivateKey(),
                alipayConfigValue.getPublicKey())
            .refundPay(refundAmountDTO.getOutTradeNo(), refundAmountDTO.getTradeNo(), refundAmountDTO.getRefundAmount(),
                refundAmountDTO.getRefundReason(), refundAmountDTO.getOutRequestNo())
            .builder()
            .refund();
    }

    /**
     * 退款查询
     * 
     * @param alipayConfigValue
     * @param refundQueryDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payRefundQuery(AlipayConfigValue alipayConfigValue, RefundQueryDTO refundQueryDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigValue.getAppId(), alipayConfigValue.getPrivateKey(),
                alipayConfigValue.getPublicKey())
            .refundQueryPay(refundQueryDTO.getOutTradeNo(), refundQueryDTO.getTradeNo(),
                refundQueryDTO.getOutRequestNo())
            .builder()
            .refundQuery();
    }

    /**
     * 查询账单下载地址
     * 
     * @return
     * @throws AlipayApiException
     */
    public static String payDownloadQuery(AlipayConfigValue alipayConfigValue, QueryBillDTO queryBillDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigValue.getAppId(), alipayConfigValue.getPrivateKey(),
                alipayConfigValue.getPublicKey())
            .downloadQueryPay(queryBillDTO.getBillType(), queryBillDTO.getBillDate())
            .builder()
            .downloadQuery();
    }

    /**
     * 支付验证
     * 
     * @param alipayConfigValue
     * @param request
     * @return
     * @throws AlipayApiException
     */
    public static boolean payCheck(AlipayConfigValue alipayConfigValue, HttpServletRequest request)
        throws AlipayApiException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> reload = PayCheckFactory.reload(parameterMap);
        return PayCheckFactory.check(reload, alipayConfigValue.getPublicKey());
    }
}
