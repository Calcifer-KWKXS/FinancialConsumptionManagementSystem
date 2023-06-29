package hj.demo01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data //对属性进行封装  这里的封装的 set 方法是没有返回值的，函数内就一句 this.amount = amount;
@Accessors(chain = true) //加这个注解，属性的 set 方法多个返回值，并且返回值是当前对象(哪个对象调用的set方法，返回的就是这个对象)。
// 好处：可以在设置属性时使用 . 语法连续赋值，精简代码
@TableName("credit")
public class Credit {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //BigDecimal:一个金融方面用的小数
    private Double amount;  //总额
    private Integer period;
    private int consumerId;
    private int orderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime = new Date();
    private int state = 1;
}
