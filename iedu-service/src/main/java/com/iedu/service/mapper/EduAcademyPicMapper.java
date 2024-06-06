package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduAcademyPic;
import org.apache.ibatis.annotations.Mapper;

/**
 * 院校照片Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
@Mapper
public interface EduAcademyPicMapper 
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
     * 删除院校照片
     * 
     * @param eduId 院校照片主键
     * @return 结果
     */
    public int deleteEduAcademyPicByEduId(Long eduId);

    /**
     * 批量删除院校照片
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAcademyPicByEduIds(Long[] eduIds);
}
