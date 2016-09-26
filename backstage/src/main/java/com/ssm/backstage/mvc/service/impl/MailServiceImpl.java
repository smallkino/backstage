package com.ssm.backstage.mvc.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ssm.backstage.model.Mail;
import com.ssm.backstage.mvc.dao.MailDao;
import com.ssm.backstage.mvc.service.MailService;

/**
 * 
 * @ClassName: MailServiceImpl
 * @Description: 邮件业务层
 * @author xiaoxiaofeng
 * @date 2016年9月19日 下午3:22:11
 *
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
	
	/**
	 * 日志
	 */
	private static final Logger log = LogManager.getLogger(MailServiceImpl.class);
	
	@Autowired
	private MailDao mailDao;

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
	public List<Mail> getAllMail(Boolean isTime) {
		
		return mailDao.getMailAll(isTime);
	}

	/**
	 * 
	 * @Title: saveOrUpdateMail
	 * @Author：xiaoxiaofeng
	 * @Description: 保存或更新邮件
	 * @param @param mail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void saveOrUpdateMail(Mail mail) {
		//为空就是保存
		if(StringUtils.isEmpty(mail.getId())){
			mailDao.saveMail(mail);
		}else{
			//更新
			mailDao.updateMail(mail);
		}
	}
	
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
	@Override
	public Mail getMailById(String id) {
		
		try {
			return mailDao.getMailById(id);
		} catch (Exception e) {
			log.error(" 根据id获取邮件报错 id:{}",id,e);
			return null;
		}
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
		Mail mail = mailDao.getMailById(id);
		if(mail.getIsTime()){
			mailDao.deleteMailById(id);
		}else{
			throw new RuntimeException("该邮件不是定时邮件,请刷新页面再进行操作!");
		}
	}

	/**
	 * 
	 * @Title: sendMail
	 * @Author：xiaoxiaofeng
	 * @Description: 发送邮件
	 * @param @param mail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@Override
	public void sendMail(Mail mail) {
		 // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost("http://"+mail.getSendHost()+"/gmBackstage/gmSendMail"); 
        try { 
        	 // 创建参数队列      
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            this.setFormParams(mail,formparams);
            //参数转码  
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");    
            httppost.setEntity(uefEntity);   
            log.info("发送邮件请求 " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                	String json = EntityUtils.toString(entity, "UTF-8");
                	log.info("邮件发送结果: {}",json);
                	JSONObject result = JSONObject.parseObject(json);
                	int rt = result.getInteger("rt");
                	if(rt != 0){
                		throw new RuntimeException("发送邮件报错,resultJson:{}"+json);
                	}
                }  
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
        	log.error("发送邮件报错",e);
        	throw new RuntimeException(e);
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
            	log.error("关闭连接",e);
            	throw new RuntimeException(e);
            }  
        }  
	}

	/**
	 * 
	 * @Title: setFormParams
	 * @Author：xiaoxiaofeng
	 * @Description: 封装邮件请求参数
	 * @param @param mail
	 * @param @param formparams    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	private void setFormParams(Mail mail, List<NameValuePair> formparams) {
		formparams.add(new BasicNameValuePair("title", mail.getTitle()));    
        formparams.add(new BasicNameValuePair("content", mail.getContent()));
        formparams.add(new BasicNameValuePair("resList", mail.getResList()));
        formparams.add(new BasicNameValuePair("playerId", mail.getPlayerId()));
        formparams.add(new BasicNameValuePair("isSendAll", String.valueOf(mail.getIsSendAll())));
        if(!StringUtils.isEmpty(mail.getStartLevel())){
        	formparams.add(new BasicNameValuePair("startLevel", String.valueOf(mail.getStartLevel())));
        }
        if(!StringUtils.isEmpty(mail.getEndLevel())){
        	formparams.add(new BasicNameValuePair("endLevel", String.valueOf(mail.getEndLevel())));
        }
		
	}

}
