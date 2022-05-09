package com.example.mad_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Question_HomeAdapter extends RecyclerView.Adapter<Question_HomeAdapter.QuestionViewHolder>{

    Context context;
    ArrayList<UserHelperClass> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("question");
    private String Tag = "hi";

    public Question_HomeAdapter(Context context, ArrayList<UserHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_home, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        UserHelperClass helperClass= list.get(position);
        holder.title.setText(helperClass.getTitle());
        holder.description.setText(helperClass.getDescription());
        
        holder.card.setOnClickListener(new View.OnClickListener() {
            private static final String LOG = "hiiiiiii";


            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dashboard_container, new QuestionPageFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                Toast.makeText(context, "Hiiiiiiil", Toast.LENGTH_SHORT).show();
//                Log.d(LOG, ""+helperClass.getKey());
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        CardView card;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title1);
            description = itemView.findViewById(R.id.details1);
            card = itemView.findViewById(R.id.cardv);
        }

    }
}
