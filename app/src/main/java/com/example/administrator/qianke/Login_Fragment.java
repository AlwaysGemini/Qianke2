package com.example.administrator.qianke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Register_Fragment register_fragment;

    private EditText account;
    private EditText password_edit;
    private Button login;
    private TextView register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, null);

        login = view.findViewById(R.id.login);
        account = view.findViewById(R.id.account);
        password_edit = view.findViewById(R.id.password);
        register = view.findViewById(R.id.register);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        password_edit.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    String phone_number = "17718150790";
    String password = "123456";

    private void postJson() {
        Map<String,String> map = new HashMap<>();
        map.put("phone_number", phone_number);
        map.put("password", password);
        JSONObject json = new JSONObject(map);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json.toString());
        //Toast.makeText(this,requestBody.toString(),Toast.LENGTH_LONG);
        /*RequestBody requestBody = new FormBody.Builder()
                .add("phone_number", phone_number)
                .add("password", password).build();*/
        Request request = new Request.Builder()
                .url("http://2cj2982897.51mypc.cn/login")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //Toast.makeText(this,response.body().string(),Toast.LENGTH_LONG);
            System.out.println(response.body().string());
        } catch (Exception e) {
            Log.i("json------", e.getMessage() + "/" + e.getCause());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                new Thread(){
                    @Override
                    public void run() {
                        postJson();
                    }
                }.start();
                break;
            case R.id.register:
                register_fragment = new Register_Fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, register_fragment).commitAllowingStateLoss();
                break;
        }
    }
}
