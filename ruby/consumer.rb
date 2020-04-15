require 'bunny'

connection = Bunny.new(:username => "user", :password => "bitnami")
connection.start

channel = connection.create_channel
queue = channel.queue('hello')

queue.subscribe do |_delivery_info, _properties, body|
    puts " > Received Message: #{body}"
end

puts "Subsribed to queue: #{queue.name}"
puts "Press [Enter] to end"
puts

gets