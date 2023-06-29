package hj.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hj.demo01.dao.AmountMapper;
import hj.demo01.dto.TbAmount;
import hj.demo01.dto.TbUser;
import hj.demo01.service.CreditApllyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditApllyServiceImpl implements CreditApllyService {
    @Autowired
    AmountMapper am;
    @Override
    public TbAmount aplly(TbUser user) {
        QueryWrapper q = new QueryWrapper();
        q.eq("account",user.getAccount());
        TbAmount amount = am.selectOne(q);
        if ( amount.getTotalamount() > 10000 ) {
            throw new RuntimeException("您已达到最高额度");
        }
        int addNumber = 500;
        amount.setTotalamount(amount.getTotalamount() + addNumber);
        amount.setFreeamount(amount.getFreeamount() + addNumber);
        //更新数据库
        am.updateById(amount); //这个函数不太明白
        return amount;
    }
}
