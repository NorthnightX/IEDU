package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyMapper;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.service.IEduCompanyService;

/**
 * 公司Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-05
 */
@Service
public class EduCompanyServiceImpl implements IEduCompanyService 
{
    @Autowired
    private EduCompanyMapper eduCompanyMapper;

    /**
     * 查询公司
     * 
     * @param eduId 公司主键
     * @return 公司
     */
    @Override
    public EduCompany selectEduCompanyByEduId(Long eduId)
    {
        return eduCompanyMapper.selectEduCompanyByEduId(eduId);
    }

    /**
     * 查询公司列表
     * 
     * @param eduCompany 公司
     * @return 公司
     */
    @Override
    public List<EduCompany> selectEduCompanyList(EduCompany eduCompany)
    {
        return eduCompanyMapper.selectEduCompanyList(eduCompany);
    }

    /**
     * 新增公司
     * 
     * @param eduCompany 公司
     * @return 结果
     */
    @Override
    public int insertEduCompany(EduCompany eduCompany)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();

        eduCompany.setEduCreateTime(LocalDateTime.now());
        eduCompany.setEduModifyTime(LocalDateTime.now());
        eduCompany.setEduCreateUser(id);
        eduCompany.setEduModifyUser(id);
        return eduCompanyMapper.insertEduCompany(eduCompany);
    }

    /**
     * 修改公司
     * 
     * @param eduCompany 公司
     * @return 结果
     */
    @Override
    public int updateEduCompany(EduCompany eduCompany)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompany.setEduModifyTime(LocalDateTime.now());
        eduCompany.setEduModifyUser(id);
        return eduCompanyMapper.updateEduCompany(eduCompany);
    }

    /**
     * 批量删除公司
     * 
     * @param eduIds 需要删除的公司主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyByEduIds(Long[] eduIds)
    {
        return eduCompanyMapper.deleteEduCompanyByEduIds(eduIds);
    }

    /**
     * 删除公司信息
     * 
     * @param eduId 公司主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyByEduId(Long eduId)
    {
        return eduCompanyMapper.deleteEduCompanyByEduId(eduId);
    }

    @Override
    public List<EduCompany> selectAll() {

        return eduCompanyMapper.selectAllCompany();
    }
}
