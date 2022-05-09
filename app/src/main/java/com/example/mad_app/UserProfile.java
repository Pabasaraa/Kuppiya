package com.example.mad_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class UserProfile extends Fragment {
    ImageView back_btn;
    TextView name, username, email, dob, status, gender, username2;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);
        back_btn = v.findViewById(R.id.back_button_userprofile);
        name = v.findViewById(R.id.userProfileName);
        username = v.findViewById(R.id.username_userprofile);
        email = v.findViewById(R.id.email_userprofile);
        dob = v.findViewById(R.id.dob_userprofile);
        gender = v.findViewById(R.id.gender_userprofile);
        status = v.findViewById(R.id.employment_userprofile);
        username2 = v.findViewById(R.id.username_title_userprofile);

        bundle = this.getArguments();

        if (bundle != null){
            String BundleName = bundle.getString("name");
            String BundleUsername = bundle.getString("username");
            String BundleEmail = bundle.getString("email");

            name.setText(BundleName);
            username.setText(BundleUsername);
            email.setText(BundleEmail);
            username2.setText(BundleUsername);
        }

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        return v;
    }
}