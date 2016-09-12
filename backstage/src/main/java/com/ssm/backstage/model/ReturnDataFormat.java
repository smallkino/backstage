package com.ssm.backstage.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: ReturnDataFormat<T>
 * @Description: js请求返回统一容器
 * @author xiaoxiaofeng
 * @date 2016年9月7日 上午10:56:53
 *
 * @param <T> data类型
 */
public class ReturnDataFormat<T> implements Serializable {

	/**
	* 序列化
	*/ 
	private static final long serialVersionUID = -1250179936457001961L;
	
	/**
	 * true 成功; false 失败
	 */
	private boolean success;
	
	/**
	 * data 返回数据
	 */
	private T data;
	
	/**
	 * 消息
	 */
	private String message;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
