package com.example.myapp.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.MainActivity;
import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class Enter_Activity extends BaseActivity {

    private EditText enterUser;
    private EditText enterPass;
    private TextView enterSign;
    private Button enterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_);
        setTitle("登录界面");
        initView();
        initData();
    }

    private void initData() {
       enterLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String User = enterUser.getText().toString().trim();
               String Password = enterPass.getText().toString().trim();
               JSONObject jsonObject = new JSONObject();
               try {
                   jsonObject.put("username",User).put("password",Password);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               Network.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
                   @Override
                   public void success(JSONObject jsonObject) {
                       if (jsonObject.optInt("code")==200){
                           Toast.makeText(Enter_Activity.this, "登录成功！！！", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(Enter_Activity.this, MainActivity.class));
                       }else {
                           Toast.makeText(Enter_Activity.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                       }
                   }
               });
           }
       });
    }

    private void initView() {
        enterUser = (EditText) findViewById(R.id.enter_user);
        enterPass = (EditText) findViewById(R.id.enter_pass);
        enterSign = (TextView) findViewById(R.id.enter_sign);
        enterLogin = (Button) findViewById(R.id.enter_login);
    }
}