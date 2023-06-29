package hj.demo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hj.demo01.dao.UserMapper;
import hj.demo01.dto.TbUser;
import hj.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

//为了产生一个UserServiceImpl对象，放到spring容器中。
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper um;

    @Override
    public TbUser doLogin(String account,String password) {
        QueryWrapper q = new QueryWrapper();

        q.eq("account",account);
        q.eq("password",password);
        //select * from tb_user where account='a1' and password='123'
        return um.selectOne(q);//selectOne 做SQL查询

    }

    @Override
//    public int doRegister(String account,String password,String nickname, String realName, String mobile, String email) {
    public int doRegister(TbUser user) {
        //QueryWrapper 是 条件构造器（专门把条件都放进这里）
        QueryWrapper q = new QueryWrapper();
        q.eq("account",user.getAccount());
        q.eq("password",user.getPassword());
        //select * from tb_user where account='a1' and password='123'
        if ( um.selectOne(q) != null ) {
            System.out.println("此用户已存在，请直接登录");
            return -1;
        }
//        TbUser user = new TbUser();//传过来 TbUser 类型，则不需要写那么多参数了，这里也不用这么麻烦了
//        user.setAccount(account);
//        user.setPassword(password);
//        user.setNickname(nickname);
//        user.setRealname(realName);
//        user.setMobile(mobile);
//        user.setEmail(email);
        user.setCreatetime(new Date());
        user.setState(1);
        System.out.println("--service-- UserServiceImpl");
//selectOne() 方法是 BaseMapper 接口中的方法之一，用于查询单个对象。它的返回值是一个实体类对象，如果查询结果为空，则返回 null。
        return um.insert(user);
    }

}


