package com.iedu.service.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyUserMapper;
import com.iedu.service.domain.EduCompanyUser;
import com.iedu.service.service.IEduCompanyUserService;

/**
 * 公司用户关系Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-19
 */
@Service
public class EduCompanyUserServiceImpl implements IEduCompanyUserService 
{
    @Autowired
    private EduCompanyUserMapper eduCompanyUserMapper;

    /**
     * 查询公司用户关系
     * 
     * @param eduCompanyId 公司用户关系主键
     * @return 公司用户关系
     */
    @Override
    public EduCompanyUser selectEduCompanyUserByEduCompanyId(Long eduCompanyId)
    {
        return eduCompanyUserMapper.selectEduCompanyUserByEduCompanyId(eduCompanyId);
    }

    /**
     * 查询公司用户关系列表
     * 
     * @param eduCompanyUser 公司用户关系
     * @return 公司用户关系
     */
    @Override
    public List<EduCompanyUser> selectEduCompanyUserList(EduCompanyUser eduCompanyUser)
    {
        return eduCompanyUserMapper.selectEduCompanyUserList(eduCompanyUser);
    }

    /**
     * 新增公司用户关系
     * 
     * @param eduCompanyUser 公司用户关系
     * @return 结果
     */
    @Override
    public int insertEduCompanyUser(EduCompanyUser eduCompanyUser)
    {
        return eduCompanyUserMapper.insertEduCompanyUser(eduCompanyUser);
    }

    /**
     * 修改公司用户关系
     * 
     * @param eduCompanyUser 公司用户关系
     * @return 结果
     */
    @Override
    public int updateEduCompanyUser(EduCompanyUser eduCompanyUser)
    {
        return eduCompanyUserMapper.updateEduCompanyUser(eduCompanyUser);
    }

    /**
     * 批量删除公司用户关系
     * 
     * @param eduCompanyIds 需要删除的公司用户关系主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyUserByEduCompanyIds(Long[] eduCompanyIds)
    {
        return eduCompanyUserMapper.deleteEduCompanyUserByEduCompanyIds(eduCompanyIds);
    }

    /**
     * 删除公司用户关系信息
     * 
     * @param eduCompanyId 公司用户关系主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyUserByEduCompanyId(Long eduCompanyId)
    {
        return eduCompanyUserMapper.deleteEduCompanyUserByEduCompanyId(eduCompanyId);
    }

    @Override
    public Long selectByUserId(Long id) {
        Long cId = eduCompanyUserMapper.selectCompanyByUserId(id);
        return cId == null ? -1L : cId;
    }
}
