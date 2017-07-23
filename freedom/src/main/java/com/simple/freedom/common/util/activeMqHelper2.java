package com.simple.freedom.common.util;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.simple.freedom.test.beans.User;

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

	public static void send()
	{
		System.out.println("成功发送了一条JMS消息");  
		MessageCreator messageCreator=new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				ObjectMessage om=session.createObjectMessage(new User());
				om.setJMSReplyTo((Destination) applicationContext
						.getBean("sessionAwareQueueBack"));
				om.setJMSCorrelationID("121212121212");
				return om;
			}

		};
		
		template.send(destination,messageCreator);
		
		
	}
}
