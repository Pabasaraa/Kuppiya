package com.example.mad_app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Settings extends Fragment {
    TextInputLayout name, username, email, dob;
    RadioGroup gender, employment;
    RadioButton selectedGender, selectedEmployment;
    Button updateBtn;
    public String CHANNEL_ID = "Id_2";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("users");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        CharSequence nameNotify = "User update notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, nameNotify, importance);
        channel.setDescription("Notification that shows when user details updated.");
        NotificationManager notificationManager = ( NotificationManager ) getActivity().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        name = v.findViewById(R.id.name_settings);
        username = v.findViewById(R.id.username_settings);
        email = v.findViewById(R.id.email_settings);
        dob = v.findViewById(R.id.dob_settings);
        updateBtn = v.findViewById(R.id.update_btn_setting);
        gender = v.findViewById(R.id.gender);
        employment = v.findViewById(R.id.employmentStatus_settings);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String nameStr = bundle.getString("name");
            String usernameStr = bundle.getString("username");
            String emailStr = bundle.getString("email");
            String dobStr = bundle.getString("dob");

            Objects.requireNonNull(name.getEditText()).setText(nameStr);
            Objects.requireNonNull(username.getEditText()).setText(usernameStr);
            Objects.requireNonNull(email.getEditText()).setText(emailStr);
            Objects.requireNonNull(dob.getEditText()).setText(dobStr);
        }

        updateBtn.setOnClickListener(view -> {
            int selectedIdGender = gender.getCheckedRadioButtonId();
            int selectedIdStatus = employment.getCheckedRadioButtonId();

            selectedGender = (RadioButton) v.findViewById(selectedIdGender);
            selectedEmployment = (RadioButton) v.findViewById(selectedIdStatus);

            String gender = selectedGender.getText().toString();
            String status = selectedEmployment.getText().toString();

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("name", Objects.requireNonNull(name.getEditText()).getText().toString());
            hashMap.put("username", Objects.requireNonNull(username.getEditText()).getText().toString());
            hashMap.put("email", Objects.requireNonNull(email.getEditText()).getText().toString());
            hashMap.put("dob", Objects.requireNonNull(dob.getEditText()).getText().toString());
            hashMap.put("gender", gender);
            hashMap.put("employment", status);

            myRef.child(username.getEditText().getText().toString()).updateChildren(hashMap).addOnSuccessListener(suc ->
            {
                Intent intentNotify = new Intent(getContext(), ProfileFragment.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, intentNotify, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Success!")
                        .setContentText("Hello "+username.getEditText().getText().toString()+" your details Updated.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManagerr = NotificationManagerCompat.from(getContext());
                notificationManagerr.notify(0, builder.build());

                Toast.makeText(getContext(), "Record is updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);

            }).addOnFailureListener(er ->
            {
                Toast.makeText(getContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        return v;
    }
}