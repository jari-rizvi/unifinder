package com.example.unifinder.RegisterPassenger.FragmentNav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unifinder.R;
import com.example.unifinder.RegisterPassenger.OnListInterface2;
import com.example.unifinder.RegisterPassenger.WorkShopAdapter;
import com.example.unifinder.RegisterPassenger.WorkShopModel;

import java.util.ArrayList;

public class FragmentC extends Fragment implements OnListInterface2 {


    ArrayList<WorkShopModel> workShopModels;

    WorkShopAdapter workShopAdapter;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        workShopModels = new ArrayList();

        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        workShopModels.add(new WorkShopModel());
        recyclerView = view.findViewById(R.id.workshopRec);

        addWorkshopRec();

    }

    void addWorkshopRec() {


        workShopAdapter = new WorkShopAdapter(this, workShopModels);

        recyclerView.setAdapter(workShopAdapter);
    }

    @Override
    public void onClickItem(int position, WorkShopModel firebaseModel) {
        Toast.makeText(requireContext(), "Enrolled", Toast.LENGTH_SHORT).show();
    }
}
