package com.ssm.backstage.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ssm.backstage.model.User;
import com.ssm.backstage.mvc.dao.UserDao;

/**
 * 
 * @ClassName: SecurityUserDetailService
 * @Description: 登陆入口用户相关验证
 * @author xiaoxiaofeng
 * @date 2016年9月8日 上午11:17:27
 *
 */
public class SecurityUserDetailService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	/**
	 * 获取用户与权限信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.getUserByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在!");
		}
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority(user.getRoleId()));
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), auths);
	}

}
