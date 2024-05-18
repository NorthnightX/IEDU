package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduIntegrateTypeMapper;
import com.iedu.service.domain.EduIntegrateType;
import com.iedu.service.service.IEduIntegrateTypeService;

/**
 * 产教融合类型管理Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@Service
public class EduIntegrateTypeServiceImpl implements IEduIntegrateTypeService 
{
    @Autowired
    private EduIntegrateTypeMapper eduIntegrateTypeMapper;

    /**
     * 查询产教融合类型管理
     * 
     * @param eduId 产教融合类型管理主键
     * @return 产教融合类型管理
     */
    @Override
    public EduIntegrateType selectEduIntegrateTypeByEduId(Long eduId)
    {
        return eduIntegrateTypeMapper.selectEduIntegrateTypeByEduId(eduId);
    }

    /**
     * 查询产教融合类型管理列表
     * 
     * @param eduIntegrateType 产教融合类型管理
     * @return 产教融合类型管理
     */
    @Override
    public List<EduIntegrateType> selectEduIntegrateTypeList(EduIntegrateType eduIntegrateType)
    {
        return eduIntegrateTypeMapper.selectEduIntegrateTypeList(eduIntegrateType);
    }

    /**
     * 新增产教融合类型管理
     * 
     * @param eduIntegrateType 产教融合类型管理
     * @return 结果
     */
    @Override
    public int insertEduIntegrateType(EduIntegrateType eduIntegrateType)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduIntegrateType.setEduCreateTime(LocalDateTime.now());
        eduIntegrateType.setEduModifyTime(LocalDateTime.now());
        eduIntegrateType.setEduCreateUser(id);
        eduIntegrateType.setEduModifyUser(id);
        return eduIntegrateTypeMapper.insertEduIntegrateType(eduIntegrateType);
    }

    /**
     * 修改产教融合类型管理
     * 
     * @param eduIntegrateType 产教融合类型管理
     * @return 结果
     */
    @Override
    public int updateEduIntegrateType(EduIntegrateType eduIntegrateType)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduIntegrateType.setEduModifyTime(LocalDateTime.now());
        eduIntegrateType.setEduModifyUser(id);
        return eduIntegrateTypeMapper.updateEduIntegrateType(eduIntegrateType);
    }

    /**
     * 批量删除产教融合类型管理
     * 
     * @param eduIds 需要删除的产教融合类型管理主键
     * @return 结果
     */
    @Override
    public int deleteEduIntegrateTypeByEduIds(Long[] eduIds)
    {
        return eduIntegrateTypeMapper.deleteEduIntegrateTypeByEduIds(eduIds);
    }

    /**
     * 删除产教融合类型管理信息
     * 
     * @param eduId 产教融合类型管理主键
     * @return 结果
     */
    @Override
    public int deleteEduIntegrateTypeByEduId(Long eduId)
    {
        return eduIntegrateTypeMapper.deleteEduIntegrateTypeByEduId(eduId);
    }

    @Override
    public List<EduIntegrateType> getAllIntegrateType() {
        return eduIntegrateTypeMapper.selectEduIntegrateTypeList(new EduIntegrateType());
    }
}
