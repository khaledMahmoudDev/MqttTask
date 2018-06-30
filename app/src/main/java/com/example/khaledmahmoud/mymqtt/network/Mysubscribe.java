package com.example.khaledmahmoud.mymqtt.network;

import android.widget.ToggleButton;

import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.Message;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;


//class to subscribe
public class Mysubscribe {

    private String mTopic;
    private int qos;
    private BlockingConnection blockingConnection;

    //constructor which take topic , QOS and object from BlockingNetwork
    public Mysubscribe(String topic, int qos, BlockingConnection blockingConnection) {
        this.mTopic = topic;
        this.qos = qos;
        this.blockingConnection = blockingConnection;
    }


    //function to subscribe to a certain topic
    public void subscribeNow()
    {
        Topic[] topic = {new Topic(mTopic, QoS.AT_LEAST_ONCE)};
        try {
            byte[] qoses = blockingConnection.subscribe(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //function to get message of the passed topic
    public String getsubscribedMessage()
    {
        Message message = null;
        try {
            message = blockingConnection.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(message == null)
        {
            return "No messages were found ";
        }
        byte[]payload = message.getPayload();
        message.ack();
        try {
            blockingConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload.toString();
    }
}
