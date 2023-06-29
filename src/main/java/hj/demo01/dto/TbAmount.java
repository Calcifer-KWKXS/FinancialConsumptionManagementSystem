package hj.demo01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_amount")
public class TbAmount {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;//账户名
    private Integer totalamount = 1000;//用户信用总额度
    private Integer useamount = 0;//用户已使用额度
    private Integer freeamount = 1000;

    private Date createtime = new Date();
    private Integer state = 1;

}
