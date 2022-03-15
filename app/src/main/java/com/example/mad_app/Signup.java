package com.example.mad_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {

    Button signup, alreadyMember;
    TextInputLayout name, username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = findViewById(R.id.signup_btn);
        alreadyMember = findViewById(R.id.already_member);
        name = findViewById(R.id.name_signup);
        username = findViewById(R.id.username_signup);
        email = findViewById(R.id.email_signup);
        password = findViewById(R.id.password_signup);

        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });
    }
}