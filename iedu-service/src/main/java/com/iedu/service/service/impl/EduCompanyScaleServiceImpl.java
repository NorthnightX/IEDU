package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyScaleMapper;
import com.iedu.service.domain.EduCompanyScale;
import com.iedu.service.service.IEduCompanyScaleService;

/**
 * 公司规模Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Service
public class EduCompanyScaleServiceImpl implements IEduCompanyScaleService 
{
    @Autowired
    private EduCompanyScaleMapper eduCompanyScaleMapper;

    /**
     * 查询公司规模
     * 
     * @param eduId 公司规模主键
     * @return 公司规模
     */
    @Override
    public EduCompanyScale selectEduCompanyScaleByEduId(Long eduId)
    {
        return eduCompanyScaleMapper.selectEduCompanyScaleByEduId(eduId);
    }

    /**
     * 查询公司规模列表
     * 
     * @param eduCompanyScale 公司规模
     * @return 公司规模
     */
    @Override
    public List<EduCompanyScale> selectEduCompanyScaleList(EduCompanyScale eduCompanyScale)
    {
        return eduCompanyScaleMapper.selectEduCompanyScaleList(eduCompanyScale);
    }

    /**
     * 新增公司规模
     * 
     * @param eduCompanyScale 公司规模
     * @return 结果
     */
    @Override
    public int insertEduCompanyScale(EduCompanyScale eduCompanyScale)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyScale.setEduCreateTime(LocalDateTime.now());
        eduCompanyScale.setEduModifyTime(LocalDateTime.now());
        eduCompanyScale.setEduCreateUser(id);
        eduCompanyScale.setEduModifyUser(id);
        return eduCompanyScaleMapper.insertEduCompanyScale(eduCompanyScale);
    }

    /**
     * 修改公司规模
     * 
     * @param eduCompanyScale 公司规模
     * @return 结果
     */
    @Override
    public int updateEduCompanyScale(EduCompanyScale eduCompanyScale)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyScale.setEduModifyTime(LocalDateTime.now());
        eduCompanyScale.setEduModifyUser(id);
        return eduCompanyScaleMapper.updateEduCompanyScale(eduCompanyScale);
    }

    /**
     * 批量删除公司规模
     * 
     * @param eduIds 需要删除的公司规模主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyScaleByEduIds(Long[] eduIds)
    {
        return eduCompanyScaleMapper.deleteEduCompanyScaleByEduIds(eduIds);
    }

    /**
     * 删除公司规模信息
     * 
     * @param eduId 公司规模主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyScaleByEduId(Long eduId)
    {
        return eduCompanyScaleMapper.deleteEduCompanyScaleByEduId(eduId);
    }
}
