package com.ssm.backstage.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssm.backstage.model.Mail;
import com.ssm.backstage.mvc.service.MailService;

/**
 * 
 * @ClassName: MailScheduled
 * @Description: 邮件定时任务
 * @author xiaoxiaofeng
 * @date 2016年9月21日 下午3:15:05
 *
 */
@Component
public class MailScheduled {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(MailScheduled.class);
	
	@Autowired
	private MailService mailService;
	
	private int i =0 ;
	
	/**
	 * 
	 * @Title: sendTask
	 * @Author：xiaoxiaofeng
	 * @Description: 发送定时任务
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Scheduled(cron="0/30 * * * * ?")
	public void sendTask(){
		//查询所有定时邮件
		List<Mail> mailList = mailService.getAllMail(true);
		for(Mail mail: mailList){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = mail.getSendDate();
			Date sendDate = null;
			try {
				sendDate = formatter.parse(dateStr);
				if(System.currentTimeMillis() >= sendDate.getTime()){
					log.info("发送定时邮件:mailId:{},mailName:{}",mail.getId(),mail.getName());
					//定时邮件发送后保存
					mailService.sendMail(mail);
					mail.setIsTime(false);
					mailService.saveOrUpdateMail(mail);
				}
			} catch (ParseException e) {
				log.error("时间格式错误.dateStr:{}",dateStr,e);
			}
		}
	}
}
