package com.example.unifinder.RegisterPassenger;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.unifinder.R;
import com.example.unifinder.RegisterPassenger.FragmentNav.FragmentA;
import com.example.unifinder.RegisterPassenger.FragmentNav.FragmentB;
import com.example.unifinder.RegisterPassenger.FragmentNav.FragmentC;
import com.example.unifinder.RegisterPassenger.FragmentNav.FragmentD;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaopiz.kprogresshud.KProgressHUD;

public class HomeScreen extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String uid = getIntent().getStringExtra("uid");
        Toast.makeText(this, "" + uid, Toast.LENGTH_SHORT).show();

        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(2).setBackgroundColor(R.color.black).setDimAmount(0.5f);


        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new FragmentA());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.dashbord:
                fragment = new FragmentA();
                break;
            case R.id.users:
                fragment = new FragmentB();
                break;
            case R.id.profile:
                fragment = new FragmentC();
                break;
            case R.id.mentor:
                fragment = new FragmentD();
                break;
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }

    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, fragment).commit();
    }

}

