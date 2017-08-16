package com.scorpio.fengyang.service;

import com.scorpio.fengyang.pojo.Brand;
import com.scorpio.fengyang.tools.PageHelper;

/**
 * 商品品牌管理业务层
 * Created by hao on 2017/8/15.
 */
public interface BrandService {
    /**
     * 根据条件查询符合的所有品牌，并对结果进行分页
     * @param pageNum
     * @param pageSize
     * @param brand
     * @return
     */
    public PageHelper.Page findByExample(Integer pageNum, Integer pageSize, Brand brand);

    /**
     * 根据id查找对应的品牌
     * @param brandId
     * @return
     */
    public Brand findById(Integer brandId);

    /**
     * 更新品牌对象
     * @param brand
     */
    public void update(Brand brand);

    /**
     * 新增品牌的添加
     * @param brand
     */
    public void add(Brand brand);
}
