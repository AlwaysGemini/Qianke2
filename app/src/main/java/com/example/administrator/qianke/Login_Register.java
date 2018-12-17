package com.example.administrator.qianke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Login_Register extends AppCompatActivity {

    private Login_Fragment login_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register);

        login_fragment = new Login_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,login_fragment).commitAllowingStateLoss();
    }
}
