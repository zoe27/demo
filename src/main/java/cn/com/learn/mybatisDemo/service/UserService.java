package cn.com.learn.mybatisDemo.service;


import java.util.List;

import cn.com.learn.mybatisDemo.vo.User;

public interface UserService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    User findById(int id);


    /**
     * 更新
     *
     * @param user
     * @return
     */
    void update(User user);

    /**
     * 创建
     *
     * @param user
     * @return
     */
    int create(User user);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int id);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    List<User> findByUsername(String username);
    /**
     * 检查登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username,String password);


}
