package com.iedu.service.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 公司信息对象 edu_company_information
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
public class EduCompanyInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long eduId;

    /** 公司Id
 */
    @Excel(name = "公司Id")
    private Long eduCompanyId;

    /** 法定代表人 */
    @Excel(name = "法定代表人")
    private String eduLegalRepresentative;

    /** 企业类型 */
    @Excel(name = "企业类型")
    private String eduEnterpriseType;

    /** 注册资本 */
    @Excel(name = "注册资本")
    private BigDecimal eduRegisteredCapital;

    /** 社会统一信用码 */
    @Excel(name = "社会统一信用码")
    private String eduSocialCreditCode;

    /** 经营期限  */
    @Excel(name = "经营期限")
    private String eduOperatingPeriod;

    /** 所属地区 */
    @Excel(name = "所属地区")
    private String eduArea;

    /** 经营状态 */
    @Excel(name = "经营状态")
    private String eduOperatingStatus;

    /** 登记机关 */
    @Excel(name = "登记机关")
    private String eduRegistrationAuthority;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String eduRegisteredAddress;

    /** 成立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "成立时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduEstablishmentDate;

    /** 经营范围  */
    @Excel(name = "经营范围 ")
    private String eduBusinessScope;

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
    public void setEduCompanyId(Long eduCompanyId) 
    {
        this.eduCompanyId = eduCompanyId;
    }

    public Long getEduCompanyId() 
    {
        return eduCompanyId;
    }
    public void setEduLegalRepresentative(String eduLegalRepresentative) 
    {
        this.eduLegalRepresentative = eduLegalRepresentative;
    }

    public String getEduLegalRepresentative() 
    {
        return eduLegalRepresentative;
    }
    public void setEduEnterpriseType(String eduEnterpriseType) 
    {
        this.eduEnterpriseType = eduEnterpriseType;
    }

    public String getEduEnterpriseType() 
    {
        return eduEnterpriseType;
    }
    public void setEduRegisteredCapital(BigDecimal eduRegisteredCapital) 
    {
        this.eduRegisteredCapital = eduRegisteredCapital;
    }

    public BigDecimal getEduRegisteredCapital() 
    {
        return eduRegisteredCapital;
    }
    public void setEduSocialCreditCode(String eduSocialCreditCode) 
    {
        this.eduSocialCreditCode = eduSocialCreditCode;
    }

    public String getEduSocialCreditCode() 
    {
        return eduSocialCreditCode;
    }
    public void setEduOperatingPeriod(String eduOperatingPeriod) 
    {
        this.eduOperatingPeriod = eduOperatingPeriod;
    }

    public String getEduOperatingPeriod() 
    {
        return eduOperatingPeriod;
    }
    public void setEduArea(String eduArea) 
    {
        this.eduArea = eduArea;
    }

    public String getEduArea() 
    {
        return eduArea;
    }
    public void setEduOperatingStatus(String eduOperatingStatus) 
    {
        this.eduOperatingStatus = eduOperatingStatus;
    }

    public String getEduOperatingStatus() 
    {
        return eduOperatingStatus;
    }
    public void setEduRegistrationAuthority(String eduRegistrationAuthority) 
    {
        this.eduRegistrationAuthority = eduRegistrationAuthority;
    }

    public String getEduRegistrationAuthority() 
    {
        return eduRegistrationAuthority;
    }
    public void setEduRegisteredAddress(String eduRegisteredAddress) 
    {
        this.eduRegisteredAddress = eduRegisteredAddress;
    }

    public String getEduRegisteredAddress() 
    {
        return eduRegisteredAddress;
    }
    public void setEduEstablishmentDate(LocalDateTime eduEstablishmentDate)
    {
        this.eduEstablishmentDate = eduEstablishmentDate;
    }

    public LocalDateTime getEduEstablishmentDate()
    {
        return eduEstablishmentDate;
    }
    public void setEduBusinessScope(String eduBusinessScope) 
    {
        this.eduBusinessScope = eduBusinessScope;
    }

    public String getEduBusinessScope() 
    {
        return eduBusinessScope;
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
            .append("eduCompanyId", getEduCompanyId())
            .append("eduLegalRepresentative", getEduLegalRepresentative())
            .append("eduEnterpriseType", getEduEnterpriseType())
            .append("eduRegisteredCapital", getEduRegisteredCapital())
            .append("eduSocialCreditCode", getEduSocialCreditCode())
            .append("eduOperatingPeriod", getEduOperatingPeriod())
            .append("eduArea", getEduArea())
            .append("eduOperatingStatus", getEduOperatingStatus())
            .append("eduRegistrationAuthority", getEduRegistrationAuthority())
            .append("eduRegisteredAddress", getEduRegisteredAddress())
            .append("eduEstablishmentDate", getEduEstablishmentDate())
            .append("eduBusinessScope", getEduBusinessScope())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .toString();
    }
}
