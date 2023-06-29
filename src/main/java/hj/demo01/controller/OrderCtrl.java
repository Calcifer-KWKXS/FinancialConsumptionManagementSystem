package hj.demo01.controller;

import hj.demo01.dto.Porder;
import hj.demo01.dto.TbUser;
import hj.demo01.service.OrderService;
import hj.demo01.util.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderCtrl {
    @Autowired
    OrderService os;

    @RequestMapping("/insertOrder")
    public String addOrder(Porder order,HttpSession session) { //实现功能是：往数据库porser中插入一条订单数据
        //是谁的订单？买了什么？多少钱？发到哪去？
        //order中前端传了省市区详细地址和分期数，还差总价、客户id、时间、
        TbUser user = ((TbUser) session.getAttribute("user"));
        return os.createOrder(order,user);
        //每个控制器写异常处理太冗余了，直接全局异常处理
//        try {
//
//        }
//        catch (Exception e) {
//            return os.createOrder(order,user);
//        }
    }
}
