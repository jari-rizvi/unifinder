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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextView btnSignup;
    private EditText email;
    private EditText password;
    private ImageView showPassword;
    private ImageView hidePassword;
    private String email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

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
        btnSignup = findViewById(R.id.btnRegister);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        showPassword = findViewById(R.id.showPassword);
        hidePassword = findViewById(R.id.hidePassword);
    }

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

        mAuth.createUserWithEmailAndPassword(usermail, userpass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("TAG", "createUserWithEmail:success");
                        Intent i = new Intent(getApplicationContext(), ImageLock.class);
                        i.putExtra("uid", mAuth.getCurrentUser().getUid());
                        i.putExtra("email", mAuth.getCurrentUser().getEmail());
                        i.putExtra("displayName", mAuth.getCurrentUser().getDisplayName());
                        i.putExtra("img", "");
                        startActivity(i);
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d("TAG", user.getEmail());

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference usersRef = database.getReference("users");
                        String email = user.getEmail();
                        String name = user.getDisplayName();
                        String id = user.getUid();
                        DatabaseReference userRef = usersRef.child(id);
                        userRef.child("email").setValue(email);
                        userRef.child("name").setValue(name);
                        userRef.child("img").setValue("");
                        userRef.child("hashId").setValue(hashFunction(email));
                        Log.d("123123", "Validate:" + email);
                        Log.d("123123", "Validate:" + name);
                        Log.d("123123", "Validate:" + id);
                        Log.d("123123", "Validate:" + userRef);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Reload UI if needed.
        }
    }
    public static String hashFunction(String data) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha256.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte hashedByte : hashedBytes) {
                sb.append(String.format("%02x", hashedByte));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}