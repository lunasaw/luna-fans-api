# baidu-spring-boot-starter

<!-- PROJECT LOGO -->
<br />

<p align="center">
  <a href="https://github.com/czy1024/baidu-spring-boot-starter/">
    <img src="https://raw.githubusercontent.com/lunasaw/luna-fans-api/master/baidu-spring-boot-starter/logo/baidu-logo.png" alt="Logo" width="300" height="150">
  </a>
</p>

[Api文档链接](https://lunasaw.github.io/luna-fans-api/baidu-spring-boot-starter/apidocs/){:target="_blank"}

### 日志

- 20230415 增加长文本语音合成
- 增加人员组人脸识别相关接口
- 增加语音识别合成相关接口
- 增加百度人脸识别,卡证审核等Api请求封装
- 增加百度OcrApi接口
- 增加百度身体状态检测
- 增加百度身份证审核


### 文档

[文档链接](https://lunasaw.github.io/luna-fans-api/javadocs/)

引入项目依赖

```xml

    <dependency>
        <groupId>io.github.lunasaw</groupId>
        <artifactId>baidu-spring-boot-starter</artifactId>
        <version>${last.version}</version>
    </dependency>
```
在配置文件 application.yml 加入可选配置

```text
# 百度API
// 生成地址https://console.bce.baidu.com/
spring:
  baidu:
    # 百度appId
    app-id: xxx
    # 百度appKey
    app-key: xxxx
    secret-key: xxx
    enable: false
```

引用示例

```java

若采用SpringBoot构建项目可通过将第三方包中的BaiduProperties,BaiduKeyGenerate通过Spring配置文件注入Spring管理

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaiduApiTest {
    @Autowired
    private BaiduKeyGenerate baiduKeyGenerate;

    @Test
    public void atest() throws Exception {
        baiduKeyGenerate.getAuth();
    }
}


```
结果即刻得到配置数据,进而调用api里的静态方法完成调用




