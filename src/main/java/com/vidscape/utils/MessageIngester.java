package com.vidscape.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
public class MessageIngester {
	
	public void sendMessageToQueu(String Message, String ConnString, String EntityType, String Action, String QueueName) throws JMSException {
		try {
			final ConnectionFactory connFactory = new ActiveMQConnectionFactory(ConnString);
			final Connection conn = connFactory.createConnection();
			final Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			final Destination dest = sess.createQueue(QueueName);
			final MessageProducer prod = sess.createProducer(dest);
			Message msg = sess.createTextMessage(Message);
			msg.setObjectProperty("EntityType", EntityType) ;
			msg.setObjectProperty("Action", Action);
			prod.send(msg);
			conn.close();
			
		}catch (JMSException e) {
				throw e;
		}
		catch (Exception e) {
			throw e;
		}
		
	}

}
