package com.scorpio.fengyang.service;

import com.github.abel533.entity.Example;
import com.scorpio.fengyang.dao.ColorDao;
import com.scorpio.fengyang.dao.ProductDao;
import com.scorpio.fengyang.dao.SkuDao;
import com.scorpio.fengyang.pojo.Color;
import com.scorpio.fengyang.pojo.Product;
import com.scorpio.fengyang.pojo.Sku;
import com.scorpio.fengyang.tools.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

/**
 * Created by hao on 2017/8/18.
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private Jedis jedis;

    @Override
    public List<Color> findAllColorInUse() {
        Example example = new Example(Color.class);
        example.createCriteria().andNotEqualTo("parentId", 0);

        return colorDao.selectByExample(example);
    }

    @Override
    public void add(Product product) {
        //设置由redis给出的id
        Long id = jedis.incr("pno");
        product.setId(id);
        //补全商品的部分数据
        product.setIsShow(1);
        product.setIsDel(1);
        product.setCreateTime(new Date());

        productDao.insertSelective(product);
        Long getId = product.getId();
        System.out.println("productId : " + id);
        System.out.println("productId : " + getId);

        //更新库存表
        String[] colors = product.getColors().split(",");
        String[] sizes = product.getSizes().split(",");

        for (String color : colors) {
            for (String size : sizes) {
                Sku sku = new Sku();
                sku.setProductId(id);
                sku.setColorId(Long.parseLong(color));
                sku.setSize(size);

                sku.setMarketPrice(1000f);
                sku.setPrice(800f);
                sku.setDeliveFee(10f);
                sku.setStock(222);
                sku.setUpperLimit(100);
                sku.setCreateTime(new Date());

                skuDao.insert(sku);
            }
        }
    }

    @Override
    public PageHelper.Page<Product> findByexample(Product product, Integer pageNum, Integer pageSize) {
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        if (product.getName() != null) {
            criteria.andLike("name", "%" + product.getName() + "%");
        }
        if (product.getBrandId() != null) {
            criteria.andEqualTo("brandId", product.getBrandId());
        }
        if (product.getIsShow() != null) {
            criteria.andEqualTo("isShow", product.getIsShow());
        }
        example.setOrderByClause("id desc");

        PageHelper.startPage(pageNum, pageSize);
        productDao.selectByExample(example);
        PageHelper.Page page = PageHelper.endPage();

        return page;
    }
}
