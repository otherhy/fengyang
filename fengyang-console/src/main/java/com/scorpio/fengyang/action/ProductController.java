package com.scorpio.fengyang.action;

import com.scorpio.fengyang.base.MaHou;
import com.scorpio.fengyang.pojo.Brand;
import com.scorpio.fengyang.pojo.Color;
import com.scorpio.fengyang.pojo.Product;
import com.scorpio.fengyang.service.BrandService;
import com.scorpio.fengyang.service.ProductService;
import com.scorpio.fengyang.tools.PageHelper;
import com.scorpio.fengyang.tools.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品管理控制器
 * Created by hao on 2017/8/18.
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

    //商品列表显示页面
    @RequestMapping(value = "/console/product/list.do")

    public String list(Product product, Integer pageNum, Integer pageSize, Model model) {
        System.out.println("商品列表显示页面");

        //查询搜索条件区域的品牌下拉列表
        List<Brand> brands = brandService.findAll();
        //查询商品信息并显示
        PageHelper.Page<Product> page = productService.findByexample(product, pageNum, pageSize);

        System.out.println("result.size = " + page.getResult().size());

        model.addAttribute("brands", brands);
        model.addAttribute("page", page);
        model.addAttribute("example", product);

        return "product/list";
    }

    //进入商品添加页面
    @RequestMapping(value = "/console/product/toAdd.do")
    public String toAdd(Model model) {
        System.out.println("进入商品添加页面");

        //查询页面所需要的信息
        List<Brand> brands = brandService.findAll();
        List<Color> colors = productService.findAllColorInUse();

        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);

        return "product/add";
    }

    @RequestMapping(value = "/uploadPics.do")
    @ResponseBody
    public List<String> uploadFiles(@RequestParam MultipartFile[] mpfs) {
        System.out.println("上传多张图片");
        List<String> list = new ArrayList<>();

        //执行文件上传
        for (MultipartFile mpf : mpfs) {
            String path = null;
            try {
                path = UploadFileTool.upload_file(mpf.getBytes(), mpf.getOriginalFilename());
                list.add(MaHou.FASTDFS_SERVLET + path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    //富文本上传图片
    @RequestMapping(value = "/uploadFck.do")
    @ResponseBody
    public Map<String, Object> uploadFck(@RequestParam("uploadFile") MultipartFile mpf) {
        System.out.println("富文本上传图片" + mpf.getOriginalFilename());
        Map<String, Object> map = new HashMap<>();

        try {
            String path = UploadFileTool.upload_file(mpf.getBytes(), mpf.getOriginalFilename());
            map.put("error", 0);
            map.put("url", MaHou.FASTDFS_SERVLET + path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @RequestMapping(value = "/console/product/doAdd.do")
    public String doAdd(Product product) {
        System.out.println(product);

        //保存进数据库
        productService.add(product);

        return "redirect:/console/product/list.do";
    }
}
