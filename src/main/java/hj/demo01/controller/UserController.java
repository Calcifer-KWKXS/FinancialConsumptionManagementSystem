package hj.demo01.controller;

import hj.demo01.dto.TbUser;
import hj.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

//@RestController 返回给前端的是数据
@RestController

public class UserController {
    @Autowired
    UserService us;//服务层应该通过接口

    @RequestMapping("/user/login")
    public String login(String account, String password, HttpSession session) {
        System.out.println("账号：" + account + ",密码：" + password);
        TbUser user = us.doLogin(account,password);
        System.out.println(user);
        if ( user == null ) {
            return null;
        }
        //HttpSession:如果后端请求使用session，但前端cookie里没有 session id，那么后端处理这个请求的时候就会new一个
        //如果有，那么就还是那个session
        //前端cookie里session id的传递用户并不知情，是在 cookie 上悄悄带的
        //第一次登陆的时候创建这个用户的 session，之后请求的时候，前端浏览器的cookie中悄悄传递了session id,所以之后使用的是登陆时创建的那个   每个用户只有一个 session
        session.setAttribute("user",user);//将查到的用户对象写进 session 中
        return user.getAccount(); //查到此用户后把用户的账户名返回给前端（）
        /*
            HttpSession是Java Web开发中的一个接口，用于在客户端和服务器之间维护会话状态。
            它可以存储和检索有关用户的信息，以便在整个Web应用程序中使用。例如，可以使用HttpSession来存储用户的登录状态，购物车内容等 。
            HttpSession的工作原理如下：
            1. 打开浏览器，在浏览器上发送首次请求。
            2. 服务器会创建一个HttpSession对象，该对象代表一次会话。
            3. 同时生成HttpSession对象对应的Cookie对象，并且Cookie对象的name是jsessionid,Cookie的value是32位长度的字符串(jsessionid=xxxx)。
            4. 服务器将Cookie的value和HttpSession对象绑定到session列表中。
            5. 服务器将Cookie完整发送给浏览器客户端。
            6. 浏览器客户端将Cookie保存到缓存中。
            7. 只要浏览器不关闭，Cookie就不会消失.
        */
    }

/*
*`@RequestMapping("/user/login")` 是一个Spring MVC中的注解，用于映射HTTP请求到特定的处理方法。
* 括号内的参数是URL路径的一部分，用于指定请求的资源位置。
具体来说，`@RequestMapping("/user/login")`表示当用户访问"/user/login"这个URL时，会触发与该注解关联的方法。
* 在这个例子中，`/user/login`是一个相对路径，表示请求应该被映射到名为"login"的方法上。
需要注意的是，`@RequestMapping`注解通常与控制器类一起使用，控制器类中的方法会被自动映射到对应的URL路径上。
* （后端通过这个注解拿到前端提交的用户名和密码）
*/
    @RequestMapping("/user/register")
//    public String register(String account,String password,String nickname,String realName,String mobile,String email) { //这里参数名要与前端名一样
    public String register(TbUser user) {
        System.out.println("--Controller--：账号：" + user.getAccount() + ",密码：" + user.getPassword()+ ",昵称：" + user.getNickname());
//        int flag = us.doRegister(account,password,nickname,realName,mobile,email);//为什么要做一个这样的（TbUser user）返回值？
        int flag = us.doRegister(user);
        System.out.println("插入数据结果返回值：" + flag);
        if ( flag == 1 ) {
            return "注册成功";
        }
        return "注册失败";
    }
}
