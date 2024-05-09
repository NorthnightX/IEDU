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
import com.iedu.service.domain.EduCompanyAddress;
import com.iedu.service.service.IEduCompanyAddressService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 公司地址Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/service/address")
public class EduCompanyAddressController extends BaseController
{
    @Autowired
    private IEduCompanyAddressService eduCompanyAddressService;

    /**
     * 查询公司地址列表
     */
    @PreAuthorize("@ss.hasPermi('service:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCompanyAddress eduCompanyAddress)
    {
        startPage();
        List<EduCompanyAddress> list = eduCompanyAddressService.selectEduCompanyAddressList(eduCompanyAddress);
        return getDataTable(list);
    }

    /**
     * 导出公司地址列表
     */
    @PreAuthorize("@ss.hasPermi('service:address:export')")
    @Log(title = "公司地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompanyAddress eduCompanyAddress)
    {
        List<EduCompanyAddress> list = eduCompanyAddressService.selectEduCompanyAddressList(eduCompanyAddress);
        ExcelUtil<EduCompanyAddress> util = new ExcelUtil<EduCompanyAddress>(EduCompanyAddress.class);
        util.exportExcel(response, list, "公司地址数据");
    }

    /**
     * 获取公司地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:address:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCompanyAddressService.selectEduCompanyAddressByEduId(eduId));
    }

    /**
     * 新增公司地址
     */
    @PreAuthorize("@ss.hasPermi('service:address:add')")
    @Log(title = "公司地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompanyAddress eduCompanyAddress)
    {
        return toAjax(eduCompanyAddressService.insertEduCompanyAddress(eduCompanyAddress));
    }

    /**
     * 修改公司地址
     */
    @PreAuthorize("@ss.hasPermi('service:address:edit')")
    @Log(title = "公司地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompanyAddress eduCompanyAddress)
    {
        return toAjax(eduCompanyAddressService.updateEduCompanyAddress(eduCompanyAddress));
    }

    /**
     * 删除公司地址
     */
    @PreAuthorize("@ss.hasPermi('service:address:remove')")
    @Log(title = "公司地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCompanyAddressService.deleteEduCompanyAddressByEduIds(eduIds));
    }


    @GetMapping("getAddressByCompany/{id}")
    public AjaxResult getAddressByCompany(@PathVariable Long id){
        return success(eduCompanyAddressService.selectAddressByCompany(id));
    }
}
