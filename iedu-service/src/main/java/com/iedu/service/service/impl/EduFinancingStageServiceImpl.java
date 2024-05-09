package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduFinancingStageMapper;
import com.iedu.service.domain.EduFinancingStage;
import com.iedu.service.service.IEduFinancingStageService;

/**
 * 融资阶段Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Service
public class EduFinancingStageServiceImpl implements IEduFinancingStageService 
{
    @Autowired
    private EduFinancingStageMapper eduFinancingStageMapper;

    /**
     * 查询融资阶段
     * 
     * @param eduId 融资阶段主键
     * @return 融资阶段
     */
    @Override
    public EduFinancingStage selectEduFinancingStageByEduId(Long eduId)
    {
        return eduFinancingStageMapper.selectEduFinancingStageByEduId(eduId);
    }

    /**
     * 查询融资阶段列表
     * 
     * @param eduFinancingStage 融资阶段
     * @return 融资阶段
     */
    @Override
    public List<EduFinancingStage> selectEduFinancingStageList(EduFinancingStage eduFinancingStage)
    {
        return eduFinancingStageMapper.selectEduFinancingStageList(eduFinancingStage);
    }

    /**
     * 新增融资阶段
     * 
     * @param eduFinancingStage 融资阶段
     * @return 结果
     */
    @Override
    public int insertEduFinancingStage(EduFinancingStage eduFinancingStage)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduFinancingStage.setEduCreateTime(LocalDateTime.now());
        eduFinancingStage.setEduModifyTime(LocalDateTime.now());
        eduFinancingStage.setEduCreateUser(id);
        eduFinancingStage.setEduModifyUser(id);
        return eduFinancingStageMapper.insertEduFinancingStage(eduFinancingStage);
    }

    /**
     * 修改融资阶段
     * 
     * @param eduFinancingStage 融资阶段
     * @return 结果
     */
    @Override
    public int updateEduFinancingStage(EduFinancingStage eduFinancingStage)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduFinancingStage.setEduModifyTime(LocalDateTime.now());
        eduFinancingStage.setEduModifyUser(id);
        return eduFinancingStageMapper.updateEduFinancingStage(eduFinancingStage);
    }

    /**
     * 批量删除融资阶段
     * 
     * @param eduIds 需要删除的融资阶段主键
     * @return 结果
     */
    @Override
    public int deleteEduFinancingStageByEduIds(Long[] eduIds)
    {
        return eduFinancingStageMapper.deleteEduFinancingStageByEduIds(eduIds);
    }

    /**
     * 删除融资阶段信息
     * 
     * @param eduId 融资阶段主键
     * @return 结果
     */
    @Override
    public int deleteEduFinancingStageByEduId(Long eduId)
    {
        return eduFinancingStageMapper.deleteEduFinancingStageByEduId(eduId);
    }
}
