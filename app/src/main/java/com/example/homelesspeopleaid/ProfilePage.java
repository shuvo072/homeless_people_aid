package com.example.homelesspeopleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;

    private String userID;

    private Button logout;
    private Button helphistory;
    private Button messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance("https://shouvo-mapapp.firebaseio.com/").getReference("Users");
        userID = firebaseUser.getUid();

        final TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        final TextView nameTextView = (TextView) findViewById(R.id.nametodisplay);
        final TextView emailTextView = (TextView) findViewById(R.id.emailtodisplay);
        final TextView phoneTextView = (TextView) findViewById(R.id.phonetodisplay);
        final TextView addressTextView = (TextView) findViewById(R.id.addresstodisplay);

        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile!=null){
                    String name = userProfile.name;
                    String email = userProfile.signupEmail;
                    String phone = userProfile.phone;
                    String address = userProfile.address;

                    greetingTextView.setText("Welcome " + name + " !");
                    nameTextView.setText(name);
                    emailTextView.setText(email);
                    phoneTextView.setText(phone);
                    addressTextView.setText(address);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });


        messages = (Button) findViewById(R.id.messagebutton);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "1. From Sheikh Hasina Shelter Project : Shelter Service Available! Come As soon as Possible";
                String str2 = "2. From Raozan Upazila Health Complex : Medical Service Available! Come As soon as Possible";
                String str3 = "3. From Raozan Police Station : Officer Available! Come As soon as Possible";
                Intent intent = new Intent(ProfilePage.this,MessagePage.class);
                intent.putExtra("message_key", str);
                intent.putExtra("message_key2", str2);
                intent.putExtra("message_key3", str3);
                startActivity(intent);
            }
        });

        helphistory = (Button) findViewById(R.id.historybutton);
        helphistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePage.this,HelpHistory.class));
            }
        });

        logout = (Button) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfilePage.this,FirstPage.class));
                finish();
            }
        });
    }
}