package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduCompanyIndustry;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司行业Mapper接口
 * 
 * @author zjz
 * @date 2024-04-02
 */
@Mapper
public interface EduCompanyIndustryMapper 
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
     * 删除公司行业
     * 
     * @param eduId 公司行业主键
     * @return 结果
     */
    public int deleteEduCompanyIndustryByEduId(Long eduId);

    /**
     * 批量删除公司行业
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCompanyIndustryByEduIds(Long[] eduIds);

    List<EduCompanyIndustry> selectIndustryListWithChildren(Long id);
}
