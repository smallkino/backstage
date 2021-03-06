package com.ssm.backstage.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ssm.backstage.model.Page;
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
		List<Menu> menuList = menuService.getMenuByParentId("-1");
		result.addObject("menuList", menuList);
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
	 * @Title: getById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id查询菜单
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("getById")
	@ResponseBody
	public ReturnDataFormat<Menu> getById(String id, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Menu> result = new ReturnDataFormat<Menu>();
		try {
			Menu menu = menuService.getById(id);
			result.setData(menu);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("查询菜单报错: {}",id,e);
			result.setSuccess(false);
			result.setMessage("查询菜单报错!");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id查询菜单
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public ReturnDataFormat<Boolean> deleteById(String id, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			menuService.deleteById(id);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("删除菜单报错: {}",id,e);
			result.setSuccess(false);
			result.setMessage("删除菜单报错!");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getAllMenu
	 * @Author：xiaoxiaofeng
	 * @Description: 分页查询
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("getAllMenu")
	@ResponseBody
	public Map<String, Object> getAllMenu(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int draw = Integer.valueOf(request.getParameter("draw"));
			int start = Integer.valueOf(request.getParameter("start"));
			int length = Integer.valueOf(request.getParameter("length"));
			Page page = new Page();
			page.setLength(length);
			page.setStart(start);
			List<Menu> menuList = menuService.getAllMenu(page);
			List<Menu> list =menuService.getMenuList();
			result.put("draw", draw);
			result.put("recordsTotal", list.size());
			result.put("recordsFiltered", list.size());
			result.put("data", menuList);
		} catch (Exception e) {
			log.error("查询菜单报错: ",e);
			result.put("error", "查询菜单报错");
		}
		return result;
	}
	
}
