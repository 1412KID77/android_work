package com.example.contentprovider_wordbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView ListView_show;
    Button Button_add;


    public static final String AUTHORITY = "com.example.myapplication";
//    public static final String CONTENT_PROVIDER = "ContentProvider_implement";
    public static final Uri CONTENT_URI_WORD = Uri.parse("content://" + AUTHORITY + "/" + Word.w.TABLE_NAME);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView_show = (ListView)findViewById(R.id.ListView_show);
        Button_add = (Button)findViewById(R.id.button_add);

        setListView_show();


//添加按钮
        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, activity_add.class);
                startActivity(intent);

            }
        });

//长按删除
        ListView_show.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final TextView del_input_word = (TextView) view.findViewById(R.id.word);
                final TextView del_input_v = (TextView) view.findViewById(R.id.v);
                final TextView del_input_notes = (TextView) view.findViewById(R.id.notes);

//                System.out.println("输出--------------------------------------------------------"  +  del_input_word.getText().toString());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final String[] items = {"修改", "删除"};// 存放选项的数组
                builder.setTitle("列表对话框");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //    Toast.makeText(MainActivity.this, "选择了" + items[which], Toast.LENGTH_LONG).show();
                        if(which == 0){
                            AlertDialog.Builder tempb = new AlertDialog.Builder(MainActivity.this);

                            final View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit, null, false);

                            final EditText re_edit_word = viewDialog.findViewById(R.id.edit_word);
                            final EditText re_edit_v = viewDialog.findViewById(R.id.edit_v);
                            final EditText re_edit_notes = viewDialog.findViewById(R.id.edit_notes);

                            re_edit_word.setText(del_input_word.getText().toString());
                            re_edit_v.setText(del_input_v.getText().toString());
                            re_edit_notes.setText(del_input_notes.getText().toString());



                            tempb.setTitle("自定义对话框")
                                    .setView(viewDialog)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            ContentValues values = new ContentValues();
                                            values.put(Word.w.COLUMN_NAME_WORD, re_edit_word.getText().toString());
                                            values.put(Word.w.COLUMN_NAME_V, re_edit_v.getText().toString());
                                            values.put(Word.w.COLUMN_NAME_NOTES, re_edit_notes.getText().toString());

                                            //取得用户输入内容，注意findViewById前面的viewDialog，表示在该view里面进行查找
                                            //       System.out.println("输出22222--------------------------------------------------------"  +  del_input_word.getText().toString());
//                                            String sq="update "+  word.w.TABLE_NAME +" set "+ word.w.COLUMN_NAME_WORD + "=?," + word.w.COLUMN_NAME_V + "=?,"+ word.w.COLUMN_NAME_NOTES +"=? where " + word.w.COLUMN_NAME_WORD +"='"+ del_input_word.getText().toString()+"'";
//                                            db.execSQL(sq, new String[]{re_edit_word.getText().toString(), re_edit_v.getText().toString(), re_edit_notes.getText().toString()});
                                            getContentResolver().update(CONTENT_URI_WORD, values, Word.w.COLUMN_NAME_WORD +"= ?", new String[]{del_input_word.getText().toString()});
                                            //重新展示列表
                                            setListView_show();

                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            tempb.create().show();

                        }else{
                            getContentResolver().delete(CONTENT_URI_WORD, Word.w.COLUMN_NAME_WORD + "= ?", new String[]{del_input_word.getText().toString()});

                            //重新展示列表
                            setListView_show();
                        }
                    }
                });
                builder.create().show();
                return false;
            }
        });
//        ListView_show.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                final TextView del_input_word = (TextView) view.findViewById(R.id.word);
//                final TextView del_input_v = (TextView) view.findViewById(R.id.v);
//                final TextView del_input_notes = (TextView) view.findViewById(R.id.notes);
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                final String[] items = {"修改", "删除"};
//                builder.setTitle("列表对话框");
//                builder.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if(which == 0){
//
//                            AlertDialog.Builder tempb = new AlertDialog.Builder(MainActivity.this);
//
//                            final View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.edit, null, false);
//
//                            final EditText re_edit_word = viewDialog.findViewById(R.id.edit_word);
//                            final EditText re_edit_v = viewDialog.findViewById(R.id.edit_v);
//                            final EditText re_edit_notes = viewDialog.findViewById(R.id.edit_notes);
//
//                            final ContentValues values = new ContentValues();
//                            values.put(Word.w.COLUMN_NAME_WORD, re_edit_word.getText().toString());
//                            values.put(Word.w.COLUMN_NAME_V, re_edit_v.getText().toString());
//                            values.put(Word.w.COLUMN_NAME_NOTES, re_edit_notes.getText().toString());
//
//
//
//                            tempb.setTitle("自定义对话框")
//                                    .setView(viewDialog)
//                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            //取得用户输入内容，注意findViewById前面的viewDialog，表示在该view里面进行查找
//                                            //       System.out.println("输出22222--------------------------------------------------------"  +  del_input_word.getText().toString());
//                                            getContentResolver().update(CONTENT_URI_WORD, values, Word.w.COLUMN_NAME_WORD, new String[]{re_edit_word.getText().toString()});
//                                            //重新展示列表
//                                            setListView_show();
//
//                                        }
//                                    })
//                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                        }
//                                    });
//                            tempb.create().show();
//
//
//                            setListView_show();
//                        }
//                        else{
//                            getContentResolver().delete(CONTENT_URI_WORD, Word.w.COLUMN_NAME_WORD, new String[]{del_input_word.getText().toString()});
//                            setListView_show();
//                        }
//                    }
//                });
//
//
//                return false;
//            }
//        });

    }







    /**
     * 在mainactivity中显示数据库中的数据
     *
     * adapter未添加数据  必须在访问数据库后
     */

    public void setListView_show(){

        System.out.println("------------------------------------------------------------------------" + CONTENT_URI_WORD);

        List<Map<String, Object>> list_item= new ArrayList<>();
        SimpleAdapter adapter;



        Cursor cursor = getContentResolver().query(CONTENT_URI_WORD,
                new String[]{Word.w.COLUMN_NAME_WORD, Word.w.COLUMN_NAME_V, Word.w.COLUMN_NAME_NOTES}, null, null, null);
        if(cursor == null){
            System.out.println("------------------------------------------------------------------cursor is null");
        }

        while (cursor.moveToNext()){
            Map<String, Object> map = new HashMap<>();
            map.put(Word.w.COLUMN_NAME_WORD, cursor.getString(cursor.getColumnIndex(Word.w.COLUMN_NAME_WORD)));
            map.put(Word.w.COLUMN_NAME_V,cursor.getString(cursor.getColumnIndex(Word.w.COLUMN_NAME_V)));
            map.put(Word.w.COLUMN_NAME_NOTES, cursor.getString(cursor.getColumnIndex(Word.w.COLUMN_NAME_NOTES)));
            list_item.add(map);
        }

        adapter = new SimpleAdapter(MainActivity.this, list_item, R.layout.item,
                new String[]{Word.w.COLUMN_NAME_WORD, Word.w.COLUMN_NAME_V,Word.w.COLUMN_NAME_NOTES}, new int[]{R.id.word,R.id.v,R.id.notes});
        ListView_show.setAdapter(adapter);
    }


}