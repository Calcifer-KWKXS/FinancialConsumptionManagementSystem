package hj.demo01.service;

import hj.demo01.dto.Porder;
import hj.demo01.dto.TbUser;

public interface OrderService {
    String createOrder(Porder order, TbUser user);
}
