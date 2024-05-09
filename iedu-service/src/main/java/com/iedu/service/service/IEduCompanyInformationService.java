package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduCompanyInformation;

/**
 * 公司信息Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
public interface IEduCompanyInformationService 
{
    /**
     * 查询公司信息
     * 
     * @param eduId 公司信息主键
     * @return 公司信息
     */
    public EduCompanyInformation selectEduCompanyInformationByEduId(Long eduId);

    /**
     * 查询公司信息列表
     * 
     * @param eduCompanyInformation 公司信息
     * @return 公司信息集合
     */
    public List<EduCompanyInformation> selectEduCompanyInformationList(EduCompanyInformation eduCompanyInformation);

    /**
     * 新增公司信息
     * 
     * @param eduCompanyInformation 公司信息
     * @return 结果
     */
    public int insertEduCompanyInformation(EduCompanyInformation eduCompanyInformation);

    /**
     * 修改公司信息
     * 
     * @param eduCompanyInformation 公司信息
     * @return 结果
     */
    public int updateEduCompanyInformation(EduCompanyInformation eduCompanyInformation);

    /**
     * 批量删除公司信息
     * 
     * @param eduIds 需要删除的公司信息主键集合
     * @return 结果
     */
    public int deleteEduCompanyInformationByEduIds(Long[] eduIds);

    /**
     * 删除公司信息信息
     * 
     * @param eduId 公司信息主键
     * @return 结果
     */
    public int deleteEduCompanyInformationByEduId(Long eduId);

    EduCompanyInformation selectEduCompanyInformationByCompanyId(Long id);

}
