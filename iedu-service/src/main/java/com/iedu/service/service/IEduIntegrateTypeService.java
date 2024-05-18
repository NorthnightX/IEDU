package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduIntegrateType;

/**
 * 产教融合类型管理Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
public interface IEduIntegrateTypeService 
{
    /**
     * 查询产教融合类型管理
     * 
     * @param eduId 产教融合类型管理主键
     * @return 产教融合类型管理
     */
    public EduIntegrateType selectEduIntegrateTypeByEduId(Long eduId);

    /**
     * 查询产教融合类型管理列表
     * 
     * @param eduIntegrateType 产教融合类型管理
     * @return 产教融合类型管理集合
     */
    public List<EduIntegrateType> selectEduIntegrateTypeList(EduIntegrateType eduIntegrateType);

    /**
     * 新增产教融合类型管理
     * 
     * @param eduIntegrateType 产教融合类型管理
     * @return 结果
     */
    public int insertEduIntegrateType(EduIntegrateType eduIntegrateType);

    /**
     * 修改产教融合类型管理
     * 
     * @param eduIntegrateType 产教融合类型管理
     * @return 结果
     */
    public int updateEduIntegrateType(EduIntegrateType eduIntegrateType);

    /**
     * 批量删除产教融合类型管理
     * 
     * @param eduIds 需要删除的产教融合类型管理主键集合
     * @return 结果
     */
    public int deleteEduIntegrateTypeByEduIds(Long[] eduIds);

    /**
     * 删除产教融合类型管理信息
     * 
     * @param eduId 产教融合类型管理主键
     * @return 结果
     */
    public int deleteEduIntegrateTypeByEduId(Long eduId);

    List<EduIntegrateType> getAllIntegrateType();
}
