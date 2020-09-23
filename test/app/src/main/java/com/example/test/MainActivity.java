package com.example.test;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE_STUDENT_ZHANGSAN = 0;
    final int REQUEST_CODE_STUDENT_LISI = 1;
    final int REQUEST_CODE_STUDENT_TEACHER = 2;

    Button phone;
    Button write_data;


    String SharedPreferencesFileName = "information";
    String key_userName = "userName";
    String key_loginDate = "loginDate";
    String key_userType = "userType";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private static String TAG = "LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(SharedPreferencesFileName, MODE_PRIVATE);
        editor = preferences.edit();



        Log.i(TAG, "(1)onCreate()");

        write_data = (Button)findViewById(R.id.write_data);
        write_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        phone = (Button)findViewById(R.id.phone);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:10086"));
                startActivity(intent1);
            }
        });




//        启动StudentActivity
        Button buttonStudentZhangSan = findViewById(R.id.buttonStudentZhangSan);
        buttonStudentZhangSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                intent.putExtra("name", "测试");
                startActivityForResult(intent, REQUEST_CODE_STUDENT_ZHANGSAN);
            }
        });


    }

    public void onWriteDataClick(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strLoginDate = simpleDateFormat.format(new Date());
        //写入键值对
        editor.putString(key_userName, "Zhang san");
        editor.putString(key_loginDate, strLoginDate);
        editor.putInt(key_userType, 1);

        editor.apply();
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();

    }




    //log 记录
    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "(2)onStart()");
    };

    /*
     * 在onStart()后被调用，用于恢复onSaveInstanceState()保存的用户界面信息
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "(3)onRestoreInstanceState()");
    };


    /*
     * 在活动周期开始时被调用，恢复被onPause()停止的用于界面更新的资源
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "(4)onResume()");
    }

    /*
     * 在onPause后被调用，保存界面的临时信息
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "(5)onSaveInstanceState()");
    }

    /*
     * 在重新进入可视生命周期前被调用，载入界面所需要的更改信息
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "(6)onRestart()");
    }

    /*
     * 在活动生命周期结束时被调用，用来保存持久的数据或释放占用的资源
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "(7)onPause()");
    }

    /*
     * 在可视化生命周期结束时被调用，用来释放占用的资源
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "(8)onStop()");
    }

    /*
     * 在完全生命周期结束时被调用，释放资源，包括线程、数据连接等
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "(9)onDestory()");
    }




    //    @Override
    @SuppressLint("SetTextI18n")
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textView = findViewById(R.id.textviewReceivedData);
        switch (requestCode) {
            case REQUEST_CODE_STUDENT_ZHANGSAN:
                if (resultCode == 0) textView.setText( data.getStringExtra("name") + "成功");
                else {
                    assert data != null;
                    textView.setText(data.getStringExtra("name") + "成功");
                }
                break;
            case REQUEST_CODE_STUDENT_LISI:
                if (resultCode == 0) textView.setText( data.getStringExtra("name") + "失败");
                else textView.setText(data.getStringExtra("name") + "失败");
                break;
            case REQUEST_CODE_STUDENT_TEACHER:
                textView.setText(data.getStringExtra("name") + "完成处理");

                break;
        }
    }
}