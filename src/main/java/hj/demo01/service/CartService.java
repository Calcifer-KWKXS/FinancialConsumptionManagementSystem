package hj.demo01.service;

import hj.demo01.dto.Cart;
import hj.demo01.dto.Sku;
import hj.demo01.dto.TbUser;

public interface CartService {
    void addToCart(Sku sku, TbUser user);

    Cart findCart(TbUser user);

    void updateCount(Integer pid, int pcount, TbUser user);

    void deleteItem(Integer pid, TbUser user);
}
