package com.example.unifinder.RegisterPassenger;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unifinder.MainActivity;
import com.example.unifinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoginScreen extends AppCompatActivity {

    ImageView btnBack;
    TextView btnRegisterr;
    TextView btnLogin;
    EditText email, password;
    String email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    KProgressHUD hud;
    SharedPreferences prefUserData;
    SharedPreferences.Editor editorUserData;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        findViews();

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setBackgroundColor(R.color.black)
                .setDimAmount(0.5f);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        btnRegisterr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });



    }


    private void findViews() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterr = findViewById(R.id.btnSignup);
        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);

    }



    private void Validate() {
        String usermail = email.getText().toString();
        String userpass = password.getText().toString();
        if (usermail.isEmpty()) {
            email.setError("Please enter Email");
        } else if (userpass.isEmpty()) {
            password.setError("Please enter valid Pass");
        } else {


            mAuth.signInWithEmailAndPassword(usermail, userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(),"Something Went Wrong ",Toast.LENGTH_SHORT).show();


                    }
                }
            });

        }
    }
}