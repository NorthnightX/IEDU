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
import com.iedu.service.domain.EduRecruit;
import com.iedu.service.service.IEduRecruitService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 招聘信息Controller
 * 
 * @author zjz
 * @date 2024-04-05
 */
@RestController
@RequestMapping("/service/recruit")
public class EduRecruitController extends BaseController
{
    @Autowired
    private IEduRecruitService eduRecruitService;

    /**
     * 查询招聘信息列表
     */
    @PreAuthorize("@ss.hasPermi('service:recruit:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduRecruit eduRecruit)
    {
        startPage();
        List<EduRecruit> list = eduRecruitService.selectEduRecruitList(eduRecruit);
        return getDataTable(list);
    }

    /**
     * 导出招聘信息列表
     */
    @PreAuthorize("@ss.hasPermi('service:recruit:export')")
    @Log(title = "招聘信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduRecruit eduRecruit)
    {
        List<EduRecruit> list = eduRecruitService.selectEduRecruitList(eduRecruit);
        ExcelUtil<EduRecruit> util = new ExcelUtil<EduRecruit>(EduRecruit.class);
        util.exportExcel(response, list, "招聘信息数据");
    }

    /**
     * 获取招聘信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:recruit:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduRecruitService.selectEduRecruitByEduId(eduId));
    }

    /**
     * 新增招聘信息
     */
    @PreAuthorize("@ss.hasPermi('service:recruit:add')")
    @Log(title = "招聘信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduRecruit eduRecruit)
    {
        return toAjax(eduRecruitService.insertEduRecruit(eduRecruit));
    }

    /**
     * 修改招聘信息
     */
    @PreAuthorize("@ss.hasPermi('service:recruit:edit')")
    @Log(title = "招聘信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduRecruit eduRecruit)
    {
        return toAjax(eduRecruitService.updateEduRecruit(eduRecruit));
    }

    /**
     * 删除招聘信息
     */
    @PreAuthorize("@ss.hasPermi('service:recruit:remove')")
    @Log(title = "招聘信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduRecruitService.deleteEduRecruitByEduIds(eduIds));
    }

    @GetMapping("/exhibit")
    public AjaxResult getInfos()
    {
        return success(eduRecruitService.selectRecruitToShow());
    }

    @GetMapping("/details/{id}")
    public AjaxResult getDetails(@PathVariable int id)
    {
        return success(eduRecruitService.selectRecruitDetailById(id));
    }
}
