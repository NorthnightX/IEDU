package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 动态对象 edu_article
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
public class EduArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 动态ID */
    private Long eduId;

    /** 动态标题 */
    @Excel(name = "动态标题")
    private String eduTitle;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduPublishTime;

    /** 内容 */
    @Excel(name = "内容")
    private String eduContent;

    /** 发布组织 */
    @Excel(name = "发布组织")
    private String eduOrganize;

    /** 组织类别（0：公司，1：学校） */
    @Excel(name = "组织类别", readConverterExp = "0=：公司，1：学校")
    private Long eduOrganizeType;

    /** 创建者 */
    @Excel(name = "创建者")
    private Long eduCreateUser;

    /** 修改者 */
    @Excel(name = "修改者")
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
    public void setEduTitle(String eduTitle) 
    {
        this.eduTitle = eduTitle;
    }

    public String getEduTitle() 
    {
        return eduTitle;
    }
    public void setEduPublishTime(LocalDateTime eduPublishTime)
    {
        this.eduPublishTime = eduPublishTime;
    }

    public LocalDateTime getEduPublishTime()
    {
        return eduPublishTime;
    }
    public void setEduContent(String eduContent) 
    {
        this.eduContent = eduContent;
    }

    public String getEduContent() 
    {
        return eduContent;
    }
    public void setEduOrganize(String eduOrganize) 
    {
        this.eduOrganize = eduOrganize;
    }

    public String getEduOrganize() 
    {
        return eduOrganize;
    }
    public void setEduOrganizeType(Long eduOrganizeType) 
    {
        this.eduOrganizeType = eduOrganizeType;
    }

    public Long getEduOrganizeType() 
    {
        return eduOrganizeType;
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
            .append("eduTitle", getEduTitle())
            .append("eduPublishTime", getEduPublishTime())
            .append("eduContent", getEduContent())
            .append("eduOrganize", getEduOrganize())
            .append("eduOrganizeType", getEduOrganizeType())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .toString();
    }
}
