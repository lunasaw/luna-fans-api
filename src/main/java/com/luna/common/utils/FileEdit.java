package com.luna.common.utils;

import com.baidu.aip.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileEdit {

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
            System.err.println("文件路径不存在！");
            return;
        }
        File[] files = file.listFiles();
        if (files.length <= 0) {
            System.err.println("当前路径文件不存在！");
            return;
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
    public static void copyFile(File input, File output) throws IOException {
        // 建立数据的输入输出通道
        FileInputStream fileInputStream = new FileInputStream(input);
        FileOutputStream fileOutputStream = new FileOutputStream(output);
        // 追加数据....

        // 每新创建一个FileOutputStream的时候，默认情况下FileOutputStream 的指针是指向了文件的开始的位置。 每写出一次，指向都会出现相应移动。
        // 建立缓冲数据，边读边写
        byte[] buf = new byte[1024];
        int length = 0;
        while ((length = fileInputStream.read(buf)) != -1) {
            fileOutputStream.write(buf, 0, length);
            // 写出很多次数据，所以就必须要追加。
        }
        // 关闭资源 原则： 先开后关，后开先关。
        fileOutputStream.close();
        fileInputStream.close();
    }

    /**
     * 批量复制文件
     * 
     * @param inputPath 输入目录
     * @param outpurPath 输出目录
     * @param number 每个文件复制数量
     * @param inputPrefix 输入文件前缀
     * @param outputPrefix 输出文件前缀
     * @param inputType 输入文件类型
     * @param outputType 输出文件类型
     * @throws IOException
     */
    public static Integer copyFile(String inputPath, String outpurPath, Integer number, String inputPrefix,
        String outputPrefix,
        String inputType, String outputType)
        throws IOException {
        // 找到目标文件
        File inFile = null;
        int cont = 1;
        Integer fileLength = FileUtils.getFileLength(inputPath);
        // 每张图片复制次数
        for (int i = 1; i < fileLength; i++) {
            inFile = new File(inputPath + "\\" + inputPrefix + i + inputType);
            for (int i1 = 0; i1 < number; i1++) {
                File destFile = new File(outpurPath + "\\" + outputPrefix + cont + outputType);
                cont++;
                FileEdit.copyFile(inFile, destFile);
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
     * @param fileName
     * @return
     */
    public static long getFileSize(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.out.println("文件不存在！！！");
            return -1;
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

    public static void main(String[] args) {

    }
}