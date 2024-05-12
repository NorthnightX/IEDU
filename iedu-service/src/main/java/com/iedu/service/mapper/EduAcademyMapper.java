package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduAcademy;
import org.apache.ibatis.annotations.Mapper;

/**
 * 院校管理Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Mapper
public interface EduAcademyMapper 
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
     * 删除院校管理
     * 
     * @param eduId 院校管理主键
     * @return 结果
     */
    public int deleteEduAcademyByEduId(Long eduId);

    /**
     * 批量删除院校管理
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAcademyByEduIds(Long[] eduIds);
}
