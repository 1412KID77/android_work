package com.example.sharedpreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText ageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) this.findViewById(R.id.et1);
        ageText = (EditText) this.findViewById(R.id.et2);

        Button button = (Button) this.findViewById(R.id.bt1);
        Button showButton = (Button) this.findViewById(R.id.bt2);

        final SharedPreferences sharedPreferences = getSharedPreferences("kid", Activity.MODE_PRIVATE);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = nameText.getText().toString();

                Editor editor = sharedPreferences.edit(); //获取编辑器
                editor.putString("name", nameText.getText().toString());
                editor.putString("age", ageText.getText().toString());
                editor.commit();//提交修改
                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
            }
        });
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = sharedPreferences.getString("name", "");
                String ageValue = sharedPreferences.getString("age", "");
                Toast.makeText(MainActivity.this, "姓名：" + nameValue + "，年龄：" + ageValue, Toast.LENGTH_LONG).show();
            }
        });


    }


}