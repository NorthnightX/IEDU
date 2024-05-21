package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduAcademyAddress;
import com.iedu.service.domain.VO.AcademyAddressVO;

/**
 * 学校地址Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
public interface IEduAcademyAddressService 
{
    /**
     * 查询学校地址
     * 
     * @param eduId 学校地址主键
     * @return 学校地址
     */
    public EduAcademyAddress selectEduAcademyAddressByEduId(Long eduId);

    /**
     * 查询学校地址列表
     * 
     * @param eduAcademyAddress 学校地址
     * @return 学校地址集合
     */
    public List<AcademyAddressVO> selectEduAcademyAddressList(EduAcademyAddress eduAcademyAddress);

    /**
     * 新增学校地址
     * 
     * @param eduAcademyAddress 学校地址
     * @return 结果
     */
    public int insertEduAcademyAddress(EduAcademyAddress eduAcademyAddress);

    /**
     * 修改学校地址
     * 
     * @param eduAcademyAddress 学校地址
     * @return 结果
     */
    public int updateEduAcademyAddress(EduAcademyAddress eduAcademyAddress);

    /**
     * 批量删除学校地址
     * 
     * @param eduIds 需要删除的学校地址主键集合
     * @return 结果
     */
    public int deleteEduAcademyAddressByEduIds(Long[] eduIds);

    /**
     * 删除学校地址信息
     * 
     * @param eduId 学校地址主键
     * @return 结果
     */
    public int deleteEduAcademyAddressByEduId(Long eduId);

    AcademyAddressVO selectAddressByAcademyId(Long id);
}
