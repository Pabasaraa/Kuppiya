package com.example.mad_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    Context context;
    ArrayList<UserHelperClass> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("question");
    private String Tag = "hi";

    public QuestionAdapter(Context context, ArrayList<UserHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position){
        UserHelperClass helperClass= list.get(position);
        holder.title.setText(helperClass.getTitle());
        holder.description.setText(helperClass.getDescription());

        holder.updateQuestion.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", helperClass.getTitle());
            bundle.putString("description", helperClass.getDescription());
            bundle.putString("key", helperClass.getKey());

            Fragment fragment = new UpdateInquiriesFragment();
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.dashboard_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragment.setArguments(bundle);
            fragmentTransaction.commit();


        });

        holder.deleteQuestion.setOnClickListener(view -> {
            myRef.child(helperClass.getKey()).removeValue().addOnSuccessListener(suc->
            {
                Log.d(Tag, ""+helperClass.getKey());
                Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                notifyItemRemoved(position);
                list.remove(helperClass);
            }).addOnFailureListener(er->
            {
                Toast.makeText(context, "Unsuccess", Toast.LENGTH_SHORT).show();
            });
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        Button updateQuestion, deleteQuestion;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
           description = itemView.findViewById(R.id.details);
            updateQuestion = itemView.findViewById(R.id.update_btn_manageQue);
            deleteQuestion = itemView.findViewById(R.id.delete_btn_manageQue);
        }

    }
}
