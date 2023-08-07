package com.example.unifinder.RegisterPassenger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unifinder.R;

import java.util.ArrayList;

public class WorkShopAdapter extends RecyclerView.Adapter<WorkShopViewHolder> {
    OnListInterface onListInterface;
    ArrayList<WorkShopModel> workShopModels;

    public WorkShopAdapter(OnListInterface onListInterface, ArrayList<WorkShopModel> firebaseModels) {
        this.onListInterface = onListInterface;
        this.workShopModels = firebaseModels;
    }

    @NonNull
    @Override
    public WorkShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkShopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_workshop, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull WorkShopViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class WorkShopViewHolder extends RecyclerView.ViewHolder {

    public WorkShopViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}