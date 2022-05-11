package com.example.mad_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends Fragment {
    ImageView back_btn;
    TextView name, username, email, dob, status, gender, username2;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate ( R.layout.fragment_user_profile , container , false );
        back_btn = v.findViewById ( R.id.back_button_userprofile );
        name = v.findViewById ( R.id.userProfileName );
        username = v.findViewById ( R.id.username_userprofile );
        email = v.findViewById ( R.id.email_userprofile );
        dob = v.findViewById ( R.id.dob_userprofile );
        gender = v.findViewById ( R.id.gender_userprofile );
        status = v.findViewById ( R.id.employment_userprofile );
        username2 = v.findViewById ( R.id.username_title_userprofile );

        DatabaseReference reference = FirebaseDatabase.getInstance ( "https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app" ).getReference ( "users" );

        bundle = this.getArguments ( );
        String BundleUsername = null;

        if (bundle != null) {
            BundleUsername = bundle.getString ( "username" );
        }

        Query checkUser = reference.orderByChild ( "username" ).equalTo ( BundleUsername );

        String finalBundleUsername = BundleUsername;
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

                    name.setText ( nameFromDB );
                    username.setText ( usernameFromDB );
                    email.setText ( emailFromDB );
                    username2.setText ( usernameFromDB );
                    dob.setText ( dobFromDB );
                    gender.setText ( genderFromDB );
                    status.setText ( statusFromDB );

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        back_btn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        } );

        return v;
    }
}