package com.example.administrator.qianke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private EditText account;
    private EditText password_edit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        account = findViewById(R.id.account);
        password_edit = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        postJson();
                    }
                }.start();

            }
        });
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

}
