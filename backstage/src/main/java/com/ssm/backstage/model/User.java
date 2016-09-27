package com.ssm.backstage.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: User
 * @Description: 用户model
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午1:40:25
 *
 */
@Document(collection="user")
public class User implements Serializable{

	/**
	*  序列化
	*/ 
	private static final long serialVersionUID = -8368162662977101520L;
	
	/**
	 * id
	 */
	@Id
	private String id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 角色 默认为-1 无角色信息 0 admin
	 */
	private String roleId = "-1";


	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
