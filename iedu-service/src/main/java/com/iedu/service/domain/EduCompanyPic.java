package com.iedu.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 企业照片对象 edu_company_pic
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
public class EduCompanyPic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long eduId;

    /** 企业ID */
    @Excel(name = "企业ID")
    private Long eduCompanyId;

    /** 企业logo */
    @Excel(name = "企业logo")
    private String eduLogo;

    /** 企业照片墙 */
    @Excel(name = "企业照片墙")
    private String eduImages;

    public void setEduId(Long eduId) 
    {
        this.eduId = eduId;
    }

    public Long getEduId() 
    {
        return eduId;
    }
    public void setEduCompanyId(Long eduCompanyId) 
    {
        this.eduCompanyId = eduCompanyId;
    }

    public Long getEduCompanyId() 
    {
        return eduCompanyId;
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
            .append("eduCompanyId", getEduCompanyId())
            .append("eduLogo", getEduLogo())
            .append("eduImages", getEduImages())
            .toString();
    }
}
