package com.ssm.backstage.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;
/**
 * 
 * @ClassName: Role
 * @Description: 角色model
 * @author xiaoxiaofeng
 * @date 2016年9月8日 下午2:15:42
 *
 */
@Document(collection="role")
public class Role implements Serializable{

	/**
	* 序列化
	*/ 
	private static final long serialVersionUID = 3351270726499820866L;
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 菜单id 逗号分隔
	 */
	private String menuIds;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

}
