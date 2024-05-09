package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduFinancingStage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 融资阶段Mapper接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Mapper
public interface EduFinancingStageMapper 
{
    /**
     * 查询融资阶段
     * 
     * @param eduId 融资阶段主键
     * @return 融资阶段
     */
    public EduFinancingStage selectEduFinancingStageByEduId(Long eduId);

    /**
     * 查询融资阶段列表
     * 
     * @param eduFinancingStage 融资阶段
     * @return 融资阶段集合
     */
    public List<EduFinancingStage> selectEduFinancingStageList(EduFinancingStage eduFinancingStage);

    /**
     * 新增融资阶段
     * 
     * @param eduFinancingStage 融资阶段
     * @return 结果
     */
    public int insertEduFinancingStage(EduFinancingStage eduFinancingStage);

    /**
     * 修改融资阶段
     * 
     * @param eduFinancingStage 融资阶段
     * @return 结果
     */
    public int updateEduFinancingStage(EduFinancingStage eduFinancingStage);

    /**
     * 删除融资阶段
     * 
     * @param eduId 融资阶段主键
     * @return 结果
     */
    public int deleteEduFinancingStageByEduId(Long eduId);

    /**
     * 批量删除融资阶段
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduFinancingStageByEduIds(Long[] eduIds);
}
