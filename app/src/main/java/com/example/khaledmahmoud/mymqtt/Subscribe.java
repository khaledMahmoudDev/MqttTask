package com.example.khaledmahmoud.mymqtt;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.khaledmahmoud.mymqtt.network.MyMqttConnection;
import com.example.khaledmahmoud.mymqtt.network.Mysubscribe;

import org.fusesource.mqtt.client.BlockingConnection;

public class Subscribe extends AppCompatActivity {
    EditText port; // port number
    EditText clientId; //client id
    EditText userName; // usere name
    EditText password; // password
    EditText topic;  // topic to be sent
    TextView message; // message to be sent
    Button btnSubscribe;  // button to create connection and receive the message
    Boolean cleanSession; // clean message
    EditText keepAlive;
    private ProgressBar mLoadingIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        port = (EditText)findViewById(R.id.subscribe_port);
        clientId = (EditText)findViewById(R.id.subscribe_client_id);
        userName = (EditText)findViewById(R.id.subscribe_user_name);
        password = (EditText)findViewById(R.id.subscribe_password);
        topic = (EditText)findViewById(R.id.subscribe_topic);
        message = (TextView) findViewById(R.id.subscribe_Message);
        btnSubscribe = (Button)findViewById(R.id.subscribe_btn);
        cleanSession = true;
        keepAlive = (EditText)findViewById(R.id.subscribe_keep_alive);

        //Progress Bar
        mLoadingIndicator = (ProgressBar) findViewById(R.id.subscribe_pb_loading_indicator);


        // listener for the button
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create connection by creating instanse from MyMqttConnection
                MyMqttConnection myMqttConnection = new MyMqttConnection(
                        Integer.valueOf(port.getText().toString())
                        ,clientId.getText().toString()
                        ,cleanSession
                        ,Short.valueOf(keepAlive.getText().toString())
                        ,userName.getText().toString()
                        ,password.getText().toString());

                // creating Asynctask for the network and passing an object from MyMqttConnection
                new NetworkStuf().execute(myMqttConnection);

            }
        });


    }
    //function for listening in the check box value
    public void onCheckBoxIsClicked(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId())
        {
            case R.id.subscribe_clean_session_checkBox:
                if(checked)
                {

                    cleanSession = true;

                }
                break;
        }

    }


    // Asynctask Inner Class
    public class NetworkStuf extends AsyncTask<MyMqttConnection, Void,String>
    {

        //after execute asynctask stop progress bar
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            message.setText(s);
        }


        //task to be done on the background
        @Override
        protected String doInBackground(MyMqttConnection... myMqttConnections) {
            String result;
            MyMqttConnection myMqttConnection = myMqttConnections[0];
            BlockingConnection connection =  myMqttConnection.createMqttConnection();

            Mysubscribe mysubscribe = new Mysubscribe(topic.getText().toString(), 1,connection);
            mysubscribe.subscribeNow();
            result = mysubscribe.getsubscribedMessage();
            return result;
        }

        //before start execution we will start progress bar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
    }
}
