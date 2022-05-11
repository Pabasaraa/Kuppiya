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

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

public class Settings extends Fragment {
    TextInputLayout name, username, email, dob;
    RadioGroup gender, employment;
    RadioButton selectedGender, selectedEmployment, male, female, employed, unemployed;
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
        male = v.findViewById(R.id.rbMaleEP);
        female = v.findViewById(R.id.rbFemaleEP);
        employed = v.findViewById(R.id.employed);
        unemployed = v.findViewById(R.id.unemployed);

        Bundle bundle = this.getArguments();
        String usernameStr = null;

        if (bundle != null) {
            usernameStr = bundle.getString("username");
        }


        DatabaseReference reference = FirebaseDatabase.getInstance ( "https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app" ).getReference ( "users" );

        Query checkUser = reference.orderByChild ("username").equalTo(usernameStr);

        String finalBundleUsername = usernameStr;
        checkUser.addListenerForSingleValueEvent ( new ValueEventListener ( ) {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists ( )) {

                    String usernameFromDB = snapshot.child ( finalBundleUsername ).child ( "username" ).getValue ( String.class );
                    String nameFromDB = snapshot.child ( finalBundleUsername ).child ( "name" ).getValue ( String.class );
                    String emailFromDB = snapshot.child ( finalBundleUsername ).child ( "email" ).getValue ( String.class );
                    String dobFromDB = snapshot.child ( finalBundleUsername ).child ( "dob" ).getValue ( String.class );
                    String genderFromDB = snapshot.child ( finalBundleUsername ).child ( "gender" ).getValue ( String.class );
                    String statusFromDB = snapshot.child ( finalBundleUsername ).child ( "employment" ).getValue ( String.class );

                    Bundle bundle = new Bundle ( );
                    bundle.putString ( "username" , usernameFromDB );
                    bundle.putString ( "name" , nameFromDB );
                    bundle.putString ( "email" , emailFromDB );
                    bundle.putString ( "dob" , dobFromDB );
                    bundle.putString ( "gender" , genderFromDB );
                    bundle.putString ( "status" , statusFromDB );

                    Objects.requireNonNull(name.getEditText()).setText(nameFromDB);
                    Objects.requireNonNull(username.getEditText()).setText(usernameFromDB);
                    Objects.requireNonNull(email.getEditText()).setText(emailFromDB);
                    Objects.requireNonNull(dob.getEditText()).setText(dobFromDB);

                    if (Objects.equals(genderFromDB ,"Male")){
                        male.setChecked(true);
                    }else{
                        female.setChecked(true);
                    }

                    if (Objects.equals(statusFromDB ,"Employed")){
                        employed.setChecked(true);
                    }else{
                        unemployed.setChecked(true);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                requireActivity().onBackPressed();

            }).addOnFailureListener(er ->
            {
                Toast.makeText(getContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        return v;
    }
}