package com.example.mad_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class QuestionPageFragment extends Fragment {
    DatabaseReference reference;

    //Variables
    EditText textView9;
    Button post_btn;

    FirebaseDatabase rootNode;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionPageFragment newInstance(String param1, String param2) {
        QuestionPageFragment fragment = new QuestionPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionPageFragment() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_question_page, container, false);
//<<<<<<< HEAD
//        // Inflate the layout for this fragment
//=======

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("answer");
        DatabaseReference keyRef = reference.push();

        textView9 = v.findViewById(R.id.question_details_add);
        post_btn = v.findViewById(R.id.post_btn);

        String key = keyRef.getKey();

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get the values
                String answer = textView9.getText().toString();
                AnswerHelperClass helperClass = new AnswerHelperClass(answer, key);

                keyRef.setValue(helperClass).addOnSuccessListener(suc ->
                {
                    helperClass.setKey(key);
                    Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(getContext(), MySolutionsFragment.class);
                    //startActivity(intent);

                });
            }
        });
//>>>>>>> 29a83bffc530bee6a7cb85d67e7423ddbcd3f98f

        return v;
    }
}