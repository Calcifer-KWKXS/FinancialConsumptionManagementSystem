package hj.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hj.demo01.dao.AmountMapper;
import hj.demo01.dto.TbAmount;
import hj.demo01.dto.TbUser;
import hj.demo01.service.CreditManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditManageServiceImpl implements CreditManageService {
    @Autowired
    AmountMapper am;
    @Override
    public TbAmount selectAmount(TbUser user) {
        QueryWrapper q = new QueryWrapper();
        q.eq("account",user.getAccount());
//        TbAmount amount = am.selectOne(q);
//        System.out.println("------ 总额度：" + amount.getTotalamount());
        return am.selectOne(q);
    }
}
