package com.ssm.backstage.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssm.backstage.model.Mail;

/**
 * 
 * @ClassName: MailDao
 * @Description: 邮件数据库操作dao
 * @author xiaoxiaofeng
 * @date 2016年9月19日 下午3:28:18
 *
 */
public interface MailDao {
	
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
	List<Mail> getMailAll(Boolean isTime);

	/**
	 * 
	 * @Title: saveMail
	 * @Author：xiaoxiaofeng
	 * @Description: 保存邮件
	 * @param @param mail    邮件
	 * @return void    返回类型
	 * @throws
	 */
	void saveMail(Mail mail);

	/**
	 * 
	 * @Title: updateMail
	 * @Author：xiaoxiaofeng
	 * @Description: 更新邮件
	 * @param @param mail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateMail(Mail mail);

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
}
