package cn.com.learn.mybatisDemo.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.com.learn.mybatisDemo.vo.UserInfo;

/**
 * 对应的操作接口类
 * @author zhoubin
 *
 */
public interface UserInfoMapper {

	/**
	 * 新增用户
	 * @param userInfo
	 * @return
	 */
	int add(UserInfo userInfo);
	
	/**
	 * 获取一个用户
	 * @param id
	 * @return
	 */
	UserInfo getUser(int id);
	
}
