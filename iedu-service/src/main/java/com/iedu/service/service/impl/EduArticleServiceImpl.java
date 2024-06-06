package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.core.domain.entity.SysRole;
import com.iedu.common.core.domain.entity.SysUser;
import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduArticleMapper;
import com.iedu.service.domain.EduArticle;
import com.iedu.service.service.IEduArticleService;

/**
 * 动态Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Service
public class EduArticleServiceImpl implements IEduArticleService 
{
    @Autowired
    private EduArticleMapper eduArticleMapper;

    /**
     * 查询动态
     * 
     * @param eduId 动态主键
     * @return 动态
     */
    @Override
    public EduArticle selectEduArticleByEduId(Long eduId)
    {
        return eduArticleMapper.selectEduArticleByEduId(eduId);
    }

    /**
     * 查询动态列表
     * 
     * @param eduArticle 动态
     * @return 动态
     */
    @Override
    public List<EduArticle> selectEduArticleList(EduArticle eduArticle)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        for (SysRole r : roles) {
            if (r.getRoleId() == 1L) {
                return eduArticleMapper.selectEduArticleList(eduArticle);
            }
        }
        Long uid = SecurityUtils.getLoginUser().getUserId();
        return eduArticleMapper.selectEduArticleListByUid(eduArticle.getEduTitle(), uid);
    }

    /**
     * 新增动态
     * 
     * @param eduArticle 动态
     * @return 结果
     */
    @Override
    public int insertEduArticle(EduArticle eduArticle)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduArticle.setEduCreateTime(LocalDateTime.now());
        eduArticle.setEduModifyTime(LocalDateTime.now());
        eduArticle.setEduCreateUser(id);
        eduArticle.setEduModifyUser(id);
        return eduArticleMapper.insertEduArticle(eduArticle);
    }

    /**
     * 修改动态
     * 
     * @param eduArticle 动态
     * @return 结果
     */
    @Override
    public int updateEduArticle(EduArticle eduArticle)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduArticle.setEduModifyTime(LocalDateTime.now());
        eduArticle.setEduModifyUser(id);
        return eduArticleMapper.updateEduArticle(eduArticle);
    }

    /**
     * 批量删除动态
     * 
     * @param eduIds 需要删除的动态主键
     * @return 结果
     */
    @Override
    public int deleteEduArticleByEduIds(Long[] eduIds)
    {
        return eduArticleMapper.deleteEduArticleByEduIds(eduIds);
    }

    /**
     * 删除动态信息
     * 
     * @param eduId 动态主键
     * @return 结果
     */
    @Override
    public int deleteEduArticleByEduId(Long eduId)
    {
        return eduArticleMapper.deleteEduArticleByEduId(eduId);
    }

    @Override
    public List<EduArticle> selectCurrentArticle() {
        return eduArticleMapper.selectEduArticleList(new EduArticle());
    }

    @Override
    public List<EduArticle> selectArticleByKeyWord(String text, Integer pageSize, Integer pageNum) {
        int offset = (pageNum - 1) * pageSize;
        text = "%" + text + "%";
        return eduArticleMapper.selectByKeyWord(text, pageSize, offset);
    }

    @Override
    public int selectCountByKeyWord(String text) {
        return eduArticleMapper.selectCountByKeyWord(text);
    }

    @Override
    public Integer selectCount() {
        return eduArticleMapper.selectCount();
    }
}
