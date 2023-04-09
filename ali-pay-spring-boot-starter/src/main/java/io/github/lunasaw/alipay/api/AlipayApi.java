package io.github.lunasaw.alipay.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import io.github.lunasaw.alipay.config.AlipayConfigProperties;
import io.github.lunasaw.alipay.dto.*;
import io.github.lunasaw.alipay.factory.PayCheckFactory;
import io.github.lunasaw.alipay.factory.PayRootChainFactory;

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
     * @param alipayConfigProperties
     * @param alipayOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public static String pagePay(AlipayConfigProperties alipayConfigProperties, AlipayOrderDTO alipayOrderDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigProperties.getAppId(), alipayConfigProperties.getPrivateKey(),
                alipayConfigProperties.getPublicKey())
            .pagePay(alipayOrderDTO.getSubject(), alipayOrderDTO.getOutTradeNo(), alipayOrderDTO.getTotalAmount())
            .builder()
            .pay(alipayConfigProperties.getReturnUrl(), alipayConfigProperties.getNotifyUrl());
    }

    /**
     * 交易查询
     * 
     * @param alipayConfigProperties
     * @param queryOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payQuery(AlipayConfigProperties alipayConfigProperties, QueryOrderDTO queryOrderDTO)
        throws AlipayApiException {
        AlipayTradeQueryModel queryModel = new AlipayTradeQueryModel();
        queryModel.setOutTradeNo(queryOrderDTO.getOutTradeNo());
        queryModel.setTradeNo(queryOrderDTO.getTradeNo());
        queryModel.setQueryOptions(queryOrderDTO.getQueryOptions());
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigProperties.getAppId(), alipayConfigProperties.getPrivateKey(),
                alipayConfigProperties.getPublicKey())
            .queryPay(queryModel)
            .builder()
            .query();
    }

    /**
     * 关闭交易
     * 
     * @param alipayConfigProperties
     * @param closeOrderDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payClose(AlipayConfigProperties alipayConfigProperties, CloseOrderDTO closeOrderDTO)
        throws AlipayApiException {
        AlipayTradeCloseModel closeModel = new AlipayTradeCloseModel();
        closeModel.setOutTradeNo(closeOrderDTO.getOutTradeNo());
        closeModel.setTradeNo(closeOrderDTO.getTradeNo());
        closeModel.setOperatorId(closeOrderDTO.getTradeNo());
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigProperties.getAppId(), alipayConfigProperties.getPrivateKey(),
                alipayConfigProperties.getPublicKey())
            .closePay(closeModel)
            .builder()
            .close();
    }

    /**
     * 交易退款
     * 
     * @param alipayConfigProperties
     * @param refundAmountDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payRefund(AlipayConfigProperties alipayConfigProperties, RefundAmountDTO refundAmountDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigProperties.getAppId(), alipayConfigProperties.getPrivateKey(),
                alipayConfigProperties.getPublicKey())
            .refundPay(refundAmountDTO.getOutTradeNo(), refundAmountDTO.getTradeNo(), refundAmountDTO.getRefundAmount(),
                refundAmountDTO.getRefundReason(), refundAmountDTO.getOutRequestNo())
            .builder()
            .refund();
    }

    /**
     * 退款查询
     * 
     * @param alipayConfigProperties
     * @param refundQueryDTO
     * @return
     * @throws AlipayApiException
     */
    public static String payRefundQuery(AlipayConfigProperties alipayConfigProperties, RefundQueryDTO refundQueryDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigProperties.getAppId(), alipayConfigProperties.getPrivateKey(),
                alipayConfigProperties.getPublicKey())
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
    public static String payDownloadQuery(AlipayConfigProperties alipayConfigProperties, QueryBillDTO queryBillDTO)
        throws AlipayApiException {
        return PayRootChainFactory
            .createdDevPayChain(alipayConfigProperties.getAppId(), alipayConfigProperties.getPrivateKey(),
                alipayConfigProperties.getPublicKey())
            .downloadQueryPay(queryBillDTO.getBillType(), queryBillDTO.getBillDate())
            .builder()
            .downloadQuery();
    }

    /**
     * 支付验证
     * 
     * @param alipayConfigProperties
     * @param request
     * @return
     * @throws AlipayApiException
     */
    public static boolean payCheck(AlipayConfigProperties alipayConfigProperties, HttpServletRequest request)
        throws AlipayApiException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> reload = PayCheckFactory.reload(parameterMap);
        return PayCheckFactory.check(reload, alipayConfigProperties.getPublicKey());
    }
}
