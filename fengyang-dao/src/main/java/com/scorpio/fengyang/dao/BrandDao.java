package com.scorpio.fengyang.dao;

import com.github.abel533.mapper.Mapper;
import com.scorpio.fengyang.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌dao接口
 * Created by hao on 2017/8/15.
 */
public interface BrandDao extends Mapper<Brand> {

    /**
     * 根据id删除指定的品牌
     * @param ids
     */
    public void deleteByIds(@Param("ids") String ids);
}
