package com.example.unifinder.RegisterPassenger

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unifinder.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kaopiz.kprogresshud.KProgressHUD

class LoginScreen : AppCompatActivity() {
    var btnBack: ImageView? = null
    var btnRegisterr: TextView? = null
    var btnLogin: TextView? = null
    var email: EditText? = null
    var password: EditText? = null
    var email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"
    var hud: KProgressHUD? = null
    var prefUserData: SharedPreferences? = null
    var editorUserData: SharedPreferences.Editor? = null
    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        findViews()
        hud = KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false)
            .setAnimationSpeed(2)
            .setBackgroundColor(R.color.black)
            .setDimAmount(0.5f)
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth!!.currentUser
        btnRegisterr!!.setOnClickListener {
            val i = Intent(applicationContext, SignupActivity::class.java)
            startActivity(i)
        }
        btnLogin!!.setOnClickListener { Validate() }
    }

    private fun findViews() {
        btnLogin = findViewById(R.id.btnLogin)
        btnRegisterr = findViewById(R.id.btnSignup)
        email = findViewById(R.id.userEmail)
        password = findViewById(R.id.userPassword)
    }

    private fun Validate() {
        val usermail = email!!.text.toString()
        val userpass = password!!.text.toString()
        if (usermail.isEmpty()) {
            email!!.error = "Please enter Email"
        } else if (userpass.isEmpty()) {
            password!!.error = "Please enter valid Pass"
        } else {
            mAuth!!.signInWithEmailAndPassword(usermail, userpass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Successfully Login", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(applicationContext, HomeScreen::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(applicationContext, "Something Went Wrong ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}