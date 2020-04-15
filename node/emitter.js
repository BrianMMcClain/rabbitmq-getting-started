#!/usr/bin/env node

const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});


var amqp = require('amqplib/callback_api');

var readInput = function(channel, queue) {
    readline.question('> ', (msg) => {
        channel.sendToQueue(queue, Buffer.from(msg));
        console.log("Message Sent: %s", msg);
        readInput(channel, queue);
    });
}

amqp.connect('amqp://user:bitnami@localhost', function(error0, connection) {
  connection.createChannel(function(error1, channel) {
    if (error1) {
      throw error1;
    }
    var queue = 'hello';
    channel.assertQueue(queue, {
      durable: false
    });

    readInput(channel, queue);
  });
});