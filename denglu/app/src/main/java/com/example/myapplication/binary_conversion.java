package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class binary_conversion extends AppCompatActivity {

    private Spinner spinner;
    private EditText input_edittext;
    private ArrayAdapter<String> adapter;

    private TextView binary;
    private TextView octal_number_system;
    private TextView decimal_system;
    private TextView hexadecimal;

    private TextView warning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_conversion);

        input_edittext = (EditText)findViewById(R.id.input_binary_editText);
        spinner = (Spinner)findViewById(R.id.input_binary_spinner);

        binary = (TextView)findViewById(R.id.binary);
        octal_number_system = (TextView)findViewById(R.id.octal_number_system);
        decimal_system = (TextView)findViewById(R.id.decimal_system);
        hexadecimal = (TextView)findViewById(R.id.hexadecimal);
        warning = (TextView)findViewById(R.id.warning);

        input_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warning.setText("");
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {



            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println("在这输出位置" + position);
                System.out.println("在这输出position"+position);
                switch (position){
                    case 1:
                        String n0 = input_edittext.getText().toString();

                        if(n0.isEmpty()){
                            n0 = "0";
                        }
                        for(int i = 0; i < n0.length(); i++){
                            if(Integer.parseInt(n0.charAt(i) + "") > 1 ){
                                System.out.println("这里输出" + n0.charAt(i));
                                binary.setText("0");
                                octal_number_system.setText("0");
                                decimal_system.setText("0");
                                hexadecimal.setText("0");
                                input_edittext.setText("");
                                warning.setText("输入错误");
                                return ;
                            }
                        }
                        System.out.println(n0);
                        binary.setText(n0 + "");
                        octal_number_system.setText(Integer.toOctalString(Integer.valueOf(n0 , 2)) + "");
                        decimal_system.setText(Integer.valueOf(n0,2) + "");
                        hexadecimal.setText(Integer.toHexString(Integer.valueOf(n0 , 2)) + "");
                        break;
                    case 2:
                        String n1 = input_edittext.getText().toString();
                        if(n1.isEmpty()){
                            n1 = "0";
                        }
                        for(int i = 0; i < n1.length(); i++){
                            if(Integer.parseInt(n1.charAt(i) + "") > 7 ){
                                binary.setText("0");
                                octal_number_system.setText("0");
                                decimal_system.setText("0");
                                hexadecimal.setText("0");
                                input_edittext.setText("");
                                warning.setText("输入错误");
                                return ;
                            }
                        }

                        binary.setText(Integer.toBinaryString(Integer.valueOf(n1 , 8)) + "");
                        octal_number_system.setText(n1 + "");
                        decimal_system.setText(Integer.valueOf(n1, 8) + "");
                        hexadecimal.setText(Integer.toHexString(Integer.valueOf(n1, 8)) + "");
                        break;
                    case 3:
                        String n2 = input_edittext.getText().toString();
                        if(n2.isEmpty()){
                            n2 = "0";
                        }
                        for(int i = 0; i < n2.length(); i++){
                            if(Integer.parseInt(n2.charAt(i) + "") > 9 ){
                                binary.setText("0");
                                octal_number_system.setText("0");
                                decimal_system.setText("0");
                                hexadecimal.setText("0");
                                input_edittext.setText("");
                                warning.setText("输入错误");
                                return ;
                            }
                        }
                        binary.setText(Integer.toBinaryString(Integer.parseInt(n2)) + "");
                        octal_number_system.setText(Integer.toOctalString(Integer.parseInt(n2)) + "");
                        decimal_system.setText(n2 + "");
                        hexadecimal.setText(Integer.toHexString(Integer.parseInt(n2)) + "");
                        break;
                    case 4:
                        String n3 = input_edittext.getText().toString();
                        if(n3.isEmpty()){
                            n3 = "0";
                        }
                        for(int i = 0; i < n3.length(); i++){
                        if(n3.charAt(i)  > 'f' ){
                            binary.setText("0");
                            octal_number_system.setText("0");
                            decimal_system.setText("0");
                            hexadecimal.setText("0");
                            input_edittext.setText("");
                            warning.setText("输入错误");
                            return ;
                        }
                    }
                        binary.setText(Integer.toBinaryString(Integer.valueOf(n3 , 16)) + "");
                        octal_number_system.setText(Integer.toOctalString(Integer.valueOf(n3 , 16)) + "");
                        decimal_system.setText(Integer.valueOf(n3, 16) + "");
                        hexadecimal.setText(n3 + "");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}