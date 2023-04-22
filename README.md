# luna-fans-api

luna-fans-api-fans 基于各个开放平台的api整合优化，大部分采用原生http调用。

<!-- PROJECT SHIELDS -->

[![Maven Central](https://img.shields.io/maven-central/v/io.github.lunasaw/luna-fans-api)](https://mvnrepository.com/artifact/io.github.lunasaw/luna-fans-api)
[![GitHub license](https://img.shields.io/badge/MIT_License-blue.svg)](https://raw.githubusercontent.com/lunasaw/luna-fans-api/master/LICENSE)
[![Build Status](https://github.com/lunasaw/luna-fans-api/actions/workflows/publish-javadoc.yml/badge.svg?branch=master)](https://github.com/lunasaw/luna-fans-api/actions)

<!-- PROJECT LOGO -->
<br />

<div align="center">
  <a href="https://github.com/lunasaw/luna-fans-api/">
    <img src="https://i.loli.net/2020/07/28/5MzIVArBZyp8NgX.png" alt="Logo" width="80" height="80">
  </a>
</div>
<h3 align="center">Api开放平台工具</h3>
  <div align="center">
    <a href="https://github.com/lunasaw/luna-fans-api"><strong>探索本项目的文档 »</strong></a>
    <br />
    <a href="https://github.com/lunasaw/luna-fans-api/tree/master/test-luna-fans-api">查看Demo</a>
    ·
    <a href="https://github.com/lunasaw/luna-fans-api/issues">报告Bug</a>
    ·
    <a href="https://github.com/lunasaw/luna-fans-api/issues">提出新特性</a>
 </div>

## 日志

- 2023.0422 升级httpClient5.2 支持更优秀的异步请求方式
- 2023.0414 自助github action Java doc打包📦部署
- 2023.04.09 升级SpringBoot 2.7.10, 精简maven, 拆分Tencent的微信支付模块，和aliPay的支付模块，新增支付测试controller
- 2022.10.03 增加阿里Oss平台，升级SpringBoot 2.7.0

- 2022-05-25 Smms图床，邮件发送，百度Api开放平台，腾讯Api开放平台，微信支付，阿里Api开放平台，阿里云oss，阿里pay

###### **使用步骤**

```xml
<dependency>
    <groupId>io.github.lunasaw</groupId>
    <artifactId>luna-fans-api-fans</artifactId>
    <version>${last.version}</version>
</dependency>
```

在配置文件application.properties加入可选配置，具体使用见各个模块

| items          | items-src                                                    | items Guide                     |
| -------------- | ------------------------------------------------------------ | ------------------------------- |
| ali            | [ali](https://lunasaw.github.io/luna-fans-api/ali-spring-boot-starter/) | ali-spring-boot-starter         |
| api            | [api](https://lunasaw.github.io/luna-fans-api//api-spring-boot-starter) | api-spring-boot-starter         |
| api-pay        | [ali-pay](https://lunasaw.github.io/luna-fans-api//ali-pay-spring-boot-starter) | ali-pay-spring-boot-starter     |
| badiu          | [baidu](https://lunasaw.github.io/luna-fans-api//baidu-spring-boot-starter) | baidu-spring-boot-starter       |
| tencent        | [tencent](https://lunasaw.github.io/luna-fans-api/tencent-spring-boot-starter) | tencent-spring-boot-starter     |
| tencent-wechat | [tencent-pay](https://lunasaw.github.io/luna-fans-api//tencent-pay-spring-boot-starter) | tencent-pay-spring-boot-starter |
| test           | [test](https://lunasaw.github.io/luna-fans-api//test-luna-fans-api) | Test-model                      |

### 使用示例

采用SpringBoot构建项目可通过将第三方包中的，通过Spring配置文件注入Spring管理，根据配置数据初始化项目, 使用配置文件的token调用api里的静态方法完成调用.



