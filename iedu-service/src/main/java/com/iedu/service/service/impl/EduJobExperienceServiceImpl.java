package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduJobExperienceMapper;
import com.iedu.service.domain.EduJobExperience;
import com.iedu.service.service.IEduJobExperienceService;

/**
 * 工作经验Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Service
public class EduJobExperienceServiceImpl implements IEduJobExperienceService 
{
    @Autowired
    private EduJobExperienceMapper eduJobExperienceMapper;

    /**
     * 查询工作经验
     * 
     * @param eduId 工作经验主键
     * @return 工作经验
     */
    @Override
    public EduJobExperience selectEduJobExperienceByEduId(Long eduId)
    {
        return eduJobExperienceMapper.selectEduJobExperienceByEduId(eduId);
    }

    /**
     * 查询工作经验列表
     * 
     * @param eduJobExperience 工作经验
     * @return 工作经验
     */
    @Override
    public List<EduJobExperience> selectEduJobExperienceList(EduJobExperience eduJobExperience)
    {
        return eduJobExperienceMapper.selectEduJobExperienceList(eduJobExperience);
    }

    /**
     * 新增工作经验
     * 
     * @param eduJobExperience 工作经验
     * @return 结果
     */
    @Override
    public int insertEduJobExperience(EduJobExperience eduJobExperience)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduJobExperience.setEduCreateTime(LocalDateTime.now());
        eduJobExperience.setEduModifyTime(LocalDateTime.now());
        eduJobExperience.setEduCreateUser(id);
        eduJobExperience.setEduModifyUser(id);
        return eduJobExperienceMapper.insertEduJobExperience(eduJobExperience);
    }

    /**
     * 修改工作经验
     * 
     * @param eduJobExperience 工作经验
     * @return 结果
     */
    @Override
    public int updateEduJobExperience(EduJobExperience eduJobExperience)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduJobExperience.setEduModifyTime(LocalDateTime.now());
        eduJobExperience.setEduModifyUser(id);
        return eduJobExperienceMapper.updateEduJobExperience(eduJobExperience);
    }

    /**
     * 批量删除工作经验
     * 
     * @param eduIds 需要删除的工作经验主键
     * @return 结果
     */
    @Override
    public int deleteEduJobExperienceByEduIds(Long[] eduIds)
    {
        return eduJobExperienceMapper.deleteEduJobExperienceByEduIds(eduIds);
    }

    /**
     * 删除工作经验信息
     * 
     * @param eduId 工作经验主键
     * @return 结果
     */
    @Override
    public int deleteEduJobExperienceByEduId(Long eduId)
    {
        return eduJobExperienceMapper.deleteEduJobExperienceByEduId(eduId);
    }

    @Override
    public List<EduJobExperience> selectAll() {
        return eduJobExperienceMapper.selectAll();
    }
}
