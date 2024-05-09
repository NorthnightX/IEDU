package com.iedu.service.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iedu.common.annotation.Excel;
import com.iedu.common.core.domain.TreeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 全国省市对象 s_provinces
 *
 * @author zjz
 * @date 2024-04-02
 */
public class SProvinces extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 城市名
     */
    @Excel(name = "城市名")
    private String cityName;

    /**
     * 简称
     */
    @Excel(name = "简称")
    private String shortName;

    /**
     * 层级
     */
    @Excel(name = "层级")
    private Long depth;

    /**
     * 城市编码
     */
    @Excel(name = "城市编码")
    private String cityCode;

    /**
     * 邮编
     */
    @Excel(name = "邮编")
    private String zipCode;

    /**
     * 全称
     */
    @Excel(name = "全称")
    private String mergerName;

    /**
     * 经度
     */
    @Excel(name = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @Excel(name = "纬度")
    private String latitude;

    /**
     * 拼音
     */
    @Excel(name = "拼音")
    private String pinyin;

    private List<SProvinces> child;

    public List<SProvinces> getChild() {
        return child;
    }

    public void setChild(List<SProvinces> child) {
        this.child = child;
    }

    /**
     * 是否使用
     */
    @Excel(name = "是否使用")
    private Integer isUse;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }

    public Long getDepth() {
        return depth;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsUse() {
        return isUse;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("cityName", getCityName())
                .append("parentId", getParentId())
                .append("shortName", getShortName())
                .append("depth", getDepth())
                .append("cityCode", getCityCode())
                .append("zipCode", getZipCode())
                .append("mergerName", getMergerName())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("pinyin", getPinyin())
                .append("isUse", getIsUse())
                .toString();
    }
}
