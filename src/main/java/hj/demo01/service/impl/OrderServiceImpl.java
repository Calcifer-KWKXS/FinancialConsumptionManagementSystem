package hj.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import hj.demo01.dao.*;
import hj.demo01.dto.*;
import hj.demo01.service.CartService;
import hj.demo01.service.OrderService;
import hj.demo01.util.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper om;
    @Autowired
    CartService cs;
    @Autowired
    OrderdetailMapper odm;
    @Autowired
    AmountMapper am;
    @Autowired
    CreditMapper cm;
    @Autowired
    PayBackMapper pbm;

    //@Transactional：作用：如果发生了异常，就回滚这组数据库操作 原理是什么？具体什么情况下回滚？
    @Transactional
    @Override
    public String createOrder(Porder order, TbUser user) { //下订单
        if (order.getProv() == "" || order.getCity() == ""
                || order.getDistrict() == "" || order.getAddr() == "") {
            throw new RuntimeException("请填写收货地址");
        }

        //1.产生订单
        Cart cart = cs.findCart(user);
        order.setTotalprice(cart.getTotalPrice());
        order.setConsumerId(user.getId());
        om.insert(order);

        //2.添加订单明细
        List<CartItem> items = cart.getItems();
        Orderdetail ot = null;
        for (CartItem item : items) {
            ot = new Orderdetail();
            ot.setSkuId(item.getPid());
            ot.setSkuName(item.getPname());
            ot.setPrice(item.getPrice());
            ot.setScount(item.getPcount());
            ot.setSumprice(item.getSumprice());
            ot.setOrderId(order.getId());
            odm.insert(ot);
        }

        //3.管理信用额度
        QueryWrapper q = new QueryWrapper();
        q.eq("account",user.getAccount());
        TbAmount amount = am.selectOne(q);
        if ( amount == null ) { //没查到说明此用户没开户
            amount = new TbAmount();
            amount.setAccount(user.getAccount());
            am.insert(amount);
        }
//        if (amount.getFreeamount() < cart.getTotalPrice()) {
//            //应该先判断能否下单吧？否则生成的订单明细需要全部改状态吧？
              //一开始应该先做好核心业务，细节上先不考虑 --> 事务后面做
              //下订单这件事情对数据库进行了多次操作，但对用户来说，就“下订单”这一件事情，所以这一组对数据库的操作应该要么全成功，要么全失败
//            return "fail";//该用户可用额度小于订单总额
//        }
        amount.setUseamount(amount.getUseamount() + order.getTotalprice());
        amount.setFreeamount(amount.getFreeamount() - order.getTotalprice());
        if (amount.getFreeamount() < 0 ) {
            throw new RuntimeException("你的可用额度不够");
        }
        am.updateById(amount);
        //4.生成账单
        //amout：要还的金额总数（加利息）
        Credit credit = new Credit();
        credit.setAmount(order.getTotalprice() + order.getTotalprice() * 0.3 * (order.getStage() / 12.));
        System.out.println("分期后总额：" +credit.getAmount() + "利息：" + order.getTotalprice() * 0.3 * (order.getStage() / 12.));
        credit.setPeriod(order.getStage())
                .setConsumerId(order.getConsumerId())
                .setOrderId(order.getId());
        cm.insert(credit);

        //5.还款计划
        Calendar calendar = Calendar.getInstance();//当前日历
        for (int i = 0; i < order.getStage(); i ++ ) {
            //更改日期
            calendar.add(Calendar.MONTH, 1); // 将月份增加1个月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            PayBack payBack = new PayBack();
            payBack.setAmount(credit.getAmount() / order.getStage())
                    .setCreditId(credit.getId())
                    .setExpectpaytime(calendar.getTime());
            pbm.insert(payBack);
        }

        //6.清空购物车
        Cache.CART.remove(order.getConsumerId());//HashMap remove 可以根据 key 把这一对键值对移除掉
        return "下单成功";
//我的写法：只传了 Porder,用户ID 控制层获得的 -----------------------------------------------------
//        //是谁的订单？买了什么？多少钱？发到哪去？
//        //order中前端传了省市区详细地址和分期数，还差总价、客户id、时间
//        Map<Integer, CartItem> cartMap = Cache.CART.get(order.getConsumerId());
//        Collection<CartItem> items = cartMap.values();//获得该用户购物车中所有商品明细
//        int total = 0;
//        for (CartItem item : items) {
//            total += item.getSumprice();
//        }
//        //总价要计算利息
//        total += total * 0.3 * (order.getStage() / 12);
//        order.setTotalprice(total / order.getStage());
//        order.setCreatetime(new Date());//这个在 Proder 类里写了默认值，所以不用写了
//        om.insert(order);
//        return "success";
    }
}
