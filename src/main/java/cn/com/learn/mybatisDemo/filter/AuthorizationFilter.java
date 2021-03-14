package cn.com.learn.mybatisDemo.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 权限拦截器
 * 
 * @date 2018/6/1
 */
@WebFilter(filterName = "authorizationFilter", urlPatterns = "/*")
@Order(1)
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
		LOGGER.info("init AuthorizationFilter ");
		System.out.println("init AuthorizationFilter");
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 支持跨域访问
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, Content-Type, Accept,X-Custom-Header");
		response.setHeader("X-Powered-By", "SpringBoot");
		if ("option".equalsIgnoreCase(request.getMethod())) {
			responseJSON(response, new HashMap<Object, Object>());
			return;
		}
		// 除了拦截login.html 其他html都拦截
		StringBuffer url = request.getRequestURL();
		LOGGER.info("filter get the url is : {}", url);
		// System.out.println(url);
		String path = url.toString();
		// 只拦截这些类型请求
		if (path.endsWith(".do") || path.endsWith(".html")) {
			// 登录，图片不拦截
			if (path.endsWith("toLogin.html") || path.endsWith("toRegister.html") || path.endsWith("register.do")
					|| path.endsWith("login.do") || path.endsWith("logout.do") || path.endsWith("error.html")
					|| path.endsWith("checkUsername.do") || path.indexOf("/mall/admin/product/img/") != -1
					// || path.endsWith("index.html")
					|| path.endsWith("classification/list.do") || path.indexOf("product") != -1) {
				chain.doFilter(request, response);
			} else {
				processAccessControl(request, response, chain);
			}

		} else {
			// 其他静态资源都不拦截
			chain.doFilter(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processAccessControl(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object adminUser = request.getSession().getAttribute("login_user");
		Object user = request.getSession().getAttribute("user");
		String url = request.getRequestURL().toString();
		LOGGER.info("the user info is {}", user);
		if (url.indexOf("admin") != -1) {
			if (adminUser == null) {
				response.sendRedirect("/mall/admin/toLogin.html");
			} else {
				chain.doFilter(request, response);
			}
		} else {

			String token = request.getHeader("token");// 从 http 请求头中取出 token
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("token")) {
					token = cookie.getValue();
				}
			}

			// return true;
			if (user == null || token == null) {
				response.sendRedirect("/mall/user/toLogin.html");
			} else {
				String userId = JWT.decode(token).getAudience().get(0);
				JWTVerifier jwtVerifier = JWT
						.require(Algorithm.HMAC256(((cn.com.learn.mybatisDemo.vo.User) user).getPassword())).build();
				try {
					jwtVerifier.verify(token);
				} catch (JWTVerificationException e) {
					throw new RuntimeException("401");
				}
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {

	}

	/**
	 * 返回JOSN数据格式
	 *
	 * @param response
	 * @param object
	 * @throws IOException
	 */
	public static void responseJSON(HttpServletResponse response, Object object) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		if (object == null)
			return;
		String jsonStr = mapper.writeValueAsString(object);
		OutputStream out = response.getOutputStream();
		out.write(jsonStr.getBytes("UTF-8"));
		out.flush();
	}
}