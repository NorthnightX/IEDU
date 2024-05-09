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
import com.iedu.service.domain.EduJobType;
import com.iedu.service.service.IEduJobTypeService;
import com.iedu.common.utils.poi.ExcelUtil;

/**
 * 工作类型Controller
 * 
 * @author zjz
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/service/type")
public class EduJobTypeController extends BaseController
{
    @Autowired
    private IEduJobTypeService eduJobTypeService;

    /**
     * 查询工作类型列表
     */
    @PreAuthorize("@ss.hasPermi('service:type:list')")
    @GetMapping("/list")
    public AjaxResult list(EduJobType eduJobType)
    {
        List<EduJobType> list = eduJobTypeService.selectEduJobTypeList(eduJobType);
        return success(list);
    }

    /**
     * 导出工作类型列表
     */
    @PreAuthorize("@ss.hasPermi('service:type:export')")
    @Log(title = "工作类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduJobType eduJobType)
    {
        List<EduJobType> list = eduJobTypeService.selectEduJobTypeList(eduJobType);
        ExcelUtil<EduJobType> util = new ExcelUtil<EduJobType>(EduJobType.class);
        util.exportExcel(response, list, "工作类型数据");
    }

    /**
     * 获取工作类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:type:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduJobTypeService.selectEduJobTypeByEduId(eduId));
    }

    /**
     * 新增工作类型
     */
    @PreAuthorize("@ss.hasPermi('service:type:add')")
    @Log(title = "工作类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduJobType eduJobType)
    {
        return toAjax(eduJobTypeService.insertEduJobType(eduJobType));
    }

    /**
     * 修改工作类型
     */
    @PreAuthorize("@ss.hasPermi('service:type:edit')")
    @Log(title = "工作类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduJobType eduJobType)
    {
        return toAjax(eduJobTypeService.updateEduJobType(eduJobType));
    }

    /**
     * 删除工作类型
     */
    @PreAuthorize("@ss.hasPermi('service:type:remove')")
    @Log(title = "工作类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduJobTypeService.deleteEduJobTypeByEduIds(eduIds));
    }

    @GetMapping("/all")
    public AjaxResult getAllType()
    {
        return success(eduJobTypeService.selectAll());
    }
}
