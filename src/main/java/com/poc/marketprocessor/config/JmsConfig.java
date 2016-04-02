package com.poc.marketprocessor.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.poc.marketprocessor.jms.TradeQueue;

@Configuration
public class JmsConfig {
	
	private static final String BROKER_URL = "tcp://localhost:61616";

	@Bean
	public BrokerService embeddedActiveMQInitializer() throws Exception{
		BrokerService broker = new BrokerService();
		 
		broker.addConnector(BROKER_URL);
		broker.start();
		
		return broker;
	}
	 
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());  
		jmsTemplate.setDefaultDestinationName(TradeQueue.QUEUE_NAME);
		return jmsTemplate;
	}

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(BROKER_URL);
		return activeMQConnectionFactory;
	}

	@Bean
	public TradeQueue consumer() { 
		return new TradeQueue();
	}

	@Bean
	public DefaultMessageListenerContainer listeners() {
		DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
		messageListenerContainer.setConnectionFactory(connectionFactory());
		messageListenerContainer.setDestination(queue());
		messageListenerContainer.setMessageListener(consumer());
		messageListenerContainer.setConcurrentConsumers(50);
		messageListenerContainer.setReceiveTimeout(10000);
		messageListenerContainer.setIdleConsumerLimit(5);
		messageListenerContainer.setIdleTaskExecutionLimit(10);
		return messageListenerContainer;
	}
	
	@Bean
	public ActiveMQQueue queue(){
		return new ActiveMQQueue(TradeQueue.QUEUE_NAME);
	}
	


	
}
