package com.ssm.backstage.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ssm.backstage.model.Mail;
import com.ssm.backstage.model.ReturnDataFormat;
import com.ssm.backstage.mvc.service.MailService;

/**
 * 
 * @ClassName: MailController
 * @Description: 邮件管理
 * @author xiaoxiaofeng
 * @date 2016年9月18日 上午10:39:23
 *
 */
@Controller
@RequestMapping("/mail/")
public class MailController {
	
	/**
	 * 日志
	 */
	private static final Logger logger = LogManager.getLogger(MailController.class);
	
	@Autowired
	private MailService mailService;
	
	@Value("${game.ip}")
	private String gameIp;
	@Value("${game.port}")
	private String gamePort;
	
	/**
	 * 
	 * @Title: index
	 * @Author：xiaoxiaofeng
	 * @Description: 邮件管理首页
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ModelAndView    返回类型
	 * @throws
	 */
	@RequestMapping("index.html")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
    		ModelAndView modelAndView = new ModelAndView("/game/mail");
		List<Mail> mailList = mailService.getAllMail(false);
		List<Mail> timeMailList = mailService.getAllMail(true);
		modelAndView.addObject("mailList", mailList);
		modelAndView.addObject("timeMailList", timeMailList);
		modelAndView.addObject("gameHost", gameIp+":"+gamePort);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateMail
	 * @Author：xiaoxiaofeng
	 * @Description: 保存或更新邮件
	 * @param @param mail 邮件
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public ReturnDataFormat<Boolean> saveOrUpdateMail(Mail mail,HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			mailService.saveOrUpdateMail(mail);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("保存或更新邮件出错:{}",JSONObject.toJSONString(mail),e);
			result.setSuccess(false);
			result.setMessage("系统异常");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: getMailById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id查询邮件
	 * @param @param id 
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Mail>    返回类型
	 * @throws
	 */
	@RequestMapping("getMailById")
	@ResponseBody
	public ReturnDataFormat<Mail> getMailById(String id,HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Mail> result = new ReturnDataFormat<Mail>();
		Mail mail = mailService.getMailById(id);
		if(mail != null){
			result.setSuccess(true);
		}
		result.setData(mail);
		return result;
	}
	
	/**
	 * 
	 * @Title: deleteMailById
	 * @Author：xiaoxiaofeng
	 * @Description: 根据id删除邮件
	 * @param @param id
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("deleteById")
	@ResponseBody
	public ReturnDataFormat<Boolean> deleteMailById(String id,HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			mailService.deleteMailById(id);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除邮件出错:{}",id,e);
			result.setSuccess(false);
			result.setMessage("系统异常");
		}
		return result;
	}
	
	/**
	 * 
	 * @Title: sendMailNow
	 * @Author：xiaoxiaofeng
	 * @Description: 立即发送邮件
	 * @param @param mail 邮件
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件
	 * @return ReturnDataFormat<Boolean>    返回类型
	 * @throws
	 */
	@RequestMapping("sendMailNow")
	@ResponseBody
	public ReturnDataFormat<Boolean> sendMailNow(Mail mail,HttpServletRequest request, HttpServletResponse response){
		ReturnDataFormat<Boolean> result = new ReturnDataFormat<Boolean>();
		try {
			mailService.sendMail(mail);
			mailService.saveOrUpdateMail(mail);
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("保存或发送邮件出错:{}",JSONObject.toJSONString(mail),e);
			result.setSuccess(false);
			result.setMessage("系统异常");
		}
		return result;
	}
	
}
