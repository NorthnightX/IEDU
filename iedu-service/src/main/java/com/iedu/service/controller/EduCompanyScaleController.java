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
import com.iedu.service.domain.EduCompanyScale;
import com.iedu.service.service.IEduCompanyScaleService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 公司规模Controller
 * 
 * @author zjz
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/service/scale")
public class EduCompanyScaleController extends BaseController
{
    @Autowired
    private IEduCompanyScaleService eduCompanyScaleService;

    /**
     * 查询公司规模列表
     */
    @PreAuthorize("@ss.hasPermi('service:scale:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCompanyScale eduCompanyScale)
    {
        startPage();
        List<EduCompanyScale> list = eduCompanyScaleService.selectEduCompanyScaleList(eduCompanyScale);
        return getDataTable(list);
    }

    /**
     * 导出公司规模列表
     */
    @PreAuthorize("@ss.hasPermi('service:scale:export')")
    @Log(title = "公司规模", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompanyScale eduCompanyScale)
    {
        List<EduCompanyScale> list = eduCompanyScaleService.selectEduCompanyScaleList(eduCompanyScale);
        ExcelUtil<EduCompanyScale> util = new ExcelUtil<EduCompanyScale>(EduCompanyScale.class);
        util.exportExcel(response, list, "公司规模数据");
    }

    /**
     * 获取公司规模详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:scale:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCompanyScaleService.selectEduCompanyScaleByEduId(eduId));
    }

    /**
     * 新增公司规模
     */
    @PreAuthorize("@ss.hasPermi('service:scale:add')")
    @Log(title = "公司规模", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompanyScale eduCompanyScale)
    {
        return toAjax(eduCompanyScaleService.insertEduCompanyScale(eduCompanyScale));
    }

    /**
     * 修改公司规模
     */
    @PreAuthorize("@ss.hasPermi('service:scale:edit')")
    @Log(title = "公司规模", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompanyScale eduCompanyScale)
    {
        return toAjax(eduCompanyScaleService.updateEduCompanyScale(eduCompanyScale));
    }

    /**
     * 删除公司规模
     */
    @PreAuthorize("@ss.hasPermi('service:scale:remove')")
    @Log(title = "公司规模", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCompanyScaleService.deleteEduCompanyScaleByEduIds(eduIds));
    }
}
