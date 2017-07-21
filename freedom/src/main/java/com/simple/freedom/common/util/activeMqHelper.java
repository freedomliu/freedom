package com.simple.freedom.common.util;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 纯java代码实现activemq 
 * @author liuxiangtao90
 *
 */
public class activeMqHelper {

	private static ConnectionFactory connectionFactory = null;
	private static Connection connection = null;
	private static Session session = null;
	private static Destination destination = null;
	private static MessageConsumer consumer = null;
	// 消费者
	static {
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("smsQueue");
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					try {
						ObjectMessage t = (ObjectMessage) message;
						System.out.println(t);

					} catch (Exception e) {
					}
				}
			});

		} catch (Exception e) {

		}
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @throws Exception
	 */
	public static void setMessage(String queueName,Serializable message) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		Connection connection = null;
		Session session=null;
		try { // 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			Destination destination = session.createQueue(queueName);
			// 得到消息生成者【发送者】
			MessageProducer producer = session.createProducer(destination);
			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			producer.send(session.createObjectMessage(message));
			session.commit();
		} catch (Exception e) {

		} finally {
			try {
				if (null != session)
					session.close();
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}
}
