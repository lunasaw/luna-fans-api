package com.luna.common.utils.file;

import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.FileException;
import com.luna.common.exception.base.BaseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Package: com.luna.file.file
 * @ClassName: LocalFileUtil
 * @Author: luna
 * @CreateTime: 2020/7/16 15:31
 * @Description:
 */
public class LocalFileUtil {

    /**
     * 获取文件数目
     *
     * @param path
     * @return
     */
    public static Integer getFileLength(String path) {
        int fileCount = 0;
        int folderCount = 0;
        File d = new File(path);
        File list[] = d.listFiles();
        for (int i = 0; i < list.length; i++) {
            if (list[i].isFile()) {
                fileCount++;
            } else {
                folderCount++;
            }
        }
        return fileCount;
    }

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
            throw new FileException(ResultCode.PARAMETER_INVALID, "文件路径不存在", new Object[] {path});
        }
        File[] files = file.listFiles();
        if (files.length <= 0) {
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
     * @return 文件操作数
     * @throws IOException
     */
    public static Integer copyFile(String inputPath, String outputPath, Integer number, String inputPrefix,
        String outputPrefix, String inputType, String outputType) {
        int cont = 1;
        Integer fileLength = getFileLength(inputPath);
        // 每张图片复制次数
        for (int i = 1; i < fileLength; i++) {
            String inFile = inputPath + "\\" + inputPrefix + i + inputType;
            for (int i1 = 0; i1 < number; i1++) {
                String destFile = outputPath + "\\" + outputPrefix + cont + outputType;
                cont++;
                copyFile(new File(inFile), new File(destFile));
            }
        }
        return cont;
    }

    /**
     * 判断一个文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isFileExists(String fileName) {
        return Files.exists(Paths.get(fileName));
    }
}
