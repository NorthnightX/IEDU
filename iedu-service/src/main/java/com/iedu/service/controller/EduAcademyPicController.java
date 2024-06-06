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
import com.iedu.service.domain.EduAcademyPic;
import com.iedu.service.service.IEduAcademyPicService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 院校照片Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
@RestController
@RequestMapping("/service/academyPic")
public class EduAcademyPicController extends BaseController
{
    @Autowired
    private IEduAcademyPicService eduAcademyPicService;

    /**
     * 查询院校照片列表
     */
    @PreAuthorize("@ss.hasPermi('service:academyPic:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduAcademyPic eduAcademyPic)
    {
        startPage();
        List<EduAcademyPic> list = eduAcademyPicService.selectEduAcademyPicList(eduAcademyPic);
        return getDataTable(list);
    }

    /**
     * 导出院校照片列表
     */
    @PreAuthorize("@ss.hasPermi('service:academyPic:export')")
    @Log(title = "院校照片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduAcademyPic eduAcademyPic)
    {
        List<EduAcademyPic> list = eduAcademyPicService.selectEduAcademyPicList(eduAcademyPic);
        ExcelUtil<EduAcademyPic> util = new ExcelUtil<EduAcademyPic>(EduAcademyPic.class);
        util.exportExcel(response, list, "院校照片数据");
    }

    /**
     * 获取院校照片详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:academyPic:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduAcademyPicService.selectEduAcademyPicByEduId(eduId));
    }

    /**
     * 新增院校照片
     */
    @PreAuthorize("@ss.hasPermi('service:academyPic:add')")
    @Log(title = "院校照片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduAcademyPic eduAcademyPic)
    {
        return toAjax(eduAcademyPicService.insertEduAcademyPic(eduAcademyPic));
    }

    /**
     * 修改院校照片
     */
    @PreAuthorize("@ss.hasPermi('service:academyPic:edit')")
    @Log(title = "院校照片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduAcademyPic eduAcademyPic)
    {
        return toAjax(eduAcademyPicService.updateEduAcademyPic(eduAcademyPic));
    }

    /**
     * 删除院校照片
     */
    @PreAuthorize("@ss.hasPermi('service:academyPic:remove')")
    @Log(title = "院校照片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduAcademyPicService.deleteEduAcademyPicByEduIds(eduIds));
    }

    @GetMapping("/getPicByAcademy/{eduId}")
    public AjaxResult getPicByAcademy(@PathVariable Long eduId){
        return success(eduAcademyPicService.selectByAcademy(eduId));
    }
}
