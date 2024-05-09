package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduRecruit;
import com.iedu.service.domain.RecruitVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 招聘信息Mapper接口
 * 
 * @author zjz
 * @date 2024-04-05
 */
@Mapper
public interface EduRecruitMapper 
{
    /**
     * 查询招聘信息
     * 
     * @param eduId 招聘信息主键
     * @return 招聘信息
     */
    public EduRecruit selectEduRecruitByEduId(Long eduId);

    /**
     * 查询招聘信息列表
     * 
     * @param eduRecruit 招聘信息
     * @return 招聘信息集合
     */
    public List<EduRecruit> selectEduRecruitList(EduRecruit eduRecruit);

    /**
     * 新增招聘信息
     * 
     * @param eduRecruit 招聘信息
     * @return 结果
     */
    public int insertEduRecruit(EduRecruit eduRecruit);

    /**
     * 修改招聘信息
     * 
     * @param eduRecruit 招聘信息
     * @return 结果
     */
    public int updateEduRecruit(EduRecruit eduRecruit);

    /**
     * 删除招聘信息
     * 
     * @param eduId 招聘信息主键
     * @return 结果
     */
    public int deleteEduRecruitByEduId(Long eduId);

    /**
     * 批量删除招聘信息
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduRecruitByEduIds(Long[] eduIds);

    List<RecruitVO> selectEduRecruitVOList();

    RecruitVO selectEduRecruitDetailById(int id);
}
