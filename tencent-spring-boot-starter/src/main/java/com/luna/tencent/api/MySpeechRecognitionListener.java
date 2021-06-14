package com.luna.tencent.api;

import com.tencent.asr.model.SpeechRecognitionResponse;
import com.tencent.asr.service.SpeechRecognitionListener;
import com.tencent.core.utils.JsonUtil;

public class MySpeechRecognitionListener extends SpeechRecognitionListener {
    @Override
    public void onRecognitionResultChange(SpeechRecognitionResponse response) {
        System.out.println("识别结果:" + JsonUtil.toJson(response));
    }

    @Override
    public void onRecognitionStart(SpeechRecognitionResponse response) {
        System.out.println("开始识别:" + JsonUtil.toJson(response));
    }

    @Override
    public void onSentenceBegin(SpeechRecognitionResponse response) {
        System.out.println("一句话开始:" + JsonUtil.toJson(response));
    }

    @Override
    public void onSentenceEnd(SpeechRecognitionResponse response) {
        System.out.println("一句话结束:" + JsonUtil.toJson(response));
    }

    @Override
    public void onRecognitionComplete(SpeechRecognitionResponse response) {
        System.out.println("识别结束:" + JsonUtil.toJson(response));
    }

    @Override
    public void onFail(SpeechRecognitionResponse response) {
        System.out.println("--------------");
        System.out.println("--------------错误:" + JsonUtil.toJson(response));
        System.out.println("--------------");
    }
}