# ali-pay-spring-boot-starter

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/lunasaw/ali-spring-boot-starter/">
    <img src="https://tva1.sinaimg.cn/large/008i3skNgy1grnvzio673j30bf03fwea.jpg" alt="Logo" width="411" height="123">
  </a>
</p>

## 日志

- 2023-04-09 拆分项目,增加支付宝Api操作
- 8.19 增加支付宝Api操作
- 8.21 增加阿里Oss存储Api调用

###### **安装步骤**

引入项目依赖

```xml

<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>ali-pay-spring-boot-starter-ali</artifactId>
    <version>${last.version}</version>
</dependency>
```

在配置文件application.properties加入可选配置

```text
spring:
  ali:
    oss:
      enable: true
      # 阿里oss服务
      bucket-name: luna97
      access-key: xxx
      secret-key: xxx
      domain: xxx
      enable-cname: true
      endpoint: oss-cn-beijing.aliyuncs.com
     
```

引用示例

```java

若采用SpringBoot构建项目可通过将第三方包中的BaiduProperties,BaiduKeyGenerate通过Spring配置文件注入Spring管理

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaiduApiTest {
    @Autowired
    private AlipayConfigValue alipayConfigValue;

    @Test
    public void atest() throws Exception {
        alipayConfigValue.getAppId();
    }

}


```

### 文档

[文档](https://lunasaw.github.io/ali-pay-spring-boot-starter)

[结果即刻得到配置数据,进而调用api里的静态方法完成调用]()

### 文件目录说明

eg:

```



```

静态Api调用

```java

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
     * @param alipayConfigValue
     * @param request
     * @return
     * @throws AlipayApiException
     */
    public static boolean payCheck(AlipayConfigValue alipayConfigValue, HttpServletRequest request) throws AlipayApiException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> reload = PayCheckFactory.reload(parameterMap);
        return PayCheckFactory.check(reload, alipayConfigValue.getPublicKey());
    }
}

```
