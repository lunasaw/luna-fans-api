# luna-fans-api

luna-fans-api-fans 基于各个开放平台的api整合优化，大部分采用原生http调用。

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
  <a href="https://github.com/lunasaw/luna-fans-api/">
    <img src="https://i.loli.net/2020/07/28/5MzIVArBZyp8NgX.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Api开放平台工具</h3>
  <p align="center">
    <a href="https://github.com/lunasaw/luna-fans-api"><strong>探索本项目的文档 »</strong></a>
    <br />
    <a href="https://github.com/lunasaw/luna-fans-api/tree/master/test-luna-fans-api">查看Demo</a>
    ·
    <a href="https://github.com/lunasaw/luna-fans-api/issues">报告Bug</a>
    ·
    <a href="https://github.com/lunasaw/luna-fans-api/issues">提出新特性</a>
</p>

## 日志

- 2023.04.09 升级SpringBoot 2.7.10, 精简maven，升级到3.1.2, 拆分Tencent的微信支付模块，和aliPay的支付模块，新增支付测试controller
- 2022.10.03 增加阿里Oss平台，升级SpringBoot 2.7.0

- 2022-05-25 Smms图床，邮件发送，百度Api开放平台，腾讯Api开放平台，微信支付，阿里Api开放平台，阿里云oss，阿里pay

###### **使用步骤**


```xml
全部引入，或者引入单个模块
<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>luna-fans-api-fans</artifactId>
    <version>${last.version}</version>
</dependency>
```

在配置文件application.properties加入可选配置，具体使用见各个模块

| items          | items-src                                        | items Guide                     |
|----------------|--------------------------------------------------|---------------------------------|
| ali            | [ali](./ali-spring-boot-starter)                 | ali-spring-boot-starter         |
| api            | [api](./api-spring-boot-starter)                 | api-spring-boot-starter         |
| api-pay        | [ali-pay](./ali-pay-spring-boot-starter)         | ali-pay-spring-boot-starter     |
| badiu          | [baidu](./baidu-spring-boot-starter)             | baidu-spring-boot-starter       |
| tencent        | [tencent](./tencent-spring-boot-starter)         | tencent-spring-boot-starter     |
| tencent-wechat | [tencent-pay](./tencent-pay-spring-boot-starter) | tencent-pay-spring-boot-starter |
| test           | [test](./test-luna-fans-api)                     | 测试工具模块                          |

### 使用示例

采用SpringBoot构建项目可通过将第三方包中的，通过Spring配置文件注入Spring管理，根据配置数据初始化项目, 调用api里的静态方法完成调用

<!-- links -->

[your-project-path]:lunasaw/luna-fans-api

[contributors-shield]: https://img.shields.io/github/contributors/lunasaw/luna-fans-api.svg?style=flat-square

[contributors-url]: https://github.com/lunasaw/luna-fans-api/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/lunasaw/luna-fans-api.svg?style=flat-square

[forks-url]: https://github.com/lunasaw/luna-fans-api/network/members

[stars-shield]: https://img.shields.io/github/stars/lunasaw/luna-fans-api.svg?style=flat-square

[stars-url]: https://github.com/lunasaw/luna-fans-api/stargazers

[issues-shield]: https://img.shields.io/github/issues/lunasaw/luna-fans-api.svg?style=flat-square

[issues-url]: https://img.shields.io/github/issues/lunasaw/luna-fans-api.svg

[license-shield]: https://img.shields.io/github/license/lunasaw/luna-fans-api.svg?style=flat-square

[license-url]: https://github.com/lunasaw/luna-fans-api/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/luna-fans-api




