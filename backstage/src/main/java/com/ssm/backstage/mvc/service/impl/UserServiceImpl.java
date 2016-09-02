package com.ssm.backstage.mvc.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ssm.backstage.model.User;
import com.ssm.backstage.mvc.service.UserService;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户service实现
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午3:43:11
 *
 */
public class UserServiceImpl implements UserService {
	
	/**
	 * 日志
	 */
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByUserNameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
