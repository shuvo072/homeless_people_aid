package com.example.homelesspeopleaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessagePage extends AppCompatActivity {

    private TextView receive_message;
    private TextView receive_message_2;
    private TextView receive_message_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_page);

        receive_message = (TextView) findViewById(R.id.messagetext);
        receive_message_2 = (TextView) findViewById(R.id.messagetext2);
        receive_message_3 = (TextView) findViewById(R.id.messagetext3);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
        String str2 = intent.getStringExtra("message_key2");
        String str3 = intent.getStringExtra("message_key3");
        receive_message.setText(str);
        receive_message_2.setText(str2);
        receive_message_3.setText(str3);
    }
}