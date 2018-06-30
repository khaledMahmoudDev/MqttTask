package com.example.khaledmahmoud.mymqtt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseClient extends AppCompatActivity {

    // this activity to choose whether you need to publish or subscribe
    Button publish;
    Button subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_client);
        publish = (Button)findViewById(R.id.btn_publish);
        subscribe = (Button)findViewById(R.id.btn_subscribe);


        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseClient.this,Publish.class));

            }
        });
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseClient.this,Subscribe.class));

            }
        });
    }
}
