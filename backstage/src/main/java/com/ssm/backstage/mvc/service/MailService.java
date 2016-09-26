package com.ssm.backstage.mvc.service;

import java.util.ArrayList;
import java.util.List;

import com.ssm.backstage.model.Mail;

/**
 * 
 * @ClassName: MailService
 * @Description: 邮件业务层
 * @author xiaoxiaofeng
 * @date 2016年9月19日 下午2:44:22
 *
 */
public interface MailService {
	
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
	List<Mail> getAllMail(Boolean isTime);

	/**
	 * 
	 * @Title: saveOrUpdateMail
	 * @Author：xiaoxiaofeng
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param mail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveOrUpdateMail(Mail mail);

	/**
	 * 
	 * @Title: getMailById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id获取邮件
	 * @param @param id
	 * @param @return    邮件
	 * @return Mail    返回类型
	 * @throws
	 */
	Mail getMailById(String id);

	/**
	 * 
	 * @Title: deleteMailById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id删除邮件
	 * @param @param id    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteMailById(String id);

	/**
	 * 
	 * @Title: sendMail
	 * @Author：xiaoxiaofeng
	 * @Description: 发送邮件
	 * @param @param mail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void sendMail(Mail mail);
}
