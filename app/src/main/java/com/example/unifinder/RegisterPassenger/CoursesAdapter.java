package com.example.unifinder.RegisterPassenger;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unifinder.R;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapterViewHolder> {
    OnListInterface onListInterface;
    ArrayList<Courses> coursesModels;

    public CoursesAdapter(ArrayList<Courses> firebaseModels) {
        this.coursesModels = firebaseModels;
    }

    @NonNull
    @Override
    public CoursesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CoursesAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_courses, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapterViewHolder holder, int position) {

        Log.d("TAG", "onBindViewHolder: " + coursesModels.get(position).getCourseName());
        holder.txtitemName.setText(coursesModels.get(position).getCourseName());
        holder.txtDescription.setText(coursesModels.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
         return coursesModels.size();
    }
}

class CoursesAdapterViewHolder extends RecyclerView.ViewHolder {

    TextView txtitemName;
    TextView txtDescription;
    public CoursesAdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        txtitemName = itemView.findViewById(R.id.txtitemName);
        txtDescription = itemView.findViewById(R.id.txtDescription);
    }
}