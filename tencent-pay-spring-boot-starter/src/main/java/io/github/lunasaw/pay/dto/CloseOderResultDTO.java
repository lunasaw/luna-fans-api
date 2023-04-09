package io.github.lunasaw.pay.dto;

/**
 * @Package: io.github.lunasaw.pay.dto
 * @ClassName: CloseOderResultDTO
 * @Author: luna
 * @CreateTime: 2020/8/17 16:52
 * @Description:
 */
public class CloseOderResultDTO {

    /** 通讯代码 */
    private String returnCode;

    /** 业务代码 */
    private String resultCode;

    /** 返回信息 */
    private String resultMsg;

    /** 错误代码 */
    private String errCode;

    /**
     * 错误信息
     *
     * ORDERPAID 订单已支付 订单已支付，不能发起关单 订单已支付，不能发起关单，请当作已支付的正常交易
     * SYSTEMERROR 系统错误 系统错误 系统异常，请重新调用该API
     * ORDERCLOSED 订单已关闭 订单已关闭，无法重复关闭 订单已关闭，无需继续调用
     * SIGNERROR 签名错误 参数签名结果不正确 请检查签名参数和方法是否都符合签名算法要求
     * REQUIRE_POST_METHOD 请使用post方法 未使用post传递参数 请检查请求参数是否通过post方法提交
     * XML_FORMAT_ERROR XML格式错误 XML格式错误 请检查XML参数格式是否正确
     *
     */
    private String errCodeDes;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
