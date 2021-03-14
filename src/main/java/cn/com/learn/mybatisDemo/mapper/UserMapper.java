package cn.com.learn.mybatisDemo.mapper;

import cn.com.learn.mybatisDemo.vo.User;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
    
    /**
     * 查询用户是否存在
     * @param name
     * @param pass
     * @return
     */
    User findByUsernameAndPassword(@Param("name") String userName, @Param("pass") String pass);
}