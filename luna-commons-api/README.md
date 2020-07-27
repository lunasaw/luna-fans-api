

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

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>luna-commons</artifactId>
        <groupId>com.luna</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>luna-commons-api</artifactId>

    <repositories>
        <repository>
            <id>luna-commons-mvn-repo-api</id>
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons-api/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.luna</groupId>
            <artifactId>luna-commons-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>

            <plugin>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>2.8.1</version>
                <configuration>
                    <altDeploymentRepository>internal.repo::default::file://${project.build.directory}/mvn-repo
                    </altDeploymentRepository>
                </configuration>
            </plugin>

            <plugin>
                <groupId>com.github.github</groupId>
                <artifactId>site-maven-plugin</artifactId>
                <version>0.12</version>
                <configuration>
                    <message>Maven artifacts for ${project.version}</message>  <!-- git commit message -->
                    <noJekyll>true</noJekyll>                                  <!-- disable webpage processing -->
                    <outputDirectory>${project.build.directory}/mvn-repo
                    </outputDirectory> <!-- matches distribution management repository url above -->
                    <branch>refs/heads/mvn-repo-luna-commons-api
                    </branch>                       <!-- remote branch name -->
                    <includes>
                        <include>**/*</include>
                    </includes>
                    <repositoryName>luna-commons</repositoryName>      <!-- github repo name -->
                    <repositoryOwner>czy1024</repositoryOwner>    <!-- github username  -->
                </configuration>
                <executions>
                    <!-- run site-maven-plugin's 'site' target as part of the build's normal 'deploy' phase -->
                    <execution>
                        <goals>
                            <goal>site</goal>
                        </goals>
                        <phase>deploy</phase>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

```


[引用示例见](https://github.com/czy1024/luna-commons/tree/master/luna-commons-baidu)


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

```text
<dependency>
  <groupId>com.luna.commons</groupId>
  <artifactId>luna-commons-api</artifactId>
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




