package com.ssm.backstage.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @ClassName: Mail
 * @Description: 邮件model
 * @author xiaoxiaofeng
 * @date 2016年9月19日 下午2:46:06
 *
 */
@Document(collection="mail")
public class Mail implements Serializable{
	
	/**
	* @Fields 序列化
	*/ 
	private static final long serialVersionUID = 5751148458207833315L;
	
	/**
	 * id
	 */
	@Id
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 附件
	 */
	private String resList;
	/**
	 * 发送时间
	 */
	private String sendDate;
	
	/**
	 * 玩家id
	 */
	private String playerId;
	
	/**
	 * 是否是群发
	 */
	private boolean isSendAll;
	
	/**
	 * 等级开始范围
	 */
	private Integer startLevel;
	
	/**
	 * 等级结束范围
	 */
	private Integer endLevel;
	
	/**
	 * 筛选服务器
	 */
	private String sendHost;
	
	/**
	 * 是否是定时邮件
	 */
	private boolean isTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResList() {
		return resList;
	}

	public void setResList(String resList) {
		this.resList = resList;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public boolean getIsSendAll() {
		return isSendAll;
	}

	public void setIsSendAll(boolean isSendAll) {
		this.isSendAll = isSendAll;
	}

	public Integer getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(Integer startLevel) {
		this.startLevel = startLevel;
	}

	public Integer getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	public String getSendHost() {
		return sendHost;
	}

	public void setSendHost(String sendHost) {
		this.sendHost = sendHost;
	}

	public boolean getIsTime() {
		return isTime;
	}

	public void setIsTime(boolean isTime) {
		this.isTime = isTime;
	}
	
	
}
