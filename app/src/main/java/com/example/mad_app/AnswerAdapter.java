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

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>{

    Context context;
    ArrayList<AnswerHelperClass> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("answer");
    private String Tag = "hi";

    public AnswerAdapter(Context context, ArrayList<AnswerHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.solutioncard, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        AnswerHelperClass helperClass= list.get(position);
        holder.answer.setText(helperClass.getAnswer());

        holder.updateAnswer.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("answer", helperClass.getAnswer());
            bundle.putString("key", helperClass.getKey());

            Fragment fragment = new UpdateSolutionsFragment();
            FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.dashboard_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragment.setArguments(bundle);
            fragmentTransaction.commit();
        });

        holder.deleteAnswer.setOnClickListener(view -> {
            myRef.child(helperClass.getKey()).removeValue().addOnSuccessListener(suc->
            {
                Log.d(Tag, ""+helperClass.getKey());
                Toast.makeText(context, "Answer is removed", Toast.LENGTH_SHORT).show();
                notifyItemRemoved(position);
                list.remove(helperClass);
            }).addOnFailureListener(er->
            {
                Toast.makeText(context, "Unsuccessful", Toast.LENGTH_SHORT).show();
            });
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder{
        TextView answer;
        Button updateAnswer, deleteAnswer;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);

            answer = itemView.findViewById(R.id.answer);
            updateAnswer = itemView.findViewById(R.id.update_btn_manageQue);
            deleteAnswer = itemView.findViewById(R.id.delete_btn_manageQue);
        }

    }
}