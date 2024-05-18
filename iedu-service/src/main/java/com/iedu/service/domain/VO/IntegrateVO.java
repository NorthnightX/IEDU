package com.iedu.service.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iedu.common.annotation.Excel;

import java.time.LocalDateTime;

public class IntegrateVO {
    /** id */
    private Long eduId;

    /** 项目名 */
    private String eduProjectName;

    /** 项目介绍 */
    private String eduProjectDescription;

    /** 项目类型 */
    private Long eduProjectType;

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public String getEduProjectName() {
        return eduProjectName;
    }

    public void setEduProjectName(String eduProjectName) {
        this.eduProjectName = eduProjectName;
    }

    public String getEduProjectDescription() {
        return eduProjectDescription;
    }

    public void setEduProjectDescription(String eduProjectDescription) {
        this.eduProjectDescription = eduProjectDescription;
    }

    public Long getEduProjectType() {
        return eduProjectType;
    }

    public void setEduProjectType(Long eduProjectType) {
        this.eduProjectType = eduProjectType;
    }

    public String getEduProjectTypeName() {
        return eduProjectTypeName;
    }

    public void setEduProjectTypeName(String eduProjectTypeName) {
        this.eduProjectTypeName = eduProjectTypeName;
    }

    public String getEduProjectField() {
        return eduProjectField;
    }

    public void setEduProjectField(String eduProjectField) {
        this.eduProjectField = eduProjectField;
    }

    public String getEduPublisher() {
        return eduPublisher;
    }

    public void setEduPublisher(String eduPublisher) {
        this.eduPublisher = eduPublisher;
    }

    public String getEduContact() {
        return eduContact;
    }

    public void setEduContact(String eduContact) {
        this.eduContact = eduContact;
    }

    public String getEduOfficialWebsite() {
        return eduOfficialWebsite;
    }

    public void setEduOfficialWebsite(String eduOfficialWebsite) {
        this.eduOfficialWebsite = eduOfficialWebsite;
    }

    public LocalDateTime getEduStartTime() {
        return eduStartTime;
    }

    public void setEduStartTime(LocalDateTime eduStartTime) {
        this.eduStartTime = eduStartTime;
    }

    public LocalDateTime getEduEndTime() {
        return eduEndTime;
    }

    public void setEduEndTime(LocalDateTime eduEndTime) {
        this.eduEndTime = eduEndTime;
    }

    public Long getEduCreateUser() {
        return eduCreateUser;
    }

    public void setEduCreateUser(Long eduCreateUser) {
        this.eduCreateUser = eduCreateUser;
    }

    public LocalDateTime getEduCreateTime() {
        return eduCreateTime;
    }

    public void setEduCreateTime(LocalDateTime eduCreateTime) {
        this.eduCreateTime = eduCreateTime;
    }

    private String eduProjectTypeName;

    /** 项目领域 */
    private String eduProjectField;

    /** 发布方 */
    private String eduPublisher;

    /** 联系方式 */
    private String eduContact;

    /** 官网 */
    private String eduOfficialWebsite;
    private Long eduModifyUser;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduModifyTime;


    public Long getEduModifyUser() {
        return eduModifyUser;
    }

    public void setEduModifyUser(Long eduModifyUser) {
        this.eduModifyUser = eduModifyUser;
    }

    public LocalDateTime getEduModifyTime() {
        return eduModifyTime;
    }

    public void setEduModifyTime(LocalDateTime eduModifyTime) {
        this.eduModifyTime = eduModifyTime;
    }

    /** 项目开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduStartTime;

    /** 项目结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduEndTime;

    /** 创建者 */
    private Long eduCreateUser;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduCreateTime;
}
