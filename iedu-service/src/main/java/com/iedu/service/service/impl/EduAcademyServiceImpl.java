package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduAcademyMapper;
import com.iedu.service.domain.EduAcademy;
import com.iedu.service.service.IEduAcademyService;

/**
 * 院校管理Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Service
public class EduAcademyServiceImpl implements IEduAcademyService 
{
    @Autowired
    private EduAcademyMapper eduAcademyMapper;

    /**
     * 查询院校管理
     * 
     * @param eduId 院校管理主键
     * @return 院校管理
     */
    @Override
    public EduAcademy selectEduAcademyByEduId(Long eduId)
    {
        return eduAcademyMapper.selectEduAcademyByEduId(eduId);
    }

    /**
     * 查询院校管理列表
     * 
     * @param eduAcademy 院校管理
     * @return 院校管理
     */
    @Override
    public List<EduAcademy> selectEduAcademyList(EduAcademy eduAcademy)
    {
        return eduAcademyMapper.selectEduAcademyList(eduAcademy);
    }

    /**
     * 新增院校管理
     * 
     * @param eduAcademy 院校管理
     * @return 结果
     */
    @Override
    public int insertEduAcademy(EduAcademy eduAcademy)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduAcademy.setEduCreateTime(LocalDateTime.now());
        eduAcademy.setEduModifyTime(LocalDateTime.now());
        eduAcademy.setEduCreateUser(id);
        eduAcademy.setEduModifyUser(id);
        return eduAcademyMapper.insertEduAcademy(eduAcademy);
    }

    /**
     * 修改院校管理
     * 
     * @param eduAcademy 院校管理
     * @return 结果
     */
    @Override
    public int updateEduAcademy(EduAcademy eduAcademy)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduAcademy.setEduModifyTime(LocalDateTime.now());
        eduAcademy.setEduModifyUser(id);
        return eduAcademyMapper.updateEduAcademy(eduAcademy);
    }

    /**
     * 批量删除院校管理
     * 
     * @param eduIds 需要删除的院校管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyByEduIds(Long[] eduIds)
    {
        return eduAcademyMapper.deleteEduAcademyByEduIds(eduIds);
    }

    /**
     * 删除院校管理信息
     * 
     * @param eduId 院校管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyByEduId(Long eduId)
    {
        return eduAcademyMapper.deleteEduAcademyByEduId(eduId);
    }
}
