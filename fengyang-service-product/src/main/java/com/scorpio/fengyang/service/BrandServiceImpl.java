package com.scorpio.fengyang.service;

import com.scorpio.fengyang.dao.BrandDao;
import com.scorpio.fengyang.pojo.Brand;
import com.scorpio.fengyang.tools.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hao on 2017/8/15.
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    /**
     * 按照条件分页查找商品品牌
     * @param pageNum
     * @param pageSize
     * @param brand
     */
    @Override
    public PageHelper.Page findByExample(Integer pageNum, Integer pageSize, Brand brand) {
        PageHelper.startPage(pageNum, pageSize);
        brandDao.findByExample(brand);
        PageHelper.Page page = PageHelper.endPage();

        return page;
    }

    @Override
    public Brand findById(Integer brandId) {
        return brandDao.findById(brandId);
    }

    @Override
    public void update(Brand brand) {
        brandDao.update(brand);
    }

    @Override
    public void add(Brand brand) {
        brandDao.add(brand);
    }
}
