package com.scorpio.fengyang.service;

import com.github.abel533.entity.Example;
import com.scorpio.fengyang.dao.SkuDao;
import com.scorpio.fengyang.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hao on 2017/8/19.
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Autowired
    private SkuDao skuDao;

    @Override
    public List<Sku> findByProductId(Long productId) {
        Example example = new Example(Sku.class);
        example.createCriteria().andEqualTo("productId", productId);

        return skuDao.selectByExample(example);
    }
}
