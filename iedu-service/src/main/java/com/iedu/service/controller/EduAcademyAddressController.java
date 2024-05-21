package com.iedu.service.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iedu.service.domain.VO.AcademyAddressVO;
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
import com.iedu.service.domain.EduAcademyAddress;
import com.iedu.service.service.IEduAcademyAddressService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 学校地址Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@RestController
@RequestMapping("/service/academyAddress")
public class EduAcademyAddressController extends BaseController
{
    @Autowired
    private IEduAcademyAddressService eduAcademyAddressService;

    /**
     * 查询学校地址列表
     */
    @PreAuthorize("@ss.hasPermi('service:academyAddress:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAcademyAddress eduAcademyAddress)
    {
        startPage();
        List<AcademyAddressVO> list = eduAcademyAddressService.selectEduAcademyAddressList(eduAcademyAddress);
        return getDataTable(list);
    }

    /**
     * 导出学校地址列表
     */
    @PreAuthorize("@ss.hasPermi('service:academyAddress:export')")
    @Log(title = "学校地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAcademyAddress eduAcademyAddress)
    {
        List<AcademyAddressVO> list = eduAcademyAddressService.selectEduAcademyAddressList(eduAcademyAddress);
        ExcelUtil<AcademyAddressVO> util = new ExcelUtil<>(AcademyAddressVO.class);
        util.exportExcel(response, list, "学校地址数据");
    }

    /**
     * 获取学校地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:academyAddress:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduAcademyAddressService.selectEduAcademyAddressByEduId(eduId));
    }

    /**
     * 新增学校地址
     */
    @PreAuthorize("@ss.hasPermi('service:academyAddress:add')")
    @Log(title = "学校地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAcademyAddress eduAcademyAddress)
    {
        return toAjax(eduAcademyAddressService.insertEduAcademyAddress(eduAcademyAddress));
    }

    /**
     * 修改学校地址
     */
    @PreAuthorize("@ss.hasPermi('service:academyAddress:edit')")
    @Log(title = "学校地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAcademyAddress eduAcademyAddress)
    {
        return toAjax(eduAcademyAddressService.updateEduAcademyAddress(eduAcademyAddress));
    }

    /**
     * 删除学校地址
     */
    @PreAuthorize("@ss.hasPermi('service:academyAddress:remove')")
    @Log(title = "学校地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduAcademyAddressService.deleteEduAcademyAddressByEduIds(eduIds));
    }

    @GetMapping("/getByAcademy/{id}")
    public AjaxResult getByAcademy(@PathVariable Long id)
    {
        return success(eduAcademyAddressService.selectAddressByAcademyId(id));
    }

    @GetMapping("/getByAcademyAsList/{id}")
    public AjaxResult getByAcademyAsList(@PathVariable Long id)
    {
        return success(new AcademyAddressVO[]{eduAcademyAddressService.selectAddressByAcademyId(id)});
    }
}
