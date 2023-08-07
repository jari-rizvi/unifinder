package com.example.unifinder.RegisterPassenger;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.unifinder.R;

public class VerifyEmailActivity extends AppCompatActivity {

    TextView btnLogin;


    private void showLongPressDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
                Toast.makeText(VerifyEmailActivity.this, "Move button clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerifyEmailActivity.this, "Move button clicked!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the "Move" button click
                Toast.makeText(VerifyEmailActivity.this, "Move button clicked!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        findViews();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(i);
            }
        });


        View longPressView = findViewById(R.id.textView6);

        longPressView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showLongPressDialog(VerifyEmailActivity.this);
                return true;
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted
            // Perform required operations
        } else {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, perform required operations
            } else {
                // Permission denied
                // Handle the denial case
            }
        }
    }


    private void findViews() {
        btnLogin = findViewById(R.id.btnLogin);


    }
}