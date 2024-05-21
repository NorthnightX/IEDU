package com.iedu.service.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CompanyAddressVO {

    private Long eduId;
    private Long eduCompanyId;
    private String companyName;
    private Long eduCountryId;
    private String eduCountryName;
    private Long eduProvinceId;
    private String eduProvinceName;
    private Long eduCityId;
    private String eduCityName;
    private Long eduDistrictId;
    private String eduDistrictName;
    private String eduDetailedAddress;
    private Long eduCreateUser;
    private Long eduModifyUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eduModifyTime;

    public Long getEduCompanyId() {
        return eduCompanyId;
    }

    public void setEduCompanyId(Long eduCompanyId) {
        this.eduCompanyId = eduCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getEduId() {
        return eduId;
    }

    public void setEduId(Long eduId) {
        this.eduId = eduId;
    }


    public Long getEduCountryId() {
        return eduCountryId;
    }

    public void setEduCountryId(Long eduCountryId) {
        this.eduCountryId = eduCountryId;
    }

    public String getEduCountryName() {
        return eduCountryName;
    }

    public void setEduCountryName(String eduCountryName) {
        this.eduCountryName = eduCountryName;
    }

    public Long getEduProvinceId() {
        return eduProvinceId;
    }

    public void setEduProvinceId(Long eduProvinceId) {
        this.eduProvinceId = eduProvinceId;
    }

    public String getEduProvinceName() {
        return eduProvinceName;
    }

    public void setEduProvinceName(String eduProvinceName) {
        this.eduProvinceName = eduProvinceName;
    }

    public Long getEduCityId() {
        return eduCityId;
    }

    public void setEduCityId(Long eduCityId) {
        this.eduCityId = eduCityId;
    }

    public String getEduCityName() {
        return eduCityName;
    }

    public void setEduCityName(String eduCityName) {
        this.eduCityName = eduCityName;
    }

    public Long getEduDistrictId() {
        return eduDistrictId;
    }

    public void setEduDistrictId(Long eduDistrictId) {
        this.eduDistrictId = eduDistrictId;
    }

    public String getEduDistrictName() {
        return eduDistrictName;
    }

    public void setEduDistrictName(String eduDistrictName) {
        this.eduDistrictName = eduDistrictName;
    }

    public String getEduDetailedAddress() {
        return eduDetailedAddress;
    }

    public void setEduDetailedAddress(String eduDetailedAddress) {
        this.eduDetailedAddress = eduDetailedAddress;
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
}
