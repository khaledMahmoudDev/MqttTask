package com.example.khaledmahmoud.mymqtt.network;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import java.net.URISyntaxException;
public class MyMqttConnection {



    private static String host; //host name
    private static int portNo;  // port number
    private static String clientId = null;//client id
    private static boolean cleanSeassion = false;  //clean seassion
    private static short keepAlive = 60;  //time to keep alive
    private static String username = null;  // iser name
    private static String password = null;// password



    //constructor fo MyMqttConnection
    public MyMqttConnection( int portNo,String clientId, boolean cleanSeassion, short keepAlive, String username, String password) {
        this.host = "localhost";
        this.portNo = portNo;
        this.clientId = clientId;
        this.cleanSeassion = cleanSeassion;
        this.keepAlive = keepAlive;
        this.username = username;
        this.password = password;
    }




    //function to create connection to the local broker  and return object of Blocking Connection to pass it to publish or subscribe
    public  BlockingConnection createMqttConnection()
    {
        MQTT mqtt = new MQTT();
        try {
            mqtt.setHost("localhost",portNo);
            mqtt.setClientId(username);
            mqtt.setCleanSession(cleanSeassion);
            mqtt.setKeepAlive(keepAlive);
            mqtt.setUserName(username);
            mqtt.setPassword(password);
            mqtt.setConnectAttemptsMax(10L);
            mqtt.setReconnectAttemptsMax(-1);
            mqtt.setReconnectDelay(100L);
            mqtt.setReconnectDelayMax(30000L);
            mqtt.setReconnectBackOffMultiplier(2);


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        //create instance of BlockingConnecton
        BlockingConnection blockingConnection = mqtt.blockingConnection();
        try {
            blockingConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (blockingConnection.isConnected())
        {

            return blockingConnection;
        }else
        {
            return null;

        }
    }



}
