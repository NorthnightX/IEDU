package com.iedu.service.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduAdvertiseMapper;
import com.iedu.service.domain.EduAdvertise;
import com.iedu.service.service.IEduAdvertiseService;

/**
 * 广告Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-06-03
 */
@Service
public class EduAdvertiseServiceImpl implements IEduAdvertiseService 
{
    @Autowired
    private EduAdvertiseMapper eduAdvertiseMapper;

    /**
     * 查询广告
     * 
     * @param eduId 广告主键
     * @return 广告
     */
    @Override
    public EduAdvertise selectEduAdvertiseByEduId(Long eduId)
    {
        return eduAdvertiseMapper.selectEduAdvertiseByEduId(eduId);
    }

    /**
     * 查询广告列表
     * 
     * @param eduAdvertise 广告
     * @return 广告
     */
    @Override
    public List<EduAdvertise> selectEduAdvertiseList(EduAdvertise eduAdvertise)
    {
        return eduAdvertiseMapper.selectEduAdvertiseList(eduAdvertise);
    }

    /**
     * 新增广告
     * 
     * @param eduAdvertise 广告
     * @return 结果
     */
    @Override
    public int insertEduAdvertise(EduAdvertise eduAdvertise)
    {

        return eduAdvertiseMapper.insertEduAdvertise(eduAdvertise);
    }

    /**
     * 修改广告
     * 
     * @param eduAdvertise 广告
     * @return 结果
     */
    @Override
    public int updateEduAdvertise(EduAdvertise eduAdvertise)
    {
        return eduAdvertiseMapper.updateEduAdvertise(eduAdvertise);
    }

    /**
     * 批量删除广告
     * 
     * @param eduIds 需要删除的广告主键
     * @return 结果
     */
    @Override
    public int deleteEduAdvertiseByEduIds(Long[] eduIds)
    {
        return eduAdvertiseMapper.deleteEduAdvertiseByEduIds(eduIds);
    }

    /**
     * 删除广告信息
     * 
     * @param eduId 广告主键
     * @return 结果
     */
    @Override
    public int deleteEduAdvertiseByEduId(Long eduId)
    {
        return eduAdvertiseMapper.deleteEduAdvertiseByEduId(eduId);
    }

    @Override
    public List<EduAdvertise> getSearch() {
        EduAdvertise eduAdvertise = new EduAdvertise();
        eduAdvertise.setEduLocation(1L);
        return eduAdvertiseMapper.selectEduAdvertiseList(eduAdvertise);
    }
}
