package com.ssm.backstage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: Menu
 * @Description: 菜单model
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午1:52:50
 *
 */
@Document(collection="menu")
public class Menu implements Serializable {

	/**
	* 序列化
	*/ 
	private static final long serialVersionUID = 6458521760707962162L;
	
	/**
	 * id
	 */
	@Id
	private String id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 访问路径
	 */
	private String path;
	
	/**
	 * 父级菜单
	 */
	private String parentId;
	
	/**
	 * 子菜单
	 */
	private List<Menu> menuList = new ArrayList<Menu>();
	
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
