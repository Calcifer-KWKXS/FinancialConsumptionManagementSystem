package hj.demo01.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("sku")
public class Sku {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    private Integer spuId;//注意：在Java规范中变量名由两个单词组成时遵循驼峰命名法，而在数据库中习惯用 _ 隔开，但在
    private Integer price;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //这两个注解是为了解决前后端日期时间格式不一致问题
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

    private Integer state;

    private String imgsrc;
}
