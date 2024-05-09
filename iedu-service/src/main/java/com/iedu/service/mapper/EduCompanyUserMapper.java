package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.EduCompanyUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公司用户关系Mapper接口
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-04-19
 */
@Mapper
public interface EduCompanyUserMapper 
{
    /**
     * 查询公司用户关系
     * 
     * @param eduCompanyId 公司用户关系主键
     * @return 公司用户关系
     */
    public EduCompanyUser selectEduCompanyUserByEduCompanyId(Long eduCompanyId);

    /**
     * 查询公司用户关系列表
     * 
     * @param eduCompanyUser 公司用户关系
     * @return 公司用户关系集合
     */
    public List<EduCompanyUser> selectEduCompanyUserList(EduCompanyUser eduCompanyUser);

    /**
     * 新增公司用户关系
     * 
     * @param eduCompanyUser 公司用户关系
     * @return 结果
     */
    public int insertEduCompanyUser(EduCompanyUser eduCompanyUser);

    /**
     * 修改公司用户关系
     * 
     * @param eduCompanyUser 公司用户关系
     * @return 结果
     */
    public int updateEduCompanyUser(EduCompanyUser eduCompanyUser);

    /**
     * 删除公司用户关系
     * 
     * @param eduCompanyId 公司用户关系主键
     * @return 结果
     */
    public int deleteEduCompanyUserByEduCompanyId(Long eduCompanyId);

    /**
     * 批量删除公司用户关系
     * 
     * @param eduCompanyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCompanyUserByEduCompanyIds(Long[] eduCompanyIds);

    Long selectCompanyByUserId(Long userId);
}
