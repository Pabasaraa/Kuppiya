package com.example.mad_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    Button login, notMember;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.question_add_btn);
        notMember = findViewById(R.id.not_member);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(view);
            }
        });

        notMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validatePassword() | !validateUsername()){
            return;
        }else {
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUsername, userEnteredPassword;

        userEnteredUsername = Objects.requireNonNull(username.getEditText()).getText().toString();
        userEnteredPassword = Objects.requireNonNull(password.getEditText()).getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        //add intent
//                        ProfileFragment fragment = new ProfileFragment();
//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString("username", usernameFromDB);
//                        bundle.putString("name", nameFromDB);
//                        bundle.putString("email", emailFromDB);
//                        bundle.putString("password", passwordFromDB);
//                        fragment.setArguments(bundle);
//                        fragmentTransaction.replace(R.id.dashboard_container, fragment).commit();

//                        FragmentManager fragmentManager = getSupportFragmentManager();
//                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.dashboard_container, new ProfileFragment());
//                        fragmentTransaction.addToBackStack(null);
//                        fragmentTransaction.commit();

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);

                        Toast.makeText(Login.this, "Log unoo", Toast.LENGTH_SHORT).show();

                    }else {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                }else {
                    username.setError("Wrong username");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}