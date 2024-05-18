package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduJobType;
import com.iedu.service.domain.VO.JobTypeVO;

/**
 * 工作类型Service接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
public interface IEduJobTypeService 
{
    /**
     * 查询工作类型
     * 
     * @param eduId 工作类型主键
     * @return 工作类型
     */
    public EduJobType selectEduJobTypeByEduId(Long eduId);

    /**
     * 查询工作类型列表
     * 
     * @param eduJobType 工作类型
     * @return 工作类型集合
     */
    public List<EduJobType> selectEduJobTypeList(EduJobType eduJobType);

    /**
     * 新增工作类型
     * 
     * @param eduJobType 工作类型
     * @return 结果
     */
    public int insertEduJobType(EduJobType eduJobType);

    /**
     * 修改工作类型
     * 
     * @param eduJobType 工作类型
     * @return 结果
     */
    public int updateEduJobType(EduJobType eduJobType);

    /**
     * 批量删除工作类型
     * 
     * @param eduIds 需要删除的工作类型主键集合
     * @return 结果
     */
    public int deleteEduJobTypeByEduIds(Long[] eduIds);

    /**
     * 删除工作类型信息
     * 
     * @param eduId 工作类型主键
     * @return 结果
     */
    public int deleteEduJobTypeByEduId(Long eduId);

    List<JobTypeVO> selectAll();
}
