package com.luna.file.httpd;

import com.google.common.collect.Lists;
import com.luna.common.http.HttpUtils;
import com.luna.file.excel.ExcelResponseUtil;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;

/**
 * @Package: com.luna.springdemo.file
 * @ClassName: FileRest
 * @Author: luna
 * @CreateTime: 2020/8/2 11:18
 * @Description:
 */
@RestController
@RequestMapping(value = "/file")
public class FileRest {

    @GetMapping("/expore")
    public ResponseEntity<FileSystemResource> exportXls() {
        return HttpUtils.exportFile(new File("C:\\Users\\improve\\Desktop\\luna.xlsx"), ".xlsx");
    }

    @GetMapping("/exporeFile")
    public HttpServletResponse exporeFile(HttpServletResponse response) {
        return HttpUtils.exportFile(response, new File("C:\\Users\\improve\\Desktop\\luna.xlsx"), ".xlsx");
    }

    @GetMapping("/xlsx")
    public HttpServletResponse getFile(HttpServletResponse response) {
        ArrayList<Object> rows = Lists.newArrayList();
        rows.add("陈章月");
        rows.add("JAVA");
        ExcelResponseUtil<Object> responseUtil = new ExcelResponseUtil<>();
        return responseUtil.exportExcel(rows, ".xlsx", response);
    }
}
