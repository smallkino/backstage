package com.ssm.backstage.mvc.service;

import com.ssm.backstage.model.User;

/**
 * 
 * @ClassName: UserService
 * @Description: 用户service接口
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午3:43:11
 *
 */
public interface UserService {
	
	/**
	 * 
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 保存用户信息
	 * @param @param user    用户对象
	 * @return void    返回类型
	 * @throws
	 */
	void save(User user);
	
	/**
	 * 
	 * @Title: getUserByUserNameAndPassword
	 * @Author：xiaoxiaofeng
	 * @Description: 根据用户名和密码获取用户信息
	 * @param @param username 用户名
	 * @param @param password 密码
	 * @param @return    用户信息
	 * @return User    返回类型
	 * @throws
	 */
	User getUserByUserNameAndPassword(String username, String password);
	

	/**
	 * 
	 * @Title: registerSubmit
	 * @Author：xiaoxiaofeng
	 * @Description: 注册操作
	 * @param @param username 用户名
	 * @param @return    注册是否成功 true 成功 false 失败
	 * @return boolean    返回类型
	 * @throws
	 */
	boolean registerSubmit(String username,String password);

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
	boolean loginSubmit(String username, String password);
	
}
