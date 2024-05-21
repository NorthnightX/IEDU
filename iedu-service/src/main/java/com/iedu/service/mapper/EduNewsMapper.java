package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 新闻Mapper接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Mapper
public interface EduNewsMapper 
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
     * 删除新闻
     * 
     * @param eduId 新闻主键
     * @return 结果
     */
    public int deleteEduNewsByEduId(Long eduId);

    /**
     * 批量删除新闻
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduNewsByEduIds(Long[] eduIds);

    List<EduNews> selectCurrentNews();

    List<EduNews> selectByKeyWord(@Param("text") String text, @Param("pageSize") int pageNum, @Param("offset") int offset);

    int selectCountByKeyWord(String text);

    List<EduNews> selectEduNewsListByUserId(@Param("title") String title, @Param("uid") Long uid);
}
