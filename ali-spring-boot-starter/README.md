# ali-spring-boot-starter

[ali-spring-boot-starter-ali](https://github.com/lunasaw/ali-spring-boot-starter-ali)

<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/lunasaw/ali-spring-boot-starter/">
    <img src="https://tva1.sinaimg.cn/large/008i3skNgy1grnvzio673j30bf03fwea.jpg" alt="Logo" width="411" height="123">
  </a>

<h3 align="center">阿里开放平台工具</h3>
  <p align="center">
    百度开放平台工具
    <br />
    <a href="https://github.com/lunasaw/ali-spring-boot-starter"><strong>探索本项目的文档 »</strong></a>
    <br />
    <br />
    <a href="">查看Demo</a>
    ·
    <a href="">报告Bug</a>
    ·
    <a href="https://github.com/lunasaw/ali-spring-boot-starter/issues">提出新特性</a>
  </p>

</p>

## 日志

8.19 增加支付宝Api操作

8.21 增加阿里Oss存储Api调用

## 目录

- [安装步骤](#安装步骤)
- [文档](#文档)
- [文件目录说明](#文件目录说明)

###### **安装步骤**

引入项目依赖

```xml

<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>ali-spring-boot-starter-ali</artifactId>
    <version>3.0.0</version>
</dependency>
```

在配置文件application.properties加入可选配置

```text
       # API
     luna:
       ali:
         # 阿里云oss
         bucketName: xxx
         host: xxx
         ossId: xxx
         ossKey: xxx
       alipay:
         # 应用ID
         appId: xxx
         # 异步数据返回地址
         notifyUrl: xxx
         # 私钥
         privateKey: xxx
         # 公钥
         publicKey: xxx
         # 同步返回地址
         returnUrl: xxx
     
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

[文档](https://lunasaw.github.io/ali-spring-boot-starter)

[结果即刻得到配置数据,进而调用api里的静态方法完成调用]()

### 文件目录说明

eg:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── luna
│   │   │           └── ali
│   │   │               ├── alipay
│   │   │               │   ├── container
│   │   │               │   │   ├── PayCheckFactoryContainer.java
│   │   │               │   │   ├── PayClientConstant.java
│   │   │               │   │   └── PayParamConstant.java
│   │   │               │   ├── factory
│   │   │               │   │   ├── PayCheckFactory.java
│   │   │               │   │   └── PayRootChainFactory.java
│   │   │               │   └── pay
│   │   │               │       ├── AppPayChain.java
│   │   │               │       ├── DefaultPayChain.java
│   │   │               │       ├── PagePayChain.java
│   │   │               │       ├── WapPayChain.java
│   │   │               │       ├── close
│   │   │               │       │   ├── PayCloseChain.java
│   │   │               │       │   └── param
│   │   │               │       │       └── PayCloseParamChain.java
│   │   │               │       ├── download
│   │   │               │       │   ├── PayDownloadQueryChain.java
│   │   │               │       │   └── param
│   │   │               │       │       └── PayDownloadQueryParamChain.java
│   │   │               │       ├── param
│   │   │               │       │   ├── AppPayParamChain.java
│   │   │               │       │   ├── PagePayParamChain.java
│   │   │               │       │   └── WapPayParamChain.java
│   │   │               │       ├── query
│   │   │               │       │   ├── PayQueryChain.java
│   │   │               │       │   └── param
│   │   │               │       │       └── PayQueryParamChain.java
│   │   │               │       └── refund
│   │   │               │           ├── PayRefundChain.java
│   │   │               │           ├── param
│   │   │               │           │   └── PayRefundParamChain.java
│   │   │               │           └── query
│   │   │               │               ├── PayRefundQueryChain.java
│   │   │               │               └── param
│   │   │               │                   └── PayRefundQueryParamChain.java
│   │   │               ├── api
│   │   │               │   └── AlipayApi.java
│   │   │               ├── config
│   │   │               │   ├── AliOssAutoConfiguration.java
│   │   │               │   ├── AliOssConfigProperties.java
│   │   │               │   ├── AliPayAutoConfiguration.java
│   │   │               │   └── AlipayConfigProperties.java
│   │   │               ├── controller
│   │   │               │   └── AliPayController.java
│   │   │               ├── dto
│   │   │               │   ├── AliPayGoodsDetailDTO.java
│   │   │               │   ├── AlipayOrderDTO.java
│   │   │               │   ├── CloseOrderDTO.java
│   │   │               │   ├── OssUploadDTO.java
│   │   │               │   ├── QueryBillDTO.java
│   │   │               │   ├── QueryOrderDTO.java
│   │   │               │   ├── QueryOrderResultDTO.java
│   │   │               │   ├── RefundAmountDTO.java
│   │   │               │   └── RefundQueryDTO.java
│   │   │               ├── oss
│   │   │               │   ├── AliOssBucketApi.java
│   │   │               │   ├── AliOssDownloadApi.java
│   │   │               │   ├── AliOssUploadApi.java
│   │   │               │   ├── AliOssUploadGoOnApi.java
│   │   │               │   ├── AliOssUtil.java
│   │   │               │   └── PostObjectSample.java
│   │   │               └── service
│   │   │                   └── AlipayService.java
│   │   └── resources
│   │       ├── META-INF
│   │       │   └── spring.factories
│   │       └── log
│   │           └── logback.xml
│   └── test
│       └── java


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

<!-- links -->

[your-project-path]:lunasaw/ali-spring-boot-starter

[contributors-shield]: https://img.shields.io/github/contributors/lunasaw/ali-spring-boot-starter.svg?style=flat-square

[contributors-url]: https://github.com/lunasaw/ali-spring-boot-starter/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/lunasaw/ali-spring-boot-starter.svg?style=flat-square

[forks-url]: https://github.com/lunasaw/ali-spring-boot-starter/network/members

[stars-shield]: https://img.shields.io/github/stars/lunasaw/ali-spring-boot-starter.svg?style=flat-square

[stars-url]: https://github.com/lunasaw/ali-spring-boot-starter/stargazers

[issues-shield]: https://img.shields.io/github/issues/lunasaw/ali-spring-boot-starter.svg?style=flat-square

[issues-url]: https://img.shields.io/github/issues/lunasaw/ali-spring-boot-starter.svg

[license-shield]: https://img.shields.io/github/license/lunasaw/ali-spring-boot-starter.svg?style=flat-square

[license-url]: https://github.com/lunasaw/ali-spring-boot-starter/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/ali-spring-boot-starter




