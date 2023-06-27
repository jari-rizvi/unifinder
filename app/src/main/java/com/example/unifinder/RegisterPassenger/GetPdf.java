package com.example.unifinder.RegisterPassenger;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unifinder.HashObject;
import com.example.unifinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kaopiz.kprogresshud.KProgressHUD;


import com.google.android.gms.tasks.Continuation;

import java.util.concurrent.atomic.AtomicBoolean;


public class GetPdf extends AppCompatActivity {


    TextView btnLogin;
    TextView btnGet;
    ImageView upload;
    Uri imageuri = null;

    KProgressHUD hud;
    ProgressDialog dialog;

    String uid = "";
    private static final int REQUEST_PDF = 1;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pdf);
        findViews();

        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(2).setBackgroundColor(R.color.black).setDimAmount(0.5f);

        String email = getIntent().getStringExtra("email");
        uid = getIntent().getStringExtra("uid");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);

//                Intent intent = new Intent();
//                intent.setType("application/pdf");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select PDF"), REQUEST_PDF);
            }
        });

        btnGet.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(GetPdf.this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.hash_check, null);
            builder.setView(dialogView);

            // Find views within the custom layout
            EditText messageTextView = dialogView.findViewById(R.id.tv_title_text);
            Button okButton = dialogView.findViewById(R.id.okBtn);
            ImageView closeBtn = dialogView.findViewById(R.id.closeBtn);


            AlertDialog dialog2 = builder.create();
            AtomicBoolean checker = new AtomicBoolean(false);
            okButton.setOnClickListener(v1 -> {
                if (HashObject.INSTANCE.verifyHash(email, messageTextView.getText().toString())) {
                    checker.set(true);
                    dialog2.dismiss();
                } else {
                    Toast.makeText(GetPdf.this, "Wrong Hash Id", Toast.LENGTH_SHORT).show();
                }

            });
            closeBtn.setOnClickListener(v1 -> {
//                    if (HashObject.INSTANCE.verifyHash(email, messageTextView.getText().toString())) {
                dialog2.dismiss();


            });

            dialog2.setCancelable(false);
            dialog2.show();
            dialog2.setOnDismissListener(dialogInterface -> {
                if (checker.get()) {
                    Intent i = new Intent(getApplicationContext(), PdfList.class);
                    i.putExtra("uid", uid);
                    startActivity(i);
                } else {
                    Log.d("123123", "onClick: ");
                }
            });
//                checkHashFunction(GetPdf.this, email);


        });


    }


    private void findViews() {
        btnLogin = findViewById(R.id.btnLogin);
        btnGet = findViewById(R.id.btnGet);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            // Here we are initialising the progress dialog box
            dialog = new ProgressDialog(this);
            dialog.setMessage("Uploading");

            // this will show message uploading
            // while pdf is uploading
            dialog.show();
            imageuri = data.getData();
            final String timestamp = "" + System.currentTimeMillis();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            final String messagePushID = timestamp;
            Toast.makeText(GetPdf.this, imageuri.toString(), Toast.LENGTH_SHORT).show();

            // Here we are uploading the pdf in firebase storage with the name of current time
            final StorageReference filepath = storageReference.child(uid + "/" + messagePushID + "." + "pdf");
            Toast.makeText(GetPdf.this, filepath.getName(), Toast.LENGTH_SHORT).show();
            filepath.putFile(imageuri).continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        // After uploading is done it progress
                        // dialog box will be dismissed
                        dialog.dismiss();
                        Uri uri = task.getResult();
                        String myurl;
                        myurl = uri.toString();
                        Toast.makeText(GetPdf.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog.dismiss();
                        Toast.makeText(GetPdf.this, "UploadedFailed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_PDF && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri pdfUri = data.getData();
//            StorageReference storageRef = storage.getReference();
//            StorageReference pdfRef = storageRef.child("pdfs/" + pdfUri.getLastPathSegment());
//
//            pdfRef.putFile(pdfUri).addOnSuccessListener(taskSnapshot -> {
//
//                    // File uploaded successfully
//                    // Handle successful upload
//                    // Get the download URL of the uploaded file
//                    pdfRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                        String downloadUrl = uri.toString();
//                        // Use the download URL as needed (e.g., save it to a database)
//                        // You can also display it to the user or perform further actions
//
//                        // Example: Save the download URL to Firebase Realtime Database
//                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
//
//                        databaseRef.child("pdfs").push().setValue(downloadUrl)
//                                .addOnSuccessListener(aVoid -> {
//                                    // Successfully saved the download URL to the database
//                                    // Perform any further actions if needed
//                                })
//                                .addOnFailureListener(e -> {
//
//
//
//                                    // File uploaded successfully
//                // Handle successful upload
//            }).addOnFailureListener(e -> {
//                // Handle unsuccessful upload
//            });
//        }
//
//    }
//        }
//    }


}