package com.flyhigh.admin.controller.system;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.domain.entity.SysDictData;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.system.domain.vo.dictData.*;
import com.flyhigh.system.service.ISysDictDataService;
import com.flyhigh.system.service.ISysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典信息
 *
 * @author flyhigh
 */
@Api(value = "数据字典管理", tags = {"数据字典管理"})
@RestController
@RequestMapping("/admin/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    /**
     * 查询字典数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @PostMapping("/queryPage")
    public TableDataInfo<SysDictData> list(@RequestBody QueryDictDataPageRequest req) {
        List<SysDictData> list = dictDataService.selectDictDataList(req);
        return getDataTable(list);
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/info")
    public AjaxResult<SysDictData> getInfo(@RequestParam Long dictCode) {
        return AjaxResult.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation("根据字典类型查询字典数据信息(忽略父节点，返回全部信息)")
    @ApiImplicitParam(name = "dictType", value = "字典类型", required = true, dataType = "path", example = "1024", dataTypeClass = Long.class)
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult<List<SysDictData>> dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return AjaxResult.success(data);
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @PostMapping(value = "/queryList")
    public AjaxResult<List<SysDictData>> dictType(@RequestBody QueryDictDataByTypeRequest req) {
        String dictType = req.getDictType();
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (CollectionUtils.isNotEmpty(data) && req.getParentDictCode() != null) {
            return AjaxResult.success(data.stream()
                    .filter(item -> item.getParentDictCode().equals(req.getParentDictCode())).collect(Collectors.toList()));
        }
        return AjaxResult.success(data);
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult<Boolean> add(@Validated @RequestBody AddDictDataRequest req) {
        return toAjax(dictDataService.insertDictData(req));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult<Boolean> edit(@Validated @RequestBody EditDictDataRequest req) {
        return toAjax(dictDataService.updateDictData(req));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@Validated @RequestBody DeleteDistDataRequest req) {
        dictDataService.deleteDictDataByIds(req.getDictIds());
        return success();
    }
}
