package com.github.brianmmcclain.rabbitmqgettingstarted;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class consumer {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("user");
		factory.setPassword("bitnami");

		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			System.out.println("Waiting for messages. To exit press CTRL+C\n");

			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" > " + message);
			};
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
