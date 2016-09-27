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
import com.ssm.backstage.model.Role;
import com.ssm.backstage.mvc.service.MenuService;
import com.ssm.backstage.mvc.service.RoleService;

/**
 * 
 * @ClassName: RoleController
 * @Description: 角色相关controller
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午2:39:17
 *
 */
@Controller
@RequestMapping("/role/")
public class RoleController {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(RoleController.class);
	
	/**
	 * 角色service
	 */
	@Autowired
	private RoleService roleService;
	
	/**
	 * 菜单service
	 */
	@Autowired
	private MenuService menuService;
	
	/**
	 * 
	 * @Title: index
	 * @Author：xiaoxiaofeng
	 * @Description: 角色信息首页
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("system/role");
		List<Menu> menuList = menuService.getMenuList();
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
	public ReturnDataFormat<Boolean> saveOrUpdate(Role role, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			roleService.saveOrUpdate(role);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("保存或更新角色报错: {}",JSONObject.toJSONString(result),e);
			result.setSuccess(false);
			result.setMessage("保存或更新角色异常!");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id查询角色
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("getById")
	@ResponseBody
	public ReturnDataFormat<Role> getById(String id, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Role> result = new ReturnDataFormat<Role>();
		try {
			Role role = roleService.getById(id);
			result.setData(role);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("查询角色报错: {}",id,e);
			result.setSuccess(false);
			result.setMessage("查询角色报错!");
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
			roleService.deleteById(id);
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
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 分页查询
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("getAllRole")
	@ResponseBody
	public Map<String, Object> getAllRole(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int draw = Integer.valueOf(request.getParameter("draw"));
			int start = Integer.valueOf(request.getParameter("start"));
			int length = Integer.valueOf(request.getParameter("length"));
			Page page = new Page();
			page.setLength(length);
			page.setStart(start);
			List<Role> roleList = roleService.getAllRole(page);
			List<Role> list =roleService.getAllRole();
			result.put("draw", draw);
			result.put("recordsTotal", list.size());
			result.put("recordsFiltered", list.size());
			result.put("data", roleList);
		} catch (Exception e) {
			log.error("查询菜单报错: ",e);
			result.put("error", "查询权限报错");
		}
		return result;
	}
	
}
