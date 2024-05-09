package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.TreeEntity;

/**
 * 公司行业对象 edu_company_industry
 * 
 * @author zjz
 * @date 2024-04-02
 */
public class EduCompanyIndustry extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 父行业 */
    @Excel(name = "父行业")
    private Long eduFaId;

    /** 产业名 */
    @Excel(name = "产业名")
    private String eduIndustry;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduCreateTime;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduModifyTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long eduCreateUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long eduModifyUser;

    private List<EduCompanyIndustry> child;
    public List<EduCompanyIndustry> getChild() {
        return child;
    }

    public void setChild(List<EduCompanyIndustry> children) {
        this.child = children;
    }

    public void setEduId(Long eduId) 
    {
        this.eduId = eduId;
    }

    public Long getEduId() 
    {
        return eduId;
    }
    public void setEduFaId(Long eduFaId) 
    {
        this.eduFaId = eduFaId;
    }

    public Long getEduFaId() 
    {
        return eduFaId;
    }
    public void setEduIndustry(String eduIndustry) 
    {
        this.eduIndustry = eduIndustry;
    }

    public String getEduIndustry() 
    {
        return eduIndustry;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduId", getEduId())
            .append("eduFaId", getEduFaId())
            .append("eduIndustry", getEduIndustry())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .toString();
    }
}
