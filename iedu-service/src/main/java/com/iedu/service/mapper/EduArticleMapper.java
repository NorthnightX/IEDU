package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 动态Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Mapper
public interface EduArticleMapper 
{
    /**
     * 查询动态
     * 
     * @param eduId 动态主键
     * @return 动态
     */
    public EduArticle selectEduArticleByEduId(Long eduId);

    /**
     * 查询动态列表
     * 
     * @param eduArticle 动态
     * @return 动态集合
     */
    public List<EduArticle> selectEduArticleList(EduArticle eduArticle);

    /**
     * 新增动态
     * 
     * @param eduArticle 动态
     * @return 结果
     */
    public int insertEduArticle(EduArticle eduArticle);

    /**
     * 修改动态
     * 
     * @param eduArticle 动态
     * @return 结果
     */
    public int updateEduArticle(EduArticle eduArticle);

    /**
     * 删除动态
     * 
     * @param eduId 动态主键
     * @return 结果
     */
    public int deleteEduArticleByEduId(Long eduId);

    /**
     * 批量删除动态
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduArticleByEduIds(Long[] eduIds);

    List<EduArticle> selectByKeyWord(@Param("text") String text, @Param("pageSize") int pageNum, @Param("offset") int offset);

    int selectCountByKeyWord(String text);

    List<EduArticle> selectEduArticleListByUid(@Param("title") String eduTitle, @Param("uid") Long uid);
}
