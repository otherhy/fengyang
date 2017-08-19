package com.scorpio.fengyang.service;

import com.github.abel533.entity.Example;
import com.scorpio.fengyang.dao.BrandDao;
import com.scorpio.fengyang.pojo.Brand;
import com.scorpio.fengyang.tools.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hao on 2017/8/15.
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    @Override
    public PageHelper.Page findByExample(Integer pageNum, Integer pageSize, Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand.getName() != null) {
            criteria.andLike("name", "%" + brand.getName() + "%");
        }
        if (brand.getIsDisplay() != null) {
            criteria.andEqualTo("isDisplay", brand.getIsDisplay());
        }

        PageHelper.startPage(pageNum, pageSize);
        brandDao.selectByExample(example);
        PageHelper.Page page = PageHelper.endPage();

        return page;
    }

    @Override
    public Brand findById(Integer brandId) {
        return brandDao.selectByPrimaryKey(brandId);
    }

    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKey(brand);
    }

    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public List<Brand> findAll() {
        return brandDao.select(new Brand());
    }

    @Override
    public void delete(String ids) {
        brandDao.deleteByIds(ids);
    }
}
