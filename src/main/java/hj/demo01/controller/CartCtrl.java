package hj.demo01.controller;

import hj.demo01.dto.Cart;
import hj.demo01.dto.CartItem;
import hj.demo01.dto.Sku;
import hj.demo01.dto.TbUser;
import hj.demo01.service.CartService;
import hj.demo01.util.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartCtrl {
    @Autowired
    CartService cs;

    @RequestMapping("/addToCart")
    public String addToCart(Sku sku, HttpSession session) { //实现功能：将商品加入到购物车中
        TbUser user = (TbUser) session.getAttribute("user");//根据cookie里面传的 session id 得到该用户信息
        if ( user == null ) { //如果查不到该用户，则说明没登陆
            return "global-exception";
        }
        cs.addToCart(sku,user);
        return "添加成功";
    }

    @RequestMapping("/selectCart")// "/cart/selectCart"
    public Cart findUserCart(HttpSession session) { //实现功能：查看该用户的购物车列表
        TbUser user = (TbUser) session.getAttribute("user");
        if ( user == null ) { //如果没有 session id --> user 为空，说明该用户没登陆
            return null;
        }
        return cs.findCart(user);//返回用户user的购物车信息
    }

    @RequestMapping("/updateCount")
    public String updateCount(Integer pid,int pcount,HttpSession session) {
        TbUser user = (TbUser) session.getAttribute("user");//拿到该用户信息
        if ( user == null ) {
            return "global-exception";
        }
        cs.updateCount(pid,pcount,user);
        return "success";
    }

    @RequestMapping("/deleteItem")
    public String deleteItem(Integer pid,HttpSession session) {
        TbUser user = (TbUser) session.getAttribute("user");//拿到该用户信息
        if ( user == null ) {
            return "global-exception";
        }
        cs.deleteItem(pid,user);
        return "success";
    }
}
