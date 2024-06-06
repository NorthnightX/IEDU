package com.iedu.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 院校照片对象 edu_academy_pic
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
public class EduAcademyPic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 院校ID */
    @Excel(name = "院校ID")
    private Long eduAcademyId;

    /** 院校logo */
    @Excel(name = "院校logo")
    private String eduLogo;

    /** 院校照片墙 */
    @Excel(name = "院校照片墙")
    private String eduImages;

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
    public void setEduLogo(String eduLogo) 
    {
        this.eduLogo = eduLogo;
    }

    public String getEduLogo() 
    {
        return eduLogo;
    }
    public void setEduImages(String eduImages) 
    {
        this.eduImages = eduImages;
    }

    public String getEduImages() 
    {
        return eduImages;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduId", getEduId())
            .append("eduAcademyId", getEduAcademyId())
            .append("eduLogo", getEduLogo())
            .append("eduImages", getEduImages())
            .toString();
    }
}
