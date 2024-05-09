package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduNews;

/**
 * 新闻Service接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
public interface IEduNewsService 
{
    /**
     * 查询新闻
     * 
     * @param eduId 新闻主键
     * @return 新闻
     */
    public EduNews selectEduNewsByEduId(Long eduId);

    /**
     * 查询新闻列表
     * 
     * @param eduNews 新闻
     * @return 新闻集合
     */
    public List<EduNews> selectEduNewsList(EduNews eduNews);

    /**
     * 新增新闻
     * 
     * @param eduNews 新闻
     * @return 结果
     */
    public int insertEduNews(EduNews eduNews);

    /**
     * 修改新闻
     * 
     * @param eduNews 新闻
     * @return 结果
     */
    public int updateEduNews(EduNews eduNews);

    /**
     * 批量删除新闻
     * 
     * @param eduIds 需要删除的新闻主键集合
     * @return 结果
     */
    public int deleteEduNewsByEduIds(Long[] eduIds);

    /**
     * 删除新闻信息
     * 
     * @param eduId 新闻主键
     * @return 结果
     */
    public int deleteEduNewsByEduId(Long eduId);

    List<EduNews> selectCurrentNews();
}
