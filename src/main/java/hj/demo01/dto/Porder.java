package hj.demo01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("porder")
public class Porder {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private int totalprice;
    private int consumerId;

    private String prov;

    private String city;

    private String district;

    private String addr;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime=new Date();

    private Integer state=1;

    @TableField(exist = false) //分期数在表中没有，但在这里有，写这个注解是说明这件事其
    Integer stage = 1;//如果不选择分期付款，那就全款下个月还？
}
