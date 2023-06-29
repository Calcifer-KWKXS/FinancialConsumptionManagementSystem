package hj.demo01.service;

import hj.demo01.dto.TbAmount;
import hj.demo01.dto.TbUser;

public interface CreditManageService {
    TbAmount selectAmount(TbUser user);
}
