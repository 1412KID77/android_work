package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class volume extends AppCompatActivity {

    Button calculation;
    EditText length;
    EditText height;
    EditText width;

    TextView shu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        shu = (TextView)findViewById(R.id.shu);
        calculation = (Button)findViewById(R.id.calculation);
        length = (EditText)findViewById(R.id.length_edit);
        height = (EditText)findViewById(R.id.height_edit);
        width = (EditText)findViewById(R.id.width_edit);


        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shu.setText(getVolume());
            }
        });
    }



    public String getVolume(){
        double l = Double.parseDouble(length.getText().toString());
        double h = Double.parseDouble(height.getText().toString());
        double w = Double.parseDouble(width.getText().toString());
        return (l*h*w) + "";
    }
}