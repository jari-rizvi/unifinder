package com.example.unifinder.RegisterPassenger;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unifinder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaopiz.kprogresshud.KProgressHUD;

public class HomeScreen extends AppCompatActivity {

    ImageView btnBack;
    TextView btnRegister;
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
        setContentView(R.layout.activity_home);
        String uid = getIntent().getStringExtra("uid");
        Toast.makeText(this, "" + uid, Toast.LENGTH_SHORT).show();

        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(2).setBackgroundColor(R.color.black).setDimAmount(0.5f);


    }


}