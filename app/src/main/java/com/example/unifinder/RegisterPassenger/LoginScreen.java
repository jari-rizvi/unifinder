package com.example.unifinder.RegisterPassenger;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unifinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoginScreen extends AppCompatActivity {

    private ImageView btnBack;
    private TextView btnRegisterr;
    private TextView btnLogin;
    private EditText email;
    private EditText password;
    private ImageView showPassword;
    private ImageView hidePassword;
    private String email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    private KProgressHUD hud;
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
                Intent i = new Intent(LoginScreen.this, SignupActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(null);
                hidePassword.setVisibility(View.VISIBLE);
                showPassword.setVisibility(View.GONE);
            }
        });

        hidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(new PasswordTransformationMethod());
                hidePassword.setVisibility(View.GONE);
                showPassword.setVisibility(View.VISIBLE);
            }
        });
    }

    private void findViews() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterr = findViewById(R.id.btnSignup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        showPassword = findViewById(R.id.showPassword);
        hidePassword = findViewById(R.id.hidePassword);
    }


    /**
     *this function will validate each field
     * if fields are not empty the user will get login via firebase auth and navigate to home screen
     */

    private void Validate() {
        String usermail = email.getText().toString();
        String userpass = password.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(usermail.trim()).matches()) {
            Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userpass.trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userpass.trim().length() < 8) {
            Toast.makeText(getApplicationContext(), "Password Length is too short", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(usermail, userpass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("users");
                DatabaseReference userRef = usersRef.child(mAuth.getCurrentUser().getUid());

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            User userData = dataSnapshot.getValue(User.class);
                            if (userData != null) {
                                String userEmail = userData.getEmail();
                                String name = userData.getName();
                                String img = userData.getImg();
                                Log.d("123123", "onDataChange: " + userEmail);
                                Log.d("123123", "onDataChange: " + name);
                                Log.d("123123", "onDataChange: " + img);
                            }
                        } else {
                            // User data does not exist
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle the error case
                    }
                });

                Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), HomeScreen.class);
                i.putExtra("uid", mAuth.getCurrentUser().getUid());
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

