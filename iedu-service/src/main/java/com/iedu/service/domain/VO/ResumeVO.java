package com.iedu.service.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iedu.common.annotation.Excel;

import java.time.LocalDateTime;

public class ResumeVO {
    private Long eduId;

    /** 岗位ID */
    @Excel(name = "岗位ID")
    private Long eduRecruitId;
    private String eduRecruitName;
    private RecruitVO job;
    private Long eduCompanyId;
    private String eduCompanyName;

    public RecruitVO getJob() {
        return job;
    }

    public void setJob(RecruitVO job) {
        this.job = job;
    }

    public CompanyVO getCompany() {
        return company;
    }

    public void setCompany(CompanyVO company) {
        this.company = company;
    }

    private CompanyVO company;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long eduUserId;

    /** 简历链接 */
    @Excel(name = "简历链接")
    private String eduResumeLink;

    /** 投递者姓名 */
    @Excel(name = "投递者姓名")
    private String eduDelivererName;

    /** 投递者年龄 */
    @Excel(name = "投递者年龄")
    private String eduDelivererAge;

    /** 投递者性别 */
    @Excel(name = "投递者性别")
    private String eduDelivererSex;

    /** 投递者身份证号 */
    @Excel(name = "投递者身份证号")
    private String eduDelivererIdCard;

    /** 投递者邮箱 */
    @Excel(name = "投递者邮箱")
    private String eduDelivererMail;

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public Long getEduRecruitId() {
        return eduRecruitId;
    }

    public void setEduRecruitId(Long eduRecruitId) {
        this.eduRecruitId = eduRecruitId;
    }

    public String getEduRecruitName() {
        return eduRecruitName;
    }

    public void setEduRecruitName(String eduRecruitName) {
        this.eduRecruitName = eduRecruitName;
    }

    public Long getEduCompanyId() {
        return eduCompanyId;
    }

    public void setEduCompanyId(Long eduCompanyId) {
        this.eduCompanyId = eduCompanyId;
    }

    public String getEduCompanyName() {
        return eduCompanyName;
    }

    public void setEduCompanyName(String eduCompanyName) {
        this.eduCompanyName = eduCompanyName;
    }

    public Long getEduUserId() {
        return eduUserId;
    }

    public void setEduUserId(Long eduUserId) {
        this.eduUserId = eduUserId;
    }

    public String getEduResumeLink() {
        return eduResumeLink;
    }

    public void setEduResumeLink(String eduResumeLink) {
        this.eduResumeLink = eduResumeLink;
    }

    public String getEduDelivererName() {
        return eduDelivererName;
    }

    public void setEduDelivererName(String eduDelivererName) {
        this.eduDelivererName = eduDelivererName;
    }

    public String getEduDelivererAge() {
        return eduDelivererAge;
    }

    public void setEduDelivererAge(String eduDelivererAge) {
        this.eduDelivererAge = eduDelivererAge;
    }

    public String getEduDelivererSex() {
        return eduDelivererSex;
    }

    public void setEduDelivererSex(String eduDelivererSex) {
        this.eduDelivererSex = eduDelivererSex;
    }

    public String getEduDelivererIdCard() {
        return eduDelivererIdCard;
    }

    public void setEduDelivererIdCard(String eduDelivererIdCard) {
        this.eduDelivererIdCard = eduDelivererIdCard;
    }

    public String getEduDelivererMail() {
        return eduDelivererMail;
    }

    public void setEduDelivererMail(String eduDelivererMail) {
        this.eduDelivererMail = eduDelivererMail;
    }

    public String getEduDelivererPhone() {
        return eduDelivererPhone;
    }

    public void setEduDelivererPhone(String eduDelivererPhone) {
        this.eduDelivererPhone = eduDelivererPhone;
    }

    public LocalDateTime getEduDeliveryTime() {
        return eduDeliveryTime;
    }

    public void setEduDeliveryTime(LocalDateTime eduDeliveryTime) {
        this.eduDeliveryTime = eduDeliveryTime;
    }

    /** 投递者手机号 */
    @Excel(name = "投递者手机号")
    private String eduDelivererPhone;

    /** 投递时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "投递时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime eduDeliveryTime;
}
