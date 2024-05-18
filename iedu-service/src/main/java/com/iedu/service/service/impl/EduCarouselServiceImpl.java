package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCarouselMapper;
import com.iedu.service.domain.EduCarousel;
import com.iedu.service.service.IEduCarouselService;

/**
 * 轮播图管理Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@Service
public class EduCarouselServiceImpl implements IEduCarouselService 
{
    @Autowired
    private EduCarouselMapper eduCarouselMapper;

    /**
     * 查询轮播图管理
     * 
     * @param eduId 轮播图管理主键
     * @return 轮播图管理
     */
    @Override
    public EduCarousel selectEduCarouselByEduId(Long eduId)
    {
        return eduCarouselMapper.selectEduCarouselByEduId(eduId);
    }

    /**
     * 查询轮播图管理列表
     * 
     * @param eduCarousel 轮播图管理
     * @return 轮播图管理
     */
    @Override
    public List<EduCarousel> selectEduCarouselList(EduCarousel eduCarousel)
    {
        return eduCarouselMapper.selectEduCarouselList(eduCarousel);
    }

    /**
     * 新增轮播图管理
     * 
     * @param eduCarousel 轮播图管理
     * @return 结果
     */
    @Override
    public int insertEduCarousel(EduCarousel eduCarousel)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCarousel.setEduCreateTime(LocalDateTime.now());
        eduCarousel.setEduModifyTime(LocalDateTime.now());
        eduCarousel.setEduCreateUser(id);
        eduCarousel.setEduModifyUser(id);
        return eduCarouselMapper.insertEduCarousel(eduCarousel);
    }

    /**
     * 修改轮播图管理
     * 
     * @param eduCarousel 轮播图管理
     * @return 结果
     */
    @Override
    public int updateEduCarousel(EduCarousel eduCarousel)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCarousel.setEduModifyTime(LocalDateTime.now());
        eduCarousel.setEduModifyUser(id);
        return eduCarouselMapper.updateEduCarousel(eduCarousel);
    }

    /**
     * 批量删除轮播图管理
     * 
     * @param eduIds 需要删除的轮播图管理主键
     * @return 结果
     */
    @Override
    public int deleteEduCarouselByEduIds(Long[] eduIds)
    {
        return eduCarouselMapper.deleteEduCarouselByEduIds(eduIds);
    }

    /**
     * 删除轮播图管理信息
     * 
     * @param eduId 轮播图管理主键
     * @return 结果
     */
    @Override
    public int deleteEduCarouselByEduId(Long eduId)
    {
        return eduCarouselMapper.deleteEduCarouselByEduId(eduId);
    }

    @Override
    public List<EduCarousel> selectAllCarouselList() {
        return eduCarouselMapper.selectEduCarouselList(new EduCarousel());
    }
}
