#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

// Connect to the rabbitmq server, channel, and queue
amqp.connect('amqp://localhost', function(error0, connection) {
  connection.createChannel(function(error1, channel) {
    var queue = 'hello';
    channel.assertQueue(queue, {
      durable: false
    });

    console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);
    // Begin listening on the queue for any messages
    channel.consume(queue, function(msg) {
      // When a message is received, log it to the console
      console.log(" > %s", msg.content.toString());
    }, {
      noAck: true
    });
  });
});