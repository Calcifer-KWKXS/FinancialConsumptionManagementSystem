package hj.demo01.util;

import hj.demo01.dto.Cart;
import hj.demo01.dto.CartItem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    //Key:用户ID， value：Cart (用户购物车)
    //网站有很多用户，但就这一块购物车缓存，故最外层是一个 用户ID：用户购物车 的键值对。
    //用户购物车里会有很多商品，故也是一个 Map， 商品ID：商品信息明细
    //如果学了缓存数据库，那么，购物车应该放到缓存数据库里，而不是放在JVM的一块虚拟内存里
    public static ConcurrentHashMap<Integer, Map<Integer, CartItem>> CART = new ConcurrentHashMap();//在多线程高并发情况下ConcurrentHashMap比HashMap安全

}
