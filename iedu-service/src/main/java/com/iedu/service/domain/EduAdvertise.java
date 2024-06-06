package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 广告对象 edu_advertise
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-06-03
 */
public class EduAdvertise extends BaseEntity
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

    /** 位置 */
    @Excel(name = "位置")
    private Long eduLocation;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduExpiredTime;

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
    public void setEduLocation(Long eduLocation) 
    {
        this.eduLocation = eduLocation;
    }

    public Long getEduLocation() 
    {
        return eduLocation;
    }
    public void setEduExpiredTime(LocalDateTime eduExpiredTime)
    {
        this.eduExpiredTime = eduExpiredTime;
    }

    public LocalDateTime getEduExpiredTime()
    {
        return eduExpiredTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduId", getEduId())
            .append("eduLink", getEduLink())
            .append("eduTarget", getEduTarget())
            .append("eduLocation", getEduLocation())
            .append("eduExpiredTime", getEduExpiredTime())
            .toString();
    }
}
