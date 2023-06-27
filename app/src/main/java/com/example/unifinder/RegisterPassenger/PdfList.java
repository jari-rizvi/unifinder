package com.example.unifinder.RegisterPassenger;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unifinder.MainActivity;
import com.example.unifinder.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;


public class PdfList extends AppCompatActivity {
    TextView btnLogin;

    ListView listView;
    KProgressHUD hud;
    ProgressDialog dialog;


    TextView view;
    DatabaseReference database;
    List<putPdf> uploadedPdf;
    String message;


    private void findViews() {
        listView = findViewById(R.id.view);

    }

//    private void retrivePdfFiles() {
//
//        // Initialising the reference to database
//        database = FirebaseDatabase.getInstance().getReference().child("pdf");
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    putPdf purpdf = ds.getValue(putPdf.class);
//                    uploadedPdf.add(purpdf);
//                }
//
//                String[] uploadNames = new String[uploadedPdf.size()];
//
//                for(int i = 0;i<uploadNames.length;i++){
//                    uploadNames[i] = uploadedPdf.get(i).getName();
//                }
//                ArrayAdapter<String> arrayAdapter  = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,uploadNames){
//                    @NonNull
//                    @Override
//                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//                        View view = super.getView(position, convertView, parent);
//                        TextView    textView = (TextView) view.findViewById(android.R.id.text1);
//                        textView.setTextColor(Color.BLACK);
//                        textView.setTextSize(20);
//                        return view;
//                    }
//                };
//
//                listView.setAdapter(arrayAdapter);
//
//                // getting a DataSnapshot for the location at the specified
//                // relative path and getting in the link variable
//                message = dataSnapshot.getValue(String.class);
//            }
//
//            // this will called when any problem
//            // occurs in getting data
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // we are showing that error message in toast
//                Toast.makeText(PdfList.this, "Error Loading Pdf", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pdff);
        findViews();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("pdf");

        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(2).setBackgroundColor(R.color.black).setDimAmount(0.5f);

        uploadedPdf = new ArrayList<>();
        Log.e("121212", "onSuccess: "+uploadedPdf.size() );


//        retrivePdfFiles();


        storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                List<StorageReference> pdfFiles = listResult.getItems();
                Log.e("12121244444", "onSuccess: "+pdfFiles.size() );


                List<String> pdfFileNames = new ArrayList<>();
                for (StorageReference pdfFile : pdfFiles) {
                    pdfFileNames.add(pdfFile.getName());
                    Log.e("121212", "onSuccess: "+pdfFileNames.toString() );
                }

                ListView pdfListView = findViewById(R.id.view);
                PDFListAdapter adapter = new PDFListAdapter(PdfList.this, pdfFileNames);
                pdfListView.setAdapter(adapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure to retrieve PDF file list
            }
        });


//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                CharSequence options[] = new CharSequence[]{
//                        "Download",
//                        "View",
//                        "Cancel"
//                };
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setTitle("Choose One");
//                builder.setItems(options, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // we will be downloading the pdf
//                        if (which == 0) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
//                            startActivity(intent);
//                        }
//                        // We will view the pdf
//                        if (which == 1) {
//                            Intent intent = new Intent(v.getContext(), LoginScreen.class);
//                            intent.putExtra("url", message);
//                            startActivity(intent);
//                        }
//                    }
//                });
//                builder.show();
//            }
//        });
    }


}