package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduCompanyPic;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业照片Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-23
 */
@Mapper
public interface EduCompanyPicMapper 
{
    /**
     * 查询企业照片
     * 
     * @param eduId 企业照片主键
     * @return 企业照片
     */
    public EduCompanyPic selectEduCompanyPicByEduId(Long eduId);

    /**
     * 查询企业照片列表
     * 
     * @param eduCompanyPic 企业照片
     * @return 企业照片集合
     */
    public List<EduCompanyPic> selectEduCompanyPicList(EduCompanyPic eduCompanyPic);

    /**
     * 新增企业照片
     * 
     * @param eduCompanyPic 企业照片
     * @return 结果
     */
    public int insertEduCompanyPic(EduCompanyPic eduCompanyPic);

    /**
     * 修改企业照片
     * 
     * @param eduCompanyPic 企业照片
     * @return 结果
     */
    public int updateEduCompanyPic(EduCompanyPic eduCompanyPic);

    /**
     * 删除企业照片
     * 
     * @param eduId 企业照片主键
     * @return 结果
     */
    public int deleteEduCompanyPicByEduId(Long eduId);

    /**
     * 批量删除企业照片
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCompanyPicByEduIds(Long[] eduIds);
}
