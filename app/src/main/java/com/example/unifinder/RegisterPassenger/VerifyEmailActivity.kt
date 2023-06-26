package com.example.unifinder.RegisterPassenger

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.unifinder.R

class VerifyEmailActivity : AppCompatActivity() {
    var btnLogin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)
        findViews()
        btnLogin!!.setOnClickListener {
            val i = Intent(applicationContext, LoginScreen::class.java)
            startActivity(i)
        }
    }

    private fun findViews() {
        btnLogin = findViewById(R.id.btnLogin)
    }
}