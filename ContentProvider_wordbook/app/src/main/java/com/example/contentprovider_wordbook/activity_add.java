package com.example.contentprovider_wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_add extends AppCompatActivity {
    EditText EditText_word;
    EditText EditText_v;
    EditText EditText_notes;
    EditText EditText_example;
    Button Button_ok;

    public static final String AUTHORITY = "com.example.myapplication";
    public static final Uri CONTENT_URI_WORD = Uri.parse("content://" + AUTHORITY + "/" + Word.w.TABLE_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        EditText_word = findViewById(R.id.EditText_word);
        EditText_v = findViewById(R.id.EditText_v);
        EditText_notes = findViewById(R.id.EditText_notes);
        EditText_example = findViewById(R.id.EditText_example);

        Button_ok = findViewById(R.id.Button_ok);

        Button_ok.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(Word.w.COLUMN_NAME_WORD,EditText_word.getText().toString());
                values.put(Word.w.COLUMN_NAME_V,EditText_v.getText().toString());
                values.put(Word.w.COLUMN_NAME_NOTES,EditText_notes.getText().toString());
                values.put(Word.w.COLUMN_NAME_EXAMPLE,EditText_example.getText().toString());

                getContentResolver().insert(CONTENT_URI_WORD, values);


                Intent intent = new Intent(activity_add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}