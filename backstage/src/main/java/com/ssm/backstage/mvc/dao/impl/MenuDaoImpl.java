package com.ssm.backstage.mvc.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.ssm.backstage.model.Menu;
import com.ssm.backstage.mvc.dao.MenuDao;

/**
 * 
 * @ClassName: MenuDao
 * @Description: 菜单dao实现
 * @author xiaoxiaofeng
 * @date 2016年9月26日 下午3:12:18
 *
 */
@Component("menuDao")
public class MenuDaoImpl implements MenuDao{
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(MenuDaoImpl.class);
	
	/**
	 * 集合名
	 */
	private static final String COLLECTION_NAME = "menu";
	
	/**
	 * mongoDB操作
	 */
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 新增菜单
	 * @param @param menu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void save(Menu menu) {
		mongoTemplate.save(menu, COLLECTION_NAME);
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
	public void update(Menu menu) {
		Query query = new Query(Criteria.where("_id").is(menu.getId()));
		Update update = new Update();
		update.set("id", menu.getName());
		update.set("parentId", menu.getParentId());
		update.set("path", menu.getPath());
		mongoTemplate.updateFirst(query, update, Menu.class, COLLECTION_NAME);
	}

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
	@Override
	public List<Menu> getMenuByParentId(String parentId) {
		Query query = new Query(Criteria.where("parentId").is(parentId));
		return mongoTemplate.find(query,Menu.class,COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: getAllMenu
	 * @Author：xiaoxiaofeng
	 * @Description: 查询
	 * @param @return    设定文件
	 * @return List<Menu>    返回类型
	 * @throws
	 */
	@Override
	public List<Menu> getAllMenu() {
		return mongoTemplate.findAll(Menu.class,COLLECTION_NAME);
	}
	
}
