package com.iedu.service.domain.VO;

import java.util.List;

public class JobTypeVO {
    private Long value;
    private String label;

    public JobTypeVO(Long value, String label, List<JobTypeVO> children) {
        this.value = value;
        this.label = label;
        this.children = children;
    }

    public JobTypeVO() {
    }

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

    public List<JobTypeVO> getChildren() {
        return children;
    }

    public void setChildren(List<JobTypeVO> children) {
        this.children = children;
    }

    private List<JobTypeVO> children;
}
