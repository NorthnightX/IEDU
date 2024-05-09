package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.mapper.EduCompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyInformationMapper;
import com.iedu.service.domain.EduCompanyInformation;
import com.iedu.service.service.IEduCompanyInformationService;

/**
 * 公司信息Service业务层处理
 *
 * @author å¼ ç»§æ³½
 * @date 2024-04-05
 */
@Service
public class EduCompanyInformationServiceImpl implements IEduCompanyInformationService {
    @Autowired
    private EduCompanyInformationMapper eduCompanyInformationMapper;
    @Autowired
    private EduCompanyMapper eduCompanyMapper;

    /**
     * 查询公司信息
     *
     * @param eduId 公司信息主键
     * @return 公司信息
     */
    @Override
    public EduCompanyInformation selectEduCompanyInformationByEduId(Long eduId) {
        return eduCompanyInformationMapper.selectEduCompanyInformationByEduId(eduId);
    }

    /**
     * 查询公司信息列表
     *
     * @param eduCompanyInformation 公司信息
     * @return 公司信息
     */
    @Override
    public List<EduCompanyInformation> selectEduCompanyInformationList(EduCompanyInformation eduCompanyInformation) {
        return eduCompanyInformationMapper.selectEduCompanyInformationList(eduCompanyInformation);
    }

    /**
     * 新增公司信息
     *
     * @param eduCompanyInformation 公司信息
     * @return 结果
     */
    @Override
    public int insertEduCompanyInformation(EduCompanyInformation eduCompanyInformation) {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyInformation.setEduCreateTime(LocalDateTime.now());
        eduCompanyInformation.setEduModifyTime(LocalDateTime.now());
        eduCompanyInformation.setEduCreateUser(id);
        eduCompanyInformation.setEduModifyUser(id);
        eduCompanyInformationMapper.insertEduCompanyInformation(eduCompanyInformation);
        Long companyId = eduCompanyInformation.getEduCompanyId();
        EduCompany eduCompany = new EduCompany();
        eduCompany.setEduId(companyId);
        eduCompany.setEduInformationId(eduCompanyInformation.getEduId());
        return eduCompanyMapper.updateEduCompany(eduCompany);
    }

    /**
     * 修改公司信息
     *
     * @param eduCompanyInformation 公司信息
     * @return 结果
     */
    @Override
    public int updateEduCompanyInformation(EduCompanyInformation eduCompanyInformation) {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduCompanyInformation.setEduModifyTime(LocalDateTime.now());
        eduCompanyInformation.setEduModifyUser(id);
        return eduCompanyInformationMapper.updateEduCompanyInformation(eduCompanyInformation);
    }

    /**
     * 批量删除公司信息
     *
     * @param eduIds 需要删除的公司信息主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyInformationByEduIds(Long[] eduIds) {
        return eduCompanyInformationMapper.deleteEduCompanyInformationByEduIds(eduIds);
    }

    /**
     * 删除公司信息信息
     *
     * @param eduId 公司信息主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyInformationByEduId(Long eduId) {
        return eduCompanyInformationMapper.deleteEduCompanyInformationByEduId(eduId);
    }


    @Override
    public EduCompanyInformation selectEduCompanyInformationByCompanyId(Long id) {
        EduCompanyInformation wrapper = new EduCompanyInformation();
        wrapper.setEduCompanyId(id);
        List<EduCompanyInformation> list = eduCompanyInformationMapper.selectEduCompanyInformationList(wrapper);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
