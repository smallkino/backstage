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
	
}
