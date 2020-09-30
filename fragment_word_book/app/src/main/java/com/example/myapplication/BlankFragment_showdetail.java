package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class BlankFragment_showdetail extends Fragment {
    TextView word_show;
    TextView v_show;
    TextView notes_show;
    TextView example_show;




//    public Fragment getFragment(){
//        return this.blankFragment_showDetail;
//    }
    int position;
    public BlankFragment_showdetail(int position){
        this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.show_detail, container, false);

        word_show =view.findViewById(R.id.word_view);
        v_show = view.findViewById(R.id.v_view);
        notes_show = view.findViewById(R.id.notes_view);
        example_show = view.findViewById(R.id.example_view);


//     将activity改为fragment后  需要改正部分
//        Intent intent = getActivity().getIntent();
//        int positon = intent.getIntExtra("position", 0);

//读取数据库内容

        DatabaseHelpler dbHelper = new DatabaseHelpler(view.getContext());
        BlankFragment.db = dbHelper.getReadableDatabase();
        Cursor cursor = BlankFragment.db.query(word.w.TABLE_NAME, new String[]{word.w.COLUMN_NAME_WORD, word.w.COLUMN_NAME_V, word.w.COLUMN_NAME_NOTES, word.w.COLUMN_NAME_EXAMPLE},
                null,null,null,null,null);
        int i = 0;
        while(!cursor.moveToPosition(position)){
            cursor.moveToNext();
        }

        word_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_WORD)));
        v_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_V)));
        notes_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_NOTES)));
        example_show.setText(cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_EXAMPLE)));

        return view;
    }
}