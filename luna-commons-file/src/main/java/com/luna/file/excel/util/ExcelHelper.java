package com.luna.file.excel.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.google.common.collect.Maps;
import com.luna.file.excel.anno.Excel;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Package: com.luna.file.excel.util
 * @ClassName: ExcelHelperUtil
 * @Author: luna
 * @CreateTime: 2020/8/13 13:15
 * @Description:
 */
public class ExcelHelper<T> {

    /**
     * 添加别名
     * 
     * @param writer
     * @param alias
     * @return
     */
    public ExcelWriter beanNameAddAlias(ExcelWriter writer, Map<String, String> alias) {
        // Iterating entries using a For Each loop
        Iterator<Map.Entry<String, String>> entries = alias.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            writer.addHeaderAlias(entry.getKey(), entry.getValue());
        }
        return writer;
    }

    /**
     * 得到所有定义字段
     */
    public Map<String, String> createExcelField(Class<T> clazz) {
        List<Object[]> fields = CollUtil.newArrayList();
        List<Field> tempFields = new ArrayList<>();
        tempFields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        tempFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        for (Field field : tempFields) {
            if (field.isAnnotationPresent(Excel.class)) {
                fields = putToField(fields, field, field.getAnnotation(Excel.class));
            }
        }

        Map<String, String> map = Maps.newHashMap();

        for (Object[] field : fields) {
            Field filed = (Field)field[0];
            Excel excel = (Excel)field[1];
            map.put(filed.getName(), excel.name());
        }
        return map;
    }

    /**
     * 放到字段集合中
     */
    public static List<Object[]> putToField(List<Object[]> files, Field field, Excel attr) {
        if (attr != null && (attr.type() == Excel.Type.ALL)) {
            files.add(new Object[] {field, attr});
        }
        return files;
    }
}
