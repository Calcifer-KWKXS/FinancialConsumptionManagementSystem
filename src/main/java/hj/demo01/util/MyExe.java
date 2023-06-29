package hj.demo01.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理
//Spring 的AOT增强，针对所有的控制器中的所有方法进行异常捕获
@RestControllerAdvice
public class MyExe {
    @ExceptionHandler(Exception.class)//专门处理 Exception 类型异常及其子类异常
    public String handEx(Exception e) {
        return e.getMessage();//获取异常的提示信息返回给前端
        //设计数据库、全局异常处理、下订单业务
    }
}
