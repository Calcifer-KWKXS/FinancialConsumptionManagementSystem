package hj.demo01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data //这个注解自动生成成员变量的 get 、set 函数
@TableName("tb_user") //绑定数据表
public class TbUser {
    @TableId(type = IdType.AUTO)//    指定主键,并且自增长

    private Integer id;
    private String account;
    private String password;

    private String nickname;

    private String realname;

    private String mobile;

    private String email;

    private Date createtime;

    private Integer state;
}
