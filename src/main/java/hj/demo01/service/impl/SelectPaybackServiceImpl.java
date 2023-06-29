package hj.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hj.demo01.dao.CreditMapper;
import hj.demo01.dao.PayBackMapper;
import hj.demo01.dto.Credit;
import hj.demo01.dto.PayBack;
import hj.demo01.dto.TbUser;
import hj.demo01.service.SelectCreditsService;
import hj.demo01.service.SelectPaybackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SelectPaybackServiceImpl implements SelectPaybackService {

    @Autowired
    SelectCreditsService scs;
    @Autowired
    CreditMapper cm;
    @Autowired
    PayBackMapper pbm;
    @Override
    public List SelectPayback(TbUser user) { //展示该用户7日内应还账单
        //1.查询该用户的所有账单
        List<Credit> creditList = scs.SelectCredits(user);
        //2.根据 账单id 查找 7日内应还 分期还款单
        List<PayBack> payBackList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();//当前日历
//        calendar.add(Calendar.WEEK_OF_YEAR, 7);// 将当前日期增加一周
        calendar.add(Calendar.MONTH, 1); // 将月份增加1个月
        for (Credit credit : creditList) {
            QueryWrapper q = new QueryWrapper();
            q.eq("credit_id",credit.getId());
            q.lt("expectpaytime",calendar.getTime());
            payBackList.addAll(pbm.selectList(q));
        }
        return payBackList;
    }
}
