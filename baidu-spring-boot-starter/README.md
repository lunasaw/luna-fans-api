

# baidu-spring-boot-starter

[baidu-spring-boot-starter-baidu](https://github.com/lunasaw/baidu-spring-boot-starter)

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
  <a href="https://github.com/czy1024/baidu-spring-boot-starter/">
    <img src="https://tva1.sinaimg.cn/large/008i3skNgy1grloxhfbmkj30f00760sv.jpg" alt="Logo" width="540" height="258">
  </a>

  <h3 align="center">百度开放平台工具</h3>
  <p align="center">
    百度开放平台工具
    <br />
    <a href="https://github.com/czy1024/baidu-spring-boot-starter"><strong>探索本项目的文档 »</strong></a>
    <br />
    <br />
    <a href="">查看Demo</a>
    ·
    <a href="">报告Bug</a>
    ·
    <a href="https://github.com/czy1024/baidu-spring-boot-starter/issues">提出新特性</a>
  </p>

</p>

## 日志
 增加人员组人脸识别相关接口

 增加语音识别合成相关接口

 增加百度人脸识别,卡证审核等Api请求封装

 增加百度OcrApi接口

 增加百度身体状态检测

 增加百度身份证审核
 
## 目录

- [文档](#文档)
- [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)

### 文档

[文档链接](https://lunasaw.github.io/baidu-spring-boot-starter/)


###### **安装步骤**



引入项目依赖

```xml

    <dependency>
        <groupId>io.github.lunasaw</groupId>
        <artifactId>baidu-spring-boot-starter-baidu</artifactId>
        <version>${last.version}</version>
    </dependency>
```
在配置文件 application.yml 加入可选配置

```text
       # 百度API
luna:
  baidu:
    // 生成地址https://console.bce.baidu.com/
    appId: xxx
    appKey: xxxx
    secretKey: xxx
    baiduKey: xxx
    jsKey: xxx
    projectId: xxx
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


### 文件目录说明
eg:

```
├── LICENSE
├── README.md
├── baidu-spring-boot-starter.iml
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── luna
│   │   │           └── baidu
│   │   │               ├── api
│   │   │               │   ├── BaiduAddressApi.java
│   │   │               │   ├── BaiduApiConstant.java
│   │   │               │   ├── BaiduBodyApi.java
│   │   │               │   ├── BaiduCreationApi.java
│   │   │               │   ├── BaiduFaceApi.java
│   │   │               │   ├── BaiduGoodsIdentifyApi.java
│   │   │               │   ├── BaiduOcrApi.java
│   │   │               │   ├── BaiduTextApi.java
│   │   │               │   ├── BaiduUserFaceApi.java
│   │   │               │   └── BaiduVoiceApi.java
│   │   │               ├── config
│   │   │               │   ├── BaiduAutoConfiguration.java
│   │   │               │   ├── BaiduKeyGenerate.java
│   │   │               │   └── BaiduProperties.java
│   │   │               ├── dto
│   │   │               │   ├── body
│   │   │               │   │   ├── BodyAttributesDTO.java
│   │   │               │   │   ├── BodyCheckDTO.java
│   │   │               │   │   └── BodyScoreNameDTO.java
│   │   │               │   ├── face
│   │   │               │   │   ├── BaiduUserFaceApi.java
│   │   │               │   │   ├── FaceCheckResultDTO.java
│   │   │               │   │   ├── FaceLiveResultDTO.java
│   │   │               │   │   ├── FaceMatchResultDTO.java
│   │   │               │   │   ├── FaceResultDTO.java
│   │   │               │   │   ├── IdCardAllInfoDTO.java
│   │   │               │   │   ├── IdCardCheckResultDTO.java
│   │   │               │   │   ├── IdCardInfoDTO.java
│   │   │               │   │   ├── UserFaceListResultDTO.java
│   │   │               │   │   ├── UserFaceResultDTO.java
│   │   │               │   │   ├── UserInfoListDTO.java
│   │   │               │   │   └── UserInfoResultDTO.java
│   │   │               │   ├── goods
│   │   │               │   │   ├── BaiKeInfoDTO.java
│   │   │               │   │   └── GoodsInfoDTO.java
│   │   │               │   ├── location
│   │   │               │   │   └── LocationDO.java
│   │   │               │   ├── map
│   │   │               │   │   ├── ip2address
│   │   │               │   │   │   ├── AddressContentDTO.java
│   │   │               │   │   │   ├── AddressDetailDTO.java
│   │   │               │   │   │   ├── AddressResultDTO.java
│   │   │               │   │   │   └── LocationDTO.java
│   │   │               │   │   └── weather
│   │   │               │   │       ├── WeatherForecastDTO.java
│   │   │               │   │       ├── WeatherLocationDTO.java
│   │   │               │   │       ├── WeatherNowDTO.java
│   │   │               │   │       └── WeatherResultDTO.java
│   │   │               │   ├── text
│   │   │               │   │   ├── TextSimilarDTO.java
│   │   │               │   │   ├── TextSimilarResultDTO.java
│   │   │               │   │   ├── TextSimilarityDTO.java
│   │   │               │   │   └── TextSimnetResultDTO.java
│   │   │               │   ├── voice
│   │   │               │   │   └── VoiceWriteResultDTO.java
│   │   │               │   ├── word
│   │   │               │   │   ├── BodyDTO.java
│   │   │               │   │   ├── FaceDTO.java
│   │   │               │   │   └── WordDTO.java
│   │   │               │   └── write
│   │   │               │       ├── CompositionDTO.java
│   │   │               │       ├── EventContextDTO.java
│   │   │               │       ├── EventKeyDTO.java
│   │   │               │       ├── HotEventContentDTO.java
│   │   │               │       ├── HotEventDTO.java
│   │   │               │       ├── VeinDTO.java
│   │   │               │       └── WriterResultCheckDTO.java
│   │   │               └── req
│   │   │                   ├── VoiceCheckReq.java
│   │   │                   ├── VoiceSynthesisReq.java
│   │   │                   └── face
│   │   │                       └── FaceLiveReq.java
│   │   └── resources
│   │       ├── META-INF
│   │       │   └── spring.factories
│   │       ├── application-pro.yml
│   │       └── log
│   │           └── logback.xml
│   └── test
│       └── java


```



<!-- links -->
[your-project-path]:czy1024/baidu-spring-boot-starter
[contributors-shield]: https://img.shields.io/github/contributors/czy1024/baidu-spring-boot-starter.svg?style=flat-square
[contributors-url]: https://github.com/czy1024/baidu-spring-boot-starter/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/czy1024/baidu-spring-boot-starter.svg?style=flat-square
[forks-url]: https://github.com/czy1024/baidu-spring-boot-starter/network/members
[stars-shield]: https://img.shields.io/github/stars/czy1024/baidu-spring-boot-starter.svg?style=flat-square
[stars-url]: https://github.com/czy1024/baidu-spring-boot-starter/stargazers
[issues-shield]: https://img.shields.io/github/issues/czy1024/baidu-spring-boot-starter.svg?style=flat-square
[issues-url]: https://img.shields.io/github/issues/czy1024/baidu-spring-boot-starter.svg
[license-shield]: https://img.shields.io/github/license/czy1024/baidu-spring-boot-starter.svg?style=flat-square
[license-url]: https://github.com/czy1024/baidu-spring-boot-starter/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/baidu-spring-boot-starter




