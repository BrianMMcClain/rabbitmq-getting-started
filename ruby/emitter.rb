require 'bunny'

# Set up the connection to the RabbitMQ server
connection = Bunny.new(:username => "user", :password => "password")
connection.start

# Set up the channel and queue in RabbitMQ
channel = connection.create_channel
queue = channel.queue('hello')

# Start reading user input
while true do
    print "> "
    message = gets.strip! # Remove newline at end of message
    # Send the message to RabbitMQ
    channel.default_exchange.publish(message, routing_key: queue.name)
    puts "Published \"#{message}\" on #{queue.name}"
end