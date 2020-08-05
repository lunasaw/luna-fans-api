package com.luna.baidu.api;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.bodyanalysis.AipBodyAnalysis;
import com.luna.common.entity.Body;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Luna@win10
 * @date 2020/5/4 14:01
 */
public class BodySDK {

    /**
     * 人体识别
     * 
     * @param client
     * @param path
     */
    public static List sample(AipBodyAnalysis client, String path) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject res = client.bodyAttr(path, options);
        System.out.println(res);
        String num = res.get("person_num").toString();
        List<com.alibaba.fastjson.JSONObject> datas =
            JSON.parseArray(res.get("person_info").toString(), com.alibaba.fastjson.JSONObject.class);
        List<Map<String, String>> list = new ArrayList<>();
        List<Body> listBody = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {
            // 定位
            String location = datas.get(i).get("location").toString();
            Body body = JSON.parseObject(location, Body.class);
            listBody.add(body);
            Map<String, String> status = new HashMap<>();
            // 是否正面
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(datas.get(i).get("attributes").toString());
            String orientation = jsonObject.get("orientation").toString();
            status.put("orientation", JSON.parseObject(orientation).get("name").toString());
            // 性别
            String gender = jsonObject.get("gender").toString();
            status.put("gender", JSON.parseObject(gender).get("name").toString());
            // 是否打伞
            String umbrella = jsonObject.get("umbrella").toString();
            status.put("umbrella", JSON.parseObject(umbrella).get("name").toString());
            // 下身服饰
            String lower_color = jsonObject.get("lower_color").toString();
            status.put("lower_color", JSON.parseObject(lower_color).get("name").toString());
            // 是否戴口罩
            String face_mask = jsonObject.get("face_mask").toString();
            status.put("face_mask", JSON.parseObject(face_mask).get("name").toString());
            // 是否抽烟
            String smoke = jsonObject.get("smoke").toString();
            status.put("smoke", JSON.parseObject(smoke).get("name").toString());
            // 上身服饰
            String upper_wear = jsonObject.get("upper_wear").toString();
            status.put("upper_wear", JSON.parseObject(upper_wear).get("name").toString());
            // 是否背包
            String bag = jsonObject.get("bag").toString();
            status.put("bag", JSON.parseObject(bag).get("name").toString());
            // 是否是正常人体
            String is_human = jsonObject.get("is_human").toString();
            status.put("is_human", JSON.parseObject(is_human).get("name").toString());
            // 交通工具,
            String vehicle = jsonObject.get("vehicle").toString();
            status.put("vehicle", JSON.parseObject(vehicle).get("name").toString());
            // 有无眼镜
            String glasses = jsonObject.get("glasses").toString();
            status.put("glasses", JSON.parseObject(glasses).get("name").toString());
            // 有无帽子
            String headwear = jsonObject.get("headwear").toString();
            status.put("headwear", JSON.parseObject(headwear).get("name").toString());
            // 上身服饰分类
            String upper_wear_fg = jsonObject.get("upper_wear_fg").toString();
            status.put("upper_wear_fg", JSON.parseObject(upper_wear_fg).get("name").toString());
            // 上身服饰纹理
            String upper_wear_texture = jsonObject.get("upper_wear_texture").toString();
            status.put("upper_wear_texture", JSON.parseObject(upper_wear_texture).get("name").toString());
            // 上方截断
            String upper_cut = jsonObject.get("upper_cut").toString();
            status.put("upper_cut", JSON.parseObject(upper_cut).get("name").toString());
            // 遮挡
            String occlusion = jsonObject.get("occlusion").toString();
            status.put("occlusion", JSON.parseObject(occlusion).get("name").toString());
            // 下方截断
            String lower_cut = jsonObject.get("lower_cut").toString();
            status.put("lower_cut", JSON.parseObject(lower_cut).get("name").toString());
            // 是否使用手机
            String cellphone = jsonObject.get("cellphone").toString();
            status.put("cellphone", JSON.parseObject(cellphone).get("name").toString());
            // 有无手提物
            String carrying_item = jsonObject.get("carrying_item").toString();
            status.put("carrying_item", JSON.parseObject(carrying_item).get("name").toString());
            // 下半身服饰
            String lower_wear = jsonObject.get("lower_wear").toString();
            status.put("lower_wear", JSON.parseObject(lower_wear).get("name").toString());
            // 年龄阶段
            String age = jsonObject.get("age").toString();
            status.put("age", JSON.parseObject(age).get("name").toString());
            // 上半身衣着颜色
            String upper_color = jsonObject.get("upper_color").toString();
            status.put("upper_color", JSON.parseObject(upper_color).get("name").toString());
            list.add(status);
        }
        List<List> returnList = new ArrayList<>();
        returnList.add(list);
        returnList.add(listBody);
        return returnList;
    }

}
