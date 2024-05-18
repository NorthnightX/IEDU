package com.iedu.service.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iedu.common.annotation.Excel;

import java.time.LocalDateTime;

public class RecruitVO {
    private Long eduId;

    private Long eduCompanyId;
    private String companyName;
    private Long eduCompanyIndustryId;
    private String industryName;

    private String eduJobName;


    private Long eduJobTypeId;
    private String jobTypeName;

    private Long eduCityId;
    private String cityName;

    private Long eduEduId;
    private String eduName;
    private Long eduExpId;
    private String expName;
    private String eduSalary;

    private String eduWelfare;
    private String eduIntroduceTag;
    private String eduIntroduce;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduEndTime;

    public Long getEduId() {
        return eduId;
    }
    public Long getEduCompanyIndustryId() {
        return eduCompanyIndustryId;
    }

    public void setEduCompanyIndustryId(Long eduCompanyIndustryId) {
        this.eduCompanyIndustryId = eduCompanyIndustryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public Long getEduCompanyId() {
        return eduCompanyId;
    }

    public void setEduCompanyId(Long eduCompanyId) {
        this.eduCompanyId = eduCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEduJobName() {
        return eduJobName;
    }

    public void setEduJobName(String eduJobName) {
        this.eduJobName = eduJobName;
    }

    public Long getEduJobTypeId() {
        return eduJobTypeId;
    }

    public void setEduJobTypeId(Long eduJobTypeId) {
        this.eduJobTypeId = eduJobTypeId;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName;
    }

    public Long getEduCityId() {
        return eduCityId;
    }

    public void setEduCityId(Long eduCityId) {
        this.eduCityId = eduCityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getEduEduId() {
        return eduEduId;
    }

    public void setEduEduId(Long eduEduId) {
        this.eduEduId = eduEduId;
    }

    public String getEduName() {
        return eduName;
    }

    public void setEduName(String eduName) {
        this.eduName = eduName;
    }

    public Long getEduExpId() {
        return eduExpId;
    }

    public void setEduExpId(Long eduExpId) {
        this.eduExpId = eduExpId;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getEduSalary() {
        return eduSalary;
    }

    public void setEduSalary(String eduSalary) {
        this.eduSalary = eduSalary;
    }

    public String getEduWelfare() {
        return eduWelfare;
    }

    public void setEduWelfare(String eduWelfare) {
        this.eduWelfare = eduWelfare;
    }

    public String getEduIntroduceTag() {
        return eduIntroduceTag;
    }

    public void setEduIntroduceTag(String eduIntroduceTag) {
        this.eduIntroduceTag = eduIntroduceTag;
    }

    public String getEduIntroduce() {
        return eduIntroduce;
    }

    public void setEduIntroduce(String eduIntroduce) {
        this.eduIntroduce = eduIntroduce;
    }

    public LocalDateTime getEduStartTime() {
        return eduStartTime;
    }

    public void setEduStartTime(LocalDateTime eduStartTime) {
        this.eduStartTime = eduStartTime;
    }

    public LocalDateTime getEduEndTime() {
        return eduEndTime;
    }

    public void setEduEndTime(LocalDateTime eduEndTime) {
        this.eduEndTime = eduEndTime;
    }

    public RecruitVO() {
    }



}
