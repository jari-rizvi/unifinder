package com.example.unifinder.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ApiClient {
 
    public static final String BASE_URL = "https://redeemcouponx.com/transport/public/api/";

    private static Retrofit retrofit = null;
    static SharedPreferences  prefUserData;


    public static Retrofit getClient(Context context) {
        prefUserData = context.getSharedPreferences("USER_DATA",MODE_PRIVATE);

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS).addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer " + prefUserData.getString("token",""))
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.e("try--=====",""+retrofit);
        return retrofit;
    }
}