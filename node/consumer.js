#!/usr/bin/env node

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://user:bitnami@localhost', function(error0, connection) {
  connection.createChannel(function(error1, channel) {
    
    var queue = 'hello';
    channel.assertQueue(queue, {
      durable: false
    });

    console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);
    channel.consume(queue, function(msg) {
      console.log(" > %s", msg.content.toString());
    }, {
      noAck: true
    });
  });
});