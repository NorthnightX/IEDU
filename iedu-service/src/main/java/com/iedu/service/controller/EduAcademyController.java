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
import com.iedu.service.domain.EduAcademy;
import com.iedu.service.service.IEduAcademyService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 院校管理Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@RestController
@RequestMapping("/service/academy")
public class EduAcademyController extends BaseController
{
    @Autowired
    private IEduAcademyService eduAcademyService;

    /**
     * 查询院校管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:academy:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAcademy eduAcademy)
    {
        startPage();
        List<EduAcademy> list = eduAcademyService.selectEduAcademyList(eduAcademy);
        return getDataTable(list);
    }

    /**
     * 导出院校管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:academy:export')")
    @Log(title = "院校管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAcademy eduAcademy)
    {
        List<EduAcademy> list = eduAcademyService.selectEduAcademyList(eduAcademy);
        ExcelUtil<EduAcademy> util = new ExcelUtil<EduAcademy>(EduAcademy.class);
        util.exportExcel(response, list, "院校管理数据");
    }

    /**
     * 获取院校管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:academy:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduAcademyService.selectEduAcademyByEduId(eduId));
    }

    /**
     * 新增院校管理
     */
    @PreAuthorize("@ss.hasPermi('service:academy:add')")
    @Log(title = "院校管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAcademy eduAcademy)
    {
        return toAjax(eduAcademyService.insertEduAcademy(eduAcademy));
    }

    /**
     * 修改院校管理
     */
    @PreAuthorize("@ss.hasPermi('service:academy:edit')")
    @Log(title = "院校管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAcademy eduAcademy)
    {
        return toAjax(eduAcademyService.updateEduAcademy(eduAcademy));
    }

    /**
     * 删除院校管理
     */
    @PreAuthorize("@ss.hasPermi('service:academy:remove')")
    @Log(title = "院校管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduAcademyService.deleteEduAcademyByEduIds(eduIds));
    }
}
