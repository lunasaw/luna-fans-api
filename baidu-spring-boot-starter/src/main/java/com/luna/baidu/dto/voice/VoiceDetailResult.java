package com.luna.baidu.dto.voice;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author weidian
 * @description 语音识别结果
 * @date 2023/4/13
 */
@Data
public class VoiceDetailResult {

    @JSONField(name = "result")
    private List<String> result;

    @JSONField(name = "err_msg")
    private String       errMsg;

    @JSONField(name = "sn")
    private String       sn;

    @JSONField(name = "corpus_no")
    private String       corpusNo;

    @JSONField(name = "err_no")
    private int          errNo;

}
