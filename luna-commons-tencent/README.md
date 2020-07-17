

# ProjectName

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

```text#阿里oss服务器
       # 腾讯APi
       luna.tencent.secretId=xxx
       luna.tencent.secretKey=xxx
       # 腾讯云市场APi
       # 天眼身份认证
       luna.tencent.skyEyeSecretid=xxx
       luna.tencent.skyEyeSecretkey=xxx
       # 腾讯地图
       luna.tencent.mapKey=xxx
       # redis
       spring.redis.host=xxx
       spring.redis.port=6379

```

引用示例

```java


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

		TencentFaceApi.faceLiveCheck(tencentConfigValue.getSecretid(),tencentConfigValue.getSecretKey(), Base64Util.encodeBase64String(ImageUtils.getBytes("C:\\Users\\improve\\Pictures\\Saved Pictures\\girl.png")));
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
[linkedin-url]: https://linkedin.com/in/shaojintian




