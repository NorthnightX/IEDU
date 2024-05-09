package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduFinancingStage;

/**
 * 融资阶段Service接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
public interface IEduFinancingStageService 
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
     * 批量删除融资阶段
     * 
     * @param eduIds 需要删除的融资阶段主键集合
     * @return 结果
     */
    public int deleteEduFinancingStageByEduIds(Long[] eduIds);

    /**
     * 删除融资阶段信息
     * 
     * @param eduId 融资阶段主键
     * @return 结果
     */
    public int deleteEduFinancingStageByEduId(Long eduId);
}
