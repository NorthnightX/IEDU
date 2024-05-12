package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.EduAcademy;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.domain.EduCompanyAddress;
import com.iedu.service.mapper.EduAcademyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduAcademyAddressMapper;
import com.iedu.service.domain.EduAcademyAddress;
import com.iedu.service.service.IEduAcademyAddressService;

/**
 * 学校地址Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Service
public class EduAcademyAddressServiceImpl implements IEduAcademyAddressService 
{
    @Autowired
    private EduAcademyAddressMapper eduAcademyAddressMapper;
    @Autowired
    private EduAcademyMapper academyMapper;

    /**
     * 查询学校地址
     * 
     * @param eduId 学校地址主键
     * @return 学校地址
     */
    @Override
    public EduAcademyAddress selectEduAcademyAddressByEduId(Long eduId)
    {
        return eduAcademyAddressMapper.selectEduAcademyAddressByEduId(eduId);
    }

    /**
     * 查询学校地址列表
     * 
     * @param eduAcademyAddress 学校地址
     * @return 学校地址
     */
    @Override
    public List<EduAcademyAddress> selectEduAcademyAddressList(EduAcademyAddress eduAcademyAddress)
    {
        return eduAcademyAddressMapper.selectEduAcademyAddressList(eduAcademyAddress);
    }

    /**
     * 新增学校地址
     * 
     * @param eduAcademyAddress 学校地址
     * @return 结果
     */
    @Override
    public int insertEduAcademyAddress(EduAcademyAddress eduAcademyAddress)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduAcademyAddress.setEduCreateTime(LocalDateTime.now());
        eduAcademyAddress.setEduModifyTime(LocalDateTime.now());
        eduAcademyAddress.setEduCreateUser(id);
        eduAcademyAddress.setEduModifyUser(id);
        eduAcademyAddressMapper.insertEduAcademyAddress(eduAcademyAddress);
        EduAcademy academy = new EduAcademy();
        academy.setEduId(eduAcademyAddress.getEduAcademyId());
        academy.setEduAddressId(eduAcademyAddress.getEduId());
        return academyMapper.updateEduAcademy(academy);
    }

    /**
     * 修改学校地址
     * 
     * @param eduAcademyAddress 学校地址
     * @return 结果
     */
    @Override
    public int updateEduAcademyAddress(EduAcademyAddress eduAcademyAddress)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduAcademyAddress.setEduModifyTime(LocalDateTime.now());
        eduAcademyAddress.setEduModifyUser(id);
        return eduAcademyAddressMapper.updateEduAcademyAddress(eduAcademyAddress);
    }

    /**
     * 批量删除学校地址
     * 
     * @param eduIds 需要删除的学校地址主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyAddressByEduIds(Long[] eduIds)
    {
        return eduAcademyAddressMapper.deleteEduAcademyAddressByEduIds(eduIds);
    }

    /**
     * 删除学校地址信息
     * 
     * @param eduId 学校地址主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyAddressByEduId(Long eduId)
    {
        return eduAcademyAddressMapper.deleteEduAcademyAddressByEduId(eduId);
    }

    @Override
    public EduAcademyAddress selectAddressByAcademyId(Long id) {
        EduAcademyAddress address = new EduAcademyAddress();
        address.setEduAcademyId(id);
        List<EduAcademyAddress> list = eduAcademyAddressMapper.selectEduAcademyAddressList(address);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
