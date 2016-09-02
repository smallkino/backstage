package com.ssm.backstage.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户相关操作
 * @author xiaoxiaofeng
 * @date 2016年9月2日 上午10:20:11
 *
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(UserController.class);
	
	/**
	 * 
	 * @Title: login
	 * @Author：xiaoxiaofeng
	 * @Description: 登录页面
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    视图对象
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("login.html")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("/user/login");
		
		return result;
	}
	
	/**
	 * 
	 * @Title: register
	 * @Author：xiaoxiaofeng
	 * @Description: 注册页面
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    视图对象
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("register.html")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("/user/register");
		
		return result;
	}
}
