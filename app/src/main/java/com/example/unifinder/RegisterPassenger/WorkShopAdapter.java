package com.example.unifinder.RegisterPassenger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        WorkShopModel workShopModel = workShopModels.get(position);
        holder.btnReview.setOnClickListener(v -> {
            onListInterface.onClickItem(position, workShopModel);
        });

        holder.txtitemName.setText(workShopModel.getWorkShopTitle());
        holder.txtDescription.setText(workShopModel.getWorkShopDetails());

//        if (workShopModel.getWorkshopActive()) {
//            holder.btnReview.setText("Enrolled");
//        }
    }

    @Override
    public int getItemCount() {
        return workShopModels.size();
    }
}

class WorkShopViewHolder extends RecyclerView.ViewHolder {
    TextView txtitemName;
    TextView btnReview;
    TextView txtDescription;

    public WorkShopViewHolder(@NonNull View itemView) {
        super(itemView);
        txtitemName = itemView.findViewById(R.id.txtitemName);
        btnReview = itemView.findViewById(R.id.btnReview);
        txtDescription = itemView.findViewById(R.id.txtDescription);
    }
}

