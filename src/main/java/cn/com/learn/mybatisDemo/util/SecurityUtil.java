package cn.com.learn.mybatisDemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import cn.com.learn.mybatisDemo.vo.User;

public class SecurityUtil {

	public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
