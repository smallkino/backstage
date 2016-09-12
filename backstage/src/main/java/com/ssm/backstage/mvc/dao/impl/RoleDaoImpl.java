package com.ssm.backstage.mvc.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.ssm.backstage.model.Role;
import com.ssm.backstage.mvc.dao.RoleDao;

/**
 * 
 * @ClassName: RoleDaoImpl
 * @Description: 角色model操作
 * @author xiaoxiaofeng
 * @date 2016年9月8日 下午2:18:35
 *
 */
@Component("roleDao")
public class RoleDaoImpl implements RoleDao {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);
	
	/**
	 * 集合名称
	 */
	private static final String COLLECTION_NAME = "role";
	
	/**
	 * mongoDB操作
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 
	 * @Title: findAll
	 * @Author：xiaoxiaofeng
	 * @Description: 查询所有角色信息
	 * @param @return    设定文件
	 * @return List<Role>    返回所有角色
	 * @throws
	 */
	@Override
	public List<Role> findAll() {
		List<Role> roleList = mongoTemplate.findAll(Role.class, "role");
		return roleList;
	}
	
}
