package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 院校管理对象 edu_academy
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
public class EduAcademy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 院校名 */
    @Excel(name = "院校名")
    private String eduAcademyName;

    /** 院校标签 */
    @Excel(name = "院校标签")
    private String eduAcademyTag;

    /** 院校介绍 */
    @Excel(name = "院校介绍")
    private String eduAcademyIntroduce;

    /** 院校地址 */
    @Excel(name = "院校地址")
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
    public void setEduAcademyName(String eduAcademyName) 
    {
        this.eduAcademyName = eduAcademyName;
    }

    public String getEduAcademyName() 
    {
        return eduAcademyName;
    }
    public void setEduAcademyTag(String eduAcademyTag) 
    {
        this.eduAcademyTag = eduAcademyTag;
    }

    public String getEduAcademyTag() 
    {
        return eduAcademyTag;
    }
    public void setEduAcademyIntroduce(String eduAcademyIntroduce) 
    {
        this.eduAcademyIntroduce = eduAcademyIntroduce;
    }

    public String getEduAcademyIntroduce() 
    {
        return eduAcademyIntroduce;
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
            .append("eduAcademyName", getEduAcademyName())
            .append("eduAcademyTag", getEduAcademyTag())
            .append("eduAcademyIntroduce", getEduAcademyIntroduce())
            .append("eduAddressId", getEduAddressId())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .toString();
    }
}
