package com.example.unifinder.RegisterPassenger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unifinder.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mUser: FirebaseUser? = null
    var btnSignup: TextView? = null
    var email: EditText? = null
    var password: EditText? = null
    var email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        findViews()
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth!!.currentUser
        btnSignup!!.setOnClickListener { Validate() }
    }

    private fun findViews() {
        btnSignup = findViewById(R.id.btnRegister)
        email = findViewById(R.id.userFirstName)
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
            mAuth!!.createUserWithEmailAndPassword(usermail, usermail)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "createUserWithEmail:success")
                        val i = Intent(applicationContext, HomeScreen::class.java)
                        startActivity(i)
                        val user = mAuth!!.currentUser
                        Log.d("TAG", user!!.email.toString())
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            applicationContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        if (currentUser != null) {
//            reload();
        }
    }
}