# tencent-spring-boot-starter

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/lunasaw/tencent-spring-boot-starter/">
    <img src="https://raw.githubusercontent.com/lunasaw/luna-fans-api/master/tencent-spring-boot-starter/logo/tencent-logo-2.jpeg" alt="Logo" width="800" height="250">
  </a>
</p>

[Api文档链接](https://lunasaw.github.io/luna-fans-api/tencent-spring-boot-starter/apidocs/){:target="_blank"}

## 日志

- 增加语音识别相关接口，包括语音合成，实时识别等
- 增加微信支付Api接口
- 超时30分钟自动关闭订单
- Mq队列处理
- 增加腾讯地图Api接口
- 增加腾讯人脸识别等Api接口


直接使用引入项目依赖

```xml

<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>tencent-spring-boot-starter</artifactId>
    <version>${last.version}</version>
</dependency>
```

### 文档

[文档链接](https://lunasaw.github.io/tencent-spring-boot-starter/)



在配置文件application.properties加入可选配置

```text
spring:
  tencent:
    # 腾讯api
    secret-id: xxx
    secret-key: xxx
    # 腾讯市场api
    sky-eye-secret-id: xxx
    sky-eye-secret-key: xxx

    # 腾讯地图api
    map-key: xxx

```

引用示例 若采用SpringBoot构建项目可通过将第三方包中的TencentConfigValue,TencentPayConfigValue通过Spring配置文件注入Spring管理

需在properties或者yml配置文件中配置相应key

若非Spring项目可直接通过调用静态APi传入key和id进行调用

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class TencentApiTest {

    @Autowired
    private TencentPayMqConfigValue tencentPayMqConfigValue;


    @Autowired
    private TencentConfigValue tencentConfigValue;

    @Test
    public void atest() {
        System.out.println(tencentPayMqConfigValue.getExchange());
        System.out.println(tencentConfigValue.getSecretid());
        JSONObject jsonObject = TencentMarketApi.checkIdByLuna(tencentConfigValue.getSkyEyeSecretid(), tencentConfigValue.getSkyEyeSecretkey(), "陈章月", "xxxx");
        System.out.println(JSON.toJSONString(jsonObject));
    }
}

```

email Keyluna@126.com &ensp; qq:1173288254

*您也可以在贡献者名单中参看所有参与该项目的开发者。*

### 版权说明

该项目签署了MIT 授权许可，详情请参阅 [LICENSE.txt](https://github.com/lunasaw/luna-commons/blob/master/LICENSE)

### 鸣谢[]()

- [tencent]()
- [rabbitmq]()


