package com.example.khaledmahmoud.mymqtt.network;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.QoS;

//class to publish and send data to the broker
public class Mypublish {

    private BlockingConnection blockingConnection;
    private String topic;
    private String message ;


    //constructor which take instance  of BlockingConnection , topic and message
    public Mypublish(BlockingConnection blockingConnection, String topic, String message) {

        this.blockingConnection = blockingConnection;
        this.topic = topic;
        this.message = message;
    }


    //function to send the message
    public String sendMessage()
    {
        String status;

        try {
            blockingConnection.publish(topic,message.getBytes(),QoS.AT_LEAST_ONCE,false);
            status = "sending done";
        } catch (Exception e) {
            e.printStackTrace();
            status = "sending false";
        }
        try {
            blockingConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}
