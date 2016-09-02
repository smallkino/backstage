package com.ssm.backstage.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Role implements Serializable {

	/**
	* 序列化
	*/ 
	private static final long serialVersionUID = 3351270726499820866L;

	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 角色拥有的权限菜单
	 */
	private ArrayList<Integer> menuId = new ArrayList<Integer>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getMenuId() {
		return menuId;
	}

	public void setMenuId(ArrayList<Integer> menuId) {
		this.menuId = menuId;
	}
	
}
