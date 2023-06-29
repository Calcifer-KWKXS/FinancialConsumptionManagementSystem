package hj.demo01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Orderdetail")
public class Orderdetail {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer skuId;

    private String skuName;

    private Integer price;

    private Integer scount;

    private Integer sumprice;

    private Integer orderId;

    private Date createtime = new Date();

    private Integer state = 1;
}
