package com.example.unifinder.RegisterPassenger.FragmentNav;

import android.os.Bundle;
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
        faqqAdapter();
    }

    private void faqqAdapter() {

        faqArrayList = new ArrayList<>();

        faqArrayList.add(new Courses("", "dsdaasdsa", "Python"
        ));
        faqArrayList.add(new Courses("", "dsasdsadsadsadsadsa", "Java"
        ));
        faqArrayList.add(new Courses("", "dsasdsadsadsadsadsa", "React"
        ));
        faqArrayList.add(new Courses("", "dsasdsadsadsadsadsa", "Flutter"
        ));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false);
        faqRecycler.setLayoutManager(linearLayoutManager);

        faqAdapter = new CoursesAdapter(faqArrayList);
        faqRecycler.setAdapter(faqAdapter);
    }


}

