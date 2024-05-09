package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 公司对象 edu_company
 * 
 * @author zjz
 * @date 2024-04-05
 */
public class EduCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 公司名 */
    @Excel(name = "公司名")
    private String eduCompanyName;

    /** 公司规模 */
    @Excel(name = "公司规模")
    private Long eduCompanyScaleId;

    /** 公司行业 */
    @Excel(name = "公司行业")
    private Long eduCompanyIndustryId;

    /** 融资阶段 */
    @Excel(name = "融资阶段")
    private Long eduFinancingStageId;

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

    public void setEduId(Long eduId) 
    {
        this.eduId = eduId;
    }

    public Long getEduId() 
    {
        return eduId;
    }
    public void setEduCompanyName(String eduCompanyName) 
    {
        this.eduCompanyName = eduCompanyName;
    }

    public String getEduCompanyName() 
    {
        return eduCompanyName;
    }
    public void setEduCompanyScaleId(Long eduCompanyScaleId) 
    {
        this.eduCompanyScaleId = eduCompanyScaleId;
    }

    public Long getEduCompanyScaleId() 
    {
        return eduCompanyScaleId;
    }
    public void setEduCompanyIndustryId(Long eduCompanyIndustryId) 
    {
        this.eduCompanyIndustryId = eduCompanyIndustryId;
    }

    public Long getEduCompanyIndustryId() 
    {
        return eduCompanyIndustryId;
    }
    public void setEduFinancingStageId(Long eduFinancingStageId) 
    {
        this.eduFinancingStageId = eduFinancingStageId;
    }

    public Long getEduFinancingStageId() 
    {
        return eduFinancingStageId;
    }
    public void setEduCompanyIntroduce(String eduCompanyIntroduce) 
    {
        this.eduCompanyIntroduce = eduCompanyIntroduce;
    }

    public String getEduCompanyIntroduce() 
    {
        return eduCompanyIntroduce;
    }
    public void setEduInformationId(Long eduInformationId) 
    {
        this.eduInformationId = eduInformationId;
    }

    public Long getEduInformationId() 
    {
        return eduInformationId;
    }
    public void setEduAddressId(Long eduAddressId) 
    {
        this.eduAddressId = eduAddressId;
    }

    public Long getEduAddressId() 
    {
        return eduAddressId;
    }
    public void setEduCreateUser(Long eduCreateUser) 
    {
        this.eduCreateUser = eduCreateUser;
    }

    public Long getEduCreateUser() 
    {
        return eduCreateUser;
    }
    public void setEduModifyUser(Long eduModifyUser) 
    {
        this.eduModifyUser = eduModifyUser;
    }

    public Long getEduModifyUser() 
    {
        return eduModifyUser;
    }
    public void setEduCreateTime(LocalDateTime eduCreateTime)
    {
        this.eduCreateTime = eduCreateTime;
    }

    public LocalDateTime getEduCreateTime()
    {
        return eduCreateTime;
    }
    public void setEduModifyTime(LocalDateTime eduModifyTime)
    {
        this.eduModifyTime = eduModifyTime;
    }

    public LocalDateTime getEduModifyTime()
    {
        return eduModifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduId", getEduId())
            .append("eduCompanyName", getEduCompanyName())
            .append("eduCompanyScaleId", getEduCompanyScaleId())
            .append("eduCompanyIndustryId", getEduCompanyIndustryId())
            .append("eduFinancingStageId", getEduFinancingStageId())
            .append("eduCompanyIntroduce", getEduCompanyIntroduce())
            .append("eduInformationId", getEduInformationId())
            .append("eduAddressId", getEduAddressId())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .toString();
    }
}
