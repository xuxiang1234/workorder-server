package com.flyhigh.admin.controller.system;

import com.flyhigh.common.annotation.Log;
import com.flyhigh.common.constant.UserConstants;
import com.flyhigh.common.core.controller.BaseController;
import com.flyhigh.common.core.domain.AjaxResult;
import com.flyhigh.common.core.domain.entity.SysDictType;
import com.flyhigh.common.core.page.TableDataInfo;
import com.flyhigh.common.enums.BusinessType;
import com.flyhigh.system.domain.vo.dictType.DeleteDistTypeRequest;
import com.flyhigh.system.domain.vo.dictType.ModifyDictTypeRequest;
import com.flyhigh.system.domain.vo.dictType.QueryDictTypeRequest;
import com.flyhigh.system.domain.vo.dictType.TreeByDictVo;
import com.flyhigh.system.service.ISysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author flyhigh
 */
@Api(value = "数据字典类型管理", tags = {"数据字典类型管理"})
@RestController
@RequestMapping("/admin/dict/type")
public class SysDictTypeController extends BaseController {
    @Autowired
    private ISysDictTypeService dictTypeService;

    /**
     * 查询字典类型列表
     */
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @PostMapping("/list")
    public TableDataInfo<SysDictType> list(@RequestBody QueryDictTypeRequest req) {
        List<SysDictType> list = dictTypeService.selectDictTypeList(req);
        return getDataTable(list);
    }

    /**
     * 查询字典类型详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/info")
    public AjaxResult<SysDictType> getInfo(@RequestParam Long dictId) {
        return AjaxResult.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult<Boolean> add(@Validated @RequestBody ModifyDictTypeRequest req) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(req))) {
            return AjaxResult.error("新增字典'" + req.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.insertDictType(req));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult<Boolean> edit(@Validated @RequestBody ModifyDictTypeRequest req) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(req))) {
            return AjaxResult.error("修改字典'" + req.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.updateDictType(req));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult remove(@Validated @RequestBody DeleteDistTypeRequest dict) {
        dictTypeService.deleteDictTypeByIds(dict.getDictIds());
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @PostMapping("/refreshCache")
    public AjaxResult refreshCache() {
        dictTypeService.resetDictCache();
        return AjaxResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @PostMapping("/optionselect")
    public AjaxResult<List<SysDictType>> optionselect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }

    /**
     * 根据字典类型查询字典数据已树形结构返回
     *
     * @return
     */
    @GetMapping("/queryTreeByDictType")
    public AjaxResult<List<TreeByDictVo>> queryTreeByDictType(@RequestParam("dictType") String dictType) {
        return AjaxResult.success(dictTypeService.queryTreeByDictType(dictType));
    }
}
