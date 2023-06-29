package hj.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hj.demo01.dao.CreditMapper;
import hj.demo01.dto.TbUser;
import hj.demo01.service.SelectCreditsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectCreditsServiceImpl implements SelectCreditsService {
    @Autowired
    CreditMapper cm;
    @Override
    public List SelectCredits(TbUser user) {
        //查询该用户的所有账单
        QueryWrapper q = new QueryWrapper();
        q.eq("consumer_id",user.getId());//q.eq("应该是表中字段名而不是类中变量名")
        return cm.selectList(q);
//        return cm.selectList(null);//查询该数据库中所有数据
    }
}
