package com.iedu.service.service.impl;

import java.util.List;

import com.iedu.service.domain.EduAcademyPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduCompanyPicMapper;
import com.iedu.service.domain.EduCompanyPic;
import com.iedu.service.service.IEduCompanyPicService;

/**
 * 企业照片Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
@Service
public class EduCompanyPicServiceImpl implements IEduCompanyPicService 
{
    @Autowired
    private EduCompanyPicMapper eduCompanyPicMapper;

    /**
     * 查询企业照片
     * 
     * @param eduId 企业照片主键
     * @return 企业照片
     */
    @Override
    public EduCompanyPic selectEduCompanyPicByEduId(Long eduId)
    {
        return eduCompanyPicMapper.selectEduCompanyPicByEduId(eduId);
    }

    /**
     * 查询企业照片列表
     * 
     * @param eduCompanyPic 企业照片
     * @return 企业照片
     */
    @Override
    public List<EduCompanyPic> selectEduCompanyPicList(EduCompanyPic eduCompanyPic)
    {
        return eduCompanyPicMapper.selectEduCompanyPicList(eduCompanyPic);
    }

    /**
     * 新增企业照片
     * 
     * @param eduCompanyPic 企业照片
     * @return 结果
     */
    @Override
    public int insertEduCompanyPic(EduCompanyPic eduCompanyPic)
    {
        return eduCompanyPicMapper.insertEduCompanyPic(eduCompanyPic);
    }

    /**
     * 修改企业照片
     * 
     * @param eduCompanyPic 企业照片
     * @return 结果
     */
    @Override
    public int updateEduCompanyPic(EduCompanyPic eduCompanyPic)
    {
        return eduCompanyPicMapper.updateEduCompanyPic(eduCompanyPic);
    }

    /**
     * 批量删除企业照片
     * 
     * @param eduIds 需要删除的企业照片主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyPicByEduIds(Long[] eduIds)
    {
        return eduCompanyPicMapper.deleteEduCompanyPicByEduIds(eduIds);
    }

    /**
     * 删除企业照片信息
     * 
     * @param eduId 企业照片主键
     * @return 结果
     */
    @Override
    public int deleteEduCompanyPicByEduId(Long eduId)
    {
        return eduCompanyPicMapper.deleteEduCompanyPicByEduId(eduId);
    }

    @Override
    public EduCompanyPic selectByCompany(Long eduId) {
        EduCompanyPic pic = new EduCompanyPic();
        pic.setEduCompanyId(eduId);
        List<EduCompanyPic> eduCompanyPics = eduCompanyPicMapper.selectEduCompanyPicList(pic);
        if(eduCompanyPics.isEmpty()){
            return null;
        }
        return eduCompanyPics.get(0);
    }
}
