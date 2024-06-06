package com.iedu.service.mapper;

import com.iedu.service.domain.Chart;
import com.iedu.service.domain.EduDeliveryRecord;
import com.iedu.service.domain.VO.ResumeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 投递记录管理Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-21
 */
@Mapper
public interface EduDeliveryRecordMapper 
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
     * 删除投递记录管理
     * 
     * @param eduId 投递记录管理主键
     * @return 结果
     */
    public int deleteEduDeliveryRecordByEduId(Long eduId);

    /**
     * 批量删除投递记录管理
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduDeliveryRecordByEduIds(Long[] eduIds);

    List<ResumeVO> selectRecordListByUser(@Param("id") Long id, @Param("pageSize") Integer pageSize,@Param("offset") int offset);

    int selectRecordCountByUser(@Param("id") Long id);

    List<ResumeVO> selectEduDeliveryRecordListByUser(@Param("record") EduDeliveryRecord eduDeliveryRecord, @Param("uid") Long uid);

    Integer selectTodayCount();

    List<Chart> selectAgeProportion();

    List<EduDeliveryRecord> selectAll();
}
