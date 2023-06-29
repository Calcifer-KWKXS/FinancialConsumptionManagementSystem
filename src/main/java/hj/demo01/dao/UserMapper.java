package hj.demo01.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hj.demo01.dto.TbUser;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface UserMapper extends BaseMapper<TbUser> {
}
