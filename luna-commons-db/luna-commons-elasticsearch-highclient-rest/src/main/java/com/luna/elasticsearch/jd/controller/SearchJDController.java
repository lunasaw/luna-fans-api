package com.luna.elasticsearch.jd.controller;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.elasticsearch.jd.service.SearchJDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.elasticsearch.jd.controller
 * @ClassName: SearchJDController
 * @Author: luna
 * @CreateTime: 2020/8/26 20:28
 * @Description:
 */
@RestController
@RequestMapping(value = "jd")
public class SearchJDController {

    @Autowired
    private SearchJDService searchJDService;

    /**
     * 搜索导入数据
     *
     * @param keyword
     * @return
     */
    @GetMapping("/parse/{keyword}")
    public ResultDTO<Boolean> parseJD(@PathVariable(name = "keyword") String keyword) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            searchJDService.parseJD(keyword));
    }

    /**
     * 精确查找
     * 
     * @param keyWord
     * @param keyValue
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{keyWord}/{keyValue}/{pageNo}/{pageSize}")
    public ResultDTO<List<Map<String, Object>>> search(@PathVariable(name = "keyWord") String keyWord,
        @PathVariable(name = "keyValue") String keyValue, @PathVariable(name = "pageNo") Integer pageNo,
        @PathVariable(name = "pageSize") Integer pageSize) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            searchJDService.searchJDPage(keyWord, keyValue, pageNo, pageSize));
    }
}
