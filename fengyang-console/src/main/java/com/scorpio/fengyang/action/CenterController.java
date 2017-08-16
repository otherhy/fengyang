package com.scorpio.fengyang.action;

import com.scorpio.fengyang.pojo.Brand;
import com.scorpio.fengyang.service.BrandService;
import com.scorpio.fengyang.tools.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 中心控制器,目前只负责通用页面跳转
 * Created by hao on 2017/8/15.
 */
@Controller
public class CenterController {

    @RequestMapping("/console/{pageName}.do")
    public String consoleShow(@PathVariable("pageName") String pageName) {
        System.out.println("pageName = " + pageName);
        return pageName;
    }

    @RequestMapping("/console/{fileName}/{pageName}.do")
    public String consoleFileShow(@PathVariable("fileName") String fileName, @PathVariable("pageName") String pageName) {
        System.out.println("page = " + fileName + "/" + pageName);
        return fileName + "/" + pageName;
    }

}
