package hj.demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //返回给前端的是页面：返回的字符串做的是页面跳转
public class PagesCtrl {
    @RequestMapping("/con/{page}")
    public String myLogin(@PathVariable String page) {
        return "consumer/" + page; // 页面转发
    }

    @RequestMapping("/cre/{page}")
    public String credit(@PathVariable String page) {
        return "credit/" + page;
    }
}
