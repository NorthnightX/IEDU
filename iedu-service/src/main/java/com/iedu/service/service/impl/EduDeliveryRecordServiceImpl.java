package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.iedu.common.core.domain.entity.SysRole;
import com.iedu.common.core.domain.entity.SysUser;
import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.Chart;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.domain.EduDeliveryRecord;
import com.iedu.service.domain.EduRecruit;
import com.iedu.service.domain.VO.CompanyVO;
import com.iedu.service.domain.VO.ResumeVO;
import com.iedu.service.mapper.EduCompanyMapper;
import com.iedu.service.mapper.EduRecruitMapper;
import com.iedu.service.service.IEduRecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduDeliveryRecordMapper;
import com.iedu.service.service.IEduDeliveryRecordService;

/**
 * 投递记录管理Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-21
 */
@Service
public class EduDeliveryRecordServiceImpl implements IEduDeliveryRecordService 
{
    @Autowired
    private EduDeliveryRecordMapper eduDeliveryRecordMapper;
    @Autowired
    private EduCompanyMapper eduCompanyMapper;
    @Autowired
    private EduRecruitMapper eduRecruitMapper;
    @Autowired
    private EduRecruitServiceImpl recruitService;

    /**
     * 查询投递记录管理
     * 
     * @param eduId 投递记录管理主键
     * @return 投递记录管理
     */
    @Override
    public EduDeliveryRecord selectEduDeliveryRecordByEduId(Long eduId)
    {
        return eduDeliveryRecordMapper.selectEduDeliveryRecordByEduId(eduId);
    }

    /**
     * 查询投递记录管理列表
     * 
     * @param eduDeliveryRecord 投递记录管理
     * @return 投递记录管理
     */
    @Override
    public List<ResumeVO> selectEduDeliveryRecordList(EduDeliveryRecord eduDeliveryRecord)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = user.getRoles();
        for (SysRole r : roles) {
            if (r.getRoleId() == 1L) {
                return eduDeliveryRecordMapper.selectEduDeliveryRecordList(eduDeliveryRecord);
            }
        }
        Long uid = SecurityUtils.getLoginUser().getUserId();
        return eduDeliveryRecordMapper.selectEduDeliveryRecordListByUser(eduDeliveryRecord, uid);
    }

    /**
     * 新增投递记录管理
     * 
     * @param eduDeliveryRecord 投递记录管理
     * @return 结果
     */
    @Override
    public int insertEduDeliveryRecord(EduDeliveryRecord eduDeliveryRecord)
    {
        return eduDeliveryRecordMapper.insertEduDeliveryRecord(eduDeliveryRecord);
    }

    /**
     * 修改投递记录管理
     * 
     * @param eduDeliveryRecord 投递记录管理
     * @return 结果
     */
    @Override
    public int updateEduDeliveryRecord(EduDeliveryRecord eduDeliveryRecord)
    {
        return eduDeliveryRecordMapper.updateEduDeliveryRecord(eduDeliveryRecord);
    }

    /**
     * 批量删除投递记录管理
     * 
     * @param eduIds 需要删除的投递记录管理主键
     * @return 结果
     */
    @Override
    public int deleteEduDeliveryRecordByEduIds(Long[] eduIds)
    {
        return eduDeliveryRecordMapper.deleteEduDeliveryRecordByEduIds(eduIds);
    }

    /**
     * 删除投递记录管理信息
     * 
     * @param eduId 投递记录管理主键
     * @return 结果
     */
    @Override
    public int deleteEduDeliveryRecordByEduId(Long eduId)
    {
        return eduDeliveryRecordMapper.deleteEduDeliveryRecordByEduId(eduId);
    }

    @Override
    public List<ResumeVO> selectRecruitByCondition(Long id, Integer pageSize, Integer pageNum) {
        int offset = (pageNum - 1) * pageSize;
        List<ResumeVO> vos = eduDeliveryRecordMapper.selectRecordListByUser(id, pageSize, offset);
        for (ResumeVO vo : vos) {
            Long companyId = vo.getEduCompanyId();
            Long eduRecruitId = vo.getEduRecruitId();
            EduCompany eduCompany = new EduCompany();
            eduCompany.setEduId(companyId);
            CompanyVO first = eduCompanyMapper.selectEduCompanyList(eduCompany).get(0);
            vo.setCompany(first);
            vo.setJob(eduRecruitMapper.selectEduRecruitDetailById(Math.toIntExact(eduRecruitId)));
        }
        return vos;
    }

    @Override
    public int getRecordCountByUser(Long id) {
        return eduDeliveryRecordMapper.selectRecordCountByUser(id);
    }

    @Override
    public Integer selectTodayCount() {
        return eduDeliveryRecordMapper.selectTodayCount();
    }

    @Override
    public List<Chart> selectAgeProportion() {
        return eduDeliveryRecordMapper.selectAgeProportion();
    }

    @Override
    public List<Chart> selectTypeProportion() {
        // 查找所有的投递记录，转而找出所有的投递的工作
        List<Long> ids = eduDeliveryRecordMapper.selectAll().stream().map(EduDeliveryRecord::getEduRecruitId).collect(Collectors.toList());
        List<EduRecruit> list = new ArrayList<>();
        // 根据工作找到类型
        for (Long id : ids) {
            EduRecruit recruit = eduRecruitMapper.selectEduRecruitByEduId(id);
            list.add(recruit);
        }
        return recruitService.fillList(list);
    }
}
