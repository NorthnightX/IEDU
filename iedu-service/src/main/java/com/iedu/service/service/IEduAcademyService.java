package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduAcademy;

/**
 * 院校管理Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
public interface IEduAcademyService 
{
    /**
     * 查询院校管理
     * 
     * @param eduId 院校管理主键
     * @return 院校管理
     */
    public EduAcademy selectEduAcademyByEduId(Long eduId);

    /**
     * 查询院校管理列表
     * 
     * @param eduAcademy 院校管理
     * @return 院校管理集合
     */
    public List<EduAcademy> selectEduAcademyList(EduAcademy eduAcademy);

    /**
     * 新增院校管理
     * 
     * @param eduAcademy 院校管理
     * @return 结果
     */
    public int insertEduAcademy(EduAcademy eduAcademy);

    /**
     * 修改院校管理
     * 
     * @param eduAcademy 院校管理
     * @return 结果
     */
    public int updateEduAcademy(EduAcademy eduAcademy);

    /**
     * 批量删除院校管理
     * 
     * @param eduIds 需要删除的院校管理主键集合
     * @return 结果
     */
    public int deleteEduAcademyByEduIds(Long[] eduIds);

    /**
     * 删除院校管理信息
     * 
     * @param eduId 院校管理主键
     * @return 结果
     */
    public int deleteEduAcademyByEduId(Long eduId);
}
