package hj.demo01.controller;

import hj.demo01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //返回数据
@RequestMapping("/product")
public class ProductCtrl {
    @Autowired
    ProductService ps;

    @RequestMapping("/selectSkuList")
    //函数返回值是什么得看前端需要什么：app.productlist = d; productlist: [] 一个列表
    public List getProducts() {
        return ps.findAllProducts();
    }
}
