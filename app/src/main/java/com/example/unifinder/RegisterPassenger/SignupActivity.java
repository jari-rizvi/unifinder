package com.example.unifinder.RegisterPassenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unifinder.MainActivity;
import com.example.unifinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    TextView btnSignup;
    EditText email, password;
    String email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findViews();


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Validate();

            }
        });


    }
    private void findViews() {
        btnSignup = findViewById(R.id.btnRegister);
        email = findViewById(R.id.userFirstName);
        password = findViewById(R.id.userPassword);

    }

    private void  Validate() {
        String usermail = email.getText().toString();
        String userpass = password.getText().toString();
        if (usermail.isEmpty()) {
            email.setError("Please enter Email");

        } else if (userpass.isEmpty()) {
            password.setError("Please enter valid Pass");
        } else {
            mAuth.createUserWithEmailAndPassword(usermail,userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Succesfully Register ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(i);


                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Something wenrt wrong ",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

}