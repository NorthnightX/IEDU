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
import com.iedu.service.domain.EduCarousel;
import com.iedu.service.service.IEduCarouselService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 轮播图管理Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@RestController
@RequestMapping("/service/carousel")
public class EduCarouselController extends BaseController
{
    @Autowired
    private IEduCarouselService eduCarouselService;

    /**
     * 查询轮播图管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:carousel:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduCarousel eduCarousel)
    {
        startPage();
        List<EduCarousel> list = eduCarouselService.selectEduCarouselList(eduCarousel);
        return getDataTable(list);
    }

    /**
     * 导出轮播图管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:carousel:export')")
    @Log(title = "轮播图管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduCarousel eduCarousel)
    {
        List<EduCarousel> list = eduCarouselService.selectEduCarouselList(eduCarousel);
        ExcelUtil<EduCarousel> util = new ExcelUtil<EduCarousel>(EduCarousel.class);
        util.exportExcel(response, list, "轮播图管理数据");
    }

    /**
     * 获取轮播图管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:carousel:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduCarouselService.selectEduCarouselByEduId(eduId));
    }

    /**
     * 新增轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('service:carousel:add')")
    @Log(title = "轮播图管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduCarousel eduCarousel)
    {
        return toAjax(eduCarouselService.insertEduCarousel(eduCarousel));
    }

    /**
     * 修改轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('service:carousel:edit')")
    @Log(title = "轮播图管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduCarousel eduCarousel)
    {
        return toAjax(eduCarouselService.updateEduCarousel(eduCarousel));
    }

    /**
     * 删除轮播图管理
     */
    @PreAuthorize("@ss.hasPermi('service:carousel:remove')")
    @Log(title = "轮播图管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduCarouselService.deleteEduCarouselByEduIds(eduIds));
    }
    @GetMapping("/u/list")
    public AjaxResult getAllList()
    {
        return success(eduCarouselService.selectAllCarouselList());
    }
}
