package com.example.administrator.qianke;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register_Fragment extends Fragment implements View.OnClickListener {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Button register;
    private EditText account;
    private EditText password;

    private boolean isSuccess = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, null);

        register = view.findViewById(R.id.register);
        account = view.findViewById(R.id.account);
        password = view.findViewById(R.id.password);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                new Thread() {
                    @Override
                    public void run() {
                        isSuccess();
                    }
                }.start();
                new Thread(){
                    @Override
                    public void run() {
                        postJson();
                    }
                }.start();
                break;
        }
    }

    String phone_number = "17718150790";
    String password_ = "123456";

    private void postJson() {
        Map<String,String> map = new HashMap<>();
        map.put("phone_number", phone_number);
        map.put("password", password_);
        JSONObject json = new JSONObject(map);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json.toString());
        //Toast.makeText(this,requestBody.toString(),Toast.LENGTH_LONG);
        /*RequestBody requestBody = new FormBody.Builder()
                .add("phone_number", phone_number)
                .add("password", password).build();*/
        Request request = new Request.Builder()
                .url("http://2cj2982897.51mypc.cn/register")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //Toast.makeText(this,response.body().string(),Toast.LENGTH_LONG);
            System.out.println(response.body().string());
            JSONObject reponseJson = new JSONObject(response.body().string());
            if (reponseJson.get("code").equals("410010")){
                isSuccess = true;
            }else if (reponseJson.get("code").equals("410020")){
                isSuccess = false;
            }
        } catch (Exception e) {
            Log.i("json------", e.getMessage() + "/" + e.getCause());
        }
    }

    private void isSuccess(){
        while (true){
            if (isSuccess){
                final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
                builder.setTitle("请输入您收到的短信验证码")
                        .setPlaceholder("")
                        .setInputType(InputType.TYPE_CLASS_TEXT)
                        .addAction("取消", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                dialog.dismiss();
                            }
                        })
                        .addAction("确定", new QMUIDialogAction.ActionListener() {
                            @Override
                            public void onClick(QMUIDialog dialog, int index) {
                                CharSequence text = builder.getEditText().getText();
                                if (text != null && text.length() > 0) {
                                    Toast.makeText(getActivity(), "您的昵称: " + text, Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(), "请填入昵称" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
                break;
            }
        }


    }
}
