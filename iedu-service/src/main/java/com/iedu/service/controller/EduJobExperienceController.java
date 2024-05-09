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
import com.iedu.service.domain.EduJobExperience;
import com.iedu.service.service.IEduJobExperienceService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 工作经验Controller
 * 
 * @author zjz
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/service/experience")
public class EduJobExperienceController extends BaseController
{
    @Autowired
    private IEduJobExperienceService eduJobExperienceService;

    /**
     * 查询工作经验列表
     */
    @PreAuthorize("@ss.hasPermi('service:experience:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduJobExperience eduJobExperience)
    {
        startPage();
        List<EduJobExperience> list = eduJobExperienceService.selectEduJobExperienceList(eduJobExperience);
        return getDataTable(list);
    }

    /**
     * 导出工作经验列表
     */
    @PreAuthorize("@ss.hasPermi('service:experience:export')")
    @Log(title = "工作经验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduJobExperience eduJobExperience)
    {
        List<EduJobExperience> list = eduJobExperienceService.selectEduJobExperienceList(eduJobExperience);
        ExcelUtil<EduJobExperience> util = new ExcelUtil<EduJobExperience>(EduJobExperience.class);
        util.exportExcel(response, list, "工作经验数据");
    }

    /**
     * 获取工作经验详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:experience:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduJobExperienceService.selectEduJobExperienceByEduId(eduId));
    }

    /**
     * 新增工作经验
     */
    @PreAuthorize("@ss.hasPermi('service:experience:add')")
    @Log(title = "工作经验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduJobExperience eduJobExperience)
    {
        return toAjax(eduJobExperienceService.insertEduJobExperience(eduJobExperience));
    }

    /**
     * 修改工作经验
     */
    @PreAuthorize("@ss.hasPermi('service:experience:edit')")
    @Log(title = "工作经验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduJobExperience eduJobExperience)
    {
        return toAjax(eduJobExperienceService.updateEduJobExperience(eduJobExperience));
    }

    /**
     * 删除工作经验
     */
    @PreAuthorize("@ss.hasPermi('service:experience:remove')")
    @Log(title = "工作经验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduJobExperienceService.deleteEduJobExperienceByEduIds(eduIds));
    }

    @GetMapping("/all")
    public AjaxResult getAll()
    {
        return success(eduJobExperienceService.selectAll());
    }
}
