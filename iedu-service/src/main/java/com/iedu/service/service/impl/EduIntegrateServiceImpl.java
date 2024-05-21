package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import com.iedu.common.utils.DateUtils;
import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.VO.IntegrateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduIntegrateMapper;
import com.iedu.service.domain.EduIntegrate;
import com.iedu.service.service.IEduIntegrateService;

/**
 * 产教融合管理Service业务层处理
 * 
 * @author å¼ ç»§æ³½
 * @date 2024-05-14
 */
@Service
public class EduIntegrateServiceImpl implements IEduIntegrateService 
{
    @Autowired
    private EduIntegrateMapper eduIntegrateMapper;

    /**
     * 查询产教融合管理
     * 
     * @param eduId 产教融合管理主键
     * @return 产教融合管理
     */
    @Override
    public EduIntegrate selectEduIntegrateByEduId(Long eduId)
    {
        return eduIntegrateMapper.selectEduIntegrateByEduId(eduId);
    }

    /**
     * 查询产教融合管理列表
     * 
     * @param eduIntegrate 产教融合管理
     * @return 产教融合管理
     */
    @Override
    public List<EduIntegrate> selectEduIntegrateList(EduIntegrate eduIntegrate)
    {
        return eduIntegrateMapper.selectEduIntegrateList(eduIntegrate);
    }

    /**
     * 新增产教融合管理
     * 
     * @param eduIntegrate 产教融合管理
     * @return 结果
     */
    @Override
    public int insertEduIntegrate(EduIntegrate eduIntegrate)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduIntegrate.setEduCreateTime(LocalDateTime.now());
        eduIntegrate.setEduModifyTime(LocalDateTime.now());
        eduIntegrate.setEduCreateUser(id);
        eduIntegrate.setEduModifyUser(id);
        return eduIntegrateMapper.insertEduIntegrate(eduIntegrate);
    }

    /**
     * 修改产教融合管理
     * 
     * @param eduIntegrate 产教融合管理
     * @return 结果
     */
    @Override
    public int updateEduIntegrate(EduIntegrate eduIntegrate)
    {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduIntegrate.setEduModifyTime(LocalDateTime.now());
        eduIntegrate.setEduModifyUser(id);
        return eduIntegrateMapper.updateEduIntegrate(eduIntegrate);
    }

    /**
     * 批量删除产教融合管理
     * 
     * @param eduIds 需要删除的产教融合管理主键
     * @return 结果
     */
    @Override
    public int deleteEduIntegrateByEduIds(Long[] eduIds)
    {
        return eduIntegrateMapper.deleteEduIntegrateByEduIds(eduIds);
    }

    /**
     * 删除产教融合管理信息
     * 
     * @param eduId 产教融合管理主键
     * @return 结果
     */
    @Override
    public int deleteEduIntegrateByEduId(Long eduId)
    {
        return eduIntegrateMapper.deleteEduIntegrateByEduId(eduId);
    }

    @Override
    public List<EduIntegrate> selectEduIntegrateListByType(EduIntegrate eduIntegrate) {
        return eduIntegrateMapper.selectEduIntegrateList(eduIntegrate);
    }

    @Override
    public List<IntegrateVO> selectIntegrateByKeyWord(String text, Integer pageSize, Integer pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return eduIntegrateMapper.selectByKeyWord(text, pageSize, offset);
    }

    @Override
    public int selectCountByKeyWord(String text) {
        return eduIntegrateMapper.selectCountByKeyWord(text);
    }

    @Override
    public IntegrateVO getIntegrateById(Long eduId) {
        return eduIntegrateMapper.selectIntegrateById(eduId);
    }

    @Override
    public int getCountByCondition(Integer tId, String keyword) {
        return eduIntegrateMapper.selectCountByCondition(tId, keyword);
    }

    @Override
    public List<IntegrateVO> getIntegrateByCondition(Integer tId, String keyword, Integer pageSize, Integer pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return eduIntegrateMapper.selectByCondition(tId, keyword, pageSize, offset);
    }

    @Override
    public List<IntegrateVO> getCurrentIntegrateList() {
        return eduIntegrateMapper.selectCurrent();
    }

}
