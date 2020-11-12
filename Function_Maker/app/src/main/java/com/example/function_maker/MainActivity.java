package com.example.function_maker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private View view;
    public EditText func_input;
    public EditText editText;

    private Bitmap bitmap;
    private Canvas canvas;

    private Paint paint;

    String[] fun = {"f(x)=x+1"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
        func_input = findViewById(R.id.func_input);
        editText = findViewById(R.id.editText);


    }

    /**
     * 输入的函数类似于 2x+3
     * @param x值
     */
    public void getFunctionValue(String x){
        String function = func_input.getText().toString();
        int result = 0;
        String tempnum = "";

        for(int i = 0; i < function.length(); i++){
            if(function.charAt(i) >= '0' || function.charAt(i) <= '9'){
                if(tempnum != null){

                }

                tempnum = tempnum + function.charAt(i);

            }
            if(function.charAt(i) == 'x'){
                String temp = null;
                for(int t = i+1; t < function.length(); t++){
                    temp = temp + function.charAt(t);
                }
                for(int t = 0; t < x.length(); t++){
                    function.charAt(i) = 
                }
            }

            if(function.charAt(i) == '+'){

            }
            if(function.charAt(i) == '/'){

            }
            if(function.charAt(i) == '-'){

            }
            if(function.charAt(i) == '*'){

            }
        }
    }


    public void drawGrid(Canvas canvas, Paint paint){

    }
}