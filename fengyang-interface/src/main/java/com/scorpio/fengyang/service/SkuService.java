package com.scorpio.fengyang.service;

import com.scorpio.fengyang.pojo.Sku;

import java.util.List;

/**
 * 库存业务层接口
 * Created by hao on 2017/8/19.
 */
public interface SkuService {
    /**
     * 根据商品id查询所有的库存记录
     * @param productId
     * @return
     */
    public List<Sku> findByProductId(Long productId);
}
