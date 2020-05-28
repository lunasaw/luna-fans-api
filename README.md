# luna-common 
![](https://img.shields.io/badge/luna--common-1.0--SNAPSHOT-green)

一款集合市面众多接口和工具类的集合,包括图像处理人脸识别等api 

# Update log

###
+Add JavaCv video processing, including picture synthesis video and video capture pictures

+Add Baidu Weather Return, Week of Hot Search, Word Matching

+Add Baidu Text Comparison and Error Correction Api

+Change Baidu Key Acquisition to Timing Task

+Join Alipay Black Box Test including order inquiry, payment connection, etc

+Introducing environment configuration files to eliminate the risk of possible key disclosure

+Increase Baidu Human Body Identification

+Increase Baidu Speech Synthesis

+Add Tencent map ip latitude and longitude to return location information

+Add Baidu Ocr text position general version and accurate version

+Add ffmpeg Image Processing Intercept Frame Synthesis Video Intercept Video Capture

+Add Baidu Object Recognition, Link Baidu Encyclopedia, Automatically Identify Celebrities, Famous Buildings, etc

+Add Rectangle Frame to Sketch Face Combined with Baidu Face Detection Api

+Increase Baidu Witness Audit

+Increase the search Api for easy answers

+Add Baidu Ocr Face Recognition Face Detection Face Comparison Live Detection Api Call

## Maven依赖
```

<repositories>
        <repository>
            <id>luna-common-mvn-repo</id>
            <url>https://raw.github.com/czy1024/luna-commons/mvn-repo-luna-commons/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
</repositories>

<dependency>
    <groupId>com.luna</groupId>
    <artifactId>common</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## 使用说明

+ 参见Wiki 《组件基础API使用说明》


## 更新日志

### 1.0--SNAPSHOT
+ 增加JavaCv 视频处理, 包含图片合成视频和视频截取图片 

+ 增加百度天气返回,一周热点搜索,词语匹配

+ 增加百度文本比对纠错Api

+ 改变百度Key获取为定时任务

+ 加入支付宝黑盒测试包括查询订单,付款连接等

+ 引入环境配置文件,免去可能密钥泄露风险

+ 增加百度人体识别

+ 增加百度语音合成

+ 增加腾讯地图 ip 经纬度返回位置信息

+ 增加百度Ocr文字位置 通用版和精确版

+ 增加ffmpeg图像处理 截帧 合成 视频截取 视频截图

+ 增加百度物体识别,链接百度百科,自动识别名人,著名建筑物等

+ 增加勾画人脸矩形框结合百度人脸检测Api

+ 增加百度人证审核

+ 增加学小易答案搜索Api

+ 增加百度Ocr 人脸识别 人脸检测 人脸比对 活体检测 Api调用


### 版权说明
![](https://img.shields.io/badge/License-Apache%20License%202.0-yellowgreen)
