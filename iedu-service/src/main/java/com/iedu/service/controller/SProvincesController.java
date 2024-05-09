package com.iedu.service.controller;

import java.util.List;
import javax.annotation.PostConstruct;
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
import com.iedu.service.domain.SProvinces;
import com.iedu.service.service.ISProvincesService;
import com.iedu.common.utils.poi.ExcelUtil;

/**
 * 全国省市Controller
 * 
 * @author zjz
 * @date 2024-04-02
 */
@RestController
@RequestMapping("/service/provinces")
public class SProvincesController extends BaseController
{
    @Autowired
    private ISProvincesService sProvincesService;

    /**
     * 查询全国省市列表
     */
    @PreAuthorize("@ss.hasPermi('service:provinces:list')")
    @GetMapping("/list")
    public AjaxResult list(SProvinces sProvinces)
    {
        List<SProvinces> list = sProvincesService.selectSProvincesList(sProvinces);
        return success(list);
    }

    /**
     * 导出全国省市列表
     */
    @PreAuthorize("@ss.hasPermi('service:provinces:export')")
    @Log(title = "全国省市", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SProvinces sProvinces)
    {
        List<SProvinces> list = sProvincesService.selectSProvincesList(sProvinces);
        ExcelUtil<SProvinces> util = new ExcelUtil<SProvinces>(SProvinces.class);
        util.exportExcel(response, list, "全国省市数据");
    }

    /**
     * 获取全国省市详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:provinces:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sProvincesService.selectSProvincesById(id));
    }

    /**
     * 新增全国省市
     */
    @PreAuthorize("@ss.hasPermi('service:provinces:add')")
    @Log(title = "全国省市", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SProvinces sProvinces)
    {
        return toAjax(sProvincesService.insertSProvinces(sProvinces));
    }

    /**
     * 修改全国省市
     */
    @PreAuthorize("@ss.hasPermi('service:provinces:edit')")
    @Log(title = "全国省市", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SProvinces sProvinces)
    {
        return toAjax(sProvincesService.updateSProvinces(sProvinces));
    }

    /**
     * 删除全国省市
     */
    @PreAuthorize("@ss.hasPermi('service:provinces:remove')")
    @Log(title = "全国省市", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sProvincesService.deleteSProvincesByIds(ids));
    }

    @GetMapping("/getListOfHierarchy")
    public AjaxResult getListOfHierarchy(){
        return success(sProvincesService.selectListOfHierarchy());
    }

}
