package com.example.homelesspeopleaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        ImageView firstPageImage = findViewById(R.id.firstPageImage);
        TextView firstPageText = findViewById(R.id.firstPageText);
        Button firstPageLoginButton = findViewById(R.id.firstPageLoginButton);
        Button firstPageSignupButton = findViewById(R.id.firstPageSignupButton);

        Intent gotoLoginPage = new Intent(FirstPage.this,LoginPage.class);
        Intent gotoSignupPage = new Intent(FirstPage.this,SignUpPage.class);

        firstPageLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(gotoLoginPage);
                finish();
            }
        });

        firstPageSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(gotoSignupPage);
            }
        });
    }
}