package com.example.hongkaixiang.kuaihuozedan.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hongkaixiang.kuaihuozedan.R;
import com.example.hongkaixiang.kuaihuozedan.net.RequestInterFace;
import com.example.hongkaixiang.kuaihuozedan.net.WebUrlBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                requestUrl();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);


    }

    private void requestUrl() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://wap.minpion.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterFace requestInterFace = build.create(RequestInterFace.class);
        Call<WebUrlBean> call = requestInterFace.getCall();

        call.enqueue(new Callback<WebUrlBean>() {
            @Override
            public void onResponse(Call<WebUrlBean> call, Response<WebUrlBean> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    WebUrlBean body = response.body();
                    if ("0".equals(body.getStatus())) {
                        fail(body.getError());
                    } else {
                        success();
                    }
                } else {
                    fail("");
                }
            }

            @Override
            public void onFailure(Call<WebUrlBean> call, Throwable t) {
                fail("");
            }
        });

    }

    private void success() {
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
    }

    private void fail(final String error) {
        GuideActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GuideActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

        //关闭app
        this.finish();
    }
}
