package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class FeedbackPage extends AppCompatActivity {
    String url = "https://homeless-people-aid.herokuapp.com/predict";
    private AlertDialog.Builder alertDialogueBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        EditText et1 = findViewById(R.id.feedbackques1);
        EditText et2 = findViewById(R.id.feedbackques2);
        EditText et3 = findViewById(R.id.feedbackques3);
        EditText et4 = findViewById(R.id.feedbackques4);
        EditText et5 = findViewById(R.id.feedbackques5);
        //EditText et11 = findViewById(R.id.feedbackques11);
        EditText et6 = findViewById(R.id.feedbackques6);
        EditText et7 = findViewById(R.id.feedbackques7);
        EditText et8 = findViewById(R.id.feedbackques8);
        EditText et9 = findViewById(R.id.feedbackques9);
        EditText et10 = findViewById(R.id.feedbackques10);
        Button submitButton = findViewById(R.id.submitbutton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstQues = et1.getText().toString().trim();
                String secondQues = et2.getText().toString().trim();
                String thirdQues = et3.getText().toString().trim();
                String fourthQues = et4.getText().toString().trim();
                String fifthQues = et5.getText().toString().trim();
                //String eleventhQues = et11.getText().toString().trim();
                String sixthQues = et6.getText().toString().trim();
                String seventhQues = et7.getText().toString().trim();
                String eightthQues = et8.getText().toString().trim();
                String ninethQues = et9.getText().toString().trim();
                String tenthQues = et10.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("Homeless");
                                    if(data.equals("1")){
                                        alertDialogueBuilder = new AlertDialog.Builder(FeedbackPage.this);
                                        alertDialogueBuilder.setTitle("Homeless Predictor");
                                        alertDialogueBuilder.setMessage("According to the information you provided the person is Homeless!");
                                        alertDialogueBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });

                                        AlertDialog alertDialog = alertDialogueBuilder.create();
                                        alertDialog.show();

                                    }else{
                                        alertDialogueBuilder = new AlertDialog.Builder(FeedbackPage.this);
                                        alertDialogueBuilder.setTitle("Homeless Predictor");
                                        alertDialogueBuilder.setMessage("According to the information you provided the person is Not Homeless!");
                                        alertDialogueBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.cancel();
                                            }
                                        });

                                        AlertDialog alertDialog = alertDialogueBuilder.create();
                                        alertDialog.show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(FeedbackPage.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){

                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("age",firstQues);
                        params.put("place",secondQues);
                        params.put("clothing",thirdQues);
                        params.put("injured",fourthQues);
                        params.put("suffer_from_malnutrition",fifthQues);
                        params.put("begging",sixthQues);
                        params.put("violent",seventhQues);
                        params.put("sick_mentality",eightthQues);
                        params.put("makeshift_home",ninethQues);
                        params.put("drug",tenthQues);

                        return params;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(FeedbackPage.this);
                queue.add(stringRequest);

//                HashMap<String,Object> hashMap = new HashMap<>();
//                hashMap.put("How old was the person you helped?",firstQues);
//                hashMap.put("Where did you find the person?",secondQues);
//                hashMap.put("How was the clothing of the person?",thirdQues);
//                hashMap.put("Was the person injured?",fourthQues);
//                hashMap.put("Did the person look like suffering from Malnutrition?",fifthQues);
//                hashMap.put("How would you rate the person's condition? (Good/Bad)",eleventhQues);
//                hashMap.put("Was the person begging?",sixthQues);
//                hashMap.put("Was the person violent?",seventhQues);
//                hashMap.put("Was the person showing any sick mentality?",eightthQues);
//                hashMap.put("Was there any makeshift home?",ninethQues);
//                hashMap.put("Was there any indication of drug abuse?",tenthQues);
//
//                FirebaseFirestore.getInstance().collection("Feedbacks").add(hashMap)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Toast.makeText(FeedbackPage.this,"Thanks for Providing Feedback",Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(FeedbackPage.this, MapsActivity.class));
//                                finish();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(FeedbackPage.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
//                            }
//                        });

            }
        });

    }

}