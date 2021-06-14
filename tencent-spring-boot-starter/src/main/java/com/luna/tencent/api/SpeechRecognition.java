package com.luna.tencent.api;

import com.tencent.SpeechClient;
import com.tencent.asr.constant.AsrConstant;
import com.tencent.asr.model.SpeechRecognitionRequest;
import com.tencent.asr.model.SpeechRecognitionSysConfig;
import com.tencent.asr.service.SpeechRecognizer;
import com.tencent.core.utils.ByteUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SpeechRecognition {

    /**
     * 获取 SpeechClient
     *
     * @return SpeechClient
     * @throws IOException IOException
     */
    public static SpeechClient getSpeechClient() throws IOException {
        // 从配置文件读取密钥（可自行修改）
        Properties props = new Properties();
        // 从配置文件读取密钥
        // props.load(new FileInputStream("../../config.properties"));
        String appId = props.getProperty("1300707296");
        String secretId = props.getProperty("AKIDQiohOwHROE1pZAYheOzShrtspiVDpLoD");
        String secretKey = props.getProperty("Rn5JUentyzxvnTwDHAqIZgdPzRioiV7Y");
        return SpeechClient.newInstance("1300707296", "AKIDQiohOwHROE1pZAYheOzShrtspiVDpLoD",
            "Rn5JUentyzxvnTwDHAqIZgdPzRioiV7Y");
    }

    public static void main(String[] args) throws IOException {
        runOnce(getSpeechClient());
    }

    /**
     * 并发
     *
     * @param client SpeechClient
     * @param threadNum 线程数
     * @throws InterruptedException InterruptedException
     */
    public static void runConcurrency(final SpeechClient client, int threadNum) throws InterruptedException {
        while (true) {
            for (int i = 0; i < threadNum; i++) {
                Thread.sleep(50);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnce(client);
                    }
                }).start();
            }
            Thread.sleep(600000);
        }
    }

    /**
     * 单独执行
     *
     * @param client SpeechClient
     */
    public static void runOnce(final SpeechClient client) {
        try {
            // 案例使用文件模拟实时获取语音流，用户使用可直接调用write传入字节数据
            FileInputStream fileInputStream = new FileInputStream(
                new File("/Users/luna/Document/project/post/2021/05/31/36911f9480a344ba8e85900fbcff3897.mp3"));
            // http 建议每次传输200ms数据 websocket建议每次传输40ms数据
            List<byte[]> speechData = ByteUtils.subToSmallBytes(fileInputStream,
                SpeechRecognitionSysConfig.requestWay == AsrConstant.RequestWay.Http ? 6400 : 640);
            // 请求参数，用于配置语音识别相关参数，可使用init方法进行默认配置或使用 builder的方式构建自定义参数
            SpeechRecognitionRequest request = SpeechRecognitionRequest.initialize();
            request.setEngineModelType("16k_zh"); // 模型类型为必传参数，否则异常
            request.setVoiceFormat(8); // 指定音频格式
            SpeechRecognizer speechWsRecognizer =
                client.newSpeechRecognizer(request, new MySpeechRecognitionListener());
            // 开始识别 调用start方法
            speechWsRecognizer.start();
            for (int i = 0; i < speechData.size(); i++) {
                // 模拟音频间隔
                Thread.sleep(SpeechRecognitionSysConfig.requestWay == AsrConstant.RequestWay.Http ? 200 : 20);
                // 发送数据
                speechWsRecognizer.write(speechData.get(i));
            }
            // 结束识别调用stop方法
            speechWsRecognizer.stop();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}