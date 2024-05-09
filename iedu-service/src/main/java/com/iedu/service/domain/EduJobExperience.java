package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 工作经验对象 edu_job_experience
 *
 * @author zjz
 * @date 2024-04-01
 */
public class EduJobExperience extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long eduId;

    /**
     * 工作经验
     */
    @Excel(name = "工作经验")
    private String eduExp;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private Long eduCreateUser;

    /**
     * 修改人
     */
    @Excel(name = "修改人")
    private Long eduModifyUser;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduCreateTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduModifyTime;

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public Long getEduId() {
        return eduId;
    }

    public void setEduExp(String eduExp) {
        this.eduExp = eduExp;
    }

    public String getEduExp() {
        return eduExp;
    }

    public void setEduCreateUser(Long eduCreateUser) {
        this.eduCreateUser = eduCreateUser;
    }

    public Long getEduCreateUser() {
        return eduCreateUser;
    }

    public void setEduModifyUser(Long eduModifyUser) {
        this.eduModifyUser = eduModifyUser;
    }

    public Long getEduModifyUser() {
        return eduModifyUser;
    }

    public void setEduCreateTime(LocalDateTime eduCreateTime) {
        this.eduCreateTime = eduCreateTime;
    }

    public LocalDateTime getEduCreateTime() {
        return eduCreateTime;
    }

    public void setEduModifyTime(LocalDateTime eduModifyTime) {
        this.eduModifyTime = eduModifyTime;
    }

    public LocalDateTime getEduModifyTime() {
        return eduModifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("eduId", getEduId())
                .append("eduExp", getEduExp())
                .append("eduCreateUser", getEduCreateUser())
                .append("eduModifyUser", getEduModifyUser())
                .append("eduCreateTime", getEduCreateTime())
                .append("eduModifyTime", getEduModifyTime())
                .toString();
    }
}
