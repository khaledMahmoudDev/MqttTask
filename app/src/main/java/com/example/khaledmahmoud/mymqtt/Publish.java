package com.example.khaledmahmoud.mymqtt;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.khaledmahmoud.mymqtt.network.MyMqttConnection;
import com.example.khaledmahmoud.mymqtt.network.Mypublish;

import org.fusesource.mqtt.client.BlockingConnection;


public class Publish extends AppCompatActivity {
    EditText port; // port number
    EditText clientId; //client id
    EditText userName; // usere name
    EditText password; // password
    EditText topic;  // topic to be sent
    EditText message; // message to be sent
    Button btnBublish;  // button to create connection and send the message
    Boolean cleanSession; // clean message
    EditText keepAlive;
    private ProgressBar mLoadingIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);


        port = (EditText)findViewById(R.id.publish_port);
        clientId = (EditText)findViewById(R.id.publish_client_id);
        userName = (EditText)findViewById(R.id.publish_user_name);
        password = (EditText)findViewById(R.id.publish_password);
        topic = (EditText)findViewById(R.id.publish_topic);
        message = (EditText)findViewById(R.id.publish_message);
        btnBublish = (Button)findViewById(R.id.publish_btn);
        cleanSession = true;
        keepAlive = (EditText)findViewById(R.id.publish_keep_alive);

        //Progress Bar
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);



// listener for the button
        btnBublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create connection by creating instanse from MyMqttConnection

                MyMqttConnection myMqttConnection = new MyMqttConnection(Integer.valueOf(port.getText().toString())
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
            case R.id.publish_clean_session_checkBox:
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
            Toast.makeText(getApplicationContext(),"sending message == "+s, Toast.LENGTH_LONG).show();
        }

        //task to be done on the background
        @Override
        protected String doInBackground(MyMqttConnection... myMqttConnections) {
            String status;
            MyMqttConnection myMqttConnection = myMqttConnections[0];
            BlockingConnection connection =  myMqttConnection.createMqttConnection();

            Mypublish mypublish = new Mypublish(connection,topic.toString(),message.toString());
            status = mypublish.sendMessage();
            return status;
        }

        //before start execution we will start progress bar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
    }

}
