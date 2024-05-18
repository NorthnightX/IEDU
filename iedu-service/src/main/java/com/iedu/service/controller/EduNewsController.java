package com.iedu.service.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iedu.service.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iedu.common.annotation.Log;
import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.common.enums.BusinessType;
import com.iedu.service.domain.EduNews;
import com.iedu.service.service.IEduNewsService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 新闻Controller
 * 
 * @author zjz
 * @date 2024-04-01
 */
@RestController
@RequestMapping("/service/news")
public class EduNewsController extends BaseController
{
    @Autowired
    private IEduNewsService eduNewsService;

    /**
     * 查询新闻列表
     */
    @PreAuthorize("@ss.hasPermi('service:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduNews eduNews)
    {
        startPage();
        List<EduNews> list = eduNewsService.selectEduNewsList(eduNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('service:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduNews eduNews)
    {
        List<EduNews> list = eduNewsService.selectEduNewsList(eduNews);
        ExcelUtil<EduNews> util = new ExcelUtil<EduNews>(EduNews.class);
        util.exportExcel(response, list, "新闻数据");
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:news:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduNewsService.selectEduNewsByEduId(eduId));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('service:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduNews eduNews)
    {
        return toAjax(eduNewsService.insertEduNews(eduNews));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('service:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduNews eduNews)
    {
        return toAjax(eduNewsService.updateEduNews(eduNews));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('service:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduNewsService.deleteEduNewsByEduIds(eduIds));
    }


    @GetMapping("/u/getCurrentNews")
    public AjaxResult getCurrentNews()
    {
        return success(eduNewsService.selectCurrentNews());
    }

    @GetMapping("/u/article")
    public AjaxResult getArticleById(@RequestParam Long eduId)
    {
        return success(eduNewsService.selectEduNewsByEduId(eduId));
    }

    @GetMapping("/u/getNewsByKeyword")
    public AjaxResult getNewsByKeyword(@RequestParam("text") String text,
                                       @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        List<EduNews> list = eduNewsService.selectNewsByKeyWord(text, pageSize, pageNum);
        int total = eduNewsService.selectCountByKeyWord(text);
        Page<EduNews> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        page.setTotal(total);
        return success(page);
    }
}
