package com.iedu.service.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iedu.common.annotation.Excel;
import com.iedu.service.domain.EduAcademyPic;

import java.time.LocalDateTime;

public class AcademyVO {
    private Long eduId;

    /** 院校名 */
    @Excel(name = "院校名")
    private String eduAcademyName;

    /** 院校标签 */
    @Excel(name = "院校标签")
    private String eduAcademyTag;
    private AcademyAddressVO academyAddressVO;
    private EduAcademyPic eduAcademyPic;

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }

    public String getEduAcademyName() {
        return eduAcademyName;
    }

    public void setEduAcademyName(String eduAcademyName) {
        this.eduAcademyName = eduAcademyName;
    }

    public String getEduAcademyTag() {
        return eduAcademyTag;
    }

    public void setEduAcademyTag(String eduAcademyTag) {
        this.eduAcademyTag = eduAcademyTag;
    }

    public AcademyAddressVO getAcademyAddressVO() {
        return academyAddressVO;
    }

    public void setAcademyAddressVO(AcademyAddressVO academyAddressVO) {
        this.academyAddressVO = academyAddressVO;
    }

    public EduAcademyPic getEduAcademyPic() {
        return eduAcademyPic;
    }

    public void setEduAcademyPic(EduAcademyPic eduAcademyPic) {
        this.eduAcademyPic = eduAcademyPic;
    }

    public String getEduAcademyIntroduce() {
        return eduAcademyIntroduce;
    }

    public void setEduAcademyIntroduce(String eduAcademyIntroduce) {
        this.eduAcademyIntroduce = eduAcademyIntroduce;
    }

    public Long getEduAddressId() {
        return eduAddressId;
    }

    public void setEduAddressId(Long eduAddressId) {
        this.eduAddressId = eduAddressId;
    }

    public Long getEduCreateUser() {
        return eduCreateUser;
    }

    public void setEduCreateUser(Long eduCreateUser) {
        this.eduCreateUser = eduCreateUser;
    }

    public Long getEduModifyUser() {
        return eduModifyUser;
    }

    public void setEduModifyUser(Long eduModifyUser) {
        this.eduModifyUser = eduModifyUser;
    }

    public LocalDateTime getEduCreateTime() {
        return eduCreateTime;
    }

    public void setEduCreateTime(LocalDateTime eduCreateTime) {
        this.eduCreateTime = eduCreateTime;
    }

    public LocalDateTime getEduModifyTime() {
        return eduModifyTime;
    }

    public void setEduModifyTime(LocalDateTime eduModifyTime) {
        this.eduModifyTime = eduModifyTime;
    }

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
}
