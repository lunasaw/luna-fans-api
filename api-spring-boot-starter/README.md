# api-spring-boot-starter

api-spring-boot-starter

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
  <a href="https://github.com/lunasaw/fans-spring-boot-starter/">
    <img src="https://i.loli.net/2020/07/28/5MzIVArBZyp8NgX.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Api开放平台工具</h3>
  <p align="center">
    Api开放平台工具
    <br />
    <a href="https://github.com/lunasaw/fans-spring-boot-starter"><strong>探索本项目的文档 »</strong></a>
    <br />
    <br />
    <a href="">查看Demo</a>
    ·
    <a href="">报告Bug</a>
    ·
    <a href="https://github.com/lunasaw/fans-spring-boot-starter/issues">提出新特性</a>
  </p>

</p>

## 日志

增加Smms图床

## 目录

- [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [部署](#部署)

###### **安装步骤**

引入项目依赖

```xml

<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>api-spring-boot-starter-fans</artifactId>
    <version>2.2.7-RELEASE</version>
</dependency>
```

在配置文件application.properties加入可选配置

```text
       
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

[结果即刻得到配置数据,进而调用api里的静态方法完成调用]()

### 文件目录说明

eg:

```
./
├── java
│   └── com
│       └── luna
│           └── api
│               ├── config
│               │   └── ApiAutoConfiguration.java
│               └── smms
│                   ├── api
│                   │   ├── ImageApiFromString.java
│                   │   ├── UserApiFromFile.java
│                   │   └── UserApiFromString.java
│                   ├── config
│                   │   └── SmMsConfigValue.java
│                   ├── constant
│                   │   └── SmMsConstant.java
│                   └── dto
│                       ├── UploadResultDTO.java
│                       └── UserProfileDTO.java
└── resources
    ├── META-INF
    │   └── spring.factories
    ├── conf.json
    └── log
        └── logback.xml

```

### 部署

暂无


<!-- links -->

[your-project-path]:lunasaw/fans-spring-boot-starter

[contributors-shield]: https://img.shields.io/github/contributors/lunasaw/fans-spring-boot-starter.svg?style=flat-square

[contributors-url]: https://github.com/lunasaw/fans-spring-boot-starter/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/lunasaw/fans-spring-boot-starter.svg?style=flat-square

[forks-url]: https://github.com/lunasaw/fans-spring-boot-starter/network/members

[stars-shield]: https://img.shields.io/github/stars/lunasaw/fans-spring-boot-starter.svg?style=flat-square

[stars-url]: https://github.com/lunasaw/fans-spring-boot-starter/stargazers

[issues-shield]: https://img.shields.io/github/issues/lunasaw/fans-spring-boot-starter.svg?style=flat-square

[issues-url]: https://img.shields.io/github/issues/lunasaw/fans-spring-boot-starter.svg

[license-shield]: https://img.shields.io/github/license/lunasaw/fans-spring-boot-starter.svg?style=flat-square

[license-url]: https://github.com/lunasaw/fans-spring-boot-starter/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/fans-spring-boot-starter




