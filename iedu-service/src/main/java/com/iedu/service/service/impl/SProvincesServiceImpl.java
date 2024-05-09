package com.iedu.service.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.iedu.service.domain.ProvincesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.iedu.service.mapper.SProvincesMapper;
import com.iedu.service.domain.SProvinces;
import com.iedu.service.service.ISProvincesService;

import javax.annotation.PostConstruct;

/**
 * 全国省市Service业务层处理
 * 
 * @author zjz
 * @date 2024-04-02
 */
@Service
public class SProvincesServiceImpl implements ISProvincesService 
{
    @Autowired
    private SProvincesMapper sProvincesMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 查询全国省市
     * 
     * @param id 全国省市主键
     * @return 全国省市
     */
    @Override
    public SProvinces selectSProvincesById(Long id)
    {
        return sProvincesMapper.selectSProvincesById(id);
    }

    /**
     * 查询全国省市列表
     * 
     * @param sProvinces 全国省市
     * @return 全国省市
     */
    @Override
    public List<SProvinces> selectSProvincesList(SProvinces sProvinces)
    {
        return sProvincesMapper.selectSProvincesList(sProvinces);
    }

    /**
     * 新增全国省市
     * 
     * @param sProvinces 全国省市
     * @return 结果
     */
    @Override
    public int insertSProvinces(SProvinces sProvinces)
    {
        return sProvincesMapper.insertSProvinces(sProvinces);
    }

    /**
     * 修改全国省市
     * 
     * @param sProvinces 全国省市
     * @return 结果
     */
    @Override
    public int updateSProvinces(SProvinces sProvinces)
    {
        return sProvincesMapper.updateSProvinces(sProvinces);
    }

    /**
     * 批量删除全国省市
     * 
     * @param ids 需要删除的全国省市主键
     * @return 结果
     */
    @Override
    public int deleteSProvincesByIds(Long[] ids)
    {
        return sProvincesMapper.deleteSProvincesByIds(ids);
    }

    /**
     * 删除全国省市信息
     * 
     * @param id 全国省市主键
     * @return 结果
     */
    @Override
    public int deleteSProvincesById(Long id)
    {
        return sProvincesMapper.deleteSProvincesById(id);
    }

    @Override
    public List<ProvincesVO> selectListOfHierarchy() {
        String key = "EDU_PROVINCES";
        if(Boolean.TRUE.equals(redisTemplate.hasKey(key))){
            String jsonStr = redisTemplate.opsForValue().get(key);
            return JSON.parseObject(jsonStr, new TypeReference<List<ProvincesVO>>() {});
        }
        SProvinces sProvinces = new SProvinces();
        sProvinces.setDepth(0L);
        List<SProvinces> root = sProvincesMapper.selectSProvincesList(sProvinces);
        dfs(root);
        List<ProvincesVO> convert = convert(root);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(convert));
        return convert;
    }

    @PostConstruct
    public void init() {
        Thread t = new Thread(this::selectListOfHierarchy);
        t.start();
    }

    private void dfs(List<SProvinces> roots){
        roots.forEach((root) -> {
            SProvinces wrapper = new SProvinces();
            wrapper.setParentId(root.getId());
            List<SProvinces> childList = sProvincesMapper.selectSProvincesList(wrapper);
            dfs(childList);
            root.setChild(childList);
        });
    }

    private List<ProvincesVO> convert(List<SProvinces> sProvincesList){
        List<ProvincesVO> result = new ArrayList<>();
        sProvincesList.forEach((province) -> {
            ProvincesVO provincesVO = new ProvincesVO();
            provincesVO.setValue(province.getId());
            provincesVO.setLabel(province.getCityName());
            provincesVO.setChildren(convert(province.getChild()));
            result.add(provincesVO);
        });
        return result;
    }
}
