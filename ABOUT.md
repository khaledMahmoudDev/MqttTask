used language :: java and we use android studio to built this project for android mobiles 
this app use mqtt library 
my main source to under stand it  was https://www.hivemq.com
this app has two major layouts 
first one for publish a message to the broker 
second one for subscribe to the broker and get message from it

first we create a connection to our local host using port number and client id
and more optional things like (user name , password , keep alive , clean seassion )


when our connection is established 
publish : we will pass our topic and message to the broker 
subscribe : we get the meassages from the broker with the topic which we pass

this was my first time to deal with such this library 
first i study it will using these articles :
https://www.hivemq.com/blog/mqtt-essentials-part-1-introducing-mqtt
https://www.hivemq.com/blog/mqtt-essentials-part2-publish-subscribe
https://www.hivemq.com/blog/mqtt-essentials-part-3-client-broker-connection-establishment
https://www.hivemq.com/blog/mqtt-essentials-part-4-mqtt-publish-subscribe-unsubscribe
untill the 8th article 


then i used this repo to implement my code https://github.com/fusesource/mqtt-client
