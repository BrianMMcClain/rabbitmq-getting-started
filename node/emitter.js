#!/usr/bin/env node

const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});
var amqp = require('amqplib/callback_api');

// Recursive function to read user input for each message to send
var readInput = function(channel, queue) {
    readline.question('> ', (msg) => {
        // Send message to RabbitMQ
        channel.sendToQueue(queue, Buffer.from(msg));
        console.log("Message Sent: %s", msg);
        readInput(channel, queue);
    });
}

// Connect to the rabbitmq server, channel, and queue
amqp.connect('amqp://localhost', function(error0, connection) {
  connection.createChannel(function(_, channel) {
    var queue = 'hello';
    channel.assertQueue(queue, {
      durable: false
    });

    // Begin reading user input
    readInput(channel, queue);
  });
});