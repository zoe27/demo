package cn.com.learn.mybatisDemo.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    /**
     * 打开首页
     * @return
     */
    @RequestMapping("/index.html")
    public String toIndex() {
        return "mall/index2";
    }
    
    /**
     * 打开首页
     * @return
     */
    @RequestMapping("/index")
    public String toIndexPro() {
        return "mall/index";
    }

    /**
     * 访问根目录转发到首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "forward:/index2.html";
    }

}
