package com.luna.api.jd.controller;

import com.luna.api.jd.dto.SearchJDQuayParamDTO;
import com.luna.api.jd.service.SearchJDService;
import com.luna.common.anno.MyValid;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.entity.Page;
import io.swagger.annotations.*;
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
@Api(value = "SearchJDController", tags = "京东搜索")
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
    @ApiOperation(value = "JD搜索导入ES", notes = "JD搜索导入ES方法详情", tags = {"SearchJDController"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "keyword", value = "关键字", required = true, dataType = "String"),
    })
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
    @ApiOperation(value = "JD搜索精确查找", notes = "JD搜索精确查找方法详情", tags = {"SearchJDController"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "pageNo", value = "当前页", required = true, dataType = "Long"),
        @ApiImplicitParam(paramType = "path", name = "pageSize", value = "每页显示条数", required = true, dataType = "Long")
    })
    @PostMapping("/search/{pageNo}/{pageSize}")
    public ResultDTO<List<Map<String, Object>>> search(@RequestBody @MyValid @ApiParam(name = "SearchJDQuayParamDTO对象",
        value = "传入JSON数据", required = false) SearchJDQuayParamDTO searchJDQuayParamDTO,
        @PathVariable(name = "pageNo") Integer pageNo,
        @PathVariable(name = "pageSize") Integer pageSize) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            searchJDService.searchJDField(searchJDQuayParamDTO.getKeyWord(), searchJDQuayParamDTO.getKeyValue(), pageNo,
                pageSize));
    }

    /**
     * 精确查找 分页显示
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "JD搜索精确查找", notes = "JD搜索精确查找方法详情", tags = {"SearchJDController"})
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "pageNo", value = "当前页", required = true, dataType = "Long"),
        @ApiImplicitParam(paramType = "path", name = "pageSize", value = "每页显示条数", required = true, dataType = "Long")
    })
    @PostMapping("/searchPage/{pageNo}/{pageSize}")
    public ResultDTO<Page> searchByPage(@RequestBody @MyValid @ApiParam(name = "SearchJDQuayParamDTO对象",
        value = "传入JSON数据", required = false) SearchJDQuayParamDTO searchJDQuayParamDTO,
        @PathVariable(name = "pageNo") Integer pageNo,
        @PathVariable(name = "pageSize") Integer pageSize) {
        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            searchJDService.searchJDPage(searchJDQuayParamDTO.getKeyWord(), searchJDQuayParamDTO.getKeyValue(), pageNo,
                pageSize));
    }
}
