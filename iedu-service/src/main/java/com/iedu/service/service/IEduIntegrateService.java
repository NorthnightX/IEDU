package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduIntegrate;
import com.iedu.service.domain.VO.IntegrateVO;

/**
 * 产教融合管理Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
public interface IEduIntegrateService 
{
    /**
     * 查询产教融合管理
     * 
     * @param eduId 产教融合管理主键
     * @return 产教融合管理
     */
    public EduIntegrate selectEduIntegrateByEduId(Long eduId);

    /**
     * 查询产教融合管理列表
     * 
     * @param eduIntegrate 产教融合管理
     * @return 产教融合管理集合
     */
    public List<EduIntegrate> selectEduIntegrateList(EduIntegrate eduIntegrate);

    /**
     * 新增产教融合管理
     * 
     * @param eduIntegrate 产教融合管理
     * @return 结果
     */
    public int insertEduIntegrate(EduIntegrate eduIntegrate);

    /**
     * 修改产教融合管理
     * 
     * @param eduIntegrate 产教融合管理
     * @return 结果
     */
    public int updateEduIntegrate(EduIntegrate eduIntegrate);

    /**
     * 批量删除产教融合管理
     * 
     * @param eduIds 需要删除的产教融合管理主键集合
     * @return 结果
     */
    public int deleteEduIntegrateByEduIds(Long[] eduIds);

    /**
     * 删除产教融合管理信息
     * 
     * @param eduId 产教融合管理主键
     * @return 结果
     */
    public int deleteEduIntegrateByEduId(Long eduId);

    List<EduIntegrate> selectEduIntegrateListByType(EduIntegrate eduIntegrate);

    List<IntegrateVO> selectIntegrateByKeyWord(String text, Integer pageSize, Integer pageNum);

    int selectCountByKeyWord(String text);

    IntegrateVO getIntegrateById(Long eduId);
}
