package com.luna.file.excel;

import com.luna.file.FileApplicationTest;
import org.junit.Test;

/**
 * @Package: com.luna.file.excel
 * @ClassName: ExcelTest
 * @Author: luna
 * @CreateTime: 2020/8/13 12:13
 * @Description:
 */
public class ExcelTest extends FileApplicationTest {

    @Test
    public void atest() {
        String path = "D:\\user-improve\\SchoolAffairsRecords\\(线下考试部分)2020年春学期期末考试日程安排汇总表.xlsx";
        // List<Map<String, Object>> maps = ExcelUtils.readExcel(path);
        // System.out.println(maps);
        ExcelUtils.excel07SaxReader(path, 0);
    }
}
