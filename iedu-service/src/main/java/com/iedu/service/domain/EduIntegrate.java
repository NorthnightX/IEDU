package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 产教融合管理对象 edu_integrate
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
public class EduIntegrate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 项目名 */
    @Excel(name = "项目名")
    private String eduProjectName;

    /** 项目介绍 */
    @Excel(name = "项目介绍")
    private String eduProjectDescription;

    /** 项目类型 */
    @Excel(name = "项目类型")
    private Long eduProjectType;

    /** 项目领域 */
    @Excel(name = "项目领域")
    private String eduProjectField;

    /** 发布方 */
    @Excel(name = "发布方")
    private String eduPublisher;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String eduContact;

    /** 官网 */
    @Excel(name = "官网")
    private String eduOfficialWebsite;

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "项目开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduStartTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "项目结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduEndTime;

    /** 创建者 */
    @Excel(name = "创建者")
    private Long eduCreateUser;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduCreateTime;

    public Long getEduCreateUser() {
        return eduCreateUser;
    }

    public void setEduCreateUser(Long eduCreateUser) {
        this.eduCreateUser = eduCreateUser;
    }

    public LocalDateTime getEduCreateTime() {
        return eduCreateTime;
    }

    public void setEduCreateTime(LocalDateTime eduCreateTime) {
        this.eduCreateTime = eduCreateTime;
    }

    public Long getEduModifyUser() {
        return eduModifyUser;
    }

    public void setEduModifyUser(Long eduModifyUser) {
        this.eduModifyUser = eduModifyUser;
    }

    public LocalDateTime getEduModifyTime() {
        return eduModifyTime;
    }

    public void setEduModifyTime(LocalDateTime eduModifyTime) {
        this.eduModifyTime = eduModifyTime;
    }

    /** 修改者 */
    @Excel(name = "修改者")
    private Long eduModifyUser;

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
    public void setEduProjectName(String eduProjectName) 
    {
        this.eduProjectName = eduProjectName;
    }

    public String getEduProjectName() 
    {
        return eduProjectName;
    }
    public void setEduProjectDescription(String eduProjectDescription) 
    {
        this.eduProjectDescription = eduProjectDescription;
    }

    public String getEduProjectDescription() 
    {
        return eduProjectDescription;
    }
    public void setEduProjectType(Long eduProjectType) 
    {
        this.eduProjectType = eduProjectType;
    }

    public Long getEduProjectType() 
    {
        return eduProjectType;
    }
    public void setEduProjectField(String eduProjectField) 
    {
        this.eduProjectField = eduProjectField;
    }

    public String getEduProjectField() 
    {
        return eduProjectField;
    }
    public void setEduPublisher(String eduPublisher) 
    {
        this.eduPublisher = eduPublisher;
    }

    public String getEduPublisher() 
    {
        return eduPublisher;
    }
    public void setEduContact(String eduContact) 
    {
        this.eduContact = eduContact;
    }

    public String getEduContact() 
    {
        return eduContact;
    }
    public void setEduOfficialWebsite(String eduOfficialWebsite) 
    {
        this.eduOfficialWebsite = eduOfficialWebsite;
    }

    public String getEduOfficialWebsite() 
    {
        return eduOfficialWebsite;
    }
    public void setEduStartTime(LocalDateTime eduStartTime)
    {
        this.eduStartTime = eduStartTime;
    }

    public LocalDateTime getEduStartTime()
    {
        return eduStartTime;
    }
    public void setEduEndTime(LocalDateTime eduEndTime)
    {
        this.eduEndTime = eduEndTime;
    }

    public LocalDateTime getEduEndTime()
    {
        return eduEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduId", getEduId())
            .append("eduProjectName", getEduProjectName())
            .append("eduProjectDescription", getEduProjectDescription())
            .append("eduProjectType", getEduProjectType())
            .append("eduProjectField", getEduProjectField())
            .append("eduPublisher", getEduPublisher())
            .append("eduContact", getEduContact())
            .append("eduOfficialWebsite", getEduOfficialWebsite())
            .append("eduStartTime", getEduStartTime())
            .append("eduEndTime", getEduEndTime())
            .append("createUser", getEduCreateUser())
            .append("modifyUser", getEduModifyUser())
            .append("createTime", getCreateTime())
            .append("modifyTime", getEduModifyTime())
            .toString();
    }
}
