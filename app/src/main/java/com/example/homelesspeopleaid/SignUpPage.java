package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SignUpPage extends AppCompatActivity {

    //private static final String TAG = "SignUpPage";
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        TextView signupPageText = findViewById(R.id.signuppagetext);
        TextView signupPageText2 = findViewById(R.id.signuppagetext2);
        EditText signupPageName = findViewById(R.id.signuppageentername);
        EditText signupPageEmail = findViewById(R.id.signuppageenteremail);
        EditText signupPagePassword = findViewById(R.id.signuppageenterpassword);
        EditText signupPagePhone = findViewById(R.id.signuppageenterphone);
        EditText signupPageAddress = findViewById(R.id.signuppageaddress);
        Button signupPageSignupButton = findViewById(R.id.signuppagesignupbutton);
        ProgressBar progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        signupPageSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

            private void registerUser() {
                String name = signupPageName.getText().toString().trim();
                String signupEmail = signupPageEmail.getText().toString().trim();
                String signupPassword = signupPagePassword.getText().toString().trim();
                String phone = signupPagePhone.getText().toString().trim();
                String address = signupPageAddress.getText().toString().trim();

                if (name.isEmpty()){
                    signupPageName.setError("Full name is required");
                    signupPageName.requestFocus();
                    return;
                }
                if (signupEmail.isEmpty()){
                    signupPageEmail.setError("Email is required");
                    signupPageEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(signupEmail).matches()) {
                    signupPageEmail.setError("Please provide valid Email Address");
                    signupPageEmail.requestFocus();
                    return;
                }

                if (signupPassword.isEmpty()){
                    signupPagePassword.setError("Password is required");
                    signupPagePassword.requestFocus();
                    return;
                }

                if(signupPassword.length() < 6){
                    signupPagePassword.setError("Password should be greater than 6 characters!");
                    signupPagePassword.requestFocus();
                    return;
                }

                if (phone.isEmpty()){
                    signupPagePhone.setError("Phone No. is required");
                    signupPagePhone.requestFocus();
                    return;
                }

                if (address.isEmpty()){
                    signupPageAddress.setError("Address is required");
                    signupPageAddress.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(signupEmail,signupPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    User user = new User(name,signupEmail,phone,address);

                                    FirebaseDatabase.getInstance("https://shouvo-mapapp.firebaseio.com/").getReference("Users")
                                            .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(SignUpPage.this, "User has been registered successfully",Toast.LENGTH_LONG).show();
                                                        progressBar.setVisibility(View.GONE);
                                                    }else {
                                                        Toast.makeText(SignUpPage.this,"Failed to Register! Try Again!",Toast.LENGTH_LONG).show();
                                                        progressBar.setVisibility(View.GONE);
                                                    }
                                                }
                                            });

                                    Intent tomap = new Intent(SignUpPage.this,MapsActivity.class);
                                    startActivity(tomap);
                                    finish();

                                }else {
                                    Toast.makeText(SignUpPage.this,"Failed to Register! Try Again!",Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });



    }
}