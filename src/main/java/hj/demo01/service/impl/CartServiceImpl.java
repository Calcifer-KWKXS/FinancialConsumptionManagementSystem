package hj.demo01.service.impl;

import hj.demo01.dto.Cart;
import hj.demo01.dto.CartItem;
import hj.demo01.dto.Sku;
import hj.demo01.dto.TbUser;
import hj.demo01.service.CartService;
import hj.demo01.util.Cache;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public void addToCart(Sku sku, TbUser user) { //传了要加入购物车的商品信息 和 该用户信息
        //从缓存中拿到该用户的购物车信息,购物车数据类型：商品ID，商品明细
        Map<Integer,CartItem> cart = Cache.CART.get(user.getId());//Cache.CART.get() 通过 Key 拿到 value
        //把商品加入购物车
        if ( cart == null ) {
            cart = new HashMap<>();//该用户还没有购物车则新建个购物车
            Cache.CART.put(user.getId(),cart);//并把新建的购物车放入缓存中
        }
        CartItem cartItem = null;
        if (cart.get(sku.getId()) == null) {
            cartItem = new CartItem();//用户首次添加此商品，则new一个item存储该商品明细
            //把明细填入
            cartItem.setPid(sku.getId());
            cartItem.setPname(sku.getName());
            cartItem.setPrice(sku.getPrice());
            cartItem.setImgsrc(sku.getImgsrc());
            cartItem.setPcount(1);
            cartItem.setSumprice(sku.getPrice());
            cart.put(sku.getId(),cartItem);//将该商品加入购物车
            return ;
        }
        //购物车中已添加过该商品，则直接改下数量和总价格
        cartItem = cart.get(sku.getId());//通过 商品ID 拿到其对于的商品明细
        cartItem.setPcount(cartItem.getPcount() + 1);
        cartItem.setSumprice(cartItem.getSumprice() + sku.getPrice());
    }

    @Override
    public Cart findCart(TbUser user) {
        Map<Integer, CartItem> map = Cache.CART.get(user.getId());//从缓存里拿到该用户的购物车
        Cart cart = new Cart();
        Collection<CartItem> items = map.values();//拿到Map里所有的 value
        //遍历计算总价格
        Integer totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getSumprice();
        }
        cart.setTotalPrice(totalPrice);
        List<CartItem> list = new ArrayList<>();
        list.addAll(items);
        cart.setItems(list);
        return cart;
    }

    @Override
    public void updateCount(Integer pid, int pcount, TbUser user) {
        //1.获得该用户的购物车
        Map<Integer,CartItem> cartMap = Cache.CART.get(user.getId());//拿到该用户购物车Map
        CartItem item = cartMap.get(pid);//拿到该用户购物车中该商品的信息
        //2.更改该用户购物车中该商品的数量
        item.setPcount(pcount);
        //3.更改总价格
        item.setSumprice(item.getPcount() * item.getPrice());
        //疑问：1.如何拿到该用户的 Cart 类型购物车
        //答：页面购物车的总价不需要在这里实现，在回调函数中，如果正确执行了会执行一下 app.showcart(); ，
        // 即存储的数据更改了，页面上会自动更改显示
    }

    @Override
    public void deleteItem(Integer pid, TbUser user) {
        //1.获得该用户的购物车
        Map<Integer,CartItem> cartMap = Cache.CART.get(user.getId());//拿到该用户购物车Map
        CartItem item = cartMap.get(pid);//拿到该用户购物车中该商品的信息
        //2.从该用户购物车内删除该商品
        cartMap.remove(pid); //从 Cache里删掉了，页面上的还在
        //错误：cartMap.remove(cartMap.get(pid));
    }
}
