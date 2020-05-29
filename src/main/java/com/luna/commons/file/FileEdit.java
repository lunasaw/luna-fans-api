package com.luna.commons.file;

import com.baidu.aip.util.Util;
import com.luna.commons.baidu.Config.GetBaiduKey;
import com.luna.commons.dto.constant.ResultCode;
import com.luna.commons.exception.FileException;
import com.luna.commons.exception.base.BaseException;
import com.luna.commons.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.regex.Pattern;

public class FileEdit {
    private static final Logger log = LoggerFactory.getLogger(GetBaiduKey.class);

    /**
     * 批量转换文件类型
     * 
     * @param path
     * @param oldExt
     * @param newExt
     */
    public static void renameFiles(String path, String oldExt, String newExt) {
        File file = new File(path);
        if (!file.exists()) {
            log.info("文件路径不存在！", path);
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件路径不存在", new Object[] {path});
        }
        File[] files = file.listFiles();
        if (files.length <= 0) {
            log.info("当前路径文件不存在！", path);
            throw new BaseException(ResultCode.PARAMETER_INVALID, "当前路径文件不存在", new Object[] {path});
        }
        for (File f : files) {
            if (f.isDirectory()) {
                renameFiles(f.getPath(), oldExt, newExt);
            } else {
                String name = f.getName();
                if (name.endsWith("." + oldExt)) {
                    name = name.substring(0, name.lastIndexOf(".") + 1);
                    name += newExt;
                    f.renameTo(new File(f.getParent() + "\\" + name));
                }
            }
        }
    }

    /**
     * 复制文件
     * 
     * @param input 输入
     * @param output 输出
     * @throws IOException
     */
    public static void copyFile(File input, File output) {
        if (!input.exists() || !output.exists()) {
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件路径不存在", new Object[] {input, output});
        }
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            // 建立数据的输入输出通道
            fileInputStream = new FileInputStream(input);
            fileOutputStream = new FileOutputStream(output);
            // 追加数据....

            // 每新创建一个FileOutputStream的时候，默认情况下FileOutputStream 的指针是指向了文件的开始的位置。 每写出一次，指向都会出现相应移动。
            // 建立缓冲数据，边读边写
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, length);
                // 写出很多次数据，所以就必须要追加。
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭资源 原则： 先开后关，后开先关。
                fileOutputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 批量复制文件
     * 
     * @param inputPath 输入目录
     * @param outputPath 输出目录
     * @param number 每个文件复制数量
     * @param inputPrefix 输入文件前缀
     * @param outputPrefix 输出文件前缀
     * @param inputType 输入文件类型
     * @param outputType 输出文件类型
     * @throws IOException
     */
    public static Integer copyFile(String inputPath, String outputPath, Integer number, String inputPrefix,
        String outputPrefix, String inputType, String outputType) {
        int cont = 1;
        Integer fileLength = FileUtils.getFileLength(inputPath);
        // 每张图片复制次数
        for (int i = 1; i < fileLength; i++) {
            String inFile = inputPath + "\\" + inputPrefix + i + inputType;
            for (int i1 = 0; i1 < number; i1++) {
                String destFile = outputPath + "\\" + outputPrefix + cont + outputType;
                cont++;
                FileEdit.copyFile(new File(inFile), new File(destFile));
            }
        }
        return cont;
    }

    /**
     * 百度API里的读二进制文件
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFile(String path) {
        byte[] bytes = new byte[0];
        try {
            bytes = Util.readFileByBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 获取文件大小
     * 
     * @param path
     * @return
     */
    public static long getFileSize(String path) {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件不存在", new Object[] {path});
        }
        return file.length();
    }

    /**
     * 百度API里的写入二进制文件
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static void writeFile(byte[] data, String path) {
        try {
            Util.writeBytesToFileSystem(data, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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