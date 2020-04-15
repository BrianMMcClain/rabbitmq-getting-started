require 'bunny'

# Set up the connection to the RabbitMQ server
connection = Bunny.new(:username => "user", :password => "bitnami")
connection.start

# Set up the channel and queue in RabbitMQ
channel = connection.create_channel
queue = channel.queue('hello')

# Subscribe on the queue and start listening for messages
queue.subscribe do |_delivery_info, _properties, body|
    # When a message is received, print to stdout
    puts " > Received Message: #{body}"
end

puts "Subsribed to queue: #{queue.name}"
puts "Press [Enter] to end"
puts

# End when user presses Enter
gets