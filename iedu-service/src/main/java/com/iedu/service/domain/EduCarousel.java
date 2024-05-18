package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 轮播图管理对象 edu_carousel
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
public class EduCarousel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String eduLink;

    /** 目标网址 */
    @Excel(name = "目标网址")
    private String eduTarget;

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

    public void setEduId(Long eduId) 
    {
        this.eduId = eduId;
    }

    public Long getEduId() 
    {
        return eduId;
    }
    public void setEduLink(String eduLink) 
    {
        this.eduLink = eduLink;
    }

    public String getEduLink() 
    {
        return eduLink;
    }
    public void setEduTarget(String eduTarget) 
    {
        this.eduTarget = eduTarget;
    }

    public String getEduTarget() 
    {
        return eduTarget;
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
            .append("eduLink", getEduLink())
            .append("eduTarget", getEduTarget())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .toString();
    }
}
