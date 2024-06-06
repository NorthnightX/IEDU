package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 投递记录对象 edu_delivery_record
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-22
 */
public class EduDeliveryRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 岗位ID */
    @Excel(name = "岗位ID")
    private Long eduRecruitId;

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

    /** 投递者手机号 */
    @Excel(name = "投递者手机号")
    private String eduDelivererPhone;

    /** 投递时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "投递时间", width = 30, dateFormat = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime eduDeliveryTime;

    public void setEduId(Long eduId) 
    {
        this.eduId = eduId;
    }

    public Long getEduId() 
    {
        return eduId;
    }
    public void setEduRecruitId(Long eduRecruitId) 
    {
        this.eduRecruitId = eduRecruitId;
    }

    public Long getEduRecruitId() 
    {
        return eduRecruitId;
    }
    public void setEduUserId(Long eduUserId) 
    {
        this.eduUserId = eduUserId;
    }

    public Long getEduUserId() 
    {
        return eduUserId;
    }
    public void setEduResumeLink(String eduResumeLink) 
    {
        this.eduResumeLink = eduResumeLink;
    }

    public String getEduResumeLink() 
    {
        return eduResumeLink;
    }
    public void setEduDelivererName(String eduDelivererName) 
    {
        this.eduDelivererName = eduDelivererName;
    }

    public String getEduDelivererName() 
    {
        return eduDelivererName;
    }
    public void setEduDelivererAge(String eduDelivererAge) 
    {
        this.eduDelivererAge = eduDelivererAge;
    }

    public String getEduDelivererAge() 
    {
        return eduDelivererAge;
    }
    public void setEduDelivererSex(String eduDelivererSex) 
    {
        this.eduDelivererSex = eduDelivererSex;
    }

    public String getEduDelivererSex() 
    {
        return eduDelivererSex;
    }
    public void setEduDelivererIdCard(String eduDelivererIdCard) 
    {
        this.eduDelivererIdCard = eduDelivererIdCard;
    }

    public String getEduDelivererIdCard() 
    {
        return eduDelivererIdCard;
    }
    public void setEduDelivererMail(String eduDelivererMail) 
    {
        this.eduDelivererMail = eduDelivererMail;
    }

    public String getEduDelivererMail() 
    {
        return eduDelivererMail;
    }
    public void setEduDelivererPhone(String eduDelivererPhone) 
    {
        this.eduDelivererPhone = eduDelivererPhone;
    }

    public String getEduDelivererPhone() 
    {
        return eduDelivererPhone;
    }
    public void setEduDeliveryTime(LocalDateTime eduDeliveryTime)
    {
        this.eduDeliveryTime = eduDeliveryTime;
    }

    public LocalDateTime getEduDeliveryTime()
    {
        return eduDeliveryTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduId", getEduId())
            .append("eduRecruitId", getEduRecruitId())
            .append("eduUserId", getEduUserId())
            .append("eduResumeLink", getEduResumeLink())
            .append("eduDelivererName", getEduDelivererName())
            .append("eduDelivererAge", getEduDelivererAge())
            .append("eduDelivererSex", getEduDelivererSex())
            .append("eduDelivererIdCard", getEduDelivererIdCard())
            .append("eduDelivererMail", getEduDelivererMail())
            .append("eduDelivererPhone", getEduDelivererPhone())
            .append("eduDeliveryTime", getEduDeliveryTime())
            .toString();
    }
}
