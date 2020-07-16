package com.luna.file.match;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @Package: com.luna.file.match
 * @ClassName: WordsMatch
 * @Author: luna
 * @CreateTime: 2020/7/16 15:34
 * @Description:
 */
public class WordsMatch {
    /** 匹配中文正则表达式 */
    private final static String PATTERN = "[\\u4e00-\\u9fa5]+";

    /**
     * 文本匹配 判断toMatch里是否存在prepare
     *
     * @param prepare 判断字符
     * @param toMatch 原始字符
     * @return
     */
    public static boolean checkKnowledge(String prepare, String toMatch) {
        if (StringUtils.isEmpty(prepare) || StringUtils.isEmpty(toMatch)) {
            return false;
        }
        Pattern pattern = Pattern.compile(PATTERN);
        // OCR识别出的文字用换行符分隔
        String[] split = toMatch.split("\n");
        for (String str : split) {
            if (pattern.matcher(str).find()) {
                // 匹配到中文
                // 判断是否是知识点
                if (str.replaceAll(" ", "").contains(prepare.replaceAll(" ", ""))) {
                    return true;
                }
            }
        }
        return false;
    }
}
