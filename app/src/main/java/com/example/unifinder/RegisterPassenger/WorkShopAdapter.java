package com.example.unifinder.RegisterPassenger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unifinder.R;

import java.util.ArrayList;

public class WorkShopAdapter extends RecyclerView.Adapter<WorkShopViewHolder> {
    OnListInterface2 onListInterface;
    ArrayList<WorkShopModel> workShopModels;

    public WorkShopAdapter(OnListInterface2 onListInterface, ArrayList<WorkShopModel> firebaseModels) {
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
        holder.itemView.setOnClickListener(v -> {
            onListInterface.onClickItem(position, workShopModels.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return workShopModels.size();
    }
}

class WorkShopViewHolder extends RecyclerView.ViewHolder {

    public WorkShopViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

