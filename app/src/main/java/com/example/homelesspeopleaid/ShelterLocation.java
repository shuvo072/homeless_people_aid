package com.example.homelesspeopleaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ShelterLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_location);

        Button shelterlocation1 = findViewById(R.id.shelterbutton1);
        Button shelterlocation2 = findViewById(R.id.shelterbutton2);
        ImageView call1 = findViewById(R.id.imageViewPhoneNo1);
        ImageView call2 = findViewById(R.id.imageViewPhoneNo2);
        Button sheltergethelp1 = findViewById(R.id.shelterhelpbutton1);
        Button sheltergethelp2 = findViewById(R.id.shelterhelpbutton2);

        sheltergethelp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShelterLocation.this, "Check the messages in your dashboard", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ShelterLocation.this,ProfilePage.class));
            }
        });
//        Button shelterhelp1 = findViewById(R.id.shelterhelpbutton1);
//        Button shelterhelp2 = findViewById(R.id.shelterhelpbutton2);

//        shelterhelp1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ShelterLocation.this, "Check the messages in your dashboard", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(ShelterLocation.this,ProfilePage.class));
//                finish();
//            }
//        });


        shelterlocation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShelterLocation.this,ShelterLocationDirection1.class));
            }
        });

        sheltergethelp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShelterLocation.this, "Check the messages in your dashboard", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ShelterLocation.this,ProfilePage.class));
            }
        });

//        shelterhelp2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ShelterLocation.this, "Check the messages in your dashboard", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(ShelterLocation.this,ProfilePage.class));
//                finish();
//            }
//        });

//        shelterlocation2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ShelterLocation.this,ShelterLocationDirection2.class));
//            }
//        });
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String uri = "01685138790";
                intent.setData(Uri.parse("tel: "+uri));
                startActivity(intent);
            }
        });
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String uri = "01858795079";
                intent.setData(Uri.parse("tel: "+uri));
                startActivity(intent);
            }
        });
    }
}