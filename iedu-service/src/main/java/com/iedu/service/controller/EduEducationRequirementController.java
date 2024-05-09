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
import com.iedu.service.domain.EduEducationRequirement;
import com.iedu.service.service.IEduEducationRequirementService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 学历要求Controller
 * 
 * @author zjz
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/service/requirement")
public class EduEducationRequirementController extends BaseController
{
    @Autowired
    private IEduEducationRequirementService eduEducationRequirementService;

    /**
     * 查询学历要求列表
     */
    @PreAuthorize("@ss.hasPermi('service:requirement:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduEducationRequirement eduEducationRequirement)
    {
        startPage();
        List<EduEducationRequirement> list = eduEducationRequirementService.selectEduEducationRequirementList(eduEducationRequirement);
        return getDataTable(list);
    }

    /**
     * 导出学历要求列表
     */
    @PreAuthorize("@ss.hasPermi('service:requirement:export')")
    @Log(title = "学历要求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduEducationRequirement eduEducationRequirement)
    {
        List<EduEducationRequirement> list = eduEducationRequirementService.selectEduEducationRequirementList(eduEducationRequirement);
        ExcelUtil<EduEducationRequirement> util = new ExcelUtil<EduEducationRequirement>(EduEducationRequirement.class);
        util.exportExcel(response, list, "学历要求数据");
    }

    /**
     * 获取学历要求详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:requirement:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduEducationRequirementService.selectEduEducationRequirementByEduId(eduId));
    }

    /**
     * 新增学历要求
     */
    @PreAuthorize("@ss.hasPermi('service:requirement:add')")
    @Log(title = "学历要求", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduEducationRequirement eduEducationRequirement)
    {
        return toAjax(eduEducationRequirementService.insertEduEducationRequirement(eduEducationRequirement));
    }

    /**
     * 修改学历要求
     */
    @PreAuthorize("@ss.hasPermi('service:requirement:edit')")
    @Log(title = "学历要求", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduEducationRequirement eduEducationRequirement)
    {
        return toAjax(eduEducationRequirementService.updateEduEducationRequirement(eduEducationRequirement));
    }

    /**
     * 删除学历要求
     */
    @PreAuthorize("@ss.hasPermi('service:requirement:remove')")
    @Log(title = "学历要求", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduEducationRequirementService.deleteEduEducationRequirementByEduIds(eduIds));
    }

    @GetMapping("/all")
    public AjaxResult getAll()
    {
        return success(eduEducationRequirementService.selectAll());
    }
}
