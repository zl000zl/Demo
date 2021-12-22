package com.example.myapp.ui.Center.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class Change_password extends BaseActivity {

    private EditText oldPassword;
    private EditText newsPassword;
    private Button passwordChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("修改密码");
        initView();
        initData();
    }

    private void initData() {
        passwordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password_new = newsPassword.getText().toString().trim();
                String password_old = oldPassword.getText().toString().trim();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("newPassword",password_new).put("oldPassword",password_old);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPut("/prod-api/api/common/user/resetPwd", jsonObject.toString(), new OkResult() {
                    @Override
                    public void success(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(Change_password.this, "修改成功,请退出后查看！！！", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(Change_password.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        oldPassword = (EditText) findViewById(R.id.old_password);
        newsPassword = (EditText) findViewById(R.id.news_password);
        passwordChange = (Button) findViewById(R.id.password_change);
    }
}