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

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
//                startActivity(i);
//            }
//        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });




//
//
//        edtMobileNo.setText("0");
//
//        edtMobileNo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence.toString().trim().isEmpty()) {
//                    edtMobileNo.setText("0");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//
//            }
//        });


//
//        signInCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Validate()) {
//                    String mobile_no = edtMobileNo.getText().toString().trim();
//                    String password = edtPass.getText().toString().trim();
//                    LoginAPI(mobile_no, password);
//                }
//            }
//        });
//
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finishActivity(1);
//                finish();
//            }
//        });
//
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    edtPass.setTransformationMethod(null);
//                } else {
//                    edtPass.setTransformationMethod(new PasswordTransformationMethod());
//                }
//            }
//        });



    }


    private void findViews() {
        btnLogin = findViewById(R.id.btnLogin);
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


            mAuth.createUserWithEmailAndPassword("jforjari@gmail.com", "jari12345").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(),"Hello ",Toast.LENGTH_SHORT).show();


                    }
                }
            });

        }
    }
}