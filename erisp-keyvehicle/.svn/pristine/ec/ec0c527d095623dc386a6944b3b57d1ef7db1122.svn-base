package com.gkhb.keyvehicle.service.etl.bus;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.Connection;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.util.ByteSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公交车数据对接
 * @author eddy
 *
 */
@Service("busEtlService")
public class BusEtlServiceImpl implements BusEtlService {

    @Autowired
    private BusInitDaemonThread busInitDaemonThreadService; // 初始化守护线程服务

	@Override
	public void BusEtl() {
		System.out.println("-------------------开始接收公交车数据-------------------");
		String user = "admin";
		String password = "admin";
		String url = "tcp://192.168.20.31:61616";
		String subject = "TopicGPS";
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, url);
		Connection connection;
		try {
			connection = factory.createConnection();
			connection.start();
			final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(subject);
			MessageConsumer consumer = session.createConsumer(topic);
			System.out.println("---------------connect start-----------------");
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message msg) {
					//MapMessage message = (MapMessage) msg;
					ActiveMQBytesMessage message = (ActiveMQBytesMessage) msg;
					System.out.println("----------------------getmessage start------------------");
					System.out.println(message);
					ByteSequence bs = message.getContent();
					System.out.println(bs.getData());
					try {
						//System.out.println("--订阅者一收到消息：" + new Date(message.getLong("count")));
						session.commit();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*System.out.println("=======================开始获取公交车数据=======================");
		// ConnectionFactory ：连接工厂，JMS 用它创建连接  
        ConnectionFactory connectionFactory;  
        // Connection ：JMS 客户端到JMS Provider 的连接  
        Connection connection = null;  
        // Session： 一个发送或接收消息的线程  
        Session session;  
        // Destination ：消息的目的地;消息发送给谁.  
        Destination destination;  
        // 消费者，消息接收者  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(  
                "admin",  
                "admin", 
                "tcp://192.168.20.31:61616"
                );  
        try {  
            // 构造从工厂得到连接对象  
            connection = connectionFactory.createConnection();  
            // 启动  
            connection.start();  
            // 获取操作连接  
            session = connection.createSession(Boolean.FALSE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
            destination = session.createQueue("TopicGPS");  
            consumer = session.createConsumer(destination);  
            while (true) {  
                // 设置接收者接收消息的时间，为了便于测试，这里谁定为100s  
                TextMessage message = (TextMessage) consumer.receive(100000);  
                System.out.println(message);
                if (null != message) {  
                	System.out.println("-----------开始接收数据--------------");
                    System.out.println("收到消息" + message.getText());  
                }else{
                	System.out.println("-----------没有最新数据---------------");
                	break;
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != connection)  
                    connection.close();  
            } catch (Throwable ignore) {  
            }  
        }*/  
	}
}
