package cn.com.learn.mybatisDemo;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.com.learn.mybatisDemo.mapper.UserInfoMapper;
import cn.com.learn.mybatisDemo.mybatisFactory.SqlSessionFactoryUtil;
import cn.com.learn.mybatisDemo.vo.UserInfo;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.com.learn.mybatisDemo.mapper")
@EnableTransactionManagement
public class App 
{
    public static void main( String[] args )
    {
        
		SpringApplication.run(App.class, args);
		//getDataTest();
            
    }
    
    
    public static void getDataTest() {
    	System.out.println( "Hello World!" );
        Configuration c;
            //获得会话对象
            SqlSession session= SqlSessionFactoryUtil.getSqlSession();
            System.out.println(UserInfoMapper.class);
            
            try {          
                UserInfoMapper userMapper = session.getMapper(UserInfoMapper.class);
                UserInfo userInfo = new UserInfo();
                userInfo.setAge(77);
                userInfo.setName("hell");
                userInfo.setNick("oooo");
                userInfo.setPass("iiii");
                userMapper.add(userInfo);
                session.commit();
                
                UserInfo user = userMapper.getUser(26);
                System.out.println(user.getAge());
            }catch(Exception e) {
            		e.printStackTrace();
            }
            finally {
                session.close();
            }
    }
}




