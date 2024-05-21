package com.iedu.service.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iedu.service.domain.EduNews;
import com.iedu.service.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iedu.common.annotation.Log;
import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.common.enums.BusinessType;
import com.iedu.service.domain.EduArticle;
import com.iedu.service.service.IEduArticleService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 动态Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@RestController
@RequestMapping("/service/article")
public class EduArticleController extends BaseController
{
    @Autowired
    private IEduArticleService eduArticleService;

    /**
     * 查询动态列表
     */
    @PreAuthorize("@ss.hasPermi('service:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduArticle eduArticle)
    {
        startPage();
        List<EduArticle> list = eduArticleService.selectEduArticleList(eduArticle);
        return getDataTable(list);
    }

    /**
     * 导出动态列表
     */
    @PreAuthorize("@ss.hasPermi('service:article:export')")
    @Log(title = "动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduArticle eduArticle)
    {
        List<EduArticle> list = eduArticleService.selectEduArticleList(eduArticle);
        ExcelUtil<EduArticle> util = new ExcelUtil<EduArticle>(EduArticle.class);
        util.exportExcel(response, list, "动态数据");
    }

    /**
     * 获取动态详细信息
     */
    @GetMapping(value = "/u/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduArticleService.selectEduArticleByEduId(eduId));
    }

    /**
     * 新增动态
     */
    @PreAuthorize("@ss.hasPermi('service:article:add')")
    @Log(title = "动态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduArticle eduArticle)
    {
        return toAjax(eduArticleService.insertEduArticle(eduArticle));
    }

    /**
     * 修改动态
     */
    @PreAuthorize("@ss.hasPermi('service:article:edit')")
    @Log(title = "动态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduArticle eduArticle)
    {
        return toAjax(eduArticleService.updateEduArticle(eduArticle));
    }

    /**
     * 删除动态
     */
    @PreAuthorize("@ss.hasPermi('service:article:remove')")
    @Log(title = "动态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduArticleService.deleteEduArticleByEduIds(eduIds));
    }
    @GetMapping("/u/getCurrentArticle")
    public AjaxResult getCurrentArticle()
    {
        return success(eduArticleService.selectCurrentArticle());
    }

    @GetMapping("/u/getArticleByKeyword")
    public AjaxResult getArticleByKeyword(@RequestParam("text") String text,
                                       @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        text = "%" + text + "%";
        List<EduArticle> list = eduArticleService.selectArticleByKeyWord(text, pageSize, pageNum);
        int total = eduArticleService.selectCountByKeyWord(text);
        Page<EduArticle> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        page.setTotal(total);
        return success(page);
    }
}
