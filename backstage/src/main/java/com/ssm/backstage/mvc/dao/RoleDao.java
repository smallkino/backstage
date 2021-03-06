package com.ssm.backstage.mvc.dao;

import java.util.List;

import com.ssm.backstage.model.Menu;
import com.ssm.backstage.model.Page;
import com.ssm.backstage.model.Role;

/**
 * 
 * @ClassName: RoleDao
 * @Description: 角色操作接口
 * @author xiaoxiaofeng
 * @date 2016年9月8日 下午3:23:29
 *
 */
public interface RoleDao {
	
	/**
	 * 
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 新增菜单
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void save(Role role);
	
	/**
	 * 
	 * @Title: update
	 * @Author：xiaoxiaofeng
	 * @Description: 更新菜单
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void update(Role role);


	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 查询
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Role> getAllRole();

	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 分页查询所有菜单
	 * @param @param page
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Role> getAllRole(Page page);

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
	Role getById(String id);

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
