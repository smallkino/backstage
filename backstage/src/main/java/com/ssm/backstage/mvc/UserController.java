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
import com.ssm.backstage.model.User;
import com.ssm.backstage.mvc.service.RoleService;
import com.ssm.backstage.mvc.service.UserService;

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
	 * 用户相关业务操作
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 角色相关业务操作
	 */
	@Autowired
	private RoleService roleService;
	
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
	 * @Title: login
	 * @Author：xiaoxiaofeng
	 * @Description: 登录页面
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    视图对象
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("logout.html")
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("/user/login");
		request.getSession().removeAttribute("loginUser");
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
	
	/**
	 * 
	 * @Title: registerSubmit
	 * @Author：xiaoxiaofeng
	 * @Description: 注册提交操作
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    js统一回传
	 * @return ReturnDataFormat<Boolean> 返回类型
	 * @throws
	 */
	@RequestMapping("registerSubmit")
	@ResponseBody
	public ReturnDataFormat<Boolean> registerSubmit(HttpServletRequest request, HttpServletResponse response,String username,String password){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			boolean flag = userService.registerSubmit(username,password);
			result.setData(flag);
			if(flag){
				result.setMessage("注册成功!");
			}else{
				result.setMessage("用户名已存在!");
			}
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("注册操作报错 username:{},password{}",username,password,e);
			result.setSuccess(false);
			result.setMessage("系统异常");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: loginSubmit
	 * @Author：xiaoxiaofeng
	 * @Description: 登录提交操作
	 * @param @param request http请求
	 * @param @param response http响应
	 * @param @return    js统一回传
	 * @return ReturnDataFormat<Boolean> 返回类型
	 * @throws
	 */
	@RequestMapping("loginSubmit")
	@ResponseBody
	public ReturnDataFormat<Boolean> loginSubmit(HttpServletRequest request, HttpServletResponse response,String username,String password){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			boolean flag = userService.loginSubmit(username,password);
			result.setData(flag);
			if(flag){
				result.setMessage("登陆成功!");
				request.getSession().setAttribute("loginUser", username);
			}else{
				result.setMessage("用户名或密码错误!");
			}
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("登录操作报错 username:{},password{}",username,password,e);
			result.setSuccess(false);
			result.setMessage("系统异常");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: index
	 * @Author：xiaoxiaofeng
	 * @Description: 用户管理首页
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView result = new ModelAndView("system/user");
		List<Role> roleList = roleService.getAllRole();
		result.addObject("roleList", roleList);
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
	public ReturnDataFormat<User> getById(String id, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<User> result = new ReturnDataFormat<User>();
		try {
			User user = userService.getById(id);
			result.setData(user);
			result.setSuccess(true);
		} catch (Exception e) {
			log.error("查询用户报错: {}",id,e);
			result.setSuccess(false);
			result.setMessage("查询用户报错!");
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
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public ReturnDataFormat<Boolean> saveOrUpdate(User user, HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			userService.saveOrUpdate(user);
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
	 * @Title: getAllUser
	 * @Author：xiaoxiaofeng
	 * @Description: 分页查询
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("getAllUser")
	@ResponseBody
	public Map<String, Object> getAllUser(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int draw = Integer.valueOf(request.getParameter("draw"));
			int start = Integer.valueOf(request.getParameter("start"));
			int length = Integer.valueOf(request.getParameter("length"));
			Page page = new Page();
			page.setLength(length);
			page.setStart(start);
			List<User> userList = userService.getAllUser(page);
			List<User> list =userService.getAllUser();
			result.put("draw", draw);
			result.put("recordsTotal", list.size());
			result.put("recordsFiltered", list.size());
			result.put("data", userList);
		} catch (Exception e) {
			log.error("查询用户报错: ",e);
			result.put("error", "查询用户报错");
		}
		return result;
	}
	
}
