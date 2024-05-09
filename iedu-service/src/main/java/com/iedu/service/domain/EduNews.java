package com.iedu.service.domain;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.BaseEntity;

/**
 * 新闻对象 edu_news
 *
 * @author zjz
 * @date 2024-04-01
 */
public class EduNews extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 新闻ID
     */
    private Long eduId;

    /**
     * 新闻标题
     */
    @Excel(name = "新闻标题")
    private String eduTitle;

    /**
     * 发布者
     */
    @Excel(name = "发布者")
    private String eduPublisher;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduPublishTime;

    /**
     * 内容
     */
    @Excel(name = "内容")
    private String eduContent;

    /**
     * 创建者
     */
    @Excel(name = "创建者")
    private Long eduCreateUser;

    /**
     * 修改者
     */
    @Excel(name = "修改者")
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

    public void setEduTitle(String eduTitle) {
        this.eduTitle = eduTitle;
    }

    public String getEduTitle() {
        return eduTitle;
    }

    public void setEduPublisher(String eduPublisher) {
        this.eduPublisher = eduPublisher;
    }

    public String getEduPublisher() {
        return eduPublisher;
    }

    public void setEduPublishTime(LocalDateTime eduPublishTime) {
        this.eduPublishTime = eduPublishTime;
    }

    public LocalDateTime getEduPublishTime() {
        return eduPublishTime;
    }

    public void setEduContent(String eduContent) {
        this.eduContent = eduContent;
    }

    public String getEduContent() {
        return eduContent;
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
                .append("eduTitle", getEduTitle())
                .append("eduPublisher", getEduPublisher())
                .append("eduPublishTime", getEduPublishTime())
                .append("eduContent", getEduContent())
                .append("eduCreateUser", getEduCreateUser())
                .append("eduModifyUser", getEduModifyUser())
                .append("eduCreateTime", getEduCreateTime())
                .append("eduModifyTime", getEduModifyTime())
                .toString();
    }
}
