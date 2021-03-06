package com.ssm.backstage.mvc.dao;

import java.util.List;

import com.ssm.backstage.model.Menu;
import com.ssm.backstage.model.Page;

/**
 * 
 * @ClassName: MenuDao
 * @Description: 菜单dao接口
 * @author xiaoxiaofeng
 * @date 2016年9月26日 下午3:12:18
 *
 */
public interface MenuDao {
	
	/**
	 * 
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 新增菜单
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void save(Menu menu);
	
	/**
	 * 
	 * @Title: update
	 * @Author：xiaoxiaofeng
	 * @Description: 更新菜单
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void update(Menu menu);

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
	 * @Description: 查询
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Menu> getAllMenu();

	/**
	 * 
	 * @Title: getAllMenu
	 * @Author：xiaoxiaofeng
	 * @Description: 分页查询所有菜单
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Menu> getAllMenu(Page page);

	/**
	 * 
	 * @Title: getById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id查询
	 * @param @param id
	 * @param @return    设定文件
	 * @return Menu    返回类型
	 * @throws
	 */
	Menu getById(String id);

	/**
	 * 
	 * @Title: deleteById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id删除
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteById(String id);
	
}
