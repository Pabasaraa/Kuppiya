package com.example.mad_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class UpdateInquiriesFragment extends Fragment {
    EditText title, description;
    Button update_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_inquiries, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("question");

        Bundle bundle = this.getArguments();
        String key = bundle.getString("key");
        String titleBundle = bundle.getString("title");
        String descriptionBundle = bundle.getString("description");


        title = v.findViewById(R.id.question_title_add);
        description = v.findViewById(R.id.question_details_add);
        update_btn = v.findViewById(R.id.update_btn_question);



        Objects.requireNonNull(title).setText(titleBundle);
        Objects.requireNonNull(description).setText(descriptionBundle);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("description", description.getText().toString());
                hashMap.put("title", title.getText().toString());

                myRef.child(key).updateChildren(hashMap).addOnSuccessListener(suc ->
                {

                    Toast.makeText(getContext(), "record is updated", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.dashboard_container, new MyInquiriesFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(getContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });


        return  v;
    }


}