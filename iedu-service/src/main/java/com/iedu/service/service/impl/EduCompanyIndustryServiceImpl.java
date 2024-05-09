package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyIndustryMapper;
import com.iedu.service.domain.EduCompanyIndustry;
import com.iedu.service.service.IEduCompanyIndustryService;

/**
 * 公司行业Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-02
 */
@Service
public class EduCompanyIndustryServiceImpl implements IEduCompanyIndustryService 
{
    @Autowired
    private EduCompanyIndustryMapper eduCompanyIndustryMapper;

    /**
     * 查询公司行业
     * 
     * @param eduId 公司行业主键
     * @return 公司行业
     */
    @Override
    public EduCompanyIndustry selectEduCompanyIndustryByEduId(Long eduId)
    {
        return eduCompanyIndustryMapper.selectEduCompanyIndustryByEduId(eduId);
    }

    /**
     * 查询公司行业列表
     * 
     * @param eduCompanyIndustry 公司行业
     * @return 公司行业
     */
    @Override
    public List<EduCompanyIndustry> selectEduCompanyIndustryList(EduCompanyIndustry eduCompanyIndustry)
    {
        return eduCompanyIndustryMapper.selectEduCompanyIndustryList(eduCompanyIndustry);
    }

    /**
     * 新增公司行业
     * 
     * @param eduCompanyIndustry 公司行业
     * @return 结果
     */
    @Override
    public int insertEduCompanyIndustry(EduCompanyIndustry eduCompanyIndustry)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyIndustry.setEduCreateTime(LocalDateTime.now());
        eduCompanyIndustry.setEduModifyTime(LocalDateTime.now());
        eduCompanyIndustry.setEduCreateUser(id);
        eduCompanyIndustry.setEduModifyUser(id);
        return eduCompanyIndustryMapper.insertEduCompanyIndustry(eduCompanyIndustry);
    }

    /**
     * 修改公司行业
     * 
     * @param eduCompanyIndustry 公司行业
     * @return 结果
     */
    @Override
    public int updateEduCompanyIndustry(EduCompanyIndustry eduCompanyIndustry)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyIndustry.setEduModifyTime(LocalDateTime.now());
        eduCompanyIndustry.setEduModifyUser(id);
        return eduCompanyIndustryMapper.updateEduCompanyIndustry(eduCompanyIndustry);
    }

    /**
     * 批量删除公司行业
     * 
     * @param eduIds 需要删除的公司行业主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyIndustryByEduIds(Long[] eduIds)
    {
        return eduCompanyIndustryMapper.deleteEduCompanyIndustryByEduIds(eduIds);
    }

    /**
     * 删除公司行业信息
     * 
     * @param eduId 公司行业主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyIndustryByEduId(Long eduId)
    {
        return eduCompanyIndustryMapper.deleteEduCompanyIndustryByEduId(eduId);
    }

    @Override
    public List<EduCompanyIndustry> getIndustryListWithChildren() {
        List<EduCompanyIndustry> root;
        EduCompanyIndustry temp = new EduCompanyIndustry();
        temp.setEduFaId(0L);
        root = eduCompanyIndustryMapper.selectEduCompanyIndustryList(temp);
        return dfs(root);
    }
    private List<EduCompanyIndustry> dfs(List<EduCompanyIndustry> root){
        root.forEach((cur) -> {
            long rootId = cur.getEduId();
            List<EduCompanyIndustry> newRoot = eduCompanyIndustryMapper.selectIndustryListWithChildren(rootId);
            cur.setChild(newRoot);
            dfs(newRoot);
        });
        return root;
    }
}
