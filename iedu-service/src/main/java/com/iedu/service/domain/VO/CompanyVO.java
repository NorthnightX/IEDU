package com.iedu.service.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iedu.common.annotation.Excel;
import com.iedu.service.domain.EduCompanyAddress;
import com.iedu.service.domain.EduCompanyInformation;
import com.iedu.service.domain.EduCompanyPic;

import java.time.LocalDateTime;

public class CompanyVO {
    /** id */
    private Long eduId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String eduCompanyName;

    /** 公司规模 */
    @Excel(name = "公司规模")
    private Long eduCompanyScaleId;
    private String eduCompanyScaleName;
    private EduCompanyInformation eduCompanyInformation;
    private CompanyAddressVO companyAddressVO;
    private EduCompanyPic eduCompanyPic;

    public EduCompanyInformation getEduCompanyInformation() {
        return eduCompanyInformation;
    }

    public void setEduCompanyInformation(EduCompanyInformation eduCompanyInformation) {
        this.eduCompanyInformation = eduCompanyInformation;
    }

    public CompanyAddressVO getCompanyAddressVO() {
        return companyAddressVO;
    }

    public void setCompanyAddressVO(CompanyAddressVO companyAddressVO) {
        this.companyAddressVO = companyAddressVO;
    }

    public EduCompanyPic getEduCompanyPic() {
        return eduCompanyPic;
    }

    public void setEduCompanyPic(EduCompanyPic eduCompanyPic) {
        this.eduCompanyPic = eduCompanyPic;
    }

    /** 公司行业 */
    @Excel(name = "公司行业")
    private Long eduCompanyIndustryId;
    private String eduCompanyIndustryName;

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public String getEduCompanyName() {
        return eduCompanyName;
    }

    public void setEduCompanyName(String eduCompanyName) {
        this.eduCompanyName = eduCompanyName;
    }

    public Long getEduCompanyScaleId() {
        return eduCompanyScaleId;
    }

    public void setEduCompanyScaleId(Long eduCompanyScaleId) {
        this.eduCompanyScaleId = eduCompanyScaleId;
    }

    public String getEduCompanyScaleName() {
        return eduCompanyScaleName;
    }

    public void setEduCompanyScaleName(String eduCompanyScaleName) {
        this.eduCompanyScaleName = eduCompanyScaleName;
    }

    public Long getEduCompanyIndustryId() {
        return eduCompanyIndustryId;
    }

    public void setEduCompanyIndustryId(Long eduCompanyIndustryId) {
        this.eduCompanyIndustryId = eduCompanyIndustryId;
    }

    public String getEduCompanyIndustryName() {
        return eduCompanyIndustryName;
    }

    public void setEduCompanyIndustryName(String eduCompanyIndustryName) {
        this.eduCompanyIndustryName = eduCompanyIndustryName;
    }

    public Long getEduFinancingStageId() {
        return eduFinancingStageId;
    }

    public void setEduFinancingStageId(Long eduFinancingStageId) {
        this.eduFinancingStageId = eduFinancingStageId;
    }

    public String getEduFinancingStageName() {
        return eduFinancingStageName;
    }

    public void setEduFinancingStageName(String eduFinancingStageName) {
        this.eduFinancingStageName = eduFinancingStageName;
    }

    public String getEduCompanyIntroduce() {
        return eduCompanyIntroduce;
    }

    public void setEduCompanyIntroduce(String eduCompanyIntroduce) {
        this.eduCompanyIntroduce = eduCompanyIntroduce;
    }

    public Long getEduInformationId() {
        return eduInformationId;
    }

    public void setEduInformationId(Long eduInformationId) {
        this.eduInformationId = eduInformationId;
    }

    public Long getEduAddressId() {
        return eduAddressId;
    }

    public void setEduAddressId(Long eduAddressId) {
        this.eduAddressId = eduAddressId;
    }

    public Long getEduCreateUser() {
        return eduCreateUser;
    }

    public void setEduCreateUser(Long eduCreateUser) {
        this.eduCreateUser = eduCreateUser;
    }

    public Long getEduModifyUser() {
        return eduModifyUser;
    }

    public void setEduModifyUser(Long eduModifyUser) {
        this.eduModifyUser = eduModifyUser;
    }

    public LocalDateTime getEduCreateTime() {
        return eduCreateTime;
    }

    public void setEduCreateTime(LocalDateTime eduCreateTime) {
        this.eduCreateTime = eduCreateTime;
    }

    public LocalDateTime getEduModifyTime() {
        return eduModifyTime;
    }

    public void setEduModifyTime(LocalDateTime eduModifyTime) {
        this.eduModifyTime = eduModifyTime;
    }

    /** 融资阶段 */
    @Excel(name = "融资阶段")
    private Long eduFinancingStageId;
    private String eduFinancingStageName;

    /** 公司简介 */
    @Excel(name = "公司简介")
    private String eduCompanyIntroduce;

    /** 公司详细信息 */
    @Excel(name = "公司详细信息")
    private Long eduInformationId;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private Long eduAddressId;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long eduCreateUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long eduModifyUser;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduCreateTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduModifyTime;
}
