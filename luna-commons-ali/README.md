

# ProjectName

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
    <img src="images/logo.png" alt="Logo" width="80" height="80">
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
在配置文件application.properties加入可选配置

```text#阿里oss服务器
       luna.ali.ossId=xxx
       luna.ali.ossKey=xxx
       luna.ali.bucketName=xxx
       luna.ali.host=xxx
       # 支付宝
       # 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
       luna.alipay.appId=xxx
       # 商户私钥，您的PKCS8格式RSA2私钥
       luna.alipay.privateKey=xxx
       # 支付宝公钥,查看地址：https://openhome.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
       luna.alipay.publicKey=xxx
       # 服务器异步通知页面路径需http://格式的完整路径，不能加?id=123这类自定义参数
       luna.alipay.notifyUrl=xxx
       # 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
       luna.alipay.returnUrl=xxx
       # 签名方式
       luna.alipay.signType=RSA2
       # 支付宝网关
       luna.alipay.gatewayUrl=https://openapi.alipaydev.com/gateway.do
```

引用示例

```java
package com.luna.springdemo;


import com.luna.springdemo.baidu.BaiduConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Luna@win10
 * @date 2020/5/6 12:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaiduApiTest {

	@Resource
	BaiduConfig baiduConfig;

	@Test
	public void aTest() throws IOException {
		System.out.println(baiduConfig.getAppId());
	}

}

```

结果


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

暂无






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
[linkedin-url]: https://linkedin.com/in/shaojintian




