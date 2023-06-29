package hj.demo01.controller;

import hj.demo01.dto.TbUser;
import hj.demo01.service.SelectCreditsService;
import hj.demo01.service.SelectPaybackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class SelectPaybackCtrl {
    @Autowired
    SelectPaybackService sps;

    @RequestMapping("/payback")
    public List selectPayback(HttpSession session) {
        TbUser user = ((TbUser) session.getAttribute("user"));
        if ( user == null ) {
            throw new RuntimeException("请先登录");
        }
        return sps.SelectPayback(user);
    }
}
