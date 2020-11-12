package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class length extends AppCompatActivity{

    Spinner input_Spinner;
    Spinner output_Spinner;
    EditText input_EdiText;
    TextView output_textview;
    Button change;

    ArrayAdapter<String> input_adper;
    ArrayAdapter<String> output_adper;

    int position1 = 0;
    int position2 = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        input_EdiText = (EditText) findViewById(R.id.input_EditText);
        output_textview = (TextView) findViewById(R.id.output_textview);
        input_Spinner = (Spinner)findViewById(R.id.input_Spinner);
        output_Spinner = (Spinner)findViewById(R.id.output_spinner);
        change = (Button)findViewById(R.id.change);


        input_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id){
                //System.out.println("测试输出" +  "    " +  position + "   " + id);
                position1 = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        output_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        if(position1 == 0 && position2 == 0){
//            output_textview.setText(input_EdiText.getText());
//        }else if(position1 == 0 && position2 == 1){
//            output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000 + "");
//        }else if(position1 == 0 && position2 == 2){
//            output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10000 + "");
//        }else if(position1 == 0 && position2 == 3){
//            output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 100000 + "");
//        }else if(position1 == 0 && position2 == 4){
//            output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000 + "");
//        }
        change.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (position1 == 0 && position2 == 0) {
                    output_textview.setText(input_EdiText.getText());
                } else if (position1 == 0 && position2 == 1) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000 + "");
                } else if (position1 == 0 && position2 == 2) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10000 + "");
                } else if (position1 == 0 && position2 == 3) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 100000 + "");
                } else if (position1 == 0 && position2 == 4) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000 + "");
                } else if (position1 == 0 && position2 == 5) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000000 + "");
                } else if (position1 == 0 && position2 == 6) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000000 + "000");
                } else if (position1 == 1 && position2 == 0) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 1000 + "");
                } else if (position1 == 1 && position2 == 1) {
                    output_textview.setText(input_EdiText.getText().toString() + "");
                } else if (position1 == 1 && position2 == 2) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10 + "");
                } else if (position1 == 1 && position2 == 3) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 100 + "");
                } else if (position1 == 1 && position2 == 4) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000 + "");
                } else if (position1 == 1 && position2 == 5) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000 + "");
                } else if (position1 == 1 && position2 == 6) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000000 + "");
                } else if (position1 == 2 && position2 == 0) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 10000 + "");
                } else if (position1 == 2 && position2 == 1) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 10 + "");
                } else if (position1 == 2 && position2 == 2) {
                    output_textview.setText(input_EdiText.getText().toString() + "");
                } else if (position1 == 2 && position2 == 3) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10 + "");
                } else if (position1 == 2 && position2 == 4) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 100 + "");
                } else if (position1 == 2 && position2 == 5) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 100000 + "");
                } else if (position1 == 2 && position2 == 6) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 100000000 + "");
                }else if (position1 == 3 && position2 == 0) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 100000 + "");
                } else if (position1 == 3 && position2 == 1) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 100 + "");
                } else if (position1 == 3 && position2 == 2) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 10 + "");
                } else if (position1 == 3 && position2 == 3) {
                    output_textview.setText(input_EdiText.getText().toString() + "");
                } else if (position1 == 3 && position2 == 4) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10 + "");
                } else if (position1 == 3 && position2 == 5) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10000 + "");
                } else if (position1 == 3 && position2 == 6) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 10000000 + "");
                } else if (position1 == 4 && position2 == 0) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 1000000 + "");
                } else if (position1 == 4 && position2 == 1) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 1000 + "");
                } else if (position1 == 4 && position2 == 2) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 100 + "");
                } else if (position1 == 4 && position2 == 3) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 10 + "");
                } else if (position1 == 4 && position2 == 4) {
                    output_textview.setText(input_EdiText.getText().toString() + "");
                } else if (position1 == 4 && position2 == 5) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000 + "");
                } else if (position1 == 4 && position2 == 6) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000000 + "");
                }else if (position1 == 5 && position2 == 0) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 1000000000 + "");
                } else if (position1 == 5 && position2 == 1) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 1000000 + "");
                } else if (position1 == 5 && position2 == 2) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 100000 + "");
                } else if (position1 == 5 && position2 == 3) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 10000 + "");
                } else if (position1 == 5 && position2 == 4) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) / 1000 + "");
                } else if (position1 == 5 && position2 == 5) {
                    output_textview.setText(input_EdiText.getText().toString() + "");
                } else if (position1 == 5 && position2 == 6) {
                    output_textview.setText(Double.parseDouble(input_EdiText.getText().toString()) * 1000 + "");
                }
            }

    });



//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String spinner1 = (String)input_Spinner.getItemAtPosition(position);
//        String spinner2 = (String)input_Spinner.getItemAtPosition(position);
////        Toast.makeText(length.this, spinner1, Toast.LENGTH_SHORT).show();
////        Toast.makeText(length.this, spinner2, Toast.LENGTH_SHORT).show();
////        System.out.println(spinner1);
//
//
//
////        if(spinner1.equals("千米") && spinner2.equals("千米")){
////            System.out.println("测试成功");
////        }else if(spinner1.equals("千米") && spinner2.equals("分米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("厘米")){
////            System.out.println("2222222222222222222222222222222222222222222222222222222");
////        }
////        else if(spinner1.equals("千米") && spinner2.equals("毫米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }else if(spinner1.equals("千米") && spinner2.equals("千米")){
////
////        }
//    }
//
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}}
