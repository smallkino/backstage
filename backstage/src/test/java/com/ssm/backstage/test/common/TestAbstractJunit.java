package com.ssm.backstage.test.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @ClassName: TestAbstractJunit
 * @Description: 测试基类 所有测试类都继承该类
 * @author xiaoxiaofeng
 * @date 2016年8月31日 下午3:35:34
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/config/spring/spring.xml"})
public abstract class TestAbstractJunit extends AbstractJUnit4SpringContextTests {
	protected final Logger logger = LogManager.getLogger(TestAbstractJunit.class);
}
