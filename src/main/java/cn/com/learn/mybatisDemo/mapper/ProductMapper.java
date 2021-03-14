package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.Product;
import java.util.List;

import org.springframework.data.domain.Pageable;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    Product selectByPrimaryKey(Integer id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);
    
    /**
     * 查询结果
     * @param pageable
     * @return
     */
    List<Product> findNew(Pageable pageable);
}