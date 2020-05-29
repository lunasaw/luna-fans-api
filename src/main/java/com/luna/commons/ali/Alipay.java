package com.luna.commons.ali;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.luna.commons.ali.entity.AlipayBean;

/**
 * 支付宝支付接口
 * 
 * @author Luna@win10
 * @date 2020/4/26 15:40
 */
public class Alipay {
    /**
     * 支付接口
     * 
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    public static String pay(AlipayClient alipayClient, String returnUrl, String notifyUrl, AlipayBean alipayBean)
        throws AlipayApiException {
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }

    /**
     * 支付宝订单查询支付金额
     * 
     * @param payNumber 订单号
     * @return 订单支付金额
     * @throws AlipayApiException
     */
    public static String query(AlipayClient alipayClient, String payNumber) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
            "\"out_trade_no\":\"" + payNumber + "\"," +
            "      \"query_options\":[" +
            "        \"TRADE_SETTLE_INFO\"" +
            "      ]" +
            "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        JSONObject jsonObject1 = JSON.parseObject(jsonObject.get("alipay_trade_query_response").toString());
        if (response.isSuccess()) {
            return jsonObject1.get("total_amount").toString();
        } else {
            return null;
        }
    }

    /**
     * 退款接口
     * 
     * @param payNumber 订单号
     * @param refund_amount 退款金额<=订单金额
     * @return 是否退款成功
     * @throws AlipayApiException
     */
    public static boolean refund(AlipayClient alipayClient, String payNumber, double refund_amount)
        throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
            "\"out_trade_no\":\"" + payNumber + "\"," +
            "\"refund_amount\":" + refund_amount + "," +
            "\"refund_reason\":\"正常退款\"" +
            "  }");
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return response.isSuccess();
    }

    /**
     * 支付宝退款查询
     * 
     * @param payNumber 订单号
     * @return 是否退款成功
     * @throws AlipayApiException
     */
    public static boolean refundPage(AlipayClient alipayClient, String payNumber) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent("{" +
            "\"out_trade_no\":\"" + payNumber + "\"," +
            "\"out_request_no\":\"" + payNumber + "\"" +
            "  }");
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        JSONObject jsonObject = JSON.parseObject(response.getBody());
        JSONObject jsonObject1 =
            JSON.parseObject(jsonObject.get("alipay_trade_fastpay_refund_query_response").toString());
        return jsonObject1.get("code").toString().equals("10000");
    }

}
