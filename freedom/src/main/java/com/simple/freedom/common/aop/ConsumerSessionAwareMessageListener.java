package com.simple.freedom.common.aop;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.listener.SessionAwareMessageListener;

import com.simple.freedom.test.beans.User;

public class ConsumerSessionAwareMessageListener implements
		SessionAwareMessageListener<ObjectMessage> {

	private Destination destination;

	public void onMessage(ObjectMessage message, Session session)
			throws JMSException {
		System.out.println("收到一条消息");
		System.out.println("消息内容是：" + message.toString());
		
		Destination destination1= message.getJMSReplyTo();
		MessageProducer producer = session.createProducer(destination1);
		User user=new User();
		Message textMessage = session
				.createObjectMessage(user);
		
		textMessage.setJMSCorrelationID(message.getJMSCorrelationID());
		textMessage.setJMSReplyTo(destination);
		producer.send(textMessage);
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