package com.iedu.service.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.iedu.common.utils.SecurityUtils;
import com.iedu.service.domain.JobTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.EduJobTypeMapper;
import com.iedu.service.domain.EduJobType;
import com.iedu.service.service.IEduJobTypeService;

/**
 * 工作类型Service业务层处理
 *
 * @author zjz
 * @date 2024-04-01
 */
@Service
public class EduJobTypeServiceImpl implements IEduJobTypeService {
    @Autowired
    private EduJobTypeMapper eduJobTypeMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询工作类型
     *
     * @param eduId 工作类型主键
     * @return 工作类型
     */
    @Override
    public EduJobType selectEduJobTypeByEduId(Long eduId) {
        return eduJobTypeMapper.selectEduJobTypeByEduId(eduId);
    }

    /**
     * 查询工作类型列表
     *
     * @param eduJobType 工作类型
     * @return 工作类型
     */
    @Override
    public List<EduJobType> selectEduJobTypeList(EduJobType eduJobType) {
        return eduJobTypeMapper.selectEduJobTypeList(eduJobType);
    }

    /**
     * 新增工作类型
     *
     * @param eduJobType 工作类型
     * @return 结果
     */
    @Override
    public int insertEduJobType(EduJobType eduJobType) {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduJobType.setEduCreateTime(LocalDateTime.now());
        eduJobType.setEduModifyTime(LocalDateTime.now());
        eduJobType.setEduCreateUser(id);
        eduJobType.setEduModifyUser(id);
        return eduJobTypeMapper.insertEduJobType(eduJobType);
    }

    /**
     * 修改工作类型
     *
     * @param eduJobType 工作类型
     * @return 结果
     */
    @Override
    public int updateEduJobType(EduJobType eduJobType) {
        Long id = SecurityUtils.getLoginUser().getUserId();
        eduJobType.setEduModifyTime(LocalDateTime.now());
        eduJobType.setEduModifyUser(id);
        return eduJobTypeMapper.updateEduJobType(eduJobType);
    }

    /**
     * 批量删除工作类型
     *
     * @param eduIds 需要删除的工作类型主键
     * @return 结果
     */
    @Override
    public int deleteEduJobTypeByEduIds(Long[] eduIds) {
        return eduJobTypeMapper.deleteEduJobTypeByEduIds(eduIds);
    }

    /**
     * 删除工作类型信息
     *
     * @param eduId 工作类型主键
     * @return 结果
     */
    @Override
    public int deleteEduJobTypeByEduId(Long eduId) {
        return eduJobTypeMapper.deleteEduJobTypeByEduId(eduId);
    }

    /**
     * 获取全部的工作类型
     *
     * @return
     */
    @Override
    public List<JobTypeVO> selectAll() {
        // 需要查询出所有的工作类型
        EduJobType jobType = new EduJobType();
        jobType.setEduFaId(0L);
        List<EduJobType> root = eduJobTypeMapper.selectEduJobTypeList(jobType);
        return dfs(root);
    }

    private List<JobTypeVO> dfs(List<EduJobType> root){
        List<JobTypeVO> ans = new ArrayList<>();
        root.forEach((type) -> {
            JobTypeVO vo = new JobTypeVO();
            vo.setValue(type.getEduId());
            vo.setLabel(type.getEduName());

            EduJobType jobType = new EduJobType();
            jobType.setEduFaId(type.getEduId());
            List<EduJobType> children = eduJobTypeMapper.selectEduJobTypeList(jobType);
            vo.setChildren(dfs(children));
            ans.add(vo);
        });
        return ans;
    }

}
