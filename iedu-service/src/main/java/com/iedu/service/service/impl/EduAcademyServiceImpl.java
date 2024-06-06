package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import com.iedu.common.utils.bean.BeanUtils;
import com.iedu.service.domain.EduAcademyPic;
import com.iedu.service.domain.VO.AcademyVO;
import com.iedu.service.mapper.EduAcademyPicMapper;
import com.iedu.service.service.IEduAcademyAddressService;
import com.iedu.service.service.IEduAcademyPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduAcademyMapper;
import com.iedu.service.domain.EduAcademy;
import com.iedu.service.service.IEduAcademyService;

/**
 * 院校管理Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-12
 */
@Service
public class EduAcademyServiceImpl implements IEduAcademyService 
{
    @Autowired
    private EduAcademyMapper eduAcademyMapper;
    @Autowired
    private EduAcademyPicMapper eduAcademyPicMapper;
    @Autowired
    private IEduAcademyAddressService addressService;
    @Autowired
    private IEduAcademyPicService picService;

    /**
     * 查询院校管理
     * 
     * @param eduId 院校管理主键
     * @return 院校管理
     */
    @Override
    public EduAcademy selectEduAcademyByEduId(Long eduId)
    {
        return eduAcademyMapper.selectEduAcademyByEduId(eduId);
    }

    /**
     * 查询院校管理列表
     * 
     * @param eduAcademy 院校管理
     * @return 院校管理
     */
    @Override
    public List<AcademyVO> selectEduAcademyList(EduAcademy eduAcademy)
    {
        List<EduAcademy> cur = eduAcademyMapper.selectEduAcademyList(eduAcademy);
        List<AcademyVO> ans = new ArrayList<>();
        cur.forEach((academy) -> {
            AcademyVO vo = new AcademyVO();
            Long eduId = academy.getEduId();
            BeanUtils.copyProperties(academy, vo);
            EduAcademyPic pic = new EduAcademyPic();
            pic.setEduAcademyId(eduId);
            List<EduAcademyPic> pics = eduAcademyPicMapper.selectEduAcademyPicList(pic);
            if(!pics.isEmpty()){
                vo.setEduAcademyPic(pics.get(0));
            }
            ans.add(vo);
        });
        return ans;
    }

    /**
     * 新增院校管理
     * 
     * @param eduAcademy 院校管理
     * @return 结果
     */
    @Override
    public int insertEduAcademy(EduAcademy eduAcademy)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduAcademy.setEduCreateTime(LocalDateTime.now());
        eduAcademy.setEduModifyTime(LocalDateTime.now());
        eduAcademy.setEduCreateUser(id);
        eduAcademy.setEduModifyUser(id);
        return eduAcademyMapper.insertEduAcademy(eduAcademy);
    }

    /**
     * 修改院校管理
     * 
     * @param eduAcademy 院校管理
     * @return 结果
     */
    @Override
    public int updateEduAcademy(EduAcademy eduAcademy)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduAcademy.setEduModifyTime(LocalDateTime.now());
        eduAcademy.setEduModifyUser(id);
        return eduAcademyMapper.updateEduAcademy(eduAcademy);
    }

    /**
     * 批量删除院校管理
     * 
     * @param eduIds 需要删除的院校管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyByEduIds(Long[] eduIds)
    {
        return eduAcademyMapper.deleteEduAcademyByEduIds(eduIds);
    }

    /**
     * 删除院校管理信息
     * 
     * @param eduId 院校管理主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyByEduId(Long eduId)
    {
        return eduAcademyMapper.deleteEduAcademyByEduId(eduId);
    }

    @Override
    public List<AcademyVO> selectEduAcademyUList(EduAcademy eduAcademy) {
        return selectEduAcademyList(eduAcademy);
    }

    @Override
    public AcademyVO selectDetailById(Long id) {
        EduAcademy academy = new EduAcademy();
        academy.setEduId(id);
        List<AcademyVO> vos = selectEduAcademyList(academy);
        AcademyVO vo = vos.get(0);
        if(vo.getEduAddressId() != null){
            vo.setAcademyAddressVO(addressService.selectAddressByAcademyId(vo.getEduId()));
        }
        EduAcademyPic eduAcademyPic = picService.selectByAcademy(vo.getEduId());
        if(eduAcademyPic != null){
            vo.setEduAcademyPic(eduAcademyPic);
        }
        return vo;
    }

    @Override
    public Integer selectCount() {
        return eduAcademyMapper.selectCount();
    }
}
