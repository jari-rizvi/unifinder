package com.example.unifinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.unifinder.Modal.searchRides.Area;
import com.example.unifinder.RegisterPassenger.LoginScreen;

public class MainActivity extends AppCompatActivity {
    CardView spCardView, rpCardView;
    TextView spText, rpText;

    Area area;

    Shader shaderSpText, shaderRpText;
    SharedPreferences prefRouteData;
    SharedPreferences.Editor editorRouteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        prefRouteData = getSharedPreferences("ROUTE_DATA", MODE_PRIVATE);
//        editorRouteData = prefRouteData.edit();

//        spCardView = findViewById(R.id.spCardView);
//        rpCardView = findViewById(R.id.rpCardView);
//        spText = findViewById(R.id.spText);
//        rpText = findViewById(R.id.rpText);
//        shaderSpText = new LinearGradient(0f, 0f, 0f, spText.getTextSize(), Color.parseColor("#ea5c04"), Color.parseColor("#f38906"), Shader.TileMode.CLAMP);
//        spText.getPaint().setShader(shaderSpText);
//        shaderRpText = new LinearGradient(0f, 0f, 0f, rpText.getTextSize(), Color.parseColor("#ea5c04"), Color.parseColor("#f38906"), Shader.TileMode.CLAMP);
//        rpText.getPaint().setShader(shaderRpText);
//
//
//        area = (com.example.unifinder.Modal.searchRides.Area) getIntent().getSerializableExtra("model");
//
//        editorRouteData.putString("vehicleId",area.getVehicleId()).apply();
//        editorRouteData.putString("fare",area.getFare()).apply();

//        Toast.makeText(MainActivity.this,area.getVehicleId(),Toast.LENGTH_LONG).show();

//        rpCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), LoginScreen.class);
//                startActivity(i);
//
//            }
//        });

//        spCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), SelectPassengerScreen.class);
//                startActivity(i);
//            }
//        });

    }
}