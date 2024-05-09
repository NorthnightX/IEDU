package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduJobType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工作类型Mapper接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Mapper
public interface EduJobTypeMapper 
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
     * 删除工作类型
     * 
     * @param eduId 工作类型主键
     * @return 结果
     */
    public int deleteEduJobTypeByEduId(Long eduId);

    /**
     * 批量删除工作类型
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduJobTypeByEduIds(Long[] eduIds);
}
