Welcome to the luna-common wiki!

 1. 您需要引入com-luna依赖

`<dependency>
    <groupId>com.luna</groupId>
    <artifactId>common</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>` 

 2. 在项目properties文件下添加所需配置

  ```xml
# 百度API
luna.baidu.appKey=xxxx
luna.baidu.secretKey=xxx
luna.baidu.appId=xxx
luna.baidu.jsKey=xxx

# 腾讯APi
luna.tencent.secretId=xxx
luna.tencent.secretKey=xxx

# 腾讯短信服务
# 短信模板Id
luna.smstencent.authCode=xxx
luna.smstencent.resetPassword=xxx
luna.smstencent.appId=xxx
luna.smstencent.sign=xxx

#邮箱操作
#自定义发送用户名
spring.mail.name=xxx
spring.mail.username=xxx
spring.mail.password=xxx
spring.mail.host=xxx
spring.mail.smtp.ssl.enable=true

# 腾讯云市场APi

# 天眼身份认证
luna.tencent.skyEyeSecretid=xxx
luna.tencent.skyEyeSecretkey=xxx
# 腾讯地图
luna.tencent.mapKey=xxx

#阿里oss服务器
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

# redis
spring.redis.host=xxx
spring.redis.port=6379

#ftp
#地址
luna.ftp.host=xxx
#端口
luna.ftp.port=21
#用户名
luna.ftp.username=xxx
#密码
luna.ftp.password=xxx
# 自定义路径
luna.ftp.basePath=/pub/luna/
luna.ftp.picture=/pub/picture/
luna.ftp.voice=/pub/voice/
# 本地临时路径
luna.ftp.localPath=D:/

  ```

  

3. 将项目com.luna.common.config路径下的三个文件复制到您的配置路径

   - 以百度为例，将百度Api的配置加入配置文件

4. 项目测试

   - 这里以SpringBoot启动演示，若非springboot框架可自行进行操作

   - 将配置文件注入Spring

 ```java
     @Resource
     BaiduConfigValue baiduConfigValue;
 ```

   - 项目Key配置完成后可直接调用方法

```java
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
		BaiduApiContent.BAIDU_KEY = GetBaiduKey.getAuth(baiduConfig.getAppKey(), baiduConfig.getSecretKey());
		System.out.println(BaiduApi.faceVerification());
	}

}
//现在已经更改成定时任务 需测试其他方法
     
```

   - ==注意==：并不是每次调用服务都需要初始化Key ，Key为静态变量，但30天需更换一次，可以调用定时任务

     可在一次初始化获取后，多次调用方法，例如

 ```java
     /**
      * @author Luna@win10
      * @date 2020/5/6 12:46
      */
     @SpringBootTest
     @RunWith(SpringRunner.class)
     public class BaiduApiTest {
     
     	@Resource
     	BaiduConfigValue baiduConfigValue;
     
     	@Test
     	public void aTest() throws IOException {
     	//key 赋值
     		BaiduApiContent.BAIDU_KEY = GetBaiduKey.getAuth(baiduConfigValue.getAppKey(), baiduConfigValue.getSecretKey());
     		System.out.println(BaiduApi.faceVerification());
     	}
     	
     }
//现在已经更改成定时任务 需测试其他方法
     
 ```

​     ## 非常的简单快捷

