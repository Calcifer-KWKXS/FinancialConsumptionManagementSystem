package hj.demo01.service.impl;

import hj.demo01.dao.ProductMapper;
import hj.demo01.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //表示这业务层？？
public class ProductServiceImpl implements ProductService {

    @Autowired  //加这个注解让下一行的 pm 拿到数据
    ProductMapper pm;

    @Override
    public List findAllProducts() {
        return pm.selectList(null);
        //selectList：查询后返回一个list
        //selectOne：查询后返回的只是一个
    }
}
