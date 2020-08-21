

# luna-commons

luna-commons-ali

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
  <a href="https://github.com/czy1024/luna-commons/">
    <img src="https://user-gold-cdn.xitu.io/2020/7/18/1736060af5cad8d0?w=128&h=128&f=png&s=2312" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">"完美的"开发工具</h3>
  <p align="center">
    市场上许多界面和工具的集合,例如ftp,httpd等文件与工具操作，包括但不限于图像处理、人脸识别等的api。让你免去寻找工具的烦恼
    <br />
    <a href="https://github.com/czy1024/luna-commons"><strong>探索本项目的文档 »</strong></a>
    <br />
    <br />
    <a href="">查看Demo</a>
    ·
    <a href="">报告Bug</a>
    ·
    <a href="https://github.com/czy1024/luna-commons/issues">提出新特性</a>
  </p>

</p>

## 日志
8.19 增加支付宝Api操作

8.21 增加阿里Oss存储Api调用
 
## 目录

- [上手指南](#上手指南)
  - [开发前的配置要求](#开发前的配置要求)
  - [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [部署](#部署)


### 上手指南


###### **安装步骤**

1. Get a free API Key at [https://account.aliyun.com](https://account.aliyun.com)
2. 找到config目录下的xxxConfigValue,application.properties
3. Clone the repo

```sh
git clone https://github.com/czy1024/luna-commons.git
```

引入项目依赖

```xml
    <repositories>
        <repository>
            <id>luna-commons-mvn-repo</id>
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons-ali/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>


    <dependency>
        <groupId>com.luna</groupId>
        <artifactId>luna-commons-ali</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
将com/luna/**/config 下的配置文件复制到自己的项目中
在配置文件application.properties加入可选配置

```text#阿里oss服务器
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
若采用SpringBoot构建项目可通过将第三方包中的AliConfigValue,AlipayConfigValue通过Spring配置文件注入Spring管理

需在properties或者yml配置文件中配置相应key

若非Spring项目可直接通过调用静态APi传入key和id进行调用

```java

/**
 * @Package: com.luna.springdemo.config
 * @ClassName: Config
 * @Author: luna
 * @CreateTime: 2020/8/6 21:23
 * @Description:
 */
@SpringBootConfiguration
public class Config {

    @Bean
    public AlipayConfigValue alipayConfigValue(){
        return new AlipayConfigValue();
    }

    @Bean
    public AliConfigValue aliConfigValue(){
        return new AliConfigValue();
    }
}



/**
 * @author Luna@win10
 * @date 2020/5/6 12:46
 */
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


### 文件目录说明
eg:

```
luna-commons-ali
├── README.md
├── src
│  ├── /config/
│  ├── /entity/
│  ├── /oss/
│  ├── /pay/
│──── /resource/
└── pom.xml

```

### 部署

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
[your-project-path]:czy1024/luna-commons
[contributors-shield]: https://img.shields.io/github/contributors/czy1024/luna-commons.svg?style=flat-square
[contributors-url]: https://github.com/czy1024/luna-commons/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/czy1024/luna-commons.svg?style=flat-square
[forks-url]: https://github.com/czy1024/luna-commons/network/members
[stars-shield]: https://img.shields.io/github/stars/czy1024/luna-commons.svg?style=flat-square
[stars-url]: https://github.com/czy1024/luna-commons/stargazers
[issues-shield]: https://img.shields.io/github/issues/czy1024/luna-commons.svg?style=flat-square
[issues-url]: https://img.shields.io/github/issues/czy1024/luna-commons.svg
[license-shield]: https://img.shields.io/github/license/czy1024/luna-commons.svg?style=flat-square
[license-url]: https://github.com/czy1024/luna-commons/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/luna-commons




