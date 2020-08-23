package com.luna.file.excel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.text.CharsetKit;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.luna.file.excel
 * @ClassName: ExcelResponseUtil
 * @Author: luna
 * @CreateTime: 2020/8/23 22:50
 * @Description:
 */
public class ExcelResponseUtil<T> {

    /**
     * List写出为Excel
     * @param rows
     * @param type 文件类型 支持 xls,xlsx
     * @param response
     * @return
     */
    public HttpServletResponse exportExcel(List<T> rows, String type, HttpServletResponse response) {
        if (CollectionUtils.isEmpty(rows)) {
            return response;
        }
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Last-Modified", new Date().toString());
        response.setHeader("ETag", String.valueOf(System.currentTimeMillis()));
        response.setCharacterEncoding(CharsetKit.UTF_8);
        // response为HttpServletResponse对象
        response.setContentType("application/octet-stream;charset=utf-8");
        // 弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + type);
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = null;
        if (".xlsx".equals(type)) {
            writer = ExcelUtil.getWriter(true);
        } else if (".xls".equals(type)) {
            writer = ExcelUtil.getWriter();
        } else {
            throw new BaseException(ResultCode.PARAMETER_INVALID, "Excel文件类型不匹配");
        }
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
            // 此处记得关闭输出Servlet流
            IoUtil.close(out);
        }
        return response;
    }
}
