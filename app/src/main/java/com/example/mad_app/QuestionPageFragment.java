package com.example.mad_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionPageFragment extends Fragment {
    //Variables
    TextView title, description;
    EditText textView9;
    Button post_btn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_page, container, false);

        title = v.findViewById(R.id.title_questionPage);
        description = v.findViewById(R.id.description_questionPage);

        Bundle bundle = this.getArguments();

        if (bundle != null){
            String bundleTitle = bundle.getString("title");
            String bundleDescription = bundle.getString("description");

            title.setText(bundleTitle);
            description.setText(bundleDescription);
        }


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

                    textView9.setText(null);
                    //Intent intent = new Intent(getContext(), MySolutionsFragment.class);
                    //startActivity(intent);

                });
            }
        });

        return v;
    }
}