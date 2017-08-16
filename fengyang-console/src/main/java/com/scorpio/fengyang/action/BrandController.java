package com.scorpio.fengyang.action;

import com.scorpio.fengyang.pojo.Brand;
import com.scorpio.fengyang.service.BrandService;
import com.scorpio.fengyang.tools.EncodingTool;
import com.scorpio.fengyang.tools.PageHelper;
import com.scorpio.fengyang.tools.UploadFileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 专门处理品牌相关的控制器
 * Created by hao on 2017/8/16.
 */
@Controller
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/console/brand/list.do")
    public String brandListShow(Integer pageNum, Integer pageSize, Brand brand, Model model) {
        System.out.println("pageNum = " + pageNum);
        System.out.println("pageSize = " + pageSize);

        brand.setName(EncodingTool.encoding(brand.getName()));

        PageHelper.Page page = brandService.findByExample(pageNum, pageSize, brand);

        System.out.println("total = " + page.getResult().size());

        model.addAttribute("page", page);
        model.addAttribute("brand", brand);

        return "brand/list";
    }

    @RequestMapping(value = "/console/brand/toEdit.do")
    public String toEdit(Integer brandId, Model model) {
        System.out.println("brandId = " + brandId);

        //将id传到后台，查询出对象的brand对象
        Brand brand = brandService.findById(brandId);
        model.addAttribute("brand", brand);

        return "brand/edit";
    }

    @RequestMapping(value = "/console/brand/doEdit.do")
    public String doEdit(Brand brand) {
        System.out.println(brand);

        //把对象传递到后台更新进数据库
        brandService.update(brand);

        return "redirect:/console/brand/list.do";
    }

    @RequestMapping(value = "/uploadFile.do")
    @ResponseBody
    public Map<String, String> uploadFile(MultipartFile mpf) {
        System.out.println(mpf.getOriginalFilename());

        Map<String, String> map = new HashMap<>();
        String path = null;
        //上传文件
        try {
            path = UploadFileTool.upload_file(mpf.getBytes(), mpf.getOriginalFilename());
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("path", "http://192.168.56.103:8888/" + path);

        return map;
    }

    @RequestMapping("/console/brand/doAdd.do")
    public String doAdd(Brand brand) {
        System.out.println(brand);

        brandService.add(brand);

        return "redirect:/console/brand/list.do";
    }
}
