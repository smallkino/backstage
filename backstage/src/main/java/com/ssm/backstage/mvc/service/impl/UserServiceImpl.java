package com.ssm.backstage.mvc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.backstage.model.User;
import com.ssm.backstage.mvc.dao.UserDao;
import com.ssm.backstage.mvc.service.UserService;
import com.ssm.backstage.util.MD5Util;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户service实现
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午3:43:11
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	/**
	 * 日志
	 */
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	/**
	 * 用户dao
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * 
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 保存用户信息
	 * @param @param user    用户对象
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void save(User user) {

	}

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
	@Override
	public User getUserByUserNameAndPassword(String username, String password) {
		return null;
	}

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
	@Override
	public boolean registerSubmit(String username,String password) {
		User result = userDao.getUserByUserName(username);
		if(result != null){
			return false;
		}else{
			User user = new User();
			user.setUsername(username);
			user.setPassword(MD5Util.getMD5(password));
			userDao.save(user);
			return true;
		}
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
	@Override
	public boolean loginSubmit(String username, String password) {
		User user = userDao.getUserByUserNameAndPassword(username, MD5Util.getMD5(password));
		if(user == null){
			return false;
		}
		return true;
	}
	
}
