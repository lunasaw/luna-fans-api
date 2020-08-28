

# luna-commons

luna-commons-api

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

## 日志 使用见部署
增加Sm.ms图床Api

增加学小易网课答案Api

增加JD搜索模拟
    jsoup爬取页面信息,放入elasticsearch,前后端分离模拟搜索页面
 
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
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons-api/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>


    <dependency>
        <groupId>com.luna</groupId>
        <artifactId>luna-commons-api</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
可直接引用api文件目录下的静态方法接口

##pom文件

```yaml

luna:
  smms:
    authorizationCode: xxx
    password: xxx
    username: xxx

```


### 文件目录说明
eg:

```


luna-commons-api
├── README.md
├── src
│  ├── /api/
│  ├── /api/smms
│──── /resource/
└── pom.xml
```


### 部署

引入依赖
```java

若采用SpringBoot构建项目可通过将第三方包中的 SmMsConfigValue通过Spring配置文件注入Spring管理


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
    public SmMsConfigValue smMsConfigValue(){
        return new SmMsConfigValue();
    }

}


/**
 * @author Luna@win10
 * @date 2020/5/6 12:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class messageApiTest {

	@Resource
	SmMsConfigValue smMsConfigValue;

    // 测试是否拿到配置文件的数据
	@Test
	public void aTest() throws IOException {
		System.out.println(smMsConfigValue.getAuthorizationCode());
	}

}

```

[结果即刻得到配置数据,进而调用api里的静态方法完成调用]()

[使用部署](https://github.com/czy1024/luna-commons/wiki/sm.ms-api-todo)

#### sm.ms 包括用户和图片两个操作

[用户相关](https://github.com/czy1024/luna-commons/wiki/sm.ms-api-User)
[图片相关](https://github.com/czy1024/luna-commons/wiki/sm.ms-api-Image)

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




