package ee.gulho.assignment.account;

import ch.qos.logback.classic.net.SimpleSocketServer;
import ee.gulho.assignment.account.service.Receiver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "ee.gulho.assignment.account.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class AccountApplication {
	static final String queueName = "tuum";

	public static void main(String[] args) {

		SpringApplication.run(AccountApplication.class, args);
	}

//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//											 MessageListenerAdapter listenerAdapter) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
////		container.setQueueNames(queueName);
//		container.setMessageListener(listenerAdapter);
//		return container;
//	}
//
//	@Bean
//	MessageListenerAdapter listenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver, "receiveMessage");
//	}



}
