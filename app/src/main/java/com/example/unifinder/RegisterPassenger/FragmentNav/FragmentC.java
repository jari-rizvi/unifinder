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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FragmentC extends Fragment implements OnListInterface2 {


    ArrayList<WorkShopModel> workShopModels;

    WorkShopAdapter workShopAdapter;
    RecyclerView recyclerView;

//    Introduction to Computer Science
//    Data Structures and Algorithms
//    Object-Oriented Programming (OOP)
//    Web Development Fundamentals
//    Database Management Systems (DBMS)
//    Operating Systems Concepts
//    Software Engineering Principles
//    Introduction to Artificial Intelligence (AI)
//    Machine Learning Fundamentals
//    Deep Learning and Neural Networks
//    Introduction to Cybersecurity
//    Network Fundamentals and Protocols
//    Cloud Computing and Virtualization
//    Mobile App Development
//    Internet of Things (IoT) Basics
//    Game Development Fundamentals
//    Introduction to Robotics
//    Data Analysis and Visualization
//    Natural Language Processing (NLP)
//    Ethical Hacking and Penetration Testing

    /*
    *:


.


.


.


.


.


.


.


.


  * */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        workShopModels = new ArrayList();

        /*workShopModels.add(new WorkShopModel("Effective Communication and Presentation Skills Workshop", "This workshop focuses on improving communication skills, including verbal and non-verbal communication, active listening, and public speaking. Participants learn to express themselves confidently and effectively, making them more impactful communicators in both professional and personal settings.", "", false));
        workShopModels.add(new WorkShopModel("Time Management and Productivity Workshop", "In this workshop, participants learn techniques to manage their time efficiently, prioritize tasks, and avoid procrastination. The workshop equips attendees with practical strategies to enhance productivity and achieve better work-life balance.", "", false));
        workShopModels.add(new WorkShopModel("Leadership and Team Building Workshop", "This workshop is designed for aspiring leaders to develop essential leadership qualities. Participants learn how to inspire and motivate their teams, build trust, and foster collaboration among team members, enhancing overall team performance.", "", false));
        workShopModels.add(new WorkShopModel("Conflict Resolution and Problem-Solving Workshop", "This workshop helps participants develop conflict resolution skills and effective problem-solving techniques. Attendees learn how to handle conflicts constructively, reach win-win solutions, and foster a harmonious work environment.", "", false));
        workShopModels.add(new WorkShopModel("Stress Management and Well-being Workshop", "The stress management workshop focuses on helping participants cope with stress effectively and maintain overall well-being. Attendees learn relaxation techniques, mindfulness practices, and strategies to manage stress in daily life.", "", false));
        workShopModels.add(new WorkShopModel("Emotional Intelligence Workshop", "This workshop explores emotional intelligence, including self-awareness, empathy, and relationship management. Participants learn to understand and regulate their emotions better, leading to improved interpersonal interactions.", "", false));
        workShopModels.add(new WorkShopModel("Creative Thinking and Innovation Workshop", "In this workshop, participants are encouraged to think creatively and embrace innovation. It includes brainstorming sessions, idea generation techniques, and problem-solving exercises to foster a culture of innovation.", "", false));
        workShopModels.add(new WorkShopModel("Goal Setting and Personal Development Workshop", "This workshop guides participants in setting clear and achievable goals, both personally and professionally. Attendees learn how to create action plans to progress toward their objectives and measure their success.", "", false));
        workShopModels.add(new WorkShopModel("Financial Literacy and Money Management Workshop", "This workshop provides valuable insights into financial literacy, including budgeting, saving, and investing. Participants gain practical financial management skills to secure their financial future.", "", false));
        workShopModels.add(new WorkShopModel("Career Development and Job Search Workshop", ".", "Designed for job seekers, this workshop offers tips on creating effective resumes, honing interview skills, and navigating the job market. It equips attendees with the tools needed to succeed in their career search.", false));

        recyclerView = view.findViewById(R.id.workshopRec);

        for (WorkShopModel i : workShopModels) {


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference usersRef = database.getReference("workshop");
            String name = i.getWorkShopTitle();
            String details = i.getWorkShopDetails();
            String time = i.getWorkShopTime();

            DatabaseReference userRef = usersRef.child(name);
            userRef.child("name").setValue(name);
            userRef.child("details").setValue(details);
            userRef.child("time").setValue(time);
            userRef.child("isactive").setValue(true);
            Log.d("123123", "Validate:" + details);
            Log.d("123123", "Validate:" + name);
            Log.d("123123", "Validate:" + time);
            Log.d("123123", "Validate:" + userRef);
        }*/


        addListFirebaseModel();

    }

    void addListFirebaseModel() {
        String uid = "";
//    uid = getIntent().getStringExtra("uid");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("workshops" + uid);
        storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                List<StorageReference> pdfFiles = listResult.getItems();

                Log.e("12121244444", "onSuccess: " + pdfFiles.size());


//                List<String> pdfFileNames = new ArrayList<>();
                for (StorageReference pdfFile : pdfFiles) {
//                    pdfFileNames.add(pdfFile.getName());
                    workShopModels.add(new WorkShopModel("", "", "pdfFile", true));
//                    Log.e("121212", "onSuccess: " + pdfFileNames.toString());
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure to retrieve PDF file list
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
