package com.ssm.backstage.mvc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.ssm.backstage.model.Page;
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
	private static final Logger log = LogManager.getLogger(RoleDaoImpl.class);
	
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
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 新增角色
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void save(Role role) {
		mongoTemplate.save(role, COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: update
	 * @Author：xiaoxiaofeng
	 * @Description: 更新菜单
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void update(Role role) {
		Query query = new Query(Criteria.where("_id").is(role.getId()));
		Update update = new Update();
		update.set("name", role.getName());
		update.set("menuIds", role.getMenuIds());
		mongoTemplate.updateFirst(query, update, Role.class, COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 查询
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Role> getAllRole() {
		return mongoTemplate.findAll(Role.class,COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: getAllRole
	 * @Author：xiaoxiaofeng
	 * @Description: 分页查询所有
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Role> getAllRole(Page page) {
		Query query = new Query();
		query.skip(page.getStart());
		query.limit(page.getLength());
		
		return mongoTemplate.find(query, Role.class);
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
		Query query = new Query(Criteria.where("id").is(id));
		
		return mongoTemplate.findOne(query, Role.class);
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
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, Role.class);
	}

}
