package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

//        Button denglu = (Button)findViewById(R.id.denglu);
//
//        final EditText name = (EditText)findViewById(R.id.name);
//        final EditText password = (EditText)findViewById(R.id.password);
//
//        denglu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String n = name.getText().toString();
//                String pd = password.getText().toString();
//                if(n.equals("kid") && pd.equals("123")){
//                    setContentView(R.layout.calculator);
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"登陆失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}