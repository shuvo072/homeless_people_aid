package com.example.homelesspeopleaid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MapsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button exploreButton = findViewById(R.id.explore);
        Button profileButton = findViewById(R.id.profile);
        Button feedbackButton = findViewById(R.id.feedback);
        Button emergencyContactButton = findViewById(R.id.emergencycontact);
        Button complaintButton = findViewById(R.id.complain);

        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, ExploreToMapPage.class));
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, ProfilePage.class));
            }
        });

        emergencyContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this,EmergencyContact.class));
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, FeedbackPage.class));
            }
        });

        complaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapsActivity.this, ComplaintPage.class));
            }
        });
    }
}