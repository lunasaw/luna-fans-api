package com.luna.code.util;

/****
 * @Author:shenkunlin
 * @Description:
 * @Date 2019/6/14 18:27
 *****/
public class JavaTypes {

    /***
     * 根据java.sql.Types的值返回java的类型
     * 
     * @param value
     * @return
     */
    public static String getType(int value) {
        switch (value) {
            case -6:
                return "java.lang.Integer";
            case 5:
                return "java.lang.Integer";
            case 4:
                return "java.lang.Integer";
            case -5:
                return "java.lang.Long";
            case 6:
                return "java.lang.Float";
            case 8:
                return "java.lang.Double";
            case 1:
                return "java.lang.String";
            case 12:
                return "java.lang.String";
            case -1:
                return "java.lang.String";
            case 91:
                return "java.util.Date";
            case 92:
                return "java.util.Date";
            case 93:
                return "java.util.Date";
            case 16:
                return "java.lang.Boolean";
            default:
                return "java.lang.String";
        }
    }

    /***
     * 去掉数据类型的包
     * 
     * @param type
     * @return
     */
    public static String simpleName(String type) {
        return type.replace("java.lang.", "").replaceFirst("java.util.", "");
    }

    /***
     * 去掉数据类型的包，并且首字母小写
     * 
     * @param type
     * @return
     */
    public static String simpleNameLowerFirst(String type) {
        // 去掉前缀
        type = simpleName(type);
        // 将第一个字母转成小写
        return StringUtils.firstLower(type);
    }

}
