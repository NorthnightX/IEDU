package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduCarousel;

/**
 * 轮播图管理Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
public interface IEduCarouselService 
{
    /**
     * 查询轮播图管理
     * 
     * @param eduId 轮播图管理主键
     * @return 轮播图管理
     */
    public EduCarousel selectEduCarouselByEduId(Long eduId);

    /**
     * 查询轮播图管理列表
     * 
     * @param eduCarousel 轮播图管理
     * @return 轮播图管理集合
     */
    public List<EduCarousel> selectEduCarouselList(EduCarousel eduCarousel);

    /**
     * 新增轮播图管理
     * 
     * @param eduCarousel 轮播图管理
     * @return 结果
     */
    public int insertEduCarousel(EduCarousel eduCarousel);

    /**
     * 修改轮播图管理
     * 
     * @param eduCarousel 轮播图管理
     * @return 结果
     */
    public int updateEduCarousel(EduCarousel eduCarousel);

    /**
     * 批量删除轮播图管理
     * 
     * @param eduIds 需要删除的轮播图管理主键集合
     * @return 结果
     */
    public int deleteEduCarouselByEduIds(Long[] eduIds);

    /**
     * 删除轮播图管理信息
     * 
     * @param eduId 轮播图管理主键
     * @return 结果
     */
    public int deleteEduCarouselByEduId(Long eduId);

    List<EduCarousel> selectAllCarouselList();
}
