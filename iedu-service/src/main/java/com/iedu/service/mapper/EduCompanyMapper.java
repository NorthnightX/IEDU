package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduCompany;
import com.iedu.service.domain.VO.CompanyVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司Mapper接口
 * 
 * @author zjz
 * @date 2024-04-05
 */
@Mapper
public interface EduCompanyMapper 
{
    /**
     * 查询公司
     * 
     * @param eduId 公司主键
     * @return 公司
     */
    public EduCompany selectEduCompanyByEduId(Long eduId);

    /**
     * 查询公司列表
     * 
     * @param eduCompany 公司
     * @return 公司集合
     */
    public List<CompanyVO> selectEduCompanyList(EduCompany eduCompany);

    /**
     * 新增公司
     * 
     * @param eduCompany 公司
     * @return 结果
     */
    public int insertEduCompany(EduCompany eduCompany);

    /**
     * 修改公司
     * 
     * @param eduCompany 公司
     * @return 结果
     */
    public int updateEduCompany(EduCompany eduCompany);

    /**
     * 删除公司
     * 
     * @param eduId 公司主键
     * @return 结果
     */
    public int deleteEduCompanyByEduId(Long eduId);

    /**
     * 批量删除公司
     * 
     * @param eduIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCompanyByEduIds(Long[] eduIds);

    List<EduCompany> selectAllCompany();
}
