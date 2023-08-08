package com.example.unifinder.RegisterPassenger.FragmentNav;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
        recyclerView = view.findViewById(R.id.workshopRec);



        addListFirebaseModel();

    }

    void addListFirebaseModel() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference("workshop");
        List<WorkShopModel> universityList = new ArrayList<>();
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                universityList.clear();
                workShopModels.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    WorkShopModel university = postSnapshot.getValue(WorkShopModel.class);

                    university.setWorkShopTitle(postSnapshot.getKey() + "");
                    university.setWorkShopDetails("This is course description");

                    workShopModels.add(university);
                    workShopAdapter.notifyDataSetChanged();
                    Log.d("2134123", "onDataChange:TitleTitle "+university.getWorkShopTitle());
                    // here you can access to name property like university.name

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
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
