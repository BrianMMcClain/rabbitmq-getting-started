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

		try {
			Connection connection = factory.newConnection();
				
			// Create a channel
			Channel channel = connection.createChannel();
			
			// Create and connect to the queue. The arguments, on order, are:
			// queue - Name of the queue we are connecting on
			// durable - If true, RabbitMQ will write messages to disk
			// exclusive - If true, only this connection may connect to the queue
			// autoDelete - If true, the queue will be deleted when it is no longer in use
			// The final argument takes a Map of additional optional arguments
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
