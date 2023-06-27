package com.example.unifinder.RegisterPassenger;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unifinder.MainActivity;
import com.example.unifinder.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                putPdf putPdf = uploadedPdf.get(i);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setType("application/pdf");
//                intent.setData(Uri.parse(putPdf.getUrl()));
//                startActivity(intent);


                showLongPressDialog(PdfList.this);




            }
        });
        Log.e("121212", "onSuccess: " + uploadedPdf.size());


//        retrivePdfFiles();


        storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                List<StorageReference> pdfFiles = listResult.getItems();
                Log.e("12121244444", "onSuccess: " + pdfFiles.size());


                List<String> pdfFileNames = new ArrayList<>();
                for (StorageReference pdfFile : pdfFiles) {
                    pdfFileNames.add(pdfFile.getName());
                    Log.e("121212", "onSuccess: " + pdfFileNames.toString());
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

    private void showLongPressDialog(Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);
        builder.setView(dialogView);

        // Find views within the custom layout
        TextView messageTextView = dialogView.findViewById(R.id.tv_title_text);
        TextView okButton = dialogView.findViewById(R.id.okBtn);
        TextView cancelButton = dialogView.findViewById(R.id.cancelBtn);
        TextView moveButton = dialogView.findViewById(R.id.Movebtn);

        messageTextView.setText("You have long-pressed the view!");

        AlertDialog dialog = builder.create();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                StorageReference pdfRef = storageRef.child("pdf");

                pdfRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "PDF file deleted successfully", Toast.LENGTH_SHORT).show();
                        // File deleted successfully
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });

                dialog.dismiss();
            }
        });

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Move" button click
                Toast.makeText(PdfList.this, "Move button clicked!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void buttonOpenFile(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
//        intent.setType("application/pdf");
        intent.setType("*/*");
        this.startActivity(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void buttonCreateFile(View view) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
//        intent.setType("application/pdf");
        intent.setType("*/*");
        this.startActivity(intent);
    }


}