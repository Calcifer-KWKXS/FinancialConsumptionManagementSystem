package hj.demo01.service;

import hj.demo01.dto.TbUser;

public interface UserService {
    TbUser doLogin(String account, String password);


//    int doRegister(String account, String password, String nickname, String realName, String mobile, String email);
    int doRegister(TbUser user);
}
