package com.example.unifinder.RegisterPassenger.FragmentNav;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unifinder.R;
import com.example.unifinder.RegisterPassenger.Courses;
import com.example.unifinder.RegisterPassenger.CoursesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentB extends Fragment {

    private CoursesAdapter faqAdapter;
    private ArrayList<Courses> faqArrayList;
    RecyclerView faqRecycler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        faqRecycler = view.findViewById(R.id.courseRecycler);
        faqArrayList = new ArrayList<>();

        for (Courses i : faqArrayList) {


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference usersRef = database.getReference("courses");
            String name = i.getCourseName();
            String details = i.getDescription();
            String time = i.getEnroll();

            DatabaseReference userRef = usersRef.child(name);
            userRef.child("name").setValue(name);
            userRef.child("details").setValue(details);
            userRef.child("time").setValue(time);
            Log.d("123123", "Validate:" + details);
            Log.d("123123", "Validate:" + name);
            Log.d("123123", "Validate:" + time);
            Log.d("123123", "Validate:" + userRef);
        }


        addListFirebaseModel();

    }

    void addListFirebaseModel() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference("courses");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                faqArrayList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Courses university = postSnapshot.getValue(Courses.class);
                    university.setCourseName(postSnapshot.getKey() + "");
                    university.setDescription("This is course description");

                    faqArrayList.add(university);
                    faqAdapter.notifyDataSetChanged();
                    Log.d("2134123", "onDataChange: " + university.getCourseName());
                    Log.d("2134123", "onDataChange: " + postSnapshot.getKey());
                    // here you can access to name property like university.name

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        faqqAdapter();
    }


    private void faqqAdapter() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        faqRecycler.setLayoutManager(linearLayoutManager);

        faqAdapter = new CoursesAdapter(faqArrayList);
        faqRecycler.setAdapter(faqAdapter);
    }


}

