package com.ssm.backstage.mvc.service;

import java.util.List;

import com.ssm.backstage.model.Menu;

/**
 * 
 * @ClassName: MenuService
 * @Description: 菜单service业务层
 * @author xiaoxiaofeng
 * @date 2016年9月26日 下午3:01:06
 *
 */
public interface MenuService {
	
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Author：xiaoxiaofeng
	 * @Description: 新增及保存
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveOrUpdate(Menu menu);
	
	/**
	 * 
	 * @Title: getMenuByParentId
	 * @Author：xiaoxiaofeng
	 * @Description: 根据父级菜单id查询子菜单
	 * @param @param parentId
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Menu> getMenuByParentId(String parentId);

	/**
	 * 
	 * @Title: getAllMenu
	 * @Author：xiaoxiaofeng
	 * @Description: 首页获取所有菜单
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Menu> getAllMenu();
}
