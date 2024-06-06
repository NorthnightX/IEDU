package com.iedu.service.service.impl;

import java.util.List;

import com.iedu.service.domain.EduAcademyAddress;
import com.iedu.service.domain.VO.AcademyAddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduAcademyPicMapper;
import com.iedu.service.domain.EduAcademyPic;
import com.iedu.service.service.IEduAcademyPicService;

/**
 * 院校照片Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
@Service
public class EduAcademyPicServiceImpl implements IEduAcademyPicService 
{
    @Autowired
    private EduAcademyPicMapper eduAcademyPicMapper;

    /**
     * 查询院校照片
     * 
     * @param eduId 院校照片主键
     * @return 院校照片
     */
    @Override
    public EduAcademyPic selectEduAcademyPicByEduId(Long eduId)
    {
        return eduAcademyPicMapper.selectEduAcademyPicByEduId(eduId);
    }

    /**
     * 查询院校照片列表
     * 
     * @param eduAcademyPic 院校照片
     * @return 院校照片
     */
    @Override
    public List<EduAcademyPic> selectEduAcademyPicList(EduAcademyPic eduAcademyPic)
    {
        return eduAcademyPicMapper.selectEduAcademyPicList(eduAcademyPic);
    }

    /**
     * 新增院校照片
     * 
     * @param eduAcademyPic 院校照片
     * @return 结果
     */
    @Override
    public int insertEduAcademyPic(EduAcademyPic eduAcademyPic)
    {
        return eduAcademyPicMapper.insertEduAcademyPic(eduAcademyPic);
    }

    /**
     * 修改院校照片
     * 
     * @param eduAcademyPic 院校照片
     * @return 结果
     */
    @Override
    public int updateEduAcademyPic(EduAcademyPic eduAcademyPic)
    {
        return eduAcademyPicMapper.updateEduAcademyPic(eduAcademyPic);
    }

    /**
     * 批量删除院校照片
     * 
     * @param eduIds 需要删除的院校照片主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyPicByEduIds(Long[] eduIds)
    {
        return eduAcademyPicMapper.deleteEduAcademyPicByEduIds(eduIds);
    }

    /**
     * 删除院校照片信息
     * 
     * @param eduId 院校照片主键
     * @return 结果
     */
    @Override
    public int deleteEduAcademyPicByEduId(Long eduId)
    {
        return eduAcademyPicMapper.deleteEduAcademyPicByEduId(eduId);
    }

    @Override
    public EduAcademyPic selectByAcademy(Long eduId) {
        EduAcademyPic pic = new EduAcademyPic();
        pic.setEduId(eduId);
        List<EduAcademyPic> list = eduAcademyPicMapper.selectEduAcademyPicList(pic);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
