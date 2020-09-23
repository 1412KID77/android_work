package com.example.wrod_book;

import androidx.appcompat.app.AppCompatActivity;

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


    }
}