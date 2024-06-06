package com.iedu.service.controller;

import com.iedu.common.core.controller.BaseController;
import com.iedu.common.core.domain.AjaxResult;
import com.iedu.service.domain.Chart;
import com.iedu.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/service/chart")
public class EduChartController extends BaseController {
    @Autowired
    private IEduNewsService newsService;
    @Autowired
    private IEduArticleService articleService;
    @Autowired
    private IEduDeliveryRecordService recordService;
    @Autowired
    private IEduCompanyService companyService;
    @Autowired
    private IEduAcademyService academyService;
    @Autowired
    private IEduRecruitService recruitService;

    @GetMapping("/top")
    public AjaxResult getTopData(){
        int[][] ans = new int[3][2];
        ans[0][0] = newsService.selectCount();
        ans[0][1] = articleService.selectCount();
        ans[1][0] = companyService.selectCount();
        ans[1][1] = academyService.selectCount();
        ans[2][0] = recordService.selectTodayCount();
        return success(ans);
    }

    @GetMapping("/ra")
    public AjaxResult getRecordAge(){
        List<Chart> arr = recordService.selectAgeProportion();
        return success(arr);
    }

    @GetMapping("/rt")
    public AjaxResult getRecordType(){
        return success(recordService.selectTypeProportion());
    }

    /**
     * 查找企业行业比例
     * @return
     */
    @GetMapping("/ci")
    public AjaxResult getCompanyIndustry(){
        return success(companyService.selectCompanyIndustryProportion());
    }

    @GetMapping("/jt")
    public AjaxResult getJobType(){
        return success(recruitService.selectJobTypeProportion());
    }

}
