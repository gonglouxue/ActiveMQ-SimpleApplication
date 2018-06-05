package com.macroflag.activemq.controller;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyMessageController {

	@Resource
	private JmsTemplate jmsTemplate;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping("/myMessage")
	public String myMessage() {
		return "/myMessage";
	}

	/**
	 * 接收页面参数,发送消息到消息中心
	 * 
	 * @param message
	 */
	@RequestMapping("/sendMyMessage")
	@ResponseBody
	public void sendMyMessage(final String message) {
		System.out.println("页面发送的消息:" + message);
		// 将消息发送到MQ中
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session
						.createTextMessage((message != null && message != "") ? message
								: "Hello!ActiveMQ!");
				return textMessage;
			}
		});
	}
}
