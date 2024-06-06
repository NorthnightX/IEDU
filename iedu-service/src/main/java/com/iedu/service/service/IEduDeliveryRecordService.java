package com.iedu.service.service;

import com.iedu.service.domain.Chart;
import com.iedu.service.domain.EduDeliveryRecord;
import com.iedu.service.domain.VO.ResumeVO;

import java.util.HashMap;
import java.util.List;

/**
 * 投递记录管理Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-21
 */
public interface IEduDeliveryRecordService 
{
    /**
     * 查询投递记录管理
     * 
     * @param eduId 投递记录管理主键
     * @return 投递记录管理
     */
    public EduDeliveryRecord selectEduDeliveryRecordByEduId(Long eduId);

    /**
     * 查询投递记录管理列表
     * 
     * @param eduDeliveryRecord 投递记录管理
     * @return 投递记录管理集合
     */
    public List<ResumeVO> selectEduDeliveryRecordList(EduDeliveryRecord eduDeliveryRecord);

    /**
     * 新增投递记录管理
     * 
     * @param eduDeliveryRecord 投递记录管理
     * @return 结果
     */
    public int insertEduDeliveryRecord(EduDeliveryRecord eduDeliveryRecord);

    /**
     * 修改投递记录管理
     * 
     * @param eduDeliveryRecord 投递记录管理
     * @return 结果
     */
    public int updateEduDeliveryRecord(EduDeliveryRecord eduDeliveryRecord);

    /**
     * 批量删除投递记录管理
     * 
     * @param eduIds 需要删除的投递记录管理主键集合
     * @return 结果
     */
    public int deleteEduDeliveryRecordByEduIds(Long[] eduIds);

    /**
     * 删除投递记录管理信息
     * 
     * @param eduId 投递记录管理主键
     * @return 结果
     */
    public int deleteEduDeliveryRecordByEduId(Long eduId);

    List<ResumeVO> selectRecruitByCondition(Long id, Integer pageSize, Integer pageNum);

    int getRecordCountByUser(Long id);

    Integer selectTodayCount();

    List<Chart> selectAgeProportion();

    List<Chart> selectTypeProportion();
}
