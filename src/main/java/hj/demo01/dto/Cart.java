package hj.demo01.dto;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<CartItem> items; //商品明细
    private Integer totalPrice; //购物车总价：查询购物车页面中所有商品的总价格
}
