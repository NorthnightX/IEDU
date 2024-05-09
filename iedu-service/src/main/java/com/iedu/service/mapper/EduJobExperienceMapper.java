package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduJobExperience;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作经验Mapper接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Mapper
public interface EduJobExperienceMapper 
{
    /**
     * 查询工作经验
     * 
     * @param eduId 工作经验主键
     * @return 工作经验
     */
    public EduJobExperience selectEduJobExperienceByEduId(Long eduId);

    /**
     * 查询工作经验列表
     * 
     * @param eduJobExperience 工作经验
     * @return 工作经验集合
     */
    public List<EduJobExperience> selectEduJobExperienceList(EduJobExperience eduJobExperience);

    /**
     * 新增工作经验
     * 
     * @param eduJobExperience 工作经验
     * @return 结果
     */
    public int insertEduJobExperience(EduJobExperience eduJobExperience);

    /**
     * 修改工作经验
     * 
     * @param eduJobExperience 工作经验
     * @return 结果
     */
    public int updateEduJobExperience(EduJobExperience eduJobExperience);

    /**
     * 删除工作经验
     * 
     * @param eduId 工作经验主键
     * @return 结果
     */
    public int deleteEduJobExperienceByEduId(Long eduId);

    /**
     * 批量删除工作经验
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduJobExperienceByEduIds(Long[] eduIds);

    List<EduJobExperience> selectAll();
}
