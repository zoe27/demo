package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.GoodsInfo;
import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Integer id);

    List<GoodsInfo> selectAll();

    int updateByPrimaryKey(GoodsInfo record);
}