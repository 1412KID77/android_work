package com.example.four;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.write);
        button2 = findViewById(R.id.read);
        editText = findViewById(R.id.edit);

        final SharedPreferences sp = getSharedPreferences("malijun", MODE_PRIVATE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor edit = getSharedPreferences("malijun",MODE_PRIVATE).edit();
                String value = editText.getText().toString();
                edit.putString("xuehao", value);
                edit.commit();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, sp.getString("xuehao","34245245"),Toast.LENGTH_LONG).show();
            }
        });
    }
}