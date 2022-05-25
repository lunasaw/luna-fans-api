// package com.luna.ali.controller;
//
// import java.util.Map;
//
// import javax.servlet.http.HttpServletRequest;
//
// import org.springframework.beans.factory.annotation.Autowired;
//
// import com.alipay.api.AlipayApiException;
// import com.luna.ali.dto.*;
// import com.luna.ali.service.AlipayService;
// import com.luna.common.dto.ResultDTO;
// import com.luna.common.dto.constant.ResultCode;
//
/// **
// * @Package: com.luna.ali.alipay.com.luna.message.controller
// * @ClassName: AliPayController
// * @Author: luna
// * @CreateTime: 2020/8/18 12:35
// * @Description:
// */
// @RestController
// @RequestMapping("ali")
// public class AliPayController {
//
// @Autowired
// private AlipayService alipayService;
//
// /**
// * 支付接口
// *
// * @param
// * @return
// * @throws AlipayApiException
// */
// @PostMapping(value = "pagePay")
// public String alipay(AlipayOrderDTO alipayOrderDTO) {
// return alipayService.createOrder(alipayOrderDTO);
// }
//
// /**
// * 订单手动查询
// *
// * @param queryOrderDTO
// * @return
// */
// @PostMapping(value = "query")
// public ResultDTO<QueryOrderResultDTO> queryOrder(QueryOrderDTO queryOrderDTO) {
// if (queryOrderDTO == null) {
// return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID,
// null);
// }
// return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
// alipayService.queryOrder(queryOrderDTO));
// }
//
// /**
// * 同步消息接受
// *
// * @param request
// * @return
// */
// @PostMapping(value = "notify")
// public String orderNotify(HttpServletRequest request) {
// Map<String, String[]> parameterMap = request.getParameterMap();
// alipayService.notifyOrder(parameterMap);
// return ResultCode.MSG_SUCCESS;
// }
//
// /**
// * 订单关闭
// *
// * @param closeOrderDTO
// * @return
// */
// @PostMapping(value = "close")
// public ResultDTO<String> closeOrder(CloseOrderDTO closeOrderDTO) {
// if (closeOrderDTO == null) {
// return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID,
// null);
// }
// return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
// alipayService.closeOrder(closeOrderDTO));
// }
//
// /**
// * 退款
// *
// * @param refundAmountDTO
// */
// @PostMapping("refund")
// public ResultDTO<String> refundAmount(RefundAmountDTO refundAmountDTO) {
// if (refundAmountDTO == null) {
// return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID,
// null);
// }
// return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
// alipayService.refundAmount(refundAmountDTO));
// }
//
// /**
// * 退款查询
// *
// * @param refundQueryDTO
// */
// @PostMapping("refundQuery")
// public ResultDTO<String> refundQuery(RefundQueryDTO refundQueryDTO) {
// if (refundQueryDTO == null) {
// return new ResultDTO<>(false, ResultCode.PARAMETER_INVALID, ResultCode.MSG_PARAMETER_INVALID,
// null);
// }
// return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
// alipayService.refundQuery(refundQueryDTO));
// }
// }
