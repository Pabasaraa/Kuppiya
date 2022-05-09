package com.example.mad_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Signup extends AppCompatActivity {

    Button signup, alreadyMember;
    TextInputLayout name, username, email, password;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("users");

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
                finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateName() | !validatePassword() | !validateUsername() | !validateEmail()){
                    return;
                }else{
                    String nameStr = Objects.requireNonNull(name.getEditText()).getText().toString();
                    String usernameStr = Objects.requireNonNull(username.getEditText()).getText().toString();
                    String emailStr = Objects.requireNonNull(email.getEditText()).getText().toString();
                    String passwordStr = Objects.requireNonNull(password.getEditText()).getText().toString();
//                    String key = keyRef.getKey();
                    String key = "null";

                    DatabaseReference keyRef = myRef.child(usernameStr);

                    UsersHelperClass usersHelperClass = new UsersHelperClass(nameStr, usernameStr, emailStr, passwordStr, key);

                    keyRef.setValue(usersHelperClass).addOnSuccessListener(suc ->
                    {
//                        usersHelperClass.setKey(key);
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    private Boolean validateName() {
        String nameStr = Objects.requireNonNull(name.getEditText()).getText().toString();

        if (nameStr.isEmpty()){
            name.setError("Field cannot be empty");
            return false;
        }else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();

        if (val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}