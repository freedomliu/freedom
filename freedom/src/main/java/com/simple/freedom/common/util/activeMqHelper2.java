package com.simple.freedom.common.util;

import javax.jms.Destination;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 通过配置文件实现activemq
 * 
 * @author liuxiangtao90
 *
 */
public class activeMqHelper2 {
	private static ApplicationContext applicationContext = null;
	private static JmsTemplate template = null;
	private static Destination destination = null;
	static {
		 applicationContext = new ClassPathXmlApplicationContext(
				"spring-jms.xml");

		 template = (JmsTemplate) applicationContext
				.getBean("jmsTemplate");

		 destination = (Destination) applicationContext
				.getBean("sessionAwareQueue");
	}

	public static void send(MessageCreator messageCreator)
	{
		System.out.println("成功发送了一条JMS消息");  
		template.send(destination,messageCreator); 
	}
}
