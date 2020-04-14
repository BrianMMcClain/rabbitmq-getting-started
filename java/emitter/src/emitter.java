package com.github.brianmmcclain.rabbitmqgettingstarted;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class emitter {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("user");
		factory.setPassword("bitnami");
		try (Connection connection = factory.newConnection();
			Channel channel = connection.createChannel()) {
				channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				
				while (true) {
					System.out.print("> ");
					String message = System.console().readLine();
					channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
					System.out.println("Sent '" + message + "'");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
