package com.example.test;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE_STUDENT_ZHANGSAN = 0;
    final int REQUEST_CODE_STUDENT_LISI = 1;
    final int REQUEST_CODE_STUDENT_TEACHER = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //启动StudentActivity
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textView = findViewById(R.id.textviewReceivedData);
        switch (requestCode) {
            case REQUEST_CODE_STUDENT_ZHANGSAN:
                if (resultCode == 0) textView.setText( data.getStringExtra("name") + "成功");
                else textView.setText(data.getStringExtra("name") + "成功");
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