package com.scorpio.fengyang.service;

import com.scorpio.fengyang.pojo.Color;
import com.scorpio.fengyang.pojo.Product;
import com.scorpio.fengyang.tools.PageHelper;

import java.util.List;

/**
 * 商品业务层接口
 * Created by hao on 2017/8/18.
 */
public interface ProductService {
    /**
     * 根据条件查找所有商品，并对结果进行分页
     * @param product
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageHelper.Page<Product> findByexample(Product product, Integer pageNum, Integer pageSize);

    /**
     * 查询所有可用的颜色（父id不为0的）
     * @return
     */
    public List<Color> findAllColorInUse();

    /**
     * 新增商品，并更新库存表
     * @param product
     */
    public void add(Product product);
}
