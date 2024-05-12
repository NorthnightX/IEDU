package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduArticle;

/**
 * 动态Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
public interface IEduArticleService 
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
     * 批量删除动态
     * 
     * @param eduIds 需要删除的动态主键集合
     * @return 结果
     */
    public int deleteEduArticleByEduIds(Long[] eduIds);

    /**
     * 删除动态信息
     * 
     * @param eduId 动态主键
     * @return 结果
     */
    public int deleteEduArticleByEduId(Long eduId);

    List<EduArticle> selectCurrentArticle();
}
