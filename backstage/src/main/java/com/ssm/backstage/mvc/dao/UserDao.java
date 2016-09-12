package com.ssm.backstage.mvc.dao;

import com.ssm.backstage.model.User;

/**
 * 
 * @ClassName: UserDao
 * @Description: user持久层操作
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午4:27:46
 *
 */
public interface UserDao {
	
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
	User getUserByUserNameAndPassword(String username,String password);

	/**
	 * 
	 * @Title: getUserByUserName
	 * @Author：xiaoxiaofeng
	 * @Description: 根据用户名获取用户
	 * @param @param username 用户名
	 * @param @return    返回数量
	 * @return User    返回类型
	 * @throws
	 */
	User getUserByUserName(String username);
}
