package com.ssm.backstage.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: Menu
 * @Description: 菜单model
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午1:52:50
 *
 */
public class Menu implements Serializable {

	/**
	* 序列化
	*/ 
	private static final long serialVersionUID = 6458521760707962162L;
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 访问路径
	 */
	private String path;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
