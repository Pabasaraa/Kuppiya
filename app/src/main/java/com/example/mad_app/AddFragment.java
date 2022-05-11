package com.example.mad_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    EditText title, description;
    Button postBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("question");
    DatabaseReference keyRef = myRef.push();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        title = v.findViewById(R.id.question_title_add);
        description = v.findViewById(R.id.question_details_add);
        postBtn = v.findViewById(R.id.question_add_btn);
        String key = keyRef.getKey();

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = Objects.requireNonNull(title).getText().toString();
                String  Description = Objects.requireNonNull(description).getText().toString();

                UserHelperClass helperClass = new UserHelperClass(Title, Description, key);

               keyRef.setValue(helperClass).addOnSuccessListener(suc ->
               {
                  helperClass.setKey(key);
                  Toast.makeText(getContext(), "record is inserted", Toast.LENGTH_SHORT).show();

                   FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                   fragmentTransaction.replace(R.id.dashboard_container, new MyInquiriesFragment());
                   fragmentTransaction.addToBackStack(null);
                   fragmentTransaction.commit();
               }).addOnFailureListener(er ->
               {
                   Toast.makeText(getContext(), "" +er.getMessage(), Toast.LENGTH_SHORT).show();
               });

            }
        });

        return v;
    }
}