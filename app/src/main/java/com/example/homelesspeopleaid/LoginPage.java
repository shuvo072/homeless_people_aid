package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView loginPageText = findViewById(R.id.loginpagetext);
        TextView loginPageText2 = findViewById(R.id.loginpagetext2);
        EditText loginPageEmail = findViewById(R.id.loginpageenteremail);
        EditText loginPagePassword = findViewById(R.id.loginpageenterpassword);
        Button loginPageLoginButton = findViewById(R.id.loginpageloginbutton);
        TextView forgotPassword = findViewById(R.id.forgotpassword);
        TextView toSignupPage = findViewById(R.id.tosignuppage);
        ProgressBar progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        loginPageLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }

            private void loginUser() {
                String loginEmail = loginPageEmail.getText().toString().trim();
                String loginPassword = loginPagePassword.getText().toString().trim();

                if (loginEmail.isEmpty()){
                    loginPageEmail.setError("Email is required");
                    loginPageEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(loginEmail).matches()) {
                    loginPageEmail.setError("Please provide valid Email Address");
                    loginPageEmail.requestFocus();
                    return;
                }

                if (loginPassword.isEmpty()){
                    loginPagePassword.setError("Password is required");
                    loginPagePassword.requestFocus();
                    return;
                }

                if(loginPassword.length() < 6){
                    loginPagePassword.setError("Password should be greater than 6 characters!");
                    loginPagePassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(LoginPage.this,MapsActivity.class));
                            finish();
                        }else {
                            Toast.makeText(LoginPage.this,"Failed to Login! Please check your credentials",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

    }

    public void tosignup(View view) {
        Intent toSignup = new Intent(LoginPage.this,SignUpPage.class);
        startActivity(toSignup);
    }

    public void toforgetpass(View view) {
        Intent toForgotPassword = new Intent(LoginPage.this,ForgotPassword.class);
        startActivity(toForgotPassword);
    }
}