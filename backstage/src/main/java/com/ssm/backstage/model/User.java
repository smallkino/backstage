package com.ssm.backstage.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: User
 * @Description: 用户model
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午1:40:25
 *
 */
public class User implements Serializable{

	/**
	*  序列化
	*/ 
	private static final long serialVersionUID = -8368162662977101520L;
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 角色
	 */
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
