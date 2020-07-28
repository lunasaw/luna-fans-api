

# luna-commons

luna-commons

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
    <img src="https://img01.sogoucdn.com/app/a/100520146/4998a732d776b4b86412296cddec7b49" alt="Logo" width="80" height="80">
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
- [使用到的框架](#使用到的框架)
- [贡献者](#贡献者)
  - [如何参与开源项目](#如何参与开源项目)
- [作者](#作者)
- [鸣谢](#鸣谢)

### 上手指南


###### **安装步骤**

1. Get a free API Key at [https://ai.baidu.com/sdk#ocr](https://ai.baidu.com/sdk#ocr)
2. Get a free API Key at [https://cloud.tencent.com](https://cloud.tencent.com)
3. Get a free API Key at [https://account.aliyun.com](https://account.aliyun.com)
4. 找到config目录下的xxxConfigValue,application.properties
5. Clone the repo

```sh
git clone https://github.com/czy1024/luna-commons.git
```

### 文件目录说明
eg:

```
luna-commons-loc
├── ARCHITECTURE.md
├── LICENSE.txt
├── .gitignore
├── README.md
├── luna-commons-ali
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-api
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-baidu
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-common
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-file
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-media
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-message
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
├── luna-commons-tencent
│  ├── pom.xml
│  │  ├── /config/
│  │  └── /api/
│  └──── /resource/
└── pom.xml

```
## 各 Module 介绍

| Module 名称                                                  | Module 介绍                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [luna-commons-ali](./luna-commons-ali) | 关于阿里oss和alpay支付宝的使用                               |
| [luna-commons-common](./luna-commons-common) | 基础工具包,加密字符等操作,http网络操作                            |
| [luna-commons-api](./luna-commons-api) | api集合模块,现阶段为学小易查题目接口,[sm.ms 图床api](https://github.com/czy1024/luna-commons/wiki/sm.ms-api-todo)                           |
| [luna-commons-baidu](./luna-commons-baidu)     | 百度Api集合,包含人脸识别,人证审核百度地图等api封装 |
| [luna-commons-file](./luna-commons-file) | 文件处理工具集合,包括ftp,httpd,fastdfs等文件操作 |
| [luna-commons-media](./luna-commons-media) | 媒体流处理工具,包含ffmpeg工具封装,JavaCv图像处理 |
| [luna-commons-message](./luna-commons-message)       |  消息发送,短信邮件消息发送,有html模板附赠 [HTML邮件模板](https://github.com/czy1024/luna-commons/blob/master/luna-commons-message/src/main/resources/static/luna-message.html)
| [luna-commons-tencent](./luna-commons-tencent) | 腾讯Api集合,包含人脸识别,人证审核腾讯地图等api封装 |



### 开发的架构 

请阅读[ARCHITECTURE.md](https://github.com/czy1024/luna-commons/blob/master/ARCHITECTURE.md) 查阅为该项目的架构。

### 部署

请前往各个小项目查看

### 使用到的框架

- [springboot](https://spring.io/)

### 贡献者

请阅读**CONTRIBUTING.md** 查阅为该项目做出贡献的开发者。

#### 如何参与开源项目

贡献使开源社区成为一个学习、激励和创造的绝佳场所。你所作的任何贡献都是**非常感谢**的。


1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



### 版本控制

该项目使用Git进行版本管理。您可以在repository参看当前可用版本。

### 作者

luna

email Keyluna@126.com  &ensp; qq:1173288254

 *您也可以在贡献者名单中参看所有参与该项目的开发者。*

### 版权说明

该项目签署了MIT 授权许可，详情请参阅 [LICENSE.txt](https://github.com/czy1024/luna-commons/blob/master/LICENSE)

### 鸣谢[]()


- [ffmpeg]()
- [Javacv]()


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




