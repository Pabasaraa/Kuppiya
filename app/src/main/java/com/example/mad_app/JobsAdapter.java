package com.example.mad_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {
    Context context;
    ArrayList<JobsHelperClass> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://kuppiya-mad-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("jobs");
    private String Tag = "hi";

    public JobsAdapter(Context context, ArrayList<JobsHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public JobsAdapter.JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jobs_cardview, parent, false);
        return new JobsAdapter.JobsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position) {
        JobsHelperClass helperClass= list.get(position);
        holder.title.setText(helperClass.getTitle());
        holder.salary.setText(helperClass.getSalary()+"/month");
        holder.companyName.setText(helperClass.getCompanyName());
        holder.Location.setText(helperClass.getLocation());

        holder.applyBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { helperClass.getEmail() });
//            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
//            intent.putExtra(Intent.EXTRA_TEXT, "mail body");
            context.startActivity(Intent.createChooser(intent, ""));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class JobsViewHolder extends RecyclerView.ViewHolder {
        TextView title, salary, companyName, Location;
        Button applyBtn;

        public JobsViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.job_title_txt);
            salary = itemView.findViewById(R.id.month_salary_txt);
            companyName = itemView.findViewById(R.id.company_name_txt);
            Location = itemView.findViewById(R.id.company_location_txt);
            applyBtn = itemView.findViewById(R.id.apply_btn);
        }
    }
}
