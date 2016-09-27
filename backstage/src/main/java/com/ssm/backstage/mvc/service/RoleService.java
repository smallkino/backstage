package com.ssm.backstage.mvc.service;

import java.util.List;

import com.ssm.backstage.model.Page;
import com.ssm.backstage.model.Role;

/**
 * 
 * @ClassName: RoleService
 * @Description: 角色service接口
 * @author xiaoxiaofeng
 * @date 2016年9月27日 下午3:31:27
 *
 */
public interface RoleService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Author：xiaoxiaofeng
	 * @Description: 新增及保存
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveOrUpdate(Role role);
	
	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 首页获取所有权限
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	List<Role> getAllRole();

	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 分页获取所有权限
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
