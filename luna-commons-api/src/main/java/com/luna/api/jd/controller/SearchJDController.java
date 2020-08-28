package com.luna.api.jd.controller;

import com.luna.api.jd.dto.SearchJDKeyWordDTO;
import com.luna.api.jd.service.SearchJDService;
import com.luna.common.anno.MyValid;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Package: com.luna.api.jd.controller
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
     * @param pageNo
     * @param pageSize
     * @return
     */
    @PostMapping("/search/{pageNo}/{pageSize}")
    public ResultDTO<List<Map<String, Object>>> search(@RequestBody @MyValid SearchJDKeyWordDTO searchJDKeyWordDTO,
        @PathVariable(name = "pageNo") Integer pageNo,
        @PathVariable(name = "pageSize") Integer pageSize) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            searchJDService.searchJDPage(searchJDKeyWordDTO.getKeyWord(), searchJDKeyWordDTO.getKeyValue(), pageNo,
                pageSize));
    }
}
