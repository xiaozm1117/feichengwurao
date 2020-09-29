package com.xiao.other;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	private static ThreadLocal<User> userThread = new ThreadLocal<User>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies  = request.getCookies();
		
		String token = null;
		if (cookies!=null) {
		for (Cookie cookie : cookies) {
			
			if("TICKET".equals(cookie.getName())){
				token = cookie.getValue();
				break;
			}
		}}
		
		//判断token是否为null
		if(!StringUtils.isEmpty(token)){
			
			//判断redis缓存中是否有数据
			String userJSON = (String) redisTemplate.opsForValue().get(token);
			
			if(!StringUtils.isEmpty(userJSON)){

				User user = objectMapper.readValue(userJSON, User.class);
				 //放行请求 
				UserThreadLocal.set(user);
				
				return true;
			}
		}
		request.getSession().setAttribute("page", request.getHeader("Referer"));

		response.sendRedirect("/denglu");
		return false; //表示拦截
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		UserThreadLocal.remove();

	}

}
