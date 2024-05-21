package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.core.domain.entity.SysRole;
import com.iedu.common.core.domain.entity.SysUser;
import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.VO.RecruitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduRecruitMapper;
import com.iedu.service.domain.EduRecruit;
import com.iedu.service.service.IEduRecruitService;

/**
 * 招聘信息Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-05
 */
@Service
public class EduRecruitServiceImpl implements IEduRecruitService 
{
    @Autowired
    private EduRecruitMapper eduRecruitMapper;

    /**
     * 查询招聘信息
     * 
     * @param eduId 招聘信息主键
     * @return 招聘信息
     */
    @Override
    public EduRecruit selectEduRecruitByEduId(Long eduId)
    {
        return eduRecruitMapper.selectEduRecruitByEduId(eduId);
    }

    /**
     * 查询招聘信息列表
     * 
     * @param eduRecruit 招聘信息
     * @return 招聘信息
     */
    @Override
    public List<RecruitVO> selectEduRecruitList(EduRecruit eduRecruit)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        for (SysRole r : roles) {
            if (r.getRoleId() == 1L) {
                return eduRecruitMapper.selectEduRecruitList(eduRecruit);
            }
        }
        Long uid = SecurityUtils.getLoginUser().getUserId();
        return eduRecruitMapper.selectEduRecruitListByUid(eduRecruit, uid);
    }

    /**
     * 新增招聘信息
     * 
     * @param eduRecruit 招聘信息
     * @return 结果
     */
    @Override
    public int insertEduRecruit(EduRecruit eduRecruit)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduRecruit.setEduCreateTime(LocalDateTime.now());
        eduRecruit.setEduModifyTime(LocalDateTime.now());
        eduRecruit.setEduCreateUser(id);
        eduRecruit.setEduModifyUser(id);
        return eduRecruitMapper.insertEduRecruit(eduRecruit);
    }

    /**
     * 修改招聘信息
     * 
     * @param eduRecruit 招聘信息
     * @return 结果
     */
    @Override
    public int updateEduRecruit(EduRecruit eduRecruit)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduRecruit.setEduModifyTime(LocalDateTime.now());
        eduRecruit.setEduModifyUser(id);
        return eduRecruitMapper.updateEduRecruit(eduRecruit);
    }

    /**
     * 批量删除招聘信息
     * 
     * @param eduIds 需要删除的招聘信息主键
     * @return 结果
     */
    @Override
    public int deleteEduRecruitByEduIds(Long[] eduIds)
    {
        return eduRecruitMapper.deleteEduRecruitByEduIds(eduIds);
    }

    /**
     * 删除招聘信息信息
     * 
     * @param eduId 招聘信息主键
     * @return 结果
     */
    @Override
    public int deleteEduRecruitByEduId(Long eduId)
    {
        return eduRecruitMapper.deleteEduRecruitByEduId(eduId);
    }

    @Override
    public List<RecruitVO> selectRecruitToShow() {
        return eduRecruitMapper.selectRecruitToShow();
    }

    @Override
    public RecruitVO selectRecruitDetailById(int id) {
        return eduRecruitMapper.selectEduRecruitDetailById(id);
    }

    @Override
    public List<RecruitVO> selectRecruitByCondition(String text, Integer pageSize, Integer pageNum, Integer jobTypeId) {
        int offset = (pageNum - 1) * pageSize;
        return eduRecruitMapper.selectRecruitByCondition(text, jobTypeId, pageSize, offset);
    }

    @Override
    public int selectCountByCondition(String text, Integer jobTypeId) {
        return eduRecruitMapper.selectCountByCondition(text, jobTypeId);
    }

}
