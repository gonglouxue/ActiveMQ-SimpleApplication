package com.macroflag.activemq.mqmessage;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			//取出消息
			ActiveMQTextMessage activeMQTextMessage = (ActiveMQTextMessage) message;
			String text = activeMQTextMessage.getText();
			//消费
			System.out.println("监听到的消息: " + text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
