package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 招聘信息对象 edu_recruit
 * 
 * @author zjz
 * @date 2024-04-05
 */
public class EduRecruit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long eduId;

    /** 公司id */
    @Excel(name = "公司id")
    private Long eduCompanyId;

    /** 名称 */
    @Excel(name = "名称")
    private String eduJobName;

    /** 工作类型 */
    @Excel(name = "工作类型")
    private Long eduJobTypeId;

    /** 城市 */
    @Excel(name = "城市")
    private Long eduCityId;

    /** 学历要求 */
    @Excel(name = "学历要求")
    private Long eduEduId;

    /** 经验要求 */
    @Excel(name = "经验要求")
    private Long eduExpId;

    /** 薪资范围 */
    @Excel(name = "薪资范围")
    private String eduSalary;

    /** 福利待遇 */
    @Excel(name = "福利待遇")
    private String eduWelfare;

    /** 职位tag */
    @Excel(name = "职位tag")
    private String eduIntroduceTag;

    /** 职位详情 */
    @Excel(name = "职位详情")
    private String eduIntroduce;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduStartTime;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduEndTime;

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
    public void setEduJobName(String eduJobName) 
    {
        this.eduJobName = eduJobName;
    }

    public String getEduJobName() 
    {
        return eduJobName;
    }
    public void setEduJobTypeId(Long eduJobTypeId) 
    {
        this.eduJobTypeId = eduJobTypeId;
    }

    public Long getEduJobTypeId() 
    {
        return eduJobTypeId;
    }
    public void setEduCityId(Long eduCityId) 
    {
        this.eduCityId = eduCityId;
    }

    public Long getEduCityId() 
    {
        return eduCityId;
    }
    public void setEduEduId(Long eduEduId) 
    {
        this.eduEduId = eduEduId;
    }

    public Long getEduEduId() 
    {
        return eduEduId;
    }
    public void setEduExpId(Long eduExpId) 
    {
        this.eduExpId = eduExpId;
    }

    public Long getEduExpId() 
    {
        return eduExpId;
    }
    public void setEduSalary(String eduSalary) 
    {
        this.eduSalary = eduSalary;
    }

    public String getEduSalary() 
    {
        return eduSalary;
    }
    public void setEduWelfare(String eduWelfare) 
    {
        this.eduWelfare = eduWelfare;
    }

    public String getEduWelfare() 
    {
        return eduWelfare;
    }
    public void setEduIntroduceTag(String eduIntroduceTag) 
    {
        this.eduIntroduceTag = eduIntroduceTag;
    }

    public String getEduIntroduceTag() 
    {
        return eduIntroduceTag;
    }
    public void setEduIntroduce(String eduIntroduce) 
    {
        this.eduIntroduce = eduIntroduce;
    }

    public String getEduIntroduce() 
    {
        return eduIntroduce;
    }
    public void setEduStartTime(LocalDateTime eduStartTime)
    {
        this.eduStartTime = eduStartTime;
    }

    public LocalDateTime getEduStartTime()
    {
        return eduStartTime;
    }
    public void setEduEndTime(LocalDateTime eduEndTime)
    {
        this.eduEndTime = eduEndTime;
    }

    public LocalDateTime getEduEndTime()
    {
        return eduEndTime;
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
            .append("eduJobName", getEduJobName())
            .append("eduJobTypeId", getEduJobTypeId())
            .append("eduCityId", getEduCityId())
            .append("eduEduId", getEduEduId())
            .append("eduExpId", getEduExpId())
            .append("eduSalary", getEduSalary())
            .append("eduWelfare", getEduWelfare())
            .append("eduIntroduceTag", getEduIntroduceTag())
            .append("eduIntroduce", getEduIntroduce())
            .append("eduStartTime", getEduStartTime())
            .append("eduEndTime", getEduEndTime())
            .append("eduCreateUser", getEduCreateUser())
            .append("eduModifyUser", getEduModifyUser())
            .append("eduCreateTime", getEduCreateTime())
            .append("eduModifyTime", getEduModifyTime())
            .toString();
    }
}
