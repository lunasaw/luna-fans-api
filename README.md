# luna-common 
![](https://img.shields.io/badge/luna--common-1.0--SNAPSHOT-green)

一直想写一个开发工具的Utils,市面各种工具类层出不穷,当然都是特别好的,但是工具类的总类不同,往往有时候需要使用的时候又去找度娘。
我就写了个工具maven包，直接导入就好。

## Maven依赖
```
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
+ 增加JavaCv 处理视频, 人脸识别等操作,包含opencv ,ffmpeg

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
