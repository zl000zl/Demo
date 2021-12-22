package com.example.myapp.ui.Center.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class Feedback extends BaseActivity {

    private EditText feedEt;
    private TextView sizeSum;
    private Button submit;
    private Button muchOpinion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        setTitle("意见反馈");
        initView();
        initData();
    }

    private void initData() {
        feedEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text_size = feedEt.getText().toString().trim();
                sizeSum.setText(text_size.length()+"/150");
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_size = feedEt.getText().toString().trim();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("content",text_size);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Network.doPost("/prod-api/api/common/feedback", jsonObject.toString(), new OkResult() {
                    @Override
                    public void success(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(Feedback.this, "意见反馈成功！！！", Toast.LENGTH_SHORT).show();
                            feedEt.setText("");
                        }else {
                            Toast.makeText(Feedback.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        feedEt = (EditText) findViewById(R.id.feed_et);
        sizeSum = (TextView) findViewById(R.id.size_sum);
        submit = (Button) findViewById(R.id.submit);
        muchOpinion = (Button) findViewById(R.id.much_opinion);
    }
}