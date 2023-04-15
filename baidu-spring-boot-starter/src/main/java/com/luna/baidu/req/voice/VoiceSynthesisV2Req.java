package com.luna.baidu.req.voice;

import com.luna.baidu.enums.voice.VideoFormat;
import com.luna.baidu.enums.voice.PersonVoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luna
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoiceSynthesisV2Req {
    /**
     * 待合成的文本，需要为UTF-8 编码；输入多段文本时，文本间会插入1s长度的空白间隔。总字数不超过10万个字符，1个中文字、英文字母、数字或符号均算作1个字符
     */
    private List<String> text;
    /**
     * 音频格式。"mp3-16k"，"mp3-48k"，"wav"，"pcm-8k"，"pcm-16k"，默认为mp3-16k
     * {@link VideoFormat}
     */
    private String       format;
    /**
     * 音库。基础音库：度小宇=1，度小美=0，度逍遥（基础）=3，度丫丫=4；精品音库：度逍遥（精品）=5003，度小鹿=5118，度博文=106，度小童=110，度小萌=111，度米朵=103，度小娇=5。默认为度小美
     * {@link PersonVoice}
     */
    private int          voice;
    /**
     * 语言。固定值zh。语言选择,目前只有中英文混合模式，填写固定值zh
     */
    private String       lang;
    /**
     * 语速。取值0-15，默认为5中语速
     */
    private int          speed;
    /**
     * 音调。取值0-15，默认为5中语调
     */
    private int          pitch;
    /**
     * 音量。取值0-15，默认为5中音量（取值为0时为音量最小值，并非为无声）
     */
    private int          volume;
    /**
     * 是否开启字幕。取值范围0, 1, 2，默认为0。0表示不开启字幕，1表示开启句级别字幕，2表示开启词级别字。
     */
    private int          enableSubtitle;
}