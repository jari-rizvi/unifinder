package com.example.unifinder.RegisterPassenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unifinder.API.ApiClient;
import com.example.unifinder.API.ApiInterface;
import com.example.unifinder.R;
import com.google.android.material.textfield.TextInputEditText;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    ImageView btnBack;
    TextView btnRegister;
    KProgressHUD hud;
    ApiInterface apiService;
    SharedPreferences prefUserData;
    SharedPreferences.Editor editorUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);



        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setBackgroundColor(R.color.black)
                .setDimAmount(0.5f);
        findViews();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
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
        btnRegister = findViewById(R.id.btnSignup);
    }
//
//    private boolean Validate() {
//        if (edtMobileNo.getText().toString().trim().isEmpty()) {
//            edtMobileNo.setError("Please enter Phone No");
//            return false;
//        } else if (edtMobileNo.getText().toString().trim().length() != 11) {
//            edtMobileNo.setError("Please enter valid Phone No");
//            return false;
//        } else if (edtPass.getText().toString().trim().isEmpty()) {
//            edtPass.setError("Please enter Password");
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    private void LoginAPI(String mobile_no, String password) {
//        hud.show();
//        mobile_no = mobile_no.substring(1,11);
//        apiService = ApiClient.getClient(this).create(ApiInterface.class);
//        Call<String> callCard = apiService.Login(mobile_no, password);
//        String finalMobile_no = mobile_no;
//        callCard.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                hud.dismiss();
//                Log.e("response", "" + response);
//                if (response.code() == 200) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response.body());
//                        if (jsonObject.has("success")) {
//                            Toast.makeText(LoginScreen.this, jsonObject.getString("message").toString(), Toast.LENGTH_SHORT).show();
//
//                            boolean otp = jsonObject.getBoolean("otp");
//
//
//                            if (otp) {
//                                editorUserData.putString("token", jsonObject.getString("token"));
//                                editorUserData.putBoolean("isFirst", false);
//                                editorUserData.apply();
//                                Intent i = new Intent(LoginScreen.this, HomeScreen.class);
//                                startActivity(i);
//                                finish();
//                            } else {
//                                Intent i = new Intent(LoginScreen.this, VerifyOTPScreen.class);
//                                i.putExtra("mobileNo", finalMobile_no);
//                                i.putExtra("token", jsonObject.getString("token"));
//                                startActivity(i);
//                                finish();
//                            }
//                        } else {
//                            if (jsonObject.has("errors")) {
//                                Toast.makeText(LoginScreen.this, jsonObject.getJSONArray("errors").toString(), Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(LoginScreen.this, jsonObject.getString("message").toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    } catch (JSONException e) {
//                        hud.dismiss();
//                        e.printStackTrace();
//                    }
//                } else if (response.code() == 500) {
//                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                } else {
//                    try {
//                        JSONObject errorBody = new JSONObject(response.errorBody().string());
//                        if (errorBody.has("message")) {
//                            Toast.makeText(LoginScreen.this, errorBody.getString("message"), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(LoginScreen.this, "Server Error", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                hud.dismiss();
//            }
//        });
//    }
}