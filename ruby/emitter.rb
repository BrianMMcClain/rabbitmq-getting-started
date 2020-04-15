require 'bunny'

connection = Bunny.new(:username => "user", :password => "bitnami")
connection.start

channel = connection.create_channel
queue = channel.queue('hello')

while true do
    #message = "Hello at #{Time.now}"
    message = gets.strip!
    channel.default_exchange.publish(message, routing_key: queue.name)
    puts "Published \"#{message}\" on #{queue.name}"
    sleep 1 
end