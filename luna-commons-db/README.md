

# luna-commons

luna-commons-db

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

   -  增加Redis模板二次封装,引用<Strig,Object>类型
   - 
   -  增加elasticsearch restful模板二次封装 crud操作
   - 
   -  增加mongoDB crud操作

## 目录

- [上手指南](#上手指南)
  - [开发前的配置要求](#开发前的配置要求)
  - [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [部署](#部署)

## 各 Module 介绍

| Module 名称                                                  | Module 介绍                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [luna-commons-cache](./luna-commons-cache) |  spingCache 整合redis 封装KeyGenerator生成策略                     |
| [luna-commons-elasticsearch-rest-highclient](./luna-commons-elasticsearch-rest-highclient) | elasticsearch 使用rest客户端操作,并封装Utils工具类                       |
| [luna-commons-elasticsearch-rest-template](./luna-commons-elasticsearch-rest-template) |           elasticsearch 使用spring  template操作             |
| [luna-commons-jpa](./luna-commons-jpa) |    jpa封装查询,一对多,多对多测试用例,提供Utils封装查询条件                       |
| [luna-commons-mongodb](./luna-commons-mongodb)     | 整合mongodb,封装操作Utils |
| [luna-commons-mybatis](./luna-commons-mybatis) | mybatis整合,配置druid数据源,多数据源配置,附generator代码生成,maven生成插件,[见pom文件](https://github.com/czy1024/luna-commons/blob/master/luna-commons-db/luna-commons-mybatis/pom.xml) ,附easyCode 代码生成模板 55dc0d237256b99d487fdd9de41ce017 7*24 过期请联系[issue](https://github.com/czy1024/luna-commons/issues) 或者 [见地址](https://hexo.iszychen.club/2020/09/13/easycode/)            |
| [luna-commons-redis](./luna-commons-redis) | redis 缓存处理 封装了Bound,Key,Ops 三种数据处理 |

### 上手指南


###### **安装步骤**


```sh
git clone https://github.com/czy1024/luna-commons.git
```

引入项目依赖

```xml
    <repositories>
        <repository>
            <id>luna-commons-mvn-repo-db</id>
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons-db/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>


    <dependency>
        <groupId>com.luna</groupId>
        <artifactId>luna-commons-db</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
在配置文件application.yml加入可选配置,并自行配置活动文件

```text
     spring:
       # 数据库
       datasource:
         driver-class-name: com.mysql.cj.jdbc.Driver
         url: jdbc:mysql://xxx:3307/luna-message?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
         username: root
         password: xxx
         # redis
       redis:
         host: 127.0.0.1 # Redis服务器地址
         database: 0 # Redis数据库索引（默认为0）
         port: 6379 # Redis服务器连接端口
         password: # Redis服务器连接密码（默认为空）
       pool:
         max-active: 200.0 # 连接池最大连接数（使用负值表示没有限制）
         max-idle: 10.0 # 连接池最大阻塞等待时间（使用负值表示没有限制）
         max-wait: -1.0 # 连接池中的最大空闲连接
         min-idle: 0.0 # 连接池中的最小空闲连接
       timeout: 1000.0 # 连接超时时间（毫秒）
     luna:
       data:
         elasticsearch:
           cluster-name: elasticsearch
           cluster-nodes: [localhost:9200]
           connect-timeout:
           socket-timeout:
           connection-request-timeout:
           index:
             number-of-replicas: 0
             number-of-shards: 3
           account:
             username: luna
             password: czy1024
```

引用示例
若采用SpringBoot构建项目可通过将第三方包中的RedisUtil配置文件注入Spring管理

需在properties或者yml配置文件中配置相应key

若非Spring项目可直接通过调用静态APi传入key和id进行调用

```java

package com.luna.springdemo.config;

import com.luna.common.spring.SpringUtils;
import com.luna.db.config.dbConfigValue;
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
    public RedisUtil redisUtil(){
        return new RedisUtil();
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
	private RedisUtil redisUtil;

	@Test
	public void atest() throws Exception {
	redisUtil.set("luna","luna,redis");
	}
}

```

### 文件目录说明
eg:

```
luna-commons-db
├── README.md
├── luna-commons-es
├── luna-commons-redis
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




