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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateInquiriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateInquiriesFragment extends Fragment {
    EditText title, description;
    Button update_btn;


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("question");

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateInquiriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateInquiriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateInquiriesFragment newInstance(String param1, String param2) {
        UpdateInquiriesFragment fragment = new UpdateInquiriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_inquiries, container, false);


//        UserHelperClass edit_question = (UserHelperClass) getIntent().getSerializableExtra("EDIT");
//
//
//        title = v.findViewById(R.id.question_title_add);
//        description = v.findViewById(R.id.question_details_add);
//        update_btn = v.findViewById(R.id.update_btn_question);
//
//        //it retrieve the unique key after testing remove this one+++
//        String gg = "hh";
//        Log.d(gg, ""+edit_question.getKey());
//
//
//        Objects.requireNonNull(title).setText(edit_question.getTitle());
//        Objects.requireNonNull(description).setText(edit_question.getDescription());
//
//        update_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HashMap<String, Object> hashMap = new HashMap<>();
//                hashMap.put("description", description.getText().toString());
//                hashMap.put("title", title.getText().toString());
//s
//                //Just for testing purpose I passed a key
//                myRef.child(edit_question.getKey()).updateChildren(hashMap).addOnSuccessListener(suc ->
//                {
//
//                    Toast.makeText(getContext(), "record is inserted", Toast.LENGTH_SHORT).show();
//
//                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.dashboard_container, new MyInquiriesFragment());
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }).addOnFailureListener(er ->
//                {
//                    Toast.makeText(getContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//            }
//        });
//

        return  v;
    }


}