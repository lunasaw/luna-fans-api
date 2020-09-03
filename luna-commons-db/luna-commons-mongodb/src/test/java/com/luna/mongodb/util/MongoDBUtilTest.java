package com.luna.mongodb.util;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.luna.mongodb.MongoDBApplicationTest;
import com.luna.mongodb.demo.Person;
import com.luna.mongodb.utils.MongoDBUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @Package: com.luna.mongodb.util
 * @ClassName: MongoDBUtilTest
 * @Author: luna
 * @CreateTime: 2020/9/3 15:24
 * @Description:
 */
public class MongoDBUtilTest extends MongoDBApplicationTest {

    @Autowired
    private MongoDBUtil mongoDBUtil;

    @Test
    public void atest() {
        Person person = new Person(3L, "刘备", "蜀国", 20, DateUtil.parse("1990-01-02 03:04:05"),
            "刘备（161年－223年6月10日），即汉昭烈帝（221年－223年在位），又称先主，字玄德，东汉末年幽州涿郡涿县（今河北省涿州市）人，西汉中山靖王刘胜之后，三国时期蜀汉开国皇帝、政治家。\n刘备少年时拜卢植为师；早年颠沛流离，备尝艰辛，投靠过多个诸侯，曾参与镇压黄巾起义。先后率军救援北海相孔融、徐州牧陶谦等。陶谦病亡后，将徐州让与刘备。赤壁之战时，刘备与孙权联盟击败曹操，趁势夺取荆州。而后进取益州。于章武元年（221年）在成都称帝，国号汉，史称蜀或蜀汉。《三国志》评刘备的机权干略不及曹操，但其弘毅宽厚，知人待士，百折不挠，终成帝业。刘备也称自己做事“每与操反，事乃成尔”。\n章武三年（223年），刘备病逝于白帝城，终年六十三岁，谥号昭烈皇帝，庙号烈祖，葬惠陵。后世有众多文艺作品以其为主角，在成都武侯祠有昭烈庙为纪念。");
        // mongoDBUtil.saveOne(String.valueOf(1),person);

        Person person1 = new Person(2L, "刘备", "蜀国", 18, DateUtil.parse("1990-01-02 03:04:05"),
            "刘备（161年－223年6月10日），即汉昭烈帝（221年－223年在位），又称先主，字玄德，东汉末年幽州涿郡涿县（今河北省涿州市）人，西汉中山靖王刘胜之后，三国时期蜀汉开国皇帝、政治家。\n刘备少年时拜卢植为师；早年颠沛流离，备尝艰辛，投靠过多个诸侯，曾参与镇压黄巾起义。先后率军救援北海相孔融、徐州牧陶谦等。陶谦病亡后，将徐州让与刘备。赤壁之战时，刘备与孙权联盟击败曹操，趁势夺取荆州。而后进取益州。于章武元年（221年）在成都称帝，国号汉，史称蜀或蜀汉。《三国志》评刘备的机权干略不及曹操，但其弘毅宽厚，知人待士，百折不挠，终成帝业。刘备也称自己做事“每与操反，事乃成尔”。\n章武三年（223年），刘备病逝于白帝城，终年六十三岁，谥号昭烈皇帝，庙号烈祖，葬惠陵。后世有众多文艺作品以其为主角，在成都武侯祠有昭烈庙为纪念。");

        ArrayList<Object> list = Lists.newArrayList();
        list.add(person);
        list.add(person1);

        // mongoDBUtil.saveAll("person",list);
        // Object luna = mongoDBUtil.findSortById(Person.class, "luna", Sort.Direction.DESC);
        // System.out.println(JSON.toJSONString(luna));
        // Object result = mongoDBUtil.findDesignField(Lists.newArrayList("name", "country", "age"),
        // ImmutableMap.of("age",18), Person.class, "luna",
        // false);

        // Object result = mongoDBUtil.findSortFirst(Person.class, ImmutableMap.of("age", 18), "luna", "age",
        // Sort.Direction.ASC);

        mongoDBUtil.delete(Person.class, "person", ImmutableMap.of("id", 2));
        // System.out.println(JSON.toJSONString(result));

    }
}
