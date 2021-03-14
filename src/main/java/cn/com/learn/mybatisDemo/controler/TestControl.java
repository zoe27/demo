package cn.com.learn.mybatisDemo.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.learn.mybatisDemo.mapper.UserInfoMapper;
import cn.com.learn.mybatisDemo.service.UserService;
import cn.com.learn.mybatisDemo.vo.UserInfo;

/**
 * 基于springboot的webapi 接口
 * 
 * @author zhoubin
 *
 */
@Controller
public class TestControl {
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private UserService userservice;

	@RequestMapping("/test1")
	@ResponseBody
	public String test() {
		return "hello";
	}

	@RequestMapping("/test")
	public String testPage() {
		return "login";
	}
	
	@RequestMapping("/testDb")
	@ResponseBody
	public String getDate() {
		
		userservice.delById(4);
		
//		UserInfo user = userInfoMapper.getUser(26);
//		System.out.println(user.getAge());
//		return "" + user.getAge();
		return userservice.findById(3).getName();
	}

}
