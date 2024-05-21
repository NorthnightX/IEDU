package com.iedu.service.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iedu.service.domain.Page;
import com.iedu.service.domain.VO.IntegrateVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iedu.common.annotation.Log;
import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.common.enums.BusinessType;
import com.iedu.service.domain.EduIntegrate;
import com.iedu.service.service.IEduIntegrateService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 产教融合管理Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@RestController
@RequestMapping("/service/integrate")
public class EduIntegrateController extends BaseController
{
    @Autowired
    private IEduIntegrateService eduIntegrateService;

    /**
     * 查询产教融合管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:integrate:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduIntegrate eduIntegrate)
    {
        startPage();
        List<EduIntegrate> list = eduIntegrateService.selectEduIntegrateList(eduIntegrate);
        return getDataTable(list);
    }

    /**
     * 导出产教融合管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:integrate:export')")
    @Log(title = "产教融合管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduIntegrate eduIntegrate)
    {
        List<EduIntegrate> list = eduIntegrateService.selectEduIntegrateList(eduIntegrate);
        ExcelUtil<EduIntegrate> util = new ExcelUtil<EduIntegrate>(EduIntegrate.class);
        util.exportExcel(response, list, "产教融合管理数据");
    }

    /**
     * 获取产教融合管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:integrate:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduIntegrateService.selectEduIntegrateByEduId(eduId));
    }

    /**
     * 新增产教融合管理
     */
    @PreAuthorize("@ss.hasPermi('service:integrate:add')")
    @Log(title = "产教融合管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduIntegrate eduIntegrate)
    {
        return toAjax(eduIntegrateService.insertEduIntegrate(eduIntegrate));
    }

    /**
     * 修改产教融合管理
     */
    @PreAuthorize("@ss.hasPermi('service:integrate:edit')")
    @Log(title = "产教融合管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduIntegrate eduIntegrate)
    {
        return toAjax(eduIntegrateService.updateEduIntegrate(eduIntegrate));
    }

    /**
     * 删除产教融合管理
     */
    @PreAuthorize("@ss.hasPermi('service:integrate:remove')")
    @Log(title = "产教融合管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduIntegrateService.deleteEduIntegrateByEduIds(eduIds));
    }
    @GetMapping("/u/list")
    public TableDataInfo getIntegrateList(EduIntegrate eduIntegrate)
    {
        startPage();
        List<EduIntegrate> list = eduIntegrateService.selectEduIntegrateListByType(eduIntegrate);
        return getDataTable(list);
    }

    @GetMapping("/u/getIntegrateByKeyWord")
    public AjaxResult getIntegrateByKeyWord(@RequestParam("text") String text,
                                            @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        text = "%" + text + "%";
        List<IntegrateVO> list = eduIntegrateService.selectIntegrateByKeyWord(text, pageSize, pageNum);
        int total = eduIntegrateService.selectCountByKeyWord(text);
        Page<IntegrateVO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        page.setTotal(total);
        return success(page);
    }

    @GetMapping("/u/getIntegrateById/{eduId}")
    public AjaxResult getIntegrateById(@PathVariable Long eduId){
        return success(eduIntegrateService.getIntegrateById(eduId));
    }

    @GetMapping("/u/getIntegrateByCondition")
    public AjaxResult getIntegrateByCondition(@RequestParam(value = "typeId") Integer tId,
                                              @RequestParam("keyword") String keyword,
    @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNum") Integer pageNum){
        keyword = "%" + keyword + "%";
        List<IntegrateVO> list = eduIntegrateService.getIntegrateByCondition(tId, keyword, pageSize, pageNum);
        int total = eduIntegrateService.getCountByCondition(tId, keyword);
        Page<IntegrateVO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        page.setTotal(total);
        return success(page);
    }

    @GetMapping("/u/getCurrentIntegrateList")
    public AjaxResult getCurrentIntegrateList(){
        return success(eduIntegrateService.getCurrentIntegrateList());
    }
}
