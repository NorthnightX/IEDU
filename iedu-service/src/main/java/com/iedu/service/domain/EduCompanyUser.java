package com.iedu.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 公司用户关系对象 edu_company_user
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-19
 */
public class EduCompanyUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司id */
    private Long eduCompanyId;

    /** 用户id */
    private Long eduUserId;

    public void setEduCompanyId(Long eduCompanyId) 
    {
        this.eduCompanyId = eduCompanyId;
    }

    public Long getEduCompanyId() 
    {
        return eduCompanyId;
    }
    public void setEduUserId(Long eduUserId) 
    {
        this.eduUserId = eduUserId;
    }

    public Long getEduUserId() 
    {
        return eduUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eduCompanyId", getEduCompanyId())
            .append("eduUserId", getEduUserId())
            .toString();
    }
}
