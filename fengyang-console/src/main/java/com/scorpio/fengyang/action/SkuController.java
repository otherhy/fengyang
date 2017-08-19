package com.scorpio.fengyang.action;

import com.scorpio.fengyang.pojo.Sku;
import com.scorpio.fengyang.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 库存控制器
 * Created by hao on 2017/8/19.
 */
@Controller
public class SkuController {
    @Autowired
    private SkuService skuService;

    @RequestMapping(value = "/console/sku/list.do")
    public String list(Long productId, Model model) {
        System.out.println("productId : " + productId);

        //查询库存列表
        List<Sku> skus = skuService.findByProductId(productId);
        System.out.println("skus size :" + skus.size());

        model.addAttribute("skus", skus);

        return "sku/list";
    }
}
