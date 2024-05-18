package com.iedu.service.domain.VO;


import java.util.List;
public class ProvincesVO {
    private Long value;
    private String label;
    private List<ProvincesVO> children;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ProvincesVO> getChildren() {
        return children;
    }

    public void setChildren(List<ProvincesVO> child) {
        this.children = child;
    }
}
