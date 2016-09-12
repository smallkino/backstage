package com.ssm.backstage.mvc.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ssm.backstage.model.User;
import com.ssm.backstage.mvc.dao.UserDao;

/**
 * 
 * @ClassName: UserDaoImpl
 * @Description: user持久层操作
 * @author xiaoxiaofeng
 * @date 2016年9月2日 下午4:27:46
 *
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {

	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);
	
	/**
	 * 集合名称
	 */
	private static final String COLLECTION_NAME = "user";
	
	/**
	 * mongoDB操作
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * redis操作
	 */
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate jedisTemplate;
	
	/**
	 * 
	 * @Title: save
	 * @Author：xiaoxiaofeng
	 * @Description: 新增用户信息
	 * @param @param user    用户对象
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void save(User user) {
		log.info(" 新增用户信息 username{},password:{} :",user.getUsername(),user.getPassword());
		mongoTemplate.save(user, COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: getUserByUserNameAndPassword
	 * @Author：xiaoxiaofeng
	 * @Description: 根据用户名和密码获取用户信息
	 * @param @param username 用户名
	 * @param @param password 密码
	 * @param @return    用户信息
	 * @return User    返回类型
	 * @throws
	 */
	@Override
	public User getUserByUserNameAndPassword(String username,String password) {
		log.info("根据用户名和密码获取用户信息 param: username:{}, password:{}",username,password);
		Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
		User user = mongoTemplate.findOne(query, User.class, COLLECTION_NAME);
		return user;
	}

	/**
	 * 
	 * @Title: getUserByUserName
	 * @Author：xiaoxiaofeng
	 * @Description: 根据用户名获取用户
	 * @param @param username 用户名
	 * @param @return    返回数量
	 * @return User    返回类型
	 * @throws
	 */
	@Override
	public User getUserByUserName(String username) {
		log.info("查询当前库中用户名的用户信息 param: username:{}",username);
		
		Query query = new Query(Criteria.where("username").is(username));
		User result = mongoTemplate.findOne(query,User.class,COLLECTION_NAME);
		return result;
	}

}
