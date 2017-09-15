package com.CloudEureka.controller;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.rabbitmq.client.Channel;

//@Configuration
@PropertySource("classpath:mq.properties")
public class MqConfig {
	
	/**mq Ip*/
	@Value(value = "${mq.ip}")
	protected String rabbitIp;
	
	/**mq port*/
	@Value(value = "${mq.port}")
	protected String rabbitPort;
	
	/**mq username*/
	@Value(value = "${mq.username}")
	protected String userName;
	/**mq passwd*/
	@Value(value = "${mq.passwd}")
	protected String paswd;
	
	@Value(value = "${mq.sender.Consumers}")
	private String rabbitConsumers;
	
	/**砸但 queue 名稱*/
	@Value(value = "${mq.queue.name.breakegg}")
	private String breakEggQueueName;

	@Bean
	protected CachingConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitIp, Integer.valueOf(rabbitPort));
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(paswd);
		connectionFactory.setPublisherConfirms(Boolean.TRUE);
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}
	
	@Bean
	public Queue eggBreakQueue() {
		Queue q = new Queue(breakEggQueueName, true);//支援持久化
		return q;
	}
	
	@Bean
	public SimpleMessageListenerContainer noticeListenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setConcurrentConsumers(Integer.valueOf(rabbitConsumers));
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setQueues(eggBreakQueue());//定義Queue
		
		container.setReceiveTimeout(10000L);
		
		container.setMessageListener(new ChannelAwareMessageListener(){
			@Override
			public void onMessage(Message message, Channel channel) throws Exception {
				try {
					String msg = new String(message.getBody(), "UTF-8");
					
					System.out.println("on Message : " + msg);
					
					
					channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
					
				} catch(Exception e1) {
					try {
						channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
					} catch(Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		return container;
	}

}
