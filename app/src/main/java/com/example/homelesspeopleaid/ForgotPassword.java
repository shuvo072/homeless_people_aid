package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        TextView forgotPasswordText = findViewById(R.id.forgotpasstext);
        EditText getEmail = findViewById(R.id.forgotpassemail);
        Button resetPassword = findViewById(R.id.resetpassword);

        mAuth = FirebaseAuth.getInstance();


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }

            private void resetPassword() {
                String email = getEmail.getText().toString().trim();
                if (email.isEmpty()){
                    getEmail.setError("Email is required");
                    getEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    getEmail.setError("Please provide valid Email Address");
                    getEmail.requestFocus();
                    return;
                }

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this,"Check your Email",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ForgotPassword.this,"Wrong Email provided",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}