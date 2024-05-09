package com.iedu.service.mapper;

import java.util.List;
import com.iedu.service.domain.SProvinces;
import org.apache.ibatis.annotations.Mapper;

/**
 * 全国省市Mapper接口
 * 
 * @author zjz
 * @date 2024-04-02
 */
@Mapper
public interface SProvincesMapper 
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
     * 删除全国省市
     * 
     * @param id 全国省市主键
     * @return 结果
     */
    public int deleteSProvincesById(Long id);

    /**
     * 批量删除全国省市
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSProvincesByIds(Long[] ids);
}
