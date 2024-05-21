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
import com.iedu.service.domain.EduCompanyInformation;
import com.iedu.service.service.IEduCompanyInformationService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 公司信息Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/service/information")
public class EduCompanyInformationController extends BaseController
{
    @Autowired
    private IEduCompanyInformationService eduCompanyInformationService;

    /**
     * 查询公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('service:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCompanyInformation eduCompanyInformation)
    {
        startPage();
        List<EduCompanyInformation> list = eduCompanyInformationService.selectEduCompanyInformationList(eduCompanyInformation);
        return getDataTable(list);
    }

    /**
     * 导出公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('service:information:export')")
    @Log(title = "公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompanyInformation eduCompanyInformation)
    {
        List<EduCompanyInformation> list = eduCompanyInformationService.selectEduCompanyInformationList(eduCompanyInformation);
        ExcelUtil<EduCompanyInformation> util = new ExcelUtil<EduCompanyInformation>(EduCompanyInformation.class);
        util.exportExcel(response, list, "公司信息数据");
    }

    /**
     * 获取公司信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:information:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCompanyInformationService.selectEduCompanyInformationByEduId(eduId));
    }

    /**
     * 新增公司信息
     */
    @PreAuthorize("@ss.hasPermi('service:information:add')")
    @Log(title = "公司信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompanyInformation eduCompanyInformation)
    {
        return toAjax(eduCompanyInformationService.insertEduCompanyInformation(eduCompanyInformation));
    }

    /**
     * 修改公司信息
     */
    @PreAuthorize("@ss.hasPermi('service:information:edit')")
    @Log(title = "公司信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompanyInformation eduCompanyInformation)
    {
        return toAjax(eduCompanyInformationService.updateEduCompanyInformation(eduCompanyInformation));
    }

    /**
     * 删除公司信息
     */
    @PreAuthorize("@ss.hasPermi('service:information:remove')")
    @Log(title = "公司信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCompanyInformationService.deleteEduCompanyInformationByEduIds(eduIds));
    }


    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult GetInfoByCompanyId(@PathVariable("id") Long id)
    {
        return success(eduCompanyInformationService.selectEduCompanyInformationByCompanyId(id));
    }
    @GetMapping(value = "/getInfoAsList/{id}")
    public AjaxResult  getInformationAsListByCompany(@PathVariable("id") Long id)
    {
        EduCompanyInformation inf = eduCompanyInformationService.selectEduCompanyInformationByCompanyId(id);
        return success(new EduCompanyInformation[]{inf});
    }
}
