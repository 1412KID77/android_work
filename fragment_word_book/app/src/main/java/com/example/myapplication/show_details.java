package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class show_details extends AppCompatActivity {
    TextView word_show;
    TextView v_show;
    TextView notes_show;
    TextView example_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);

        word_show = (TextView)findViewById(R.id.word_view);
        v_show = (TextView)findViewById(R.id.v_view);
        notes_show = (TextView)findViewById(R.id.notes_view);
        example_show = (TextView)findViewById(R.id.example_view);

        Intent intent = getIntent();
        int positon = intent.getIntExtra("position", 0);

//读取数据库内容

        DatabaseHelpler dbHelper = new DatabaseHelpler(this);
        BlankFragment.db = dbHelper.getReadableDatabase();
        Cursor cursor = BlankFragment.db.query(word.w.TABLE_NAME, new String[]{word.w.COLUMN_NAME_WORD, word.w.COLUMN_NAME_V, word.w.COLUMN_NAME_NOTES, word.w.COLUMN_NAME_EXAMPLE},
                null,null,null,null,null);
        int i = 0;
        while(!cursor.moveToPosition(positon)){
            cursor.moveToNext();
        }

        word_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_WORD)));
        v_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_V)));
        notes_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_NOTES)));
        example_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_EXAMPLE)));

    }
}