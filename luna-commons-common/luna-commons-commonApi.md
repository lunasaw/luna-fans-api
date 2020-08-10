## 基础工具包Api

### http操作

##### HttpUtils类

1. 普通get请求

```java
/**
     * get
     *
     * @param host 主机
     * @param path 路径
     * @param headers 请求头
     * @param queries 请求参数
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path, Map<String, String> headers,Map<String, String> queries) 
```

2. 参数/文件post请求

```java
    /**
     * Post File/form
     *
     * @param host 主机
     * @param path 路径
     * @param headers 请求头
     * @param queries 请求参数
     * @param bodies <p>Map<文件名或者参数key,文件地址或者参数value><p/>
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, Map<String, String> bodies)
```

3. 流post请求

```java
    /**
     * Post stream
     *
     * @param host 主机
     * @param path 路径
     * @param headers 请求头
     * @param queries 请求参数
     * @param body 字节流
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, byte[] body)
```

4. 字符串post请求

```java
    /**
     * Post String
     *
     * @param host 主机
     * @param path 路径
     * @param headers 请求头
     * @param queries 请求参数
     * @param body 字符串
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, Map<String, String> headers,
        Map<String, String> queries, String body) 
```

5. 返回结果解析及检查

```java
/**
     * 检测响应体
     * 
     * @param httpResponse
     * @return
     */
    public static String checkResponseAndGetResult(HttpResponse httpResponse, boolean isEnsure)
```

6. 简单的检查是否为网络路径

```java
 /**
     * 检查是不是网络路径
     * 
     * @param url
     * @return
     */
    public static boolean isNetUrl(String url) {
```

### jsonfile

##### InformationConverter类

1. 读取JSON文件操作

```java
/**
     * 读取json文件并转换为JSONObject
     * @param path JSON文件路径
     * @return a fastjson JSONObject
     */
    public static JSONObject fileToJson(String path)
```

2. JSONObject写入JSON文件

```java
    /**
     * write json String to a json file
     * @param text the json text needs to write
     * @param path the json file path needs to save
     */
    public static void stringToFile(String text, String path)
```

3. 获取JSON文件中的指定值

```java
/**
     * get item from json text file
     * @param path the json file path
     * @param item the item you want to get, can be para, header
     * @return a Map, key is String, value is Object
     */
    public static Map<String, Object> getItem(String path, String item)
```

4. 将JSONObject的值转为HashMap

```java
    /**
     * Convert jsonobject to hashMap
     * @param object the jsonObject need to convert
     * @return the hashMap(String, String)
     */
    public static HashMap<String, String> jsonToHasMap(JSONObject object)
```

### okHttp

1. Get请求

```java
    /**
     * send a get to a site
     * @param url the website url
     * @param para the parameter hashMap, use put to add
     * @param header the header hasMap, use put to add
     * @return a response json object
     */
    public static JSONObject get(String url, HashMap<String,String> para, HashMap<String,String> header)
```

2. POST请求

```java
/**
     * send a post to a site
     * 
     * @param url the website url
     * @param para the parameter hashMap, use put to add
     * @param header the header hasMap, use put to add
     * @param bodyFormat the body hashMap, if use none, put("none",""), if form-data, put(key,value)
     * @return a response json object
     */
    public static JSONObject post(String url, HashMap<String, String> para, HashMap<String, String> header,
        HashMap<String, String> bodyFormat)
```

### utils

##### fileUtils

1. 获取文件夹内文件数

```java
    /**
     * 获取文件夹内文件数目
     *
     * @param path
     * @return
     */
    public static Integer getFileLength(String path) {
```

2. 批量转换文件类型

```java
    /**
     * 批量转换文件类型
     *
     * @param path 文件夹路径
     * @param oldExt 原本类型
     * @param newExt 转换后类型
     */
    public static void renameFiles(String path, String oldExt, String newExt)
```

3. 复制文件

```java
    /**
     * 复制文件
     *
     * @param input 输入
     * @param output 输出
     * @throws IOException
     */
    public static void copyFile(File input, File output) 
```

4. 批量复制文件

```java
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
        String outputPrefix, String inputType, String outputType)
```

##### imageUtils

1. 图片/文件转为字节

```java
    /**
     * 图片转字节
     * 
     * @param imgFile 文件路径
     * @return
     */
    public static byte[] getBytes(String imgFile) 
```

2. 字节转图片

```java
    /**
     * 字节转图片
     * 
     * @param data 数据
     * @param path 输出路径
     */
    public static void byte2image(byte[] data, String path)
```

##### textUtils

CharsetKit 字符串处理工具栏

Convert 类型转换器

 DatePattern 日期格式匹配

IdWorker Id生成器

RandomValueUtil 随机姓名生成器

StrFormatter 字符串格式化,替换{}标识

