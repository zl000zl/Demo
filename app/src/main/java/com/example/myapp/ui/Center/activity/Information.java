package com.example.myapp.ui.Center.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapp.Package.BaseActivity;
import com.example.myapp.R;
import com.example.myapp.bank.Network;
import com.example.myapp.bank.OkResult;
import com.example.myapp.ui.Center.bean.Formation;
import com.example.myapp.ui.Center.bean.User;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Information extends BaseActivity {

    private EditText inUser;
    private RadioButton inBoy;
    private RadioButton inGirl;
    private EditText inId;
    private EditText inPhone;
    private Button inXiugai;
    private ImageButton inTx;
    private TextView tvSelectGallery;
    private TextView tvSelectCamera;

    private Bitmap head;
    private static String path = "/sdcard/myHead/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setTitle("个人信息");
        initView();
        initData();
        initXiugai();
    }

    private void initXiugai() {
        inTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTypeDialog();
            }
        });
        inXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = inUser.getText().toString().trim();
                String sex;
                if (inBoy.isChecked()){
                    sex="0";
                }else {
                    sex="1";
                }
                String phone_number = inPhone.getText().toString().trim();

                Formation formation = new Formation();
                formation.setNickName(Username);
                formation.setSex(sex);
                formation.setPhonenumber(phone_number);
                Network.doPut("/prod-api/api/common/user", new Gson().toJson(formation), new OkResult() {
                    @Override
                    public void success(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(Information.this, "修改成功！！！", Toast.LENGTH_SHORT).show();
                            Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
                                @Override
                                public void success(JSONObject jsonObject) {
                                    User user = new Gson().fromJson(jsonObject.optString("user"), User.class);
                                    inUser.setText("" + user.getNickName());
                                    if (user.getSex().equals("0")) {
                                        inBoy.setChecked(true);
                                    } else {
                                        inGirl.setChecked(true);
                                    }
                                    String id_number = user.getIdCard().substring(0, 2);
                                    id_number = id_number + "************";
                                    id_number = id_number + user.getIdCard().substring(user.getIdCard().length() - 4);
                                    inId.setText(id_number);

                                    inPhone.setText(user.getPhonenumber());
                                }
                            });
                        }else {
                            Toast.makeText(Information.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_select_photo, null);

        tvSelectGallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        tvSelectCamera = (TextView) view.findViewById(R.id.tv_select_camera);

        tvSelectGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK,null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent1,1);
                dialog.dismiss();
            }
        });
        tvSelectCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (ContextCompat.checkSelfPermission(Information.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(Information.this,new String[] {Manifest.permission.CAMERA},1);
               }else {
                   startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),999);
               }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            inTx.setImageBitmap((Bitmap)data.getExtras().get("data"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1 && grantResults.length>0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivityForResult(new Intent(MediaStore.ACTION_VIDEO_CAPTURE),999);
            }else {
                Toast.makeText(this, "获取权限失败！！！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initData() {
        Network.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void success(JSONObject jsonObject) {
                User user = new Gson().fromJson(jsonObject.optString("user"), User.class);
                inUser.setText("" + user.getNickName());
                if (user.getSex().equals("0")) {
                    inBoy.setChecked(true);
                } else {
                    inGirl.setChecked(true);
                }
                String id_number = user.getIdCard().substring(0, 2);
                id_number = id_number + "************";
                id_number = id_number + user.getIdCard().substring(user.getIdCard().length() - 4);
                inId.setText(id_number);

                inPhone.setText(user.getPhonenumber());
            }
        });
    }

    private void initView() {
        inUser = (EditText) findViewById(R.id.in_user);
        inBoy = (RadioButton) findViewById(R.id.in_boy);
        inGirl = (RadioButton) findViewById(R.id.in_girl);
        inId = (EditText) findViewById(R.id.in_id);
        inPhone = (EditText) findViewById(R.id.in_phone);
        inXiugai = (Button) findViewById(R.id.in_xiugai);
        inTx = (ImageButton) findViewById(R.id.in_tx);
        Bitmap bt = BitmapFactory.decodeFile(path+"head.jpg");
        if (bt != null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);
            inTx.setImageDrawable(drawable);
        }else {
        }
    }
}