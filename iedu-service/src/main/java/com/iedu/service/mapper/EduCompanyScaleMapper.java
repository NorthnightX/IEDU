package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduCompanyScale;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司规模Mapper接口
 * 
 * @author zjz
 * @date 2024-04-01
 */
@Mapper
public interface EduCompanyScaleMapper 
{
    /**
     * 查询公司规模
     * 
     * @param eduId 公司规模主键
     * @return 公司规模
     */
    public EduCompanyScale selectEduCompanyScaleByEduId(Long eduId);

    /**
     * 查询公司规模列表
     * 
     * @param eduCompanyScale 公司规模
     * @return 公司规模集合
     */
    public List<EduCompanyScale> selectEduCompanyScaleList(EduCompanyScale eduCompanyScale);

    /**
     * 新增公司规模
     * 
     * @param eduCompanyScale 公司规模
     * @return 结果
     */
    public int insertEduCompanyScale(EduCompanyScale eduCompanyScale);

    /**
     * 修改公司规模
     * 
     * @param eduCompanyScale 公司规模
     * @return 结果
     */
    public int updateEduCompanyScale(EduCompanyScale eduCompanyScale);

    /**
     * 删除公司规模
     * 
     * @param eduId 公司规模主键
     * @return 结果
     */
    public int deleteEduCompanyScaleByEduId(Long eduId);

    /**
     * 批量删除公司规模
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCompanyScaleByEduIds(Long[] eduIds);
}
