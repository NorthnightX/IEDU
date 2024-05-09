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
import com.iedu.service.domain.EduCompanyIndustry;
import com.iedu.service.service.IEduCompanyIndustryService;
import com.iedu.common.utils.poi.ExcelUtil;

/**
 * 公司行业Controller
 * 
 * @author zjz
 * @date 2024-04-02
 */
@RestController
@RequestMapping("/service/industry")
public class EduCompanyIndustryController extends BaseController
{
    @Autowired
    private IEduCompanyIndustryService eduCompanyIndustryService;

    /**
     * 查询公司行业列表
     */
    @PreAuthorize("@ss.hasPermi('service:industry:list')")
    @GetMapping("/list")
    public AjaxResult list(EduCompanyIndustry eduCompanyIndustry)
    {
        List<EduCompanyIndustry> list = eduCompanyIndustryService.selectEduCompanyIndustryList(eduCompanyIndustry);
        return success(list);
    }

    /**
     * 导出公司行业列表
     */
    @PreAuthorize("@ss.hasPermi('service:industry:export')")
    @Log(title = "公司行业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompanyIndustry eduCompanyIndustry)
    {
        List<EduCompanyIndustry> list = eduCompanyIndustryService.selectEduCompanyIndustryList(eduCompanyIndustry);
        ExcelUtil<EduCompanyIndustry> util = new ExcelUtil<EduCompanyIndustry>(EduCompanyIndustry.class);
        util.exportExcel(response, list, "公司行业数据");
    }

    /**
     * 获取公司行业详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:industry:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCompanyIndustryService.selectEduCompanyIndustryByEduId(eduId));
    }

    /**
     * 新增公司行业
     */
    @PreAuthorize("@ss.hasPermi('service:industry:add')")
    @Log(title = "公司行业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompanyIndustry eduCompanyIndustry)
    {
        return toAjax(eduCompanyIndustryService.insertEduCompanyIndustry(eduCompanyIndustry));
    }

    /**
     * 修改公司行业
     */
    @PreAuthorize("@ss.hasPermi('service:industry:edit')")
    @Log(title = "公司行业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompanyIndustry eduCompanyIndustry)
    {
        return toAjax(eduCompanyIndustryService.updateEduCompanyIndustry(eduCompanyIndustry));
    }

    /**
     * 删除公司行业
     */
    @PreAuthorize("@ss.hasPermi('service:industry:remove')")
    @Log(title = "公司行业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCompanyIndustryService.deleteEduCompanyIndustryByEduIds(eduIds));
    }

    @GetMapping("/listHaveChild")
    public AjaxResult listHaveChild()
    {
        return success(eduCompanyIndustryService.getIndustryListWithChildren());
    }
}
