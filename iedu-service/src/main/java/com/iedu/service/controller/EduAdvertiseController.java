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
import com.iedu.service.domain.EduAdvertise;
import com.iedu.service.service.IEduAdvertiseService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 广告Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-06-03
 */
@RestController
@RequestMapping("/service/advertise")
public class EduAdvertiseController extends BaseController
{
    @Autowired
    private IEduAdvertiseService eduAdvertiseService;

    /**
     * 查询广告列表
     */
    @PreAuthorize("@ss.hasPermi('service:advertise:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAdvertise eduAdvertise)
    {
        startPage();
        List<EduAdvertise> list = eduAdvertiseService.selectEduAdvertiseList(eduAdvertise);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @PreAuthorize("@ss.hasPermi('service:advertise:export')")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAdvertise eduAdvertise)
    {
        List<EduAdvertise> list = eduAdvertiseService.selectEduAdvertiseList(eduAdvertise);
        ExcelUtil<EduAdvertise> util = new ExcelUtil<EduAdvertise>(EduAdvertise.class);
        util.exportExcel(response, list, "广告数据");
    }

    /**
     * 获取广告详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:advertise:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduAdvertiseService.selectEduAdvertiseByEduId(eduId));
    }

    /**
     * 新增广告
     */
    @PreAuthorize("@ss.hasPermi('service:advertise:add')")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAdvertise eduAdvertise)
    {
        return toAjax(eduAdvertiseService.insertEduAdvertise(eduAdvertise));
    }

    /**
     * 修改广告
     */
    @PreAuthorize("@ss.hasPermi('service:advertise:edit')")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAdvertise eduAdvertise)
    {
        return toAjax(eduAdvertiseService.updateEduAdvertise(eduAdvertise));
    }

    /**
     * 删除广告
     */
    @PreAuthorize("@ss.hasPermi('service:advertise:remove')")
    @Log(title = "广告", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduAdvertiseService.deleteEduAdvertiseByEduIds(eduIds));
    }

    @GetMapping("/u/getAdvertiseOfSearch")
    public AjaxResult getAdvertiseOfSearch(){
        return success(eduAdvertiseService.getSearch());
    }
}
