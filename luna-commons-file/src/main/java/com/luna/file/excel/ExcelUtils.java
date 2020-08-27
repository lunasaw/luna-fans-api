package com.luna.file.excel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.sax.Excel03SaxReader;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.luna.file.excel.util.ExcelHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.file.excel
 * @ClassName: ExcelUtils
 * @Author: luna
 * @CreateTime: 2020/8/13 10:57
 * @Description:
 */
public class ExcelUtils<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 打印Excel数据
     * 
     * @param file
     * @param sheet
     */
    public static void printExcel(String file, Integer sheet) {
        ExcelUtil.readBySax(FileUtil.file(file), sheet, createRowHandler());
    }

    /**
     * hutool 写入数据 未注解别名的不参与排序 但会写出
     * 
     * @param list
     * @param basePath
     * @param sheetName
     * @return
     */
    public void writeExcel(List<T> list, Class<T> c, String basePath, String sheetName) {
        log.info("writeExcel start basePath={},sheetName={}", basePath, sheetName);
        String absoluteFile = BaseExcelUtil.getAbsoluteFile(basePath, BaseExcelUtil.encodingFilename(sheetName));
        ExcelWriter writer = ExcelUtil.getWriter(FileUtil.file(absoluteFile));
        ExcelHelper excelHelper = new ExcelHelper();
        Map<String, String> excelField = excelHelper.createExcelField(c);
        writer = excelHelper.beanNameAddAlias(writer, excelField);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(excelField.size(), sheetName);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        // 关闭writer，释放内存
        writer.close();
        log.info("writeExcel success basePath={},absoluteFile={}", basePath, absoluteFile);
    }

    /**
     * 文件流的形式写出
     * 
     * @param list
     * @param basePath
     * @param sheetName
     * @param xlsx
     * @throws FileNotFoundException
     */
    public void writeExcel2Srteam(List<T> list, String basePath, String sheetName, Boolean xlsx)
        throws FileNotFoundException {
        log.info("writeExcel start basePath={},sheetName={}", basePath, sheetName);
        String absoluteFile = BaseExcelUtil.getAbsoluteFile(basePath, BaseExcelUtil.encodingFilename(sheetName));
        // 创建xlsx格式的
        ExcelWriter writer = ExcelUtil.getWriter(xlsx);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        FileOutputStream out = new FileOutputStream(absoluteFile);
        // out为OutputStream，需要写出到的目标流
        writer.flush(out);
        // 关闭writer，释放内存
        writer.close();
        log.info("writeExcel success basePath={},absoluteFile={}", basePath, absoluteFile);
    }

    /**
     * 导入数据
     *
     * @param filePath
     * @return
     */
    public static List<Map<String, Object>> readExcel(String filePath) {
        log.info("readExcel start filePath={}", filePath);
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file(filePath));
        return reader.readAll();
    }

    /**
     * 07 版流读取
     * 
     * @param path 文件地址
     * @param num sheet的序号，-1表示读取所有sheet，0表示第一个sheet，依此类推。
     */
    public static void excel07SaxReader(String path, Integer num) {
        Excel07SaxReader reader = new Excel07SaxReader(createRowHandler());
        reader.read(path, num);
    }

    /**
     * 03 版流读取
     * 
     * @param path 文件地址
     * @param num sheet的序号，-1表示读取所有sheet，0表示第一个sheet，依此类推。
     */
    public static void excel03SaxReader(String path, Integer num) {
        Excel03SaxReader reader = new Excel03SaxReader(createRowHandler());
        reader.read(path, num);
    }

    /**
     * 导入数据
     * 
     * @param sheetName
     * @param fileInputStream
     * @return
     * @throws Exception
     */
    public List<T> importData(Class<T> c, String sheetName, FileInputStream fileInputStream) throws Exception {
        BaseExcelUtil<T> excelUtils = new BaseExcelUtil<>(c);
        return excelUtils.importExcel(sheetName, fileInputStream);
    }

    /**
     * 写出数据
     * 
     * @param c
     * @param list
     * @param basePath
     * @return
     */
    public String exportData(Class<T> c, List<T> list, String basePath) {
        BaseExcelUtil<T> excelUtils = new BaseExcelUtil<>(c);
        return excelUtils.exportExcel(basePath);
    }

    private static RowHandler createRowHandler() {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowList) {
                Console.log("[{}] [{}] {}", sheetIndex, rowIndex, rowList);
            }
        };
    }
}
