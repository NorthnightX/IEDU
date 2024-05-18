package com.iedu.service.service;

import java.util.List;

import com.iedu.service.domain.VO.ProvincesVO;
import com.iedu.service.domain.SProvinces;

/**
 * 全国省市Service接口
 * 
 * @author zjz
 * @date 2024-04-02
 */
public interface ISProvincesService 
{
    /**
     * 查询全国省市
     * 
     * @param id 全国省市主键
     * @return 全国省市
     */
    public SProvinces selectSProvincesById(Long id);

    /**
     * 查询全国省市列表
     * 
     * @param sProvinces 全国省市
     * @return 全国省市集合
     */
    public List<SProvinces> selectSProvincesList(SProvinces sProvinces);

    /**
     * 新增全国省市
     * 
     * @param sProvinces 全国省市
     * @return 结果
     */
    public int insertSProvinces(SProvinces sProvinces);

    /**
     * 修改全国省市
     * 
     * @param sProvinces 全国省市
     * @return 结果
     */
    public int updateSProvinces(SProvinces sProvinces);

    /**
     * 批量删除全国省市
     * 
     * @param ids 需要删除的全国省市主键集合
     * @return 结果
     */
    public int deleteSProvincesByIds(Long[] ids);

    /**
     * 删除全国省市信息
     * 
     * @param id 全国省市主键
     * @return 结果
     */
    public int deleteSProvincesById(Long id);

    List<ProvincesVO> selectListOfHierarchy();

}
