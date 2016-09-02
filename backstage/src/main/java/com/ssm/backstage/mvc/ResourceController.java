package com.ssm.backstage.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: ResourceController
 * @Description: 资源相关controller
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午2:39:17
 *
 */
@Controller
@RequestMapping("/resource/")
public class ResourceController {
	
	/**
	 * 
	 * @Title: index
	 * @Author：xiaoxiaofeng
	 * @Description: 资源管理首页
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    设定文件 视图对象
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("/game/resource");
		return result;
	}
}
