package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.domain.VO.CompanyAddressVO;
import com.iedu.service.mapper.EduCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyAddressMapper;
import com.iedu.service.domain.EduCompanyAddress;
import com.iedu.service.service.IEduCompanyAddressService;

/**
 * 公司地址Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
@Service
public class EduCompanyAddressServiceImpl implements IEduCompanyAddressService 
{
    @Autowired
    private EduCompanyAddressMapper eduCompanyAddressMapper;
    @Autowired
    private EduCompanyMapper eduCompanyMapper;

    /**
     * 查询公司地址
     * 
     * @param eduId 公司地址主键
     * @return 公司地址
     */
    @Override
    public EduCompanyAddress selectEduCompanyAddressByEduId(Long eduId)
    {
        return eduCompanyAddressMapper.selectEduCompanyAddressByEduId(eduId);
    }

    /**
     * 查询公司地址列表
     * 
     * @param eduCompanyAddress 公司地址
     * @return 公司地址
     */
    @Override
    public List<CompanyAddressVO> selectEduCompanyAddressList(EduCompanyAddress eduCompanyAddress)
    {
        return eduCompanyAddressMapper.selectEduCompanyAddressList(eduCompanyAddress);
    }

    /**
     * 新增公司地址
     * 
     * @param eduCompanyAddress 公司地址
     * @return 结果
     */
    @Override
    public int insertEduCompanyAddress(EduCompanyAddress eduCompanyAddress)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyAddress.setEduCreateTime(LocalDateTime.now());
        eduCompanyAddress.setEduModifyTime(LocalDateTime.now());
        eduCompanyAddress.setEduCreateUser(id);
        eduCompanyAddress.setEduModifyUser(id);
        eduCompanyAddressMapper.insertEduCompanyAddress(eduCompanyAddress);
        EduCompany company = new EduCompany();
        company.setEduId(eduCompanyAddress.getEduCompanyId());
        company.setEduAddressId(eduCompanyAddress.getEduId());
        return eduCompanyMapper.updateEduCompany(company);
    }

    /**
     * 修改公司地址
     * 
     * @param eduCompanyAddress 公司地址
     * @return 结果
     */
    @Override
    public int updateEduCompanyAddress(EduCompanyAddress eduCompanyAddress)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyAddress.setEduModifyTime(LocalDateTime.now());
        eduCompanyAddress.setEduModifyUser(id);
        return eduCompanyAddressMapper.updateEduCompanyAddress(eduCompanyAddress);
    }

    /**
     * 批量删除公司地址
     * 
     * @param eduIds 需要删除的公司地址主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyAddressByEduIds(Long[] eduIds)
    {
        return eduCompanyAddressMapper.deleteEduCompanyAddressByEduIds(eduIds);
    }

    /**
     * 删除公司地址信息
     * 
     * @param eduId 公司地址主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyAddressByEduId(Long eduId)
    {
        return eduCompanyAddressMapper.deleteEduCompanyAddressByEduId(eduId);
    }

    @Override
    public CompanyAddressVO selectAddressByCompany(Long id) {
        EduCompanyAddress address = new EduCompanyAddress();
        address.setEduCompanyId(id);
        List<CompanyAddressVO> list = eduCompanyAddressMapper.selectEduCompanyAddressList(address);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
