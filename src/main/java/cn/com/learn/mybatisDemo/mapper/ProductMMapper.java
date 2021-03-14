package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.ProductM;
import java.util.List;

public interface ProductMMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(ProductM record);

    ProductM selectByPrimaryKey(Integer pid);

    List<ProductM> selectAll();

    int updateByPrimaryKey(ProductM record);
}