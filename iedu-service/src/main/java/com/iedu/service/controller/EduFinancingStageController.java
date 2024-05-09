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
import com.iedu.service.domain.EduFinancingStage;
import com.iedu.service.service.IEduFinancingStageService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 融资阶段Controller
 * 
 * @author zjz
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/service/stage")
public class EduFinancingStageController extends BaseController
{
    @Autowired
    private IEduFinancingStageService eduFinancingStageService;

    /**
     * 查询融资阶段列表
     */
    @PreAuthorize("@ss.hasPermi('service:stage:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduFinancingStage eduFinancingStage)
    {
        startPage();
        List<EduFinancingStage> list = eduFinancingStageService.selectEduFinancingStageList(eduFinancingStage);
        return getDataTable(list);
    }

    /**
     * 导出融资阶段列表
     */
    @PreAuthorize("@ss.hasPermi('service:stage:export')")
    @Log(title = "融资阶段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduFinancingStage eduFinancingStage)
    {
        List<EduFinancingStage> list = eduFinancingStageService.selectEduFinancingStageList(eduFinancingStage);
        ExcelUtil<EduFinancingStage> util = new ExcelUtil<EduFinancingStage>(EduFinancingStage.class);
        util.exportExcel(response, list, "融资阶段数据");
    }

    /**
     * 获取融资阶段详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:stage:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduFinancingStageService.selectEduFinancingStageByEduId(eduId));
    }

    /**
     * 新增融资阶段
     */
    @PreAuthorize("@ss.hasPermi('service:stage:add')")
    @Log(title = "融资阶段", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduFinancingStage eduFinancingStage)
    {
        return toAjax(eduFinancingStageService.insertEduFinancingStage(eduFinancingStage));
    }

    /**
     * 修改融资阶段
     */
    @PreAuthorize("@ss.hasPermi('service:stage:edit')")
    @Log(title = "融资阶段", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduFinancingStage eduFinancingStage)
    {
        return toAjax(eduFinancingStageService.updateEduFinancingStage(eduFinancingStage));
    }

    /**
     * 删除融资阶段
     */
    @PreAuthorize("@ss.hasPermi('service:stage:remove')")
    @Log(title = "融资阶段", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduFinancingStageService.deleteEduFinancingStageByEduIds(eduIds));
    }
}
