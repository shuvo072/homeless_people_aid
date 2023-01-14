package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DatasetCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset_collection);
        EditText et1 = findViewById(R.id.datasetques1);
        EditText et2 = findViewById(R.id.datasetques2);
        EditText et3 = findViewById(R.id.datasetques3);
        EditText et4 = findViewById(R.id.datasetques4);
        EditText et5 = findViewById(R.id.datasetques5);
        EditText et11 = findViewById(R.id.datasetques11);
        EditText et6 = findViewById(R.id.datasetques6);
        EditText et7 = findViewById(R.id.datasetques7);
        EditText et8 = findViewById(R.id.datasetques8);
        EditText et9 = findViewById(R.id.datasetques9);
        EditText et10 = findViewById(R.id.datasetques10);
        Button submitButton = findViewById(R.id.datasetsubmitbutton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstQues = et1.getText().toString().trim();
                String secondQues = et2.getText().toString().trim();
                String thirdQues = et3.getText().toString().trim();
                String fourthQues = et4.getText().toString().trim();
                String fifthQues = et5.getText().toString().trim();
                String eleventhQues = et11.getText().toString().trim();
                String sixthQues = et6.getText().toString().trim();
                String seventhQues = et7.getText().toString().trim();
                String eightthQues = et8.getText().toString().trim();
                String ninethQues = et9.getText().toString().trim();
                String tenthQues = et10.getText().toString().trim();

                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("How old was the person you helped?",firstQues);
                hashMap.put("Where did you find the person?",secondQues);
                hashMap.put("How was the clothing of the person?",thirdQues);
                hashMap.put("Was the person injured?",fourthQues);
                hashMap.put("Did the person look like suffering from Malnutrition?",fifthQues);
                hashMap.put("How would you rate the person's condition? (Good/Bad)",eleventhQues);
                hashMap.put("Was the person begging?",sixthQues);
                hashMap.put("Was the person violent?",seventhQues);
                hashMap.put("Was the person showing any sick mentality?",eightthQues);
                hashMap.put("Was there any makeshift home?",ninethQues);
                hashMap.put("Was there any indication of drug abuse?",tenthQues);

                FirebaseFirestore.getInstance().collection("Dataset").add(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(DatasetCollection.this,"Thanks for Providing Feedback", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(DatasetCollection.this, MapsActivity.class));
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DatasetCollection.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}