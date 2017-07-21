package com.simple.freedom.common.aop;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.listener.SessionAwareMessageListener;

public class ConsumerSessionAwareMessageListener implements
		SessionAwareMessageListener<ObjectMessage> {

	private Destination destination;

	public void onMessage(ObjectMessage message, Session session)
			throws JMSException {
		System.out.println("收到一条消息");
		System.out.println("消息内容是：" + message.toString());
		/*MessageProducer producer = session.createProducer(destination);
		Message textMessage = session
				.createTextMessage("ConsumerSessionAwareMessageListener。。。");
		producer.send(textMessage);*/
	}

	public Destination getDestination() 
	{  
		return destination;  
	}	
	
	public void setDestination(Destination destination) 
	{
		this.destination = destination;
	}
}