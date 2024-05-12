package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduAcademyAddress;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学校地址Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Mapper
public interface EduAcademyAddressMapper 
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
    public List<EduAcademyAddress> selectEduAcademyAddressList(EduAcademyAddress eduAcademyAddress);

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
     * 删除学校地址
     * 
     * @param eduId 学校地址主键
     * @return 结果
     */
    public int deleteEduAcademyAddressByEduId(Long eduId);

    /**
     * 批量删除学校地址
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAcademyAddressByEduIds(Long[] eduIds);
}
