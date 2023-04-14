# api-spring-boot-starter

<p align="center">
  <a href="https://github.com/lunasaw/luna-fans-api/">
    <img src="https://i.loli.net/2020/07/28/5MzIVArBZyp8NgX.png" alt="Logo" width="80" height="80">
  </a>
</p>

[Api文档链接](https://lunasaw.github.io/luna-fans-api/api-spring-boot-starter/apidocs/){:target="_blank"}

## 日志

2023-04-09 增加邮件发送

增加Smms图床

#### 使用

引入项目依赖

```xml

<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>api-spring-boot-starter</artifactId>
    <version>${last.version}</version>
</dependency>
```

在配置文件application.properties加入可选配置

```text
# https://doc.sm.ms/
spring:
  smms:
    enable: true
    authorization-code: xxx
    password: xx!xx
    username: luna_nov

  mail:
    default-encoding: UTF-8
    host: smtp.qq.com
    nick: LUNA
    password: xxx
    port: 465
    properties:
      mail:
        debug: false
        smtp:
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
    username: 1173x@qq.com
```

引用示例

```java

若采用SpringBoot构建项目可通过将第三方包中的 通过Spring配置文件注入Spring管理

@SpringBootTest
@RunWith(SpringRunner.class)
public class AliApiTest {
    @Autowired
    private SmMsConfigValue smMsConfigValue;

    @Test
    public void atest() throws Exception {
        List<UploadResultDTO> allHistory = ImageApiFromString.getAllHistory(smMsConfigValue.getAuthorizationCode());
        System.out.println(JSON.toJSONString(allHistory));
    }
}


```



