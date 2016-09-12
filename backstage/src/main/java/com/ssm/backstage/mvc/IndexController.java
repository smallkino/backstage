package com.ssm.backstage.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: IndexController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午2:02:03
 *
 */
@Controller
public class IndexController {
	
	/**
	 * 
	 * @Title: index
	 * @Author：xiaoxiaofeng
	 * @Description: 首页链接
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    视图对象
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView result = new ModelAndView("index");
		return result;
	}
	
	/**
	 * 
	 * @Title: error500
	 * @Author：xiaoxiaofeng
	 * @Description: 500错误页面
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("/error500.html")
	public ModelAndView error500(HttpServletRequest request,HttpServletResponse response){
		ModelAndView result = new ModelAndView("error500");
		return result;
	}
}
