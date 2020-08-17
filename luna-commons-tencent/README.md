

# luna-commons

luna-commons-tencent

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
    <img src="https://i.loli.net/2020/07/28/5MzIVArBZyp8NgX.png" alt="Logo" width="80" height="80">
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
增加微信支付Api接口
   - 超时30分钟自动关闭订单
   - Mq队列处理


增加腾讯地图Api接口

增加腾讯人脸识别等Api接口

## 目录

- [上手指南](#上手指南)
  - [开发前的配置要求](#开发前的配置要求)
  - [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [部署](#部署)


### 上手指南


###### **安装步骤**

1. Get a free API Key at [https://account.aliyun.com](https://account.aliyun.com)
2. 找到config目录下的xxxConfigValue,TencentPayConfigValue
3. Clone the repo

```sh
git clone https://github.com/czy1024/luna-commons.git
```

引入项目依赖

```xml
    <repositories>
        <repository>
            <id>luna-commons-mvn-repo-tencent</id>
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons-tencent/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>


    <dependency>
        <groupId>com.luna</groupId>
        <artifactId>luna-commons-tencent</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
在配置文件application.properties加入可选配置

```text
      luna:
        ten:
          # 腾讯地图api
          mapKey: xxx
          # 腾讯api
          secretId: xxx
          secretKey: xxx
          # 腾讯市场api
          skyEyeSecretid: xxx
          skyEyeSecretkey: xxx
      
        #微信支付信息配置
        weixin:
          appid: xxx
          partner: xxx
          partnerkey: xxx
          notifyurl: xxx
      spring:
        redis:
          host: xxx
          port: 6379
      
      server:
        port: 8080
      



```

引用示例
若采用SpringBoot构建项目可通过将第三方包中的TencentConfigValue,TencentPayConfigValue通过Spring配置文件注入Spring管理

需在properties或者yml配置文件中配置相应key

若非Spring项目可直接通过调用静态APi传入key和id进行调用

```java

package com.luna.springdemo.config;

import com.luna.common.spring.SpringUtils;
import com.luna.tencent.config.TencentConfigValue;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

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
    public SpringUtils springUtils(){
        return new SpringUtils();
    }

    @Bean
    public TencentConfigValue tencentConfigValue(){
        return new TencentConfigValue();
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
	private TencentConfigValue tencentConfigValue;

	@Test
	public void atest() throws Exception {
		tencentConfigValue.getSkyEyeSecretid();

		TencentFaceApi.faceLiveCheck(tencentConfigValue.getSecretid(),tencentConfigValue.getSecretKey(), Base64Util.encodeBase64(ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Saved Pictures\\girl.png")));
	}
}

```

结果
```java
{"Response":{"Score":0,"RequestId":"c9611df7-9efb-41c0-8f61-dc18604d9388","IsLiveness":false,"FaceModelVersion":"2.0"}}
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
[linkedin-url]: https://linkedin.com/in/luna-commons




