package com.scorpio.fengyang.dao;

import com.scorpio.fengyang.pojo.Brand;

import java.util.List;

/**
 * 商品品牌dao接口
 * Created by hao on 2017/8/15.
 */
public interface BrandDao {
    /**
     * 根据条件查询所有符合的商品品牌
     * @param brand
     * @return
     */
    public List<Brand> findByExample(Brand brand);

    /**
     * 根据id查找对应的品牌对象
     * @param id
     * @return
     */
    public Brand findById(Integer id);

    /**
     * 更新品牌操作
     * @param brand
     */
    public void update(Brand brand);

    /**
     * 将新增品牌添加进数据库
     * @param brand
     */
    public void add(Brand brand);
}
