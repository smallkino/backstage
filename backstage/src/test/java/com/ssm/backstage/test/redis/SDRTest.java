package com.ssm.backstage.test.redis;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.ssm.backstage.test.common.TestAbstractJunit;

/**
 * 
 * @ClassName: SDRTest
 * @Description: spring-data-redis测试
 * @author xiaoxiaofeng
 * @date 2016年8月31日 下午3:37:31
 *
 */
public class SDRTest extends TestAbstractJunit{
	
	@Autowired
	private RedisTemplate jedisTemplate;
	
	@Test
	public void redisSetTest(){
		//key-value 存储
		ValueOperations<String,String> vo = jedisTemplate.opsForValue();
		vo.set("lalala", "002112");
		String str = vo.get("lalala");
		Assert.assertEquals("002112", str);
	}
}
