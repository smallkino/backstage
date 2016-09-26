package com.ssm.backstage.mvc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.ssm.backstage.model.Mail;
import com.ssm.backstage.mvc.dao.MailDao;

/**
 * 
 * @ClassName: MailDaoImpl
 * @Description: 邮件数据库实现
 * @author xiaoxiaofeng
 * @date 2016年9月19日 下午3:40:42
 *
 */
@Component("mailDao")
public class MailDaoImpl implements MailDao {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(MailDaoImpl.class);
	
	/**
	 * 集合名称
	 */
	private static final String COLLECTION_NAME = "mail";
	
	/**
	 * mongoDB操作
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * 
	 * @Title: getAllMail
	 * @Author：xiaoxiaofeng
	 * @Description: 查询所有已保存邮件
	 * @param @return    设定文件
	 * @param isTime null查所有 true查定时 false查保存
	 * @return ArrayList<Mail>    返回类型
	 * @throws
	 */
	@Override
	public List<Mail> getMailAll(Boolean isTime) {
		List<Mail> result = new ArrayList<Mail>();
		try {
			if(isTime == null){
				result.addAll(mongoTemplate.findAll(Mail.class, COLLECTION_NAME));
			}else if(isTime){
				Query query = new Query(Criteria.where("isTime").is(true));
				result.addAll(mongoTemplate.find(query, Mail.class, COLLECTION_NAME));
			}else{
				Query query = new Query(Criteria.where("isTime").is(false));
				result.addAll(mongoTemplate.find(query, Mail.class, COLLECTION_NAME));
			}
		} catch (Exception e) {
			log.error("查询邮件报错 isTime:{}",isTime,e);
		}
		return result;
	}

	/**
	 * 
	 * @Title: saveMail
	 * @Author：xiaoxiaofeng
	 * @Description: 保存邮件
	 * @param @param mail    邮件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void saveMail(Mail mail) {
		mongoTemplate.save(mail, COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: updateMail
	 * @Author：xiaoxiaofeng
	 * @Description: 更新邮件
	 * @param @param mail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void updateMail(Mail mail) {
		log.info("更新邮件:id {}",mail.getId());
		Query query = new Query(Criteria.where("_id").is(mail.getId()));
		Update update = new Update();
		update.set("title", mail.getTitle());
		update.set("name", mail.getName());
		update.set("content", mail.getContent());
		update.set("resList", mail.getResList());
		update.set("playerId", mail.getPlayerId());
		update.set("isSendAll", mail.getIsSendAll());
		update.set("startLevel", mail.getStartLevel());
		update.set("endLevel", mail.getEndLevel());
		update.set("sendHost", mail.getSendHost());
		update.set("isTime", mail.getIsTime());
		mongoTemplate.updateFirst(query, update, COLLECTION_NAME);
	}
	
	/**
	 * 
	 * @Title: getMailById
	 * @Author：xiaoxiaofeng
	 * @Description: 更具id查询邮件
	 * @param @param id
	 * @param @return    邮件
	 * @return Mail    返回类型
	 * @throws
	 */
	@Override
	public Mail getMailById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query,Mail.class,COLLECTION_NAME);
	}

	/**
	 * 
	 * @Title: deleteMailById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id删除邮件
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void deleteMailById(String id) {
		log.info("删除邮件 id:{}",id);
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, COLLECTION_NAME);
	}

}
