package hj.demo01.controller;

import hj.demo01.dao.AmountMapper;
import hj.demo01.dto.TbAmount;
import hj.demo01.dto.TbUser;
import hj.demo01.service.CreditApllyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CreditApllyCtrl {
    @Autowired
    CreditApllyService cas;

    @RequestMapping("/aplly")
    public TbAmount aplly(HttpSession session) {
        TbUser user = ((TbUser) session.getAttribute("user"));
        if (user == null) {
            throw new RuntimeException("请先登录");
        }
        return cas.aplly(user);
    }
}
