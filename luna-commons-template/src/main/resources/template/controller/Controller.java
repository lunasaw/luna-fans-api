package ${package_controller};
import ${package_pojo}.${Table};
import ${package_service}.${Table}Service;
import com.github.pagehelper.PageInfo;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
<#if swagger==true>import io.swagger.annotations.*;</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:luna
 * @Description:
 * @Date 2019/6/14 0:18
 *****/
<#if swagger==true>@Api(value = "${Table}Controller")</#if>
@RestController
@RequestMapping("/${table}")
@CrossOrigin
public class ${Table}Controller {

    @Autowired
    private ${Table}Service ${table}Service;

    /***
     * ${Table}分页条件搜索实现
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}条件分页查询",notes = "分页条件查询${Table}方法详情",tags = {"${Table}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    </#if>
    @PostMapping(value = "/search/{page}/{size}" )
    public ResultDTO<PageInfo> findPage(@RequestBody(required = false) <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = false)</#if> ${Table} ${table}, @PathVariable  int page, @PathVariable  int size){
        //调用${Table}Service实现分页条件查询${Table}
        PageInfo<${Table}> pageInfo = ${table}Service.findPage(${table}, page, size);
        return new ResultDTO(true,ResultCode.SUCCESS,"查询成功",pageInfo);
    }

    /***
     * ${Table}分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}分页查询",notes = "分页查询${Table}方法详情",tags = {"${Table}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    </#if>
    @GetMapping(value = "/search/{page}/{size}" )
    public ResultDTO<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用${Table}Service实现分页查询${Table}
        PageInfo<${Table}> pageInfo = ${table}Service.findPage(page, size);
        return new ResultDTO<PageInfo>(true,ResultCode.SUCCESS,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索数据
     * @param ${table}
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}条件查询",notes = "条件查询${Table}方法详情",tags = {"${Table}Controller"})
    </#if>
    @PostMapping(value = "/search" )
    public ResultDTO<List<${Table}>> findList(@RequestBody(required = false) <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = false)</#if> ${Table} ${table}){
        //调用${Table}Service实现条件查询${Table}
        List<${Table}> list = ${table}Service.findList(${table});
        return new ResultDTO<List<${Table}>>(true,ResultCode.SUCCESS,"查询成功",list);
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}根据ID删除",notes = "根据ID删除${Table}方法详情",tags = {"${Table}Controller"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @DeleteMapping(value = "/{id}" )
    public ResultDTO delete(@PathVariable ${keyType} id){
        //调用${Table}Service实现根据主键删除
        ${table}Service.delete(id);
        return new ResultDTO(true,ResultCode.SUCCESS,"删除成功");
    }

    /***
     * 修改${Table}数据
     * @param ${table}
     * @param id
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}根据ID修改",notes = "根据ID修改${Table}方法详情",tags = {"${Table}Controller"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @PutMapping(value="/{id}")
    public ResultDTO update(@RequestBody <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = false)</#if> ${Table} ${table},@PathVariable ${keyType} id){
        //设置主键值
        ${table}.${keySetMethod}(id);
        //调用${Table}Service实现修改${Table}
        ${table}Service.update(${table});
        return new ResultDTO(true,ResultCode.SUCCESS,"修改成功");
    }

    /***
     * 新增${Table}数据
     * @param ${table}
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}添加",notes = "添加${Table}方法详情",tags = {"${Table}Controller"})
    </#if>
    @PostMapping
    public ResultDTO add(@RequestBody  <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = true)</#if> ${Table} ${table}){
        //调用${Table}Service实现添加${Table}
        ${table}Service.add(${table});
        return new ResultDTO(true,ResultCode.SUCCESS,"添加成功");
    }

    /***
     * 根据ID查询${Table}数据
     * @param id
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}根据ID查询",notes = "根据ID查询${Table}方法详情",tags = {"${Table}Controller"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @GetMapping("/{id}")
    public ResultDTO<${Table}> findById(@PathVariable ${keyType} id){
        //调用${Table}Service实现根据主键查询${Table}
        ${Table} ${table} = ${table}Service.findById(id);
        return new ResultDTO<${Table}>(true,ResultCode.SUCCESS,"查询成功",${table});
    }

    /***
     * 查询${Table}全部数据
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "查询所有${Table}",notes = "查询所${Table}有方法详情",tags = {"${Table}Controller"})
    </#if>
    @GetMapping
    public ResultDTO<List<${Table}>> findAll(){
        //调用${Table}Service实现查询所有${Table}
        List<${Table}> list = ${table}Service.findAll();
        return new ResultDTO<List<${Table}>>(true, ResultCode.SUCCESS,"查询成功",list) ;
    }
}
