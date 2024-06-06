package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduAdvertise;

/**
 * 广告Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-06-03
 */
public interface IEduAdvertiseService 
{
    /**
     * 查询广告
     * 
     * @param eduId 广告主键
     * @return 广告
     */
    public EduAdvertise selectEduAdvertiseByEduId(Long eduId);

    /**
     * 查询广告列表
     * 
     * @param eduAdvertise 广告
     * @return 广告集合
     */
    public List<EduAdvertise> selectEduAdvertiseList(EduAdvertise eduAdvertise);

    /**
     * 新增广告
     * 
     * @param eduAdvertise 广告
     * @return 结果
     */
    public int insertEduAdvertise(EduAdvertise eduAdvertise);

    /**
     * 修改广告
     * 
     * @param eduAdvertise 广告
     * @return 结果
     */
    public int updateEduAdvertise(EduAdvertise eduAdvertise);

    /**
     * 批量删除广告
     * 
     * @param eduIds 需要删除的广告主键集合
     * @return 结果
     */
    public int deleteEduAdvertiseByEduIds(Long[] eduIds);

    /**
     * 删除广告信息
     * 
     * @param eduId 广告主键
     * @return 结果
     */
    public int deleteEduAdvertiseByEduId(Long eduId);

    List<EduAdvertise> getSearch();
}
