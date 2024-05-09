package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduCompanyIndustry;

/**
 * 公司行业Service接口
 * 
 * @author zjz
 * @date 2024-04-02
 */
public interface IEduCompanyIndustryService 
{
    /**
     * 查询公司行业
     * 
     * @param eduId 公司行业主键
     * @return 公司行业
     */
    public EduCompanyIndustry selectEduCompanyIndustryByEduId(Long eduId);

    /**
     * 查询公司行业列表
     * 
     * @param eduCompanyIndustry 公司行业
     * @return 公司行业集合
     */
    public List<EduCompanyIndustry> selectEduCompanyIndustryList(EduCompanyIndustry eduCompanyIndustry);

    /**
     * 新增公司行业
     * 
     * @param eduCompanyIndustry 公司行业
     * @return 结果
     */
    public int insertEduCompanyIndustry(EduCompanyIndustry eduCompanyIndustry);

    /**
     * 修改公司行业
     * 
     * @param eduCompanyIndustry 公司行业
     * @return 结果
     */
    public int updateEduCompanyIndustry(EduCompanyIndustry eduCompanyIndustry);

    /**
     * 批量删除公司行业
     * 
     * @param eduIds 需要删除的公司行业主键集合
     * @return 结果
     */
    public int deleteEduCompanyIndustryByEduIds(Long[] eduIds);

    /**
     * 删除公司行业信息
     * 
     * @param eduId 公司行业主键
     * @return 结果
     */
    public int deleteEduCompanyIndustryByEduId(Long eduId);

    /**
     * 获取公司行业级联列表
     * @return
     */
    public List<EduCompanyIndustry> getIndustryListWithChildren();
}
