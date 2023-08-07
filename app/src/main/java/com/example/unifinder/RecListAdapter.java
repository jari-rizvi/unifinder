package com.example.unifinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unifinder.RegisterPassenger.FirebaseModel;
import com.example.unifinder.RegisterPassenger.OnListInterface;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class RecListAdapter extends RecyclerView.Adapter<ClassViewHolder> {
    OnListInterface onListInterface;
    ArrayList<FirebaseModel> firebaseModels;

    public RecListAdapter(OnListInterface onListInterface, ArrayList<FirebaseModel> firebaseModels) {
        this.onListInterface = onListInterface;
        this.firebaseModels = firebaseModels;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClassViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_pref, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        holder.name.setText(firebaseModels.get(position).getFileName());

        holder.name.setOnClickListener(v -> {
            onListInterface.onClickItem(position, firebaseModels.get(position));
        });
    }


    @Override
    public int getItemCount() {
        return firebaseModels.size();
    }

}

class ClassViewHolder extends RecyclerView.ViewHolder {
    TextView name;

    public ClassViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.item_name);

    }
}



