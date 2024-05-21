package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduIntegrate;
import com.iedu.service.domain.VO.IntegrateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 产教融合管理Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@Mapper
public interface EduIntegrateMapper 
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
     * 删除产教融合管理
     * 
     * @param eduId 产教融合管理主键
     * @return 结果
     */
    public int deleteEduIntegrateByEduId(Long eduId);

    /**
     * 批量删除产教融合管理
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduIntegrateByEduIds(Long[] eduIds);

    List<IntegrateVO> selectByKeyWord(@Param("text") String text, @Param("pageSize") int pageNum, @Param("offset") int offset);

    int selectCountByKeyWord(String text);

    IntegrateVO selectIntegrateById(Long eduId);

    List<IntegrateVO> selectByCondition(@Param("tId")Integer tId, @Param("keyword")String keyword, @Param("pageSize")Integer pageSize, @Param("offset")int offset);

    int selectCountByCondition(@Param("tId")Integer tId, @Param("keyword")String keyword);

    List<IntegrateVO> selectCurrent();
}
