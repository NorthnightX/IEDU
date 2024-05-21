package com.iedu.service.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iedu.service.domain.VO.CompanyVO;
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
import com.iedu.service.domain.EduCompany;
import com.iedu.service.service.IEduCompanyService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 公司Controller
 * 
 * @author zjz
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/service/company")
public class EduCompanyController extends BaseController
{
    @Autowired
    private IEduCompanyService eduCompanyService;

    /**
     * 查询公司列表
     */
    @PreAuthorize("@ss.hasPermi('service:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCompany eduCompany)
    {
        startPage();
        List<CompanyVO> list = eduCompanyService.selectEduCompanyList(eduCompany);
        return getDataTable(list);
    }

    /**
     * 导出公司列表
     */
    @PreAuthorize("@ss.hasPermi('service:company:export')")
    @Log(title = "公司", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompany eduCompany)
    {
        List<CompanyVO> list = eduCompanyService.selectEduCompanyList(eduCompany);
        ExcelUtil<CompanyVO> util = new ExcelUtil<>(CompanyVO.class);
        util.exportExcel(response, list, "公司数据");
    }

    /**
     * 获取公司详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:company:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCompanyService.selectEduCompanyByEduId(eduId));
    }

    /**
     * 新增公司
     */
    @PreAuthorize("@ss.hasPermi('service:company:add')")
    @Log(title = "公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompany eduCompany)
    {
        return toAjax(eduCompanyService.insertEduCompany(eduCompany));
    }

    /**
     * 修改公司
     */
    @PreAuthorize("@ss.hasPermi('service:company:edit')")
    @Log(title = "公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompany eduCompany)
    {
        return toAjax(eduCompanyService.updateEduCompany(eduCompany));
    }

    /**
     * 删除公司
     */
    @PreAuthorize("@ss.hasPermi('service:company:remove')")
    @Log(title = "公司", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCompanyService.deleteEduCompanyByEduIds(eduIds));
    }

    @GetMapping(value = "/all")
    public AjaxResult getAll()
    {
        return success(eduCompanyService.selectAll());
    }
}
