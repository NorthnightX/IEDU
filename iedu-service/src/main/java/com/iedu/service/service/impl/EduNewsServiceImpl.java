package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.core.domain.entity.SysRole;
import com.iedu.common.core.domain.entity.SysUser;
import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduNewsMapper;
import com.iedu.service.domain.EduNews;
import com.iedu.service.service.IEduNewsService;

/**
 * 新闻Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Service
public class EduNewsServiceImpl implements IEduNewsService 
{
    @Autowired
    private EduNewsMapper eduNewsMapper;

    /**
     * 查询新闻
     * 
     * @param eduId 新闻主键
     * @return 新闻
     */
    @Override
    public EduNews selectEduNewsByEduId(Long eduId)
    {
        return eduNewsMapper.selectEduNewsByEduId(eduId);
    }

    /**
     * 查询新闻列表
     * 
     * @param eduNews 新闻
     * @return 新闻
     */
    @Override
    public List<EduNews> selectEduNewsList(EduNews eduNews)
    {
        // 检查用户角色，如果是管理员，则允许查找所有的，否则只允许查找自己的
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        for (SysRole r : roles) {
            if (r.getRoleId() == 1L) {
                return eduNewsMapper.selectEduNewsList(eduNews);
            }
        }
        Long uid = SecurityUtils.getLoginUser().getUserId();
        return eduNewsMapper.selectEduNewsListByUserId(eduNews.getEduTitle(), uid);
    }

    /**
     * 新增新闻
     * 
     * @param eduNews 新闻
     * @return 结果
     */
    @Override
    public int insertEduNews(EduNews eduNews)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduNews.setEduCreateTime(LocalDateTime.now());
        eduNews.setEduModifyTime(LocalDateTime.now());
        eduNews.setEduCreateUser(id);
        eduNews.setEduModifyUser(id);
        return eduNewsMapper.insertEduNews(eduNews);
    }

    /**
     * 修改新闻
     * 
     * @param eduNews 新闻
     * @return 结果
     */
    @Override
    public int updateEduNews(EduNews eduNews)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduNews.setEduModifyTime(LocalDateTime.now());
        eduNews.setEduModifyUser(id);
        return eduNewsMapper.updateEduNews(eduNews);
    }

    /**
     * 批量删除新闻
     * 
     * @param eduIds 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteEduNewsByEduIds(Long[] eduIds)
    {
        return eduNewsMapper.deleteEduNewsByEduIds(eduIds);
    }

    /**
     * 删除新闻信息
     * 
     * @param eduId 新闻主键
     * @return 结果
     */
    @Override
    public int deleteEduNewsByEduId(Long eduId)
    {
        return eduNewsMapper.deleteEduNewsByEduId(eduId);
    }

    @Override
    public List<EduNews> selectCurrentNews() {
        return eduNewsMapper.selectEduNewsList(new EduNews());
    }

    @Override
    public List<EduNews> selectNewsByKeyWord(String text, Integer pageSize, Integer pageNum) {
        int offset = (pageNum - 1) * pageSize;
        text = "%" + text + "%";
        return eduNewsMapper.selectByKeyWord(text, pageSize, offset);
    }

    @Override
    public int selectCountByKeyWord(String text) {
        return eduNewsMapper.selectCountByKeyWord(text);
    }

    @Override
    public Integer selectCount() {
        return eduNewsMapper.selectCount();
    }

}
