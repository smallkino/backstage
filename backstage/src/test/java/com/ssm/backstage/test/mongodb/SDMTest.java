package com.ssm.backstage.test.mongodb;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DBCollection;
import com.ssm.backstage.test.common.TestAbstractJunit;

/**
 * 
 * @ClassName: SDMTest
 * @Description: mongodb测试
 * @author xiaoxiaofeng
 * @date 2016年9月1日 上午10:19:37
 *
 */
public class SDMTest extends TestAbstractJunit{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void saveTest(){
		mongoTemplate.save("{'_id':1120,'name':'123'}", "sdmtest");
	}
	
}
