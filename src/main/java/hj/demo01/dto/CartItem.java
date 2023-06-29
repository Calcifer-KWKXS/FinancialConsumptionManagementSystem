package hj.demo01.dto;

import lombok.Data;

@Data
public class CartItem {
    private Integer pid;
    private String pname;
    private Integer price;
    private int pcount = 1;
    private Integer sumprice;//该商品加入购物车中件数的价格总和
    private String imgsrc;
}
