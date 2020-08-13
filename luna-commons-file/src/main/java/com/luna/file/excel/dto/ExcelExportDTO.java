package com.luna.file.excel.dto;

import java.util.List;

/**
 * @Package: com.luna.file.excel.dto
 * @ClassName: ExcelExportDTO
 * @Author: luna
 * @CreateTime: 2020/8/13 19:00
 * @Description:
 */
public class ExcelExportDTO<T> {

    /** 数据 */
    private List<T> data;

    /** 路径 */
    private String  basePath;

    /** 表名 */
    private String  sheetName;

    /** 是否 xlsx */
    private Boolean xlsx;

    public Boolean getXlsx() {
        return xlsx;
    }

    public void setXlsx(Boolean xlsx) {
        this.xlsx = xlsx;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
