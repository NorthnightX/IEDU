package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.Chart;
import com.iedu.service.domain.EduCompanyIndustry;
import com.iedu.service.domain.EduCompanyPic;
import com.iedu.service.domain.VO.CompanyVO;
import com.iedu.service.mapper.EduCompanyPicMapper;
import com.iedu.service.service.IEduCompanyAddressService;
import com.iedu.service.service.IEduCompanyIndustryService;
import com.iedu.service.service.IEduCompanyInformationService;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private EduCompanyPicMapper eduCompanyPicMapper;
    @Autowired
    private IEduCompanyAddressService companyAddressService;
    @Autowired
    private IEduCompanyInformationService informationService;
    @Autowired
    private IEduCompanyIndustryService industryService;

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
    public List<CompanyVO> selectEduCompanyList(EduCompany eduCompany)
    {
        List<CompanyVO> vos = eduCompanyMapper.selectEduCompanyList(eduCompany);
        vos.forEach((vo) -> {
            EduCompanyPic pic = new EduCompanyPic();
            pic.setEduCompanyId(vo.getEduId());
            List<EduCompanyPic> pics = eduCompanyPicMapper.selectEduCompanyPicList(pic);
            if(!pics.isEmpty()){
                vo.setEduCompanyPic(pics.get(0));
            }
        });
        return vos;
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

    @Override
    public List<CompanyVO> selectEduCompanyUList(EduCompany eduCompany) {
        return selectEduCompanyList(eduCompany);
    }

    @Override
    public CompanyVO selectDetailById(Long id) {

        EduCompany eduCompany = new EduCompany();
        eduCompany.setEduId(id);
        List<CompanyVO> company = selectEduCompanyList(eduCompany);
        CompanyVO vo = company.get(0);
        // 设置地址
        if(vo.getEduAddressId() != null){
            vo.setCompanyAddressVO(companyAddressService.selectAddressByCompany(vo.getEduId()));
        }
        // 设置图片
        EduCompanyPic pic = new EduCompanyPic();
        pic.setEduCompanyId(vo.getEduId());
        List<EduCompanyPic> pics = eduCompanyPicMapper.selectEduCompanyPicList(pic);
        if(!pics.isEmpty()){
            vo.setEduCompanyPic(pics.get(0));
        }
        if(vo.getEduInformationId() != null){
            vo.setEduCompanyInformation(informationService.selectEduCompanyInformationByCompanyId(vo.getEduId()));
        }
        return vo;
    }

    @Override
    public Integer selectCount() {
        return eduCompanyMapper.selectCount();
    }



    @Override
    public List<Chart> selectCompanyIndustryProportion() {
        // 公司行业比例
        List<EduCompany> eduCompanies = eduCompanyMapper.selectAllCompany();
        HashMap<String, Integer> m = new HashMap<>();
        for (EduCompany eduCompany : eduCompanies) {
            // 公司行业的id
            Long iid = eduCompany.getEduCompanyIndustryId();
            EduCompanyIndustry cur = industryService.selectEduCompanyIndustryByEduId(iid);
            // 递归查询父节点直到根
            String root = dfs(cur);
            m.put(root, m.getOrDefault(root, 0) + 1);
        }
        List<Chart> list = new ArrayList<>();
        for (String l : m.keySet()) {
            Chart c = new Chart();
            c.setSpan(l);
            c.setV(m.get(l));
            list.add(c);
        }
        return list;
    }

    private String dfs(EduCompanyIndustry cur){
        if(cur.getEduFaId() == 0L){
            return cur.getEduIndustry();
        }
        return dfs(industryService.selectEduCompanyIndustryByEduId(cur.getEduFaId()));
    }
}
