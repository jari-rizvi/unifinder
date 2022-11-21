package com.example.unifinder.RegisterPassenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.unifinder.R;
import com.google.android.material.textfield.TextInputEditText;

public class VerifyEmailActivity extends AppCompatActivity {

    TextView btnLogin;


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

    }


      private void findViews() {
          btnLogin = findViewById(R.id.btnLogin);


      }
}