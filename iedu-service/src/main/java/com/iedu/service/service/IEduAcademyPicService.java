package com.iedu.service.service;

import java.util.List;
import com.iedu.service.domain.EduAcademyPic;

/**
 * 院校照片Service接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
public interface IEduAcademyPicService 
{
    /**
     * 查询院校照片
     * 
     * @param eduId 院校照片主键
     * @return 院校照片
     */
    public EduAcademyPic selectEduAcademyPicByEduId(Long eduId);

    /**
     * 查询院校照片列表
     * 
     * @param eduAcademyPic 院校照片
     * @return 院校照片集合
     */
    public List<EduAcademyPic> selectEduAcademyPicList(EduAcademyPic eduAcademyPic);

    /**
     * 新增院校照片
     * 
     * @param eduAcademyPic 院校照片
     * @return 结果
     */
    public int insertEduAcademyPic(EduAcademyPic eduAcademyPic);

    /**
     * 修改院校照片
     * 
     * @param eduAcademyPic 院校照片
     * @return 结果
     */
    public int updateEduAcademyPic(EduAcademyPic eduAcademyPic);

    /**
     * 批量删除院校照片
     * 
     * @param eduIds 需要删除的院校照片主键集合
     * @return 结果
     */
    public int deleteEduAcademyPicByEduIds(Long[] eduIds);

    /**
     * 删除院校照片信息
     * 
     * @param eduId 院校照片主键
     * @return 结果
     */
    public int deleteEduAcademyPicByEduId(Long eduId);

    EduAcademyPic selectByAcademy(Long eduId);
}
