package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 学校地址对象 edu_academy_address
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
public class EduAcademyAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 学校id */
    @Excel(name = "学校id")
    private Long eduAcademyId;

    /** 国家 */
    @Excel(name = "国家")
    private Long eduCountryId;

    /** 省 */
    @Excel(name = "省")
    private Long eduProvinceId;

    /** 市 */
    @Excel(name = "市")
    private Long eduCityId;

    /** 区 */
    @Excel(name = "区")
    private Long eduDistrictId;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String eduDetailedAddress;

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
    public void setEduAcademyId(Long eduAcademyId) 
    {
        this.eduAcademyId = eduAcademyId;
    }

    public Long getEduAcademyId() 
    {
        return eduAcademyId;
    }
    public void setEduCountryId(Long eduCountryId)
    {
        this.eduCountryId = eduCountryId;
    }

    public Long getEduCountryId()
    {
        return eduCountryId;
    }
    public void setEduProvinceId(Long eduProvinceId)
    {
        this.eduProvinceId = eduProvinceId;
    }

    public Long getEduProvinceId()
    {
        return eduProvinceId;
    }
    public void setEduCityId(Long eduCityId)
    {
        this.eduCityId = eduCityId;
    }

    public Long getEduCityId()
    {
        return eduCityId;
    }
    public void setEduDistrictId(Long eduDistrictId)
    {
        this.eduDistrictId = eduDistrictId;
    }

    public Long getEduDistrictId()
    {
        return eduDistrictId;
    }
    public void setEduDetailedAddress(String eduDetailedAddress) 
    {
        this.eduDetailedAddress = eduDetailedAddress;
    }

    public String getEduDetailedAddress() 
    {
        return eduDetailedAddress;
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
            .append("eduAcademyId", getEduAcademyId())
            .append("eduCountryId", getEduCountryId())
            .append("eduProvinceId", getEduProvinceId())
            .append("eduCityId", getEduCityId())
            .append("eduDistrictId", getEduDistrictId())
            .append("eduDetailedAddress", getEduDetailedAddress())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .toString();
    }
}
