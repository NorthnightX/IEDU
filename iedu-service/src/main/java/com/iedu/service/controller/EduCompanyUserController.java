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
import com.iedu.service.domain.EduCompanyUser;
import com.iedu.service.service.IEduCompanyUserService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 公司用户关系Controller
 * 
 * @author zjz
 * @date 2024-04-19
 */
@RestController
@RequestMapping("/service/cu")
public class EduCompanyUserController extends BaseController
{
    @Autowired
    private IEduCompanyUserService eduCompanyUserService;

    /**
     * 查询公司用户关系列表
     */
    @PreAuthorize("@ss.hasPermi('service:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCompanyUser eduCompanyUser)
    {
        startPage();
        List<EduCompanyUser> list = eduCompanyUserService.selectEduCompanyUserList(eduCompanyUser);
        return getDataTable(list);
    }

    /**
     * 导出公司用户关系列表
     */
    @PreAuthorize("@ss.hasPermi('service:user:export')")
    @Log(title = "公司用户关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompanyUser eduCompanyUser)
    {
        List<EduCompanyUser> list = eduCompanyUserService.selectEduCompanyUserList(eduCompanyUser);
        ExcelUtil<EduCompanyUser> util = new ExcelUtil<>(EduCompanyUser.class);
        util.exportExcel(response, list, "公司用户关系数据");
    }

    /**
     * 获取公司用户关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:user:query')")
    @GetMapping(value = "/{eduCompanyId}")
    public AjaxResult getInfo(@PathVariable("eduCompanyId") Long eduCompanyId)
    {
        return success(eduCompanyUserService.selectEduCompanyUserByEduCompanyId(eduCompanyId));
    }

    /**
     * 新增公司用户关系
     */
    @Log(title = "公司用户关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompanyUser eduCompanyUser)
    {
        return toAjax(eduCompanyUserService.insertEduCompanyUser(eduCompanyUser));
    }

    /**
     * 修改公司用户关系
     */
    @Log(title = "公司用户关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompanyUser eduCompanyUser)
    {
        return toAjax(eduCompanyUserService.updateEduCompanyUser(eduCompanyUser));
    }

    /**
     * 删除公司用户关系
     */
    @PreAuthorize("@ss.hasPermi('service:user:remove')")
    @Log(title = "公司用户关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduCompanyIds}")
    public AjaxResult remove(@PathVariable Long[] eduCompanyIds)
    {
        return toAjax(eduCompanyUserService.deleteEduCompanyUserByEduCompanyIds(eduCompanyIds));
    }

    @GetMapping( "getUserCompany/{id}")
    public AjaxResult getCompanyId(@PathVariable("id") Long id)
    {
        return success(eduCompanyUserService.selectByUserId(id));
    }
}
