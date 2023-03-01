package com.luna.api.email.mapper;

import com.luna.api.email.entity.TemplateDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description templateDO Mapper
 * @author MrZhang-YUBO
 * @date 2020年1月20日 17:22:27
 */
@Mapper
public interface TemplateDAO {

    /**
     * 插入
     *
     * @param templateDO
     */
    @Insert("INSERT INTO tb_template (create_time, modified_time, subject, content) VALUES(now(), now(), #{subject}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(TemplateDO templateDO);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM tb_template WHERE id=#{id}")
    int delete(@Param("id") long id);

    /**
     * 更新内容
     *
     * @param templateDO
     * @return
     */
    @Update("UPDATE tb_template SET modified_time=now(), subject=#{subject}, content=#{content} WHERE id=#{id}")
    int update(TemplateDO templateDO);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Select("SELECT id, create_time, modified_time, subject, content FROM tb_template WHERE id=#{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "subject", column = "subject"),
        @Result(property = "content", column = "content")
    })
    TemplateDO get(@Param("id") long id);

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Select("SELECT id, create_time, modified_time, subject, content FROM tb_template WHERE subject=#{subject}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "subject", column = "subject"),
        @Result(property = "content", column = "content")
    })
    TemplateDO getBySubject(@Param("subject") String subject);

    /**
     * 查找
     *
     * @return
     */
    @Select("SELECT id, create_time, modified_time, subject, content FROM tb_template ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "subject", column = "subject"),
        @Result(property = "content", column = "content")
    })
    List<TemplateDO> list();

    /**
     * 查找所有标题
     *
     * @return
     */
    @Select("SELECT id, create_time, modified_time, subject FROM tb_template ")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "modifiedTime", column = "modified_time"),
        @Result(property = "subject", column = "subject"),
    })
    List<TemplateDO> subjectList();
}
