package com.iedu.service.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iedu.common.annotation.Log;
import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.common.enums.BusinessType;
import com.iedu.service.domain.EduIntegrateType;
import com.iedu.service.service.IEduIntegrateTypeService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 产教融合类型管理Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@RestController
@RequestMapping("/service/integrateType")
public class EduIntegrateTypeController extends BaseController
{
    @Autowired
    private IEduIntegrateTypeService eduIntegrateTypeService;

    /**
     * 查询产教融合类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:integrateType:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduIntegrateType eduIntegrateType)
    {
        startPage();
        List<EduIntegrateType> list = eduIntegrateTypeService.selectEduIntegrateTypeList(eduIntegrateType);
        return getDataTable(list);
    }

    /**
     * 导出产教融合类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:integrateType:export')")
    @Log(title = "产教融合类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduIntegrateType eduIntegrateType)
    {
        List<EduIntegrateType> list = eduIntegrateTypeService.selectEduIntegrateTypeList(eduIntegrateType);
        ExcelUtil<EduIntegrateType> util = new ExcelUtil<EduIntegrateType>(EduIntegrateType.class);
        util.exportExcel(response, list, "产教融合类型管理数据");
    }

    /**
     * 获取产教融合类型管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:integrateType:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduIntegrateTypeService.selectEduIntegrateTypeByEduId(eduId));
    }

    /**
     * 新增产教融合类型管理
     */
    @PreAuthorize("@ss.hasPermi('service:integrateType:add')")
    @Log(title = "产教融合类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduIntegrateType eduIntegrateType)
    {
        return toAjax(eduIntegrateTypeService.insertEduIntegrateType(eduIntegrateType));
    }

    /**
     * 修改产教融合类型管理
     */
    @PreAuthorize("@ss.hasPermi('service:integrateType:edit')")
    @Log(title = "产教融合类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduIntegrateType eduIntegrateType)
    {
        return toAjax(eduIntegrateTypeService.updateEduIntegrateType(eduIntegrateType));
    }

    /**
     * 删除产教融合类型管理
     */
    @PreAuthorize("@ss.hasPermi('service:integrateType:remove')")
    @Log(title = "产教融合类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduIntegrateTypeService.deleteEduIntegrateTypeByEduIds(eduIds));
    }

    @GetMapping("/u/allType")
    public AjaxResult getAllIntegrateType(){
        return success(eduIntegrateTypeService.getAllIntegrateType());
    }
}
