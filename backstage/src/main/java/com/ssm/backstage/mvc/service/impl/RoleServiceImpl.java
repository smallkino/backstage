package com.ssm.backstage.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.backstage.model.Page;
import com.ssm.backstage.model.Role;
import com.ssm.backstage.mvc.dao.RoleDao;
import com.ssm.backstage.mvc.service.RoleService;

/**
 * 
 * @ClassName: RoleService
 * @Description: 角色service实现
 * @author xiaoxiaofeng
 * @date 2016年9月27日 下午3:31:27
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	/**
	 * 角色数据库操作
	 */
	@Autowired
	private RoleDao roleDao;

	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Author：xiaoxiaofeng
	 * @Description: 新增及保存
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void saveOrUpdate(Role role) {
		if(role.getId() == null){
			roleDao.save(role);
		}else{
			roleDao.update(role);
		}
	}

	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 首页获取所有权限
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 分页获取所有权限
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Role> getAllRole(Page page) {
		return roleDao.getAllRole(page);
	}

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
	@Override
	public Role getById(String id) {
		return roleDao.getById(id);
	}

	/**
	 * 
	 * @Title: deleteById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id删除
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void deleteById(String id) {
		roleDao.deleteById(id);
	}

}
