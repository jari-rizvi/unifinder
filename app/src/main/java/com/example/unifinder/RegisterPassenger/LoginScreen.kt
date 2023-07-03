package com.example.unifinder.RegisterPassenger

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import com.example.unifinder.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false).setAnimationSpeed(2).setBackgroundColor(R.color.black)
            .setDimAmount(0.5f)
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth!!.currentUser
        btnRegisterr!!.setOnClickListener {
            val i = Intent(this@LoginScreen, SignupActivity::class.java)
            startActivity(i)
        }
        btnLogin!!.setOnClickListener { Validate() }
    }

    private fun findViews() {
        btnLogin = findViewById(R.id.btnLogin)
        btnRegisterr = findViewById(R.id.btnSignup)
        email = findViewById(R.id.email)
        password = findViewById(R.id.pass)
    }

    private fun Validate() {
        val usermail = email!!.text.toString()
        val userpass = password!!.text.toString()

//        if (usermail.isEmpty()) {
//            email!!.error = "Please enter Email"
//        } else if (userpass.isEmpty()) {
//            password!!.error = "Please enter valid Pass"
//        }

        if (!Patterns.EMAIL_ADDRESS.matcher(usermail.trim())
                .matches()
        ) {
            Toast.makeText(
                applicationContext, "Invalid Email", Toast.LENGTH_SHORT
            ).show()

        }
        if (userpass.trim().isEmpty()) {
            Toast.makeText(
                applicationContext, "Enter Password", Toast.LENGTH_SHORT
            ).show()

        }
        if (userpass.trim().length < 8) {
            Toast.makeText(
                applicationContext, "Password Length is too short", Toast.LENGTH_SHORT
            ).show()

        }



        else {
            mAuth!!.signInWithEmailAndPassword(usermail, userpass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val database = FirebaseDatabase.getInstance()
                    val usersRef = database.getReference("users")

                    val userRef = usersRef.child(mAuth?.currentUser!!.uid)

                    userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Access the user data from the snapshot
                                val userData = dataSnapshot.getValue(User::class.java)
                                // Handle the retrieved user data
                                if (userData != null) {
                                    val userEmail = userData.email
                                    val name = userData.name
                                    val img = userData.img
                                    Log.d("123123", "onDataChange: $userEmail")
                                    Log.d("123123", "onDataChange: $name")
                                    Log.d("123123", "onDataChange: $img ")
                                    // Access other user data fields as needed
                                }
                            } else {
                                // User data does not exist
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Handle the error case
                        }
                    })



                    Toast.makeText(applicationContext, "Successfully Login", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(applicationContext, ImageLocktwo::class.java)
                    i.putExtra("uid",mAuth?.currentUser!!.uid.toString())
                    startActivity(i)
                } else {
                    Toast.makeText(applicationContext, "Something Went Wrong ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

