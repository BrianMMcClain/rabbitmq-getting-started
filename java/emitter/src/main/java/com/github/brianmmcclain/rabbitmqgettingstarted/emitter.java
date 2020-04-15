package com.github.brianmmcclain.rabbitmqgettingstarted;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import com.rabbitmq.client.Channel;

public class emitter {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) {
		
		// Connect to the RabbitMQ server
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("user");
		factory.setPassword("password");
		try (Connection connection = factory.newConnection();
			
			// Create a channel and connect it to the queue
			Channel channel = connection.createChannel()) {
				channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
				// Begin reading user input
				while (true) {
					System.out.print("> ");
					String message = System.console().readLine();
					
					// Send the message to the queue
					channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
					System.out.println("Sent '" + message + "'");
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
