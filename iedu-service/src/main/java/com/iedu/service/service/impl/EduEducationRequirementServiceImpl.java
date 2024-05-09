package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduEducationRequirementMapper;
import com.iedu.service.domain.EduEducationRequirement;
import com.iedu.service.service.IEduEducationRequirementService;

/**
 * 学历要求Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Service
public class EduEducationRequirementServiceImpl implements IEduEducationRequirementService 
{
    @Autowired
    private EduEducationRequirementMapper eduEducationRequirementMapper;

    /**
     * 查询学历要求
     * 
     * @param eduId 学历要求主键
     * @return 学历要求
     */
    @Override
    public EduEducationRequirement selectEduEducationRequirementByEduId(Long eduId)
    {
        return eduEducationRequirementMapper.selectEduEducationRequirementByEduId(eduId);
    }

    /**
     * 查询学历要求列表
     * 
     * @param eduEducationRequirement 学历要求
     * @return 学历要求
     */
    @Override
    public List<EduEducationRequirement> selectEduEducationRequirementList(EduEducationRequirement eduEducationRequirement)
    {
        return eduEducationRequirementMapper.selectEduEducationRequirementList(eduEducationRequirement);
    }

    /**
     * 新增学历要求
     * 
     * @param eduEducationRequirement 学历要求
     * @return 结果
     */
    @Override
    public int insertEduEducationRequirement(EduEducationRequirement eduEducationRequirement)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduEducationRequirement.setEduCreateTime(LocalDateTime.now());
        eduEducationRequirement.setEduModifyTime(LocalDateTime.now());
        eduEducationRequirement.setEduCreateUser(id);
        eduEducationRequirement.setEduModifyUser(id);
        return eduEducationRequirementMapper.insertEduEducationRequirement(eduEducationRequirement);
    }

    /**
     * 修改学历要求
     * 
     * @param eduEducationRequirement 学历要求
     * @return 结果
     */
    @Override
    public int updateEduEducationRequirement(EduEducationRequirement eduEducationRequirement)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduEducationRequirement.setEduModifyTime(LocalDateTime.now());
        eduEducationRequirement.setEduModifyUser(id);
        return eduEducationRequirementMapper.updateEduEducationRequirement(eduEducationRequirement);
    }

    /**
     * 批量删除学历要求
     * 
     * @param eduIds 需要删除的学历要求主键
     * @return 结果
     */
    @Override
    public int deleteEduEducationRequirementByEduIds(Long[] eduIds)
    {
        return eduEducationRequirementMapper.deleteEduEducationRequirementByEduIds(eduIds);
    }

    /**
     * 删除学历要求信息
     * 
     * @param eduId 学历要求主键
     * @return 结果
     */
    @Override
    public int deleteEduEducationRequirementByEduId(Long eduId)
    {
        return eduEducationRequirementMapper.deleteEduEducationRequirementByEduId(eduId);
    }

    @Override
    public List<EduEducationRequirement> selectAll() {
        return eduEducationRequirementMapper.selectAll();
    }
}
