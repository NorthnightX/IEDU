package com.iedu.service.controller;

import com.iedu.common.annotation.Log;
import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.common.core.page.TableDataInfo;
import com.iedu.common.enums.BusinessType;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.service.domain.EduRecruit;
import com.iedu.service.domain.Page;
import com.iedu.service.domain.VO.RecruitVO;
import com.iedu.service.service.IEduRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        List<RecruitVO> list = eduRecruitService.selectEduRecruitList(eduRecruit);
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
        List<RecruitVO> list = eduRecruitService.selectEduRecruitList(eduRecruit);
        ExcelUtil<RecruitVO> util = new ExcelUtil<>(RecruitVO.class);
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

    @GetMapping("/u/exhibit")
    public AjaxResult getInfos()
    {
        return success(eduRecruitService.selectRecruitToShow());
    }

    @GetMapping("/u/details/{id}")
    public AjaxResult getDetails(@PathVariable int id)
    {
        return success(eduRecruitService.selectRecruitDetailById(id));
    }

    @GetMapping("/u/getRecruitByCondition")
    public AjaxResult getRecruitByKeyWord(@RequestParam("text") String text,
                                          @RequestParam("jobTypeId") Integer jobTypeId,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("pageNum") Integer pageNum){

        text = "%" + text + "%";
        List<RecruitVO> list = eduRecruitService.selectRecruitByCondition(text, pageSize, pageNum, jobTypeId);
        int total = eduRecruitService.selectCountByCondition(text, jobTypeId);
        Page<RecruitVO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        page.setTotal(total);
        return success(page);
    }
}
