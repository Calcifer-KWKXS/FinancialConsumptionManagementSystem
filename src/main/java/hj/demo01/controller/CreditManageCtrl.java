package hj.demo01.controller;

import hj.demo01.dto.TbAmount;
import hj.demo01.dto.TbUser;
import hj.demo01.service.CreditManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CreditManageCtrl {
    @Autowired
    CreditManageService cms;

    @RequestMapping("/selectAmount")
    public TbAmount selectAmount(HttpSession session) {
        //查询该用户的信用额度
        TbUser user = (TbUser) session.getAttribute("user");
        if ( user == null ) {
            throw new RuntimeException("global-exception");
        }
        return cms.selectAmount(user);
    }
}
