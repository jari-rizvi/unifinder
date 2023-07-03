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


    }
}