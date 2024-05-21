package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.domain.VO.CompanyVO;

/**
 * 公司Service接口
 * 
 * @author zjz
 * @date 2024-04-05
 */
public interface IEduCompanyService 
{
    /**
     * 查询公司
     * 
     * @param eduId 公司主键
     * @return 公司
     */
    public EduCompany selectEduCompanyByEduId(Long eduId);

    /**
     * 查询公司列表
     * 
     * @param eduCompany 公司
     * @return 公司集合
     */
    public List<CompanyVO> selectEduCompanyList(EduCompany eduCompany);

    /**
     * 新增公司
     * 
     * @param eduCompany 公司
     * @return 结果
     */
    public int insertEduCompany(EduCompany eduCompany);

    /**
     * 修改公司
     * 
     * @param eduCompany 公司
     * @return 结果
     */
    public int updateEduCompany(EduCompany eduCompany);

    /**
     * 批量删除公司
     * 
     * @param eduIds 需要删除的公司主键集合
     * @return 结果
     */
    public int deleteEduCompanyByEduIds(Long[] eduIds);

    /**
     * 删除公司信息
     * 
     * @param eduId 公司主键
     * @return 结果
     */
    public int deleteEduCompanyByEduId(Long eduId);

    List<EduCompany> selectAll();
}
