package com.example.unifinder.RegisterPassenger

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.unifinder.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kaopiz.kprogresshud.KProgressHUD

class HomeScreen : AppCompatActivity() {
    var btnBack: ImageView? = null
    var btnRegister: TextView? = null
    var btnLogin: TextView? = null
    var email: EditText? = null
    var password: EditText? = null
    var email_pattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"
    var hud: KProgressHUD? = null
    var prefUserData: SharedPreferences? = null
    var editorUserData: SharedPreferences.Editor? = null
    private val mAuth: FirebaseAuth? = null
    private val mUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setCancellable(false).setAnimationSpeed(2).setBackgroundColor(R.color.black)
            .setDimAmount(0.5f)
    }
}