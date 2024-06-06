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
import com.iedu.service.domain.EduCompanyPic;
import com.iedu.service.service.IEduCompanyPicService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 企业照片Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
@RestController
@RequestMapping("/service/companyPic")
public class EduCompanyPicController extends BaseController
{
    @Autowired
    private IEduCompanyPicService eduCompanyPicService;

    /**
     * 查询企业照片列表
     */
    @PreAuthorize("@ss.hasPermi('service:companyPic:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCompanyPic eduCompanyPic)
    {
        startPage();
        List<EduCompanyPic> list = eduCompanyPicService.selectEduCompanyPicList(eduCompanyPic);
        return getDataTable(list);
    }

    /**
     * 导出企业照片列表
     */
    @PreAuthorize("@ss.hasPermi('service:companyPic:export')")
    @Log(title = "企业照片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCompanyPic eduCompanyPic)
    {
        List<EduCompanyPic> list = eduCompanyPicService.selectEduCompanyPicList(eduCompanyPic);
        ExcelUtil<EduCompanyPic> util = new ExcelUtil<EduCompanyPic>(EduCompanyPic.class);
        util.exportExcel(response, list, "企业照片数据");
    }

    /**
     * 获取企业照片详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:companyPic:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCompanyPicService.selectEduCompanyPicByEduId(eduId));
    }

    /**
     * 新增企业照片
     */
    @PreAuthorize("@ss.hasPermi('service:companyPic:add')")
    @Log(title = "企业照片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCompanyPic eduCompanyPic)
    {
        return toAjax(eduCompanyPicService.insertEduCompanyPic(eduCompanyPic));
    }

    /**
     * 修改企业照片
     */
    @PreAuthorize("@ss.hasPermi('service:companyPic:edit')")
    @Log(title = "企业照片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCompanyPic eduCompanyPic)
    {
        return toAjax(eduCompanyPicService.updateEduCompanyPic(eduCompanyPic));
    }

    /**
     * 删除企业照片
     */
    @PreAuthorize("@ss.hasPermi('service:companyPic:remove')")
    @Log(title = "企业照片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCompanyPicService.deleteEduCompanyPicByEduIds(eduIds));
    }
    @GetMapping("/getPicByCompany/{eduId}")
    public AjaxResult getPicByCompany(@PathVariable Long eduId){
        return success(eduCompanyPicService.selectByCompany(eduId));
    }
}
