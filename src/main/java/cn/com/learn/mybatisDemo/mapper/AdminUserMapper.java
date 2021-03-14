package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.AdminUser;
import java.util.List;

public interface AdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    List<AdminUser> selectAll();

    int updateByPrimaryKey(AdminUser record);
}