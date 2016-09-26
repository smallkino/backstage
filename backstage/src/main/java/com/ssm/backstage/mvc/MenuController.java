package com.ssm.backstage.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ssm.backstage.model.Menu;
import com.ssm.backstage.model.ReturnDataFormat;
import com.ssm.backstage.mvc.service.MenuService;

/**
 * 
 * @ClassName: MenuController
 * @Description: 菜单相关控制器
 * @author xiaoxiaofeng
 * @date 2016年9月26日 上午10:41:20
 *
 */
@Controller
@RequestMapping("/menu/")
public class MenuController {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(MenuController.class);
	
	/**
	 * 菜单service
	 */
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * @Title: index
	 * @Author：xiaoxiaofeng
	 * @Description: 菜单信息首页
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("system/menu");
		return result;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Author：xiaoxiaofeng
	 * @Description: 保存或者更新
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public ReturnDataFormat<Boolean> saveOrUpdate(Menu menu, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			menuService.saveOrUpdate(menu);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("保存或更新菜单报错: {}",JSONObject.toJSONString(result),e);
			result.setSuccess(false);
			result.setMessage("保存或更新菜单异常!");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Author：xiaoxiaofeng
	 * @Description: 保存或者更新
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("getAllMenu")
	@ResponseBody
	public ReturnDataFormat<List<Menu>> getAllMenu(HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<List<Menu>> result = new ReturnDataFormat<List<Menu>>();
		try {
			List<Menu> menuList = menuService.getAllMenu();
			result.setSuccess(true);
			result.setData(menuList);
		} catch (Exception e) {
			log.error("查询菜单报错: ",e);
			result.setSuccess(false);
			result.setMessage("查询菜单报错");
		}
		return result;
	}
	
}
