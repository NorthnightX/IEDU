package com.iedu.service.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.iedu.service.common.RegexConstant;
import com.iedu.service.domain.EduDeliveryRecord;
import com.iedu.service.domain.Page;
import com.iedu.service.domain.VO.RecruitVO;
import com.iedu.service.domain.VO.ResumeVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.iedu.common.annotation.Log;
import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.common.enums.BusinessType;
import com.iedu.service.service.IEduDeliveryRecordService;
import com.iedu.common.utils.poi.ExcelUtil;
import com.iedu.common.core.page.TableDataInfo;

/**
 * 投递记录管理Controller
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-21
 */
@RestController
@RequestMapping("/service/record")
public class EduDeliveryRecordController extends BaseController
{
    @Autowired
    private IEduDeliveryRecordService eduDeliveryRecordService;

    /**
     * 查询投递记录管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(EduDeliveryRecord eduDeliveryRecord)
    {
        startPage();
        List<ResumeVO> list = eduDeliveryRecordService.selectEduDeliveryRecordList(eduDeliveryRecord);
        return getDataTable(list);
    }

    /**
     * 导出投递记录管理列表
     */
    @PreAuthorize("@ss.hasPermi('service:record:export')")
    @Log(title = "投递记录管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EduDeliveryRecord eduDeliveryRecord)
    {
        List<ResumeVO> list = eduDeliveryRecordService.selectEduDeliveryRecordList(eduDeliveryRecord);
        ExcelUtil<ResumeVO> util = new ExcelUtil<>(ResumeVO.class);
        util.exportExcel(response, list, "投递记录管理数据");
    }

    /**
     * 获取投递记录管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('service:record:query')")
    @GetMapping(value = "/{eduId}")
    public AjaxResult getInfo(@PathVariable("eduId") Long eduId)
    {
        return success(eduDeliveryRecordService.selectEduDeliveryRecordByEduId(eduId));
    }

    /**
     * 新增投递记录管理
     */
    @PreAuthorize("@ss.hasPermi('service:record:add')")
    @Log(title = "投递记录管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EduDeliveryRecord eduDeliveryRecord)
    {
        if(eduDeliveryRecord.getEduResumeLink() == null || eduDeliveryRecord.getEduResumeLink().isEmpty()){
            return success("请上传简历");
        }
        if(eduDeliveryRecord.getEduDelivererMail() == null || !eduDeliveryRecord.getEduDelivererMail().matches(RegexConstant.EMAIL_REGEX)){
            return success("请检查邮箱");
        }
        if(eduDeliveryRecord.getEduDelivererPhone()== null || !eduDeliveryRecord.getEduDelivererPhone().matches(RegexConstant.PHONE_REGEX)){
            return success("请检查手机号");
        }
        if(eduDeliveryRecord.getEduDelivererIdCard() == null || !eduDeliveryRecord.getEduDelivererIdCard().matches(RegexConstant.ID_CARD_REGEX)){
            return success("请检查身份证号");
        }
        eduDeliveryRecord.setEduDeliveryTime(LocalDateTime.now());
        eduDeliveryRecordService.insertEduDeliveryRecord(eduDeliveryRecord);
        return success("投递成功");
    }

    /**
     * 修改投递记录管理
     */
    @PreAuthorize("@ss.hasPermi('service:record:edit')")
    @Log(title = "投递记录管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EduDeliveryRecord eduDeliveryRecord)
    {
        return toAjax(eduDeliveryRecordService.updateEduDeliveryRecord(eduDeliveryRecord));
    }

    /**
     * 删除投递记录管理
     */
    @PreAuthorize("@ss.hasPermi('service:record:remove')")
    @Log(title = "投递记录管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eduIds}")
    public AjaxResult remove(@PathVariable Long[] eduIds)
    {
        return toAjax(eduDeliveryRecordService.deleteEduDeliveryRecordByEduIds(eduIds));
    }

    @GetMapping("/getRecordsByUser")
    public AjaxResult getRecordsByUser(@RequestParam("id") Long id,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("pageNum") Integer pageNum)
    {
        List<ResumeVO> list = eduDeliveryRecordService.selectRecruitByCondition(id, pageSize, pageNum);
        int total = eduDeliveryRecordService.getRecordCountByUser(id);
        Page<ResumeVO> page = new Page<>();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setData(list);
        page.setTotal(total);
        return success(page);
    }
}
