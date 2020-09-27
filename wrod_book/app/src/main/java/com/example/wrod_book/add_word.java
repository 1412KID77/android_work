package com.example.wrod_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_word extends AppCompatActivity {
    EditText worde;
    EditText vv;
    EditText notes;
    EditText example;

    TextView result;

    Button ok_button;
    Button cancel_button;

    word cword = new word();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
 //       System.out.println("是否异常--------------------------------------------------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        worde = (EditText)findViewById(R.id.word_input);
        vv = (EditText)findViewById(R.id.input_v);
        notes = (EditText)findViewById(R.id.input_notes);
        example = (EditText)findViewById(R.id.input_examples);

        result = (TextView)findViewById(R.id.result);

        ok_button = (Button)findViewById(R.id.ok_button);
        cancel_button = (Button)findViewById(R.id.cancel_button);

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();

                if(!worde.getText().toString().isEmpty()){

                    cword.setWord(worde.getText().toString());
                    cword.setV(vv.getText().toString());
                    cword.setNotes(notes.getText().toString());
                    cword.setExample(example.getText().toString());

                    values.put(word.w.COLUMN_NAME_WORD, cword.getWord());
                    values.put(word.w.COLUMN_NAME_V, cword.getV());
                    values.put(word.w.COLUMN_NAME_NOTES, cword.getNotes());
                    values.put(word.w.COLUMN_NAME_EXAMPLE, cword.getExample());

//插入操作
                    SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
                    db.insert(word.w.TABLE_NAME, null, values);
                    Toast.makeText(add_word.this, "添加成功", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(add_word.this, MainActivity.class);
                    startActivity(intent);

                }
                else{
                    result.setText("输入错误，未输入词汇");
                }

            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_word.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}