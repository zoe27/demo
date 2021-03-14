package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.Classification;
import java.util.List;

public interface ClassificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classification record);

    Classification selectByPrimaryKey(Integer id);

    List<Classification> selectAll();

    int updateByPrimaryKey(Classification record);
}