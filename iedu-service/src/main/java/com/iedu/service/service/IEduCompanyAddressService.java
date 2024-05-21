package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduCompanyAddress;
import com.iedu.service.domain.VO.CompanyAddressVO;

/**
 * 公司地址Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
public interface IEduCompanyAddressService 
{
    /**
     * 查询公司地址
     * 
     * @param eduId 公司地址主键
     * @return 公司地址
     */
    public EduCompanyAddress selectEduCompanyAddressByEduId(Long eduId);

    /**
     * 查询公司地址列表
     * 
     * @param eduCompanyAddress 公司地址
     * @return 公司地址集合
     */
    public List<CompanyAddressVO> selectEduCompanyAddressList(EduCompanyAddress eduCompanyAddress);

    /**
     * 新增公司地址
     * 
     * @param eduCompanyAddress 公司地址
     * @return 结果
     */
    public int insertEduCompanyAddress(EduCompanyAddress eduCompanyAddress);

    /**
     * 修改公司地址
     * 
     * @param eduCompanyAddress 公司地址
     * @return 结果
     */
    public int updateEduCompanyAddress(EduCompanyAddress eduCompanyAddress);

    /**
     * 批量删除公司地址
     * 
     * @param eduIds 需要删除的公司地址主键集合
     * @return 结果
     */
    public int deleteEduCompanyAddressByEduIds(Long[] eduIds);

    /**
     * 删除公司地址信息
     * 
     * @param eduId 公司地址主键
     * @return 结果
     */
    public int deleteEduCompanyAddressByEduId(Long eduId);

    CompanyAddressVO selectAddressByCompany(Long id);
}
