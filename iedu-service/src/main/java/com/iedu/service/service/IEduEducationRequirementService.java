package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduEducationRequirement;

/**
 * 学历要求Service接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
public interface IEduEducationRequirementService 
{
    /**
     * 查询学历要求
     * 
     * @param eduId 学历要求主键
     * @return 学历要求
     */
    public EduEducationRequirement selectEduEducationRequirementByEduId(Long eduId);

    /**
     * 查询学历要求列表
     * 
     * @param eduEducationRequirement 学历要求
     * @return 学历要求集合
     */
    public List<EduEducationRequirement> selectEduEducationRequirementList(EduEducationRequirement eduEducationRequirement);

    /**
     * 新增学历要求
     * 
     * @param eduEducationRequirement 学历要求
     * @return 结果
     */
    public int insertEduEducationRequirement(EduEducationRequirement eduEducationRequirement);

    /**
     * 修改学历要求
     * 
     * @param eduEducationRequirement 学历要求
     * @return 结果
     */
    public int updateEduEducationRequirement(EduEducationRequirement eduEducationRequirement);

    /**
     * 批量删除学历要求
     * 
     * @param eduIds 需要删除的学历要求主键集合
     * @return 结果
     */
    public int deleteEduEducationRequirementByEduIds(Long[] eduIds);

    /**
     * 删除学历要求信息
     * 
     * @param eduId 学历要求主键
     * @return 结果
     */
    public int deleteEduEducationRequirementByEduId(Long eduId);

    List<EduEducationRequirement> selectAll();
}
