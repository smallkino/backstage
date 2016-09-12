package com.ssm.backstage.mvc.dao;

import java.util.List;

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
	 * @Title: findAll
	 * @Author：xiaoxiaofeng
	 * @Description: 查询所有角色信息
	 * @param @return    设定文件
	 * @return ArrayList<Role>    返回类型
	 * @throws
	 */
	List<Role> findAll();
	
}
