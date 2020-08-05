

# luna-commons

luna-commons-common

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
    <img src="https://user-gold-cdn.xitu.io/2020/7/18/1736060af5cad8d0?w=128&h=128&f=png&s=2312" alt="Logo" width="80" height="80">
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
== 增加BufferedImage的画图操作,可结合百度腾讯APi返回值实现人脸面部勾画,或者文字识别勾画
 
## 目录

- [上手指南](#上手指南)
  - [开发前的配置要求](#开发前的配置要求)
  - [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [部署](#部署)


### 上手指南


###### **安装步骤**

```sh
git clone https://github.com/czy1024/luna-commons.git
```

引入项目依赖

```xml
   <repositories>
           <repository>
               <id>luna-commons-mvn-repo-common</id>
               <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons-common/</url>
               <snapshots>
                   <enabled>true</enabled>
                   <updatePolicy>always</updatePolicy>
               </snapshots>
           </repository>
       </repositories>

    <dependency>
        <groupId>com.luna</groupId>
        <artifactId>luna-commons-commons</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```
可直接引用api文件目录下的静态方法接口

##pom文件,包含依赖

```xml
    <dependencies>
            <dependency>
                <groupId>cn.hutool</groupId>
                <artifactId>hutool-all</artifactId>
                <version>5.3.10</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp -->
            <dependency>
                <groupId>com.squareup.okhttp3</groupId>
                <artifactId>okhttp</artifactId>
                <version>4.8.0</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.squareup.okio/okio -->
            <dependency>
                <groupId>com.squareup.okio</groupId>
                <artifactId>okio</artifactId>
                <version>2.7.0</version>
            </dependency>
            <!-- Mysql驱动包 -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
            </dependency>
            <!-- mybatis整合spring -->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-text -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-text</artifactId>
                <version>1.8</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-collections4 -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-collections4</artifactId>
                <version>4.3</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/commons-validator/commons-validator -->
            <dependency>
                <groupId>commons-validator</groupId>
                <artifactId>commons-validator</artifactId>
                <version>1.6</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>3.9</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
            <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpclient</artifactId>
                <version>4.5.11</version>
            </dependency>
            <dependency>
                <groupId>org.apache.httpcomponents</groupId>
                <artifactId>httpmime</artifactId>
                <version>4.5.11</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
            <dependency>
                <groupId>commons-io</groupId>
                <artifactId>commons-io</artifactId>
                <version>2.6</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/commons-net/commons-net -->
            <dependency>
                <groupId>commons-net</groupId>
                <artifactId>commons-net</artifactId>
                <version>3.6</version>
            </dependency>
            <!-- JSON工具类 -->
            <dependency>
                <groupId>com.fasterxml.jackson.core</groupId>
                <artifactId>jackson-databind</artifactId>
                <version>2.10.3</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>1.2.72</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>27.0.1-jre</version>
            </dependency>
            <!-- https://mvnrepository.com/artifact/junit/junit -->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.13</version>
            </dependency>
        </dependencies>
```


[引用示例见](https://github.com/czy1024/luna-commons/tree/master/luna-commons-baidu)


### 文件目录说明
eg:

```


luna-commons-common
├── README.md
├── src
│  ├── /api/
│  ├── /api/smms
│──── /resource/
└── pom.xml
```


### 部署

引入依赖

```text
<dependency>
  <groupId>com.luna.commons</groupId>
  <artifactId>luna-commons-common</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```
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




