package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    Button b00;
    Button point;
    Button ce;
    Button equle;
    Button percent;
    Button multiplication;
    Button division;
    Button minus;
    Button add;
    Button gen;
    Button fang;
    Button fen;
    Button left_bracket;
    Button right_bracket;
    Button ln;
    Button log;
    Button pai;
    Button sin;
    Button cos;
    Button tan;

    boolean gao_sin;
    boolean gao_cos;
    boolean gao_tan;


    //操作括号入栈
    final Stack<Character> stack = new Stack<>();

    String sign = new String();
    //第二个操作数
    String second_num = new String();

    TextView textview;
    TextView result;
    TextView finalresult;
// 第一次完整计算得到的数
    double result_first_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);



        b0 = (Button)findViewById(R.id.b0);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4= (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        b7 = (Button)findViewById(R.id.b7);
        b8 = (Button)findViewById(R.id.b8);
        b9= (Button)findViewById(R.id.b9);
        b00 = (Button)findViewById(R.id.b00);
        point = (Button)findViewById(R.id.point);
        ce = (Button)findViewById(R.id.ce);
        equle = (Button)findViewById(R.id.equle);
        percent = (Button)findViewById(R.id.percent);
        minus = (Button)findViewById(R.id.minus);
        multiplication = (Button)findViewById(R.id.multiplication);
        division = (Button)findViewById(R.id.division);
        add = (Button)findViewById(R.id.add);

        gen = (Button)findViewById(R.id.gen);
        fang = (Button)findViewById(R.id.fang);
        fen = (Button)findViewById(R.id.fen);

        left_bracket = (Button)findViewById(R.id.left_bracket);
        right_bracket = (Button)findViewById(R.id.right_bracket);
        ln = (Button)findViewById(R.id.ln);
        log = (Button)findViewById(R.id.log);
        sin = (Button)findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        pai = (Button)findViewById(R.id.pai);



        textview = (TextView) findViewById(R.id.firstnum);
        result = (TextView) findViewById(R.id.result);
        finalresult = (TextView)findViewById(R.id.finalresult);

//点击事件
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b00.setOnClickListener(this);
        point.setOnClickListener(this);
        ce.setOnClickListener(this);
        equle.setOnClickListener(this);
        percent.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        add.setOnClickListener(this);

        fang.setOnClickListener(this);
        fen.setOnClickListener(this);
        gen.setOnClickListener(this);

        left_bracket.setOnClickListener(this);
        right_bracket.setOnClickListener(this);
        ln.setOnClickListener(this);
        log.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        pai.setOnClickListener(this);



    }
//菜单
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.length:
                Intent intent1 = new Intent(MainActivity.this, length.class);
                startActivity(intent1);
                break;
            case R.id.volume:
                Intent intent2 = new Intent(MainActivity.this, volume.class);
                startActivity(intent2);
                break;
            case R.id.binary_conversion:
                Intent intent3 = new Intent(MainActivity.this, binary_conversion.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b0:
                textview.setText(con("0"));
                //判断符号是否为空  即是否开始运算
                if (sign.isEmpty() == false){
                    //对加数处理
                    String str = textview.getText().toString();
                    result.setText(result());
                }
                break;
            case R.id.b1:
                textview.setText(con("1"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }

                break;
            case R.id.b2:
                textview.setText(con("2"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b3:
                textview.setText(con("3"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b4:
                textview.setText(con("4"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b5:
                textview.setText(con("5"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b6:
                textview.setText(con("6"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b7:
                textview.setText(con("7"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b8:
                textview.setText(con("8"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b9:
                textview.setText(con("9"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.b00:
                textview.setText(con("00"));
                if (sign.isEmpty() == false){
                    result.setText(result());
                }
                break;
            case R.id.point:
                if (textview.getText().toString().isEmpty()){
                    textview.setText("error!");
                }else{
                    textview.setText(textview.getText().toString() + ".");
                }
                break;
            case R.id.ce:
                textview.setText("0");
                result.setText("");
                finalresult.setText("");
                sign = "";
                gao_sin = false;
                gao_tan = false;
                gao_cos = false;
                break;

            case R.id.percent:
                if(textview.getText().toString().equals("0") || textview.getText().toString().equals("0.")){
                    textview.setText("0");
                }
                else{
                    if(result.getText().toString().isEmpty()) {
                        String s = textview.getText().toString();
                        double n = Double.parseDouble(s);
                        textview.setText(n / 100 + "");
                    }
                    else{
                        String s = result.getText().toString();
                        double n = Double.parseDouble(s);
                        textview.setText(n / 100 + "");
                    }
                }
                    break;

            case R.id.minus:
//                String s1 = textview.getText().toString();
//                textview.setText(s1 +"-");
                only_sign("-");
                sign="-";
                second_num = "";
                save_result_first_num();
                break;
            case R.id.multiplication:
//                String s2 = textview.getText().toString();
                //textview.setText(s2 +"*");
                only_sign("*");
                sign="*";
                second_num = "";
                save_result_first_num();
                break;
            case R.id.division:
//                String s3 = textview.getText().toString();
                //textview.setText(s3 +"/");
                only_sign("/");
                sign="/";
                second_num = "";
                save_result_first_num();
                break;
            case R.id.add:
//                String s4 = textview.getText().toString();
               // textview.setText(s4 +"+");
                only_sign("+");
                sign="+";
                second_num = "";
                save_result_first_num();
                break;
            case R.id.equle:
                if(gao_tan || gao_sin || gao_cos){
                    result.setText(gao_result());
                }else{
                    if(result.getText().toString().isEmpty()){
                        finalresult.setText(textview.getText().toString());
                    }else {
                        finalresult.setText(result.getText().toString());
                    }
                    textview.setText("0");
                    result.setText("");

                    sign="";
                }

                break;
            case R.id.fang:
                if(result.getText().toString().isEmpty()){
                    double d = Double.parseDouble(textview.getText().toString());
                    System.out.println("输出在这" + d);
                    result.setText(Math.pow(d,2) + "");
                }else{
                    double d = Double.parseDouble(result.getText().toString());
                    result.setText(Math.pow(d,2) + "");
                }
                break;
            case R.id.gen:
                if(result.getText().toString().isEmpty()){
                    double d = Double.parseDouble(textview.getText().toString());
                    result.setText(Math.pow(d,0.5) + "");
                }else{
                    double d = Double.parseDouble(result.getText().toString());
                    result.setText(Math.pow(d,0.5) + "");
                }
                break;

            case R.id.fen:
                if(result.getText().toString().isEmpty()){
                    double d = Double.parseDouble(textview.getText().toString());
                    result.setText(1 / d + "");
                }else{
                    double d = Double.parseDouble(result.getText().toString());
                    result.setText(1/d + "");
                }
                break;

//横屏后的按钮事件
//            case R.id.left_bracket:
//                if(textview.getText().toString().equals("0")){
//                    textview.setText("(");
//                }
//                else{
//                    textview.setText(con("("));
//                }
//                stack.push('(');
//                check_bracket();
//                break;
//            case R.id.right_bracket:
//                if(textview.getText().toString().equals("0")){
//                    result.setText("error!");
//
//                }
//                else{
//                    textview.setText(con(")"));
//                }
//                stack.push(')');
//                check_bracket();
//                break;
            case R.id.sin:
                gao_sin = true;
                textview.setText(con("sin"));

                break;
            case R.id.cos:
                gao_sin = true;
                textview.setText(con("cos"));

                break;
            case R.id.tan:
                gao_tan = true;
                textview.setText(con("tan"));

                break;
            case R.id.ln:
                break;
            case R.id.log:
                break;
            case R.id.pai:
                break;

        }
    }
//    public String getSecond_num(){
//        String secondn = new String();
//        String s = textview.getText().toString();
//        int index = s.lastIndexOf(sign);
//        secondn = s.substring(index + 1, s.length());
//        System.out.println(s);
//        System.out.println("输出在这" + secondn);
//        return secondn;
//    }

  //括号检查
//    public void check_bracket(){
//        if(stack.size() % 2 != 0){
//            result.setText("error!");
//        }else{
//            int left_b = 0;
//            int right_b = 0;
//            for(Character c : stack){
//                if(c == '('){
//                    left_b++;
//                }
//                else{
//                    right_b++;
//                }
//            }
//            if(left_b != right_b){
//                result.setText("error!");
//            }
//        }
//
//    }



//判断只有一个运算符号
    public void only_sign(String sign){
        String s = textview.getText().toString();
        if(s.charAt(s.length() - 1)=='+' || s.charAt(s.length() - 1)=='/' || s.charAt(s.length() - 1)=='*' ||s.charAt(s.length() - 1)=='-'){
            s = s.substring(0, s.length() - 1);
            s = s + sign;
        }else{
            s = s + sign;
        }
        textview.setText(s);
        this.sign = sign;
    }

    public void save_result_first_num(){
        if(result.getText().toString().isEmpty()){
            String s = textview.getText().toString();

            s = s.substring(0, s.length() - 1);
            result_first_num = Double.parseDouble(s);
        }else {
            result_first_num = Double.parseDouble(result.getText().toString());
        }
    }
    //延续数字
    public String con(String str){
        String num =  textview.getText().toString();
        if(num.equals("0") || num.equals("00")){
            num = str;
        }
        else{
            num = num + str;
        }
        return num;
    }

    public String gao_result(){
        String res = textview.getText().toString();

        if(res.length() > 3){
            if(gao_sin == true){
                res = res.substring(3,res.length());
                System.out.println("测试----------------------------------" + res);
                return Math.sin(Integer.parseInt(res)) + "";
            }else if(gao_tan == true){
                res = res.substring(3,res.length());
                return Math.tan(Integer.parseInt(res)) + "";
            }else if(gao_cos == true){
                res = res.substring(3,res.length());
                return Math.cos(Integer.parseInt(res)) + "";
            }
        }
        return null;


    }

    //第二个数与第一个数运算后返回结果
    public String result(){
        String res = textview.getText().toString();
//        System.out.println("textview 文本" + res);
//        String[] num = res.split("\\" + sign);
        double n1 = result_first_num;
//        double n2 = Double.parseDouble(num[1]);
        int index = res.lastIndexOf(sign);
        String s2 = res.substring(index + 1, res.length());
        double n2 = Double.parseDouble(s2);
       // System.out.println(res);
        // 第二次输入运算符号
//        double n2;
//        if(result.getText().toString().isEmpty()){
//            res = res.substring(0,res.length() - 2);
//            n2 = Double.parseDouble(res);
//        }
//        else{
//            n2 = Double.parseDouble(result.getText().toString());
//        }
//        res = res.substring(0,res.length() - 2);
//        System.out.println("第一个数为" + res);
//        n2 = Double.parseDouble(res);

//        double n2 = Double.parseDouble(num[0]);
//        System.out.println(n2);
//        double n1 = Double.parseDouble(s);


        if(sign.equals("+")){
            res = (n1 + n2) + "";
        } else if (sign.equals("-")) {
            res = (n1 - n2) + "";
        }else if (sign.equals("*")){
            res = (n1 * n2) + "";
        }else if(sign.equals("/")){
            res = (n1 / n2) + "";
        }

        return res;
    }
}



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
