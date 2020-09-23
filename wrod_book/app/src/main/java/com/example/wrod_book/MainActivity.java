package com.example.wrod_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

//搜索功能
    EditText search_edittext;
//新建数据库
    static DatabaseHelpler dbHelper;
    static SQLiteDatabase db;

    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//listview
        listview = (ListView)findViewById(R.id.listview);

//搜索单词
        search_edittext = (EditText)findViewById(R.id.search_word);

        //链接数据库
        setDbHelper();


//设置显示界面
        setListView();


        listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, show_details.class);
                startActivity(intent);
            }
        });
    }


//add_button
    public void onAddClick(View view) {
        Intent intent = new Intent("start_add_word");
        startActivity(intent);
    }

//adapter
    public void setListView(){
        SimpleAdapter adapeter;
        List<Map<String, Object>> lists;


        lists = new ArrayList<>();


//        String[] word = {"kid","fad","name","te"};
//        String[] v = {"n","b","v","das"};
//        String[] notes = {"哎到","撒打发","收到","额外"};
//        String[] example = {"asdfghjk","poiuytre","uhgfd","lkjhgfdsa"};
//
//
//        for(int i = 0; i < word.length; i++){
//            Map<String, Object> map = new HashMap<>();
//            map.put("word", word[i]);
//            map.put("v", v[i]);
//            map.put("notes", notes[i]);
//            map.put("example", example[i]);
//            lists.add(map);
//
//        }

//-------------------------------------------------------------------------------
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(word.w.TABLE_NAME, new String[]{word.w.COLUMN_NAME_WORD, word.w.COLUMN_NAME_V, word.w.COLUMN_NAME_NOTES, word.w.COLUMN_NAME_EXAMPLE},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            word new_word = new word();
            Map<String, Object> map = new HashMap<>();
            map.put(word.w.COLUMN_NAME_WORD, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_WORD)));
            map.put(word.w.COLUMN_NAME_V, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_V)));
            map.put(word.w.COLUMN_NAME_NOTES, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_NOTES)));
            map.put(word.w.COLUMN_NAME_EXAMPLE, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_EXAMPLE)));
            lists.add(map);
        }
        cursor.close();


//listview  测试无误
        adapeter = new SimpleAdapter(MainActivity.this, lists, R.layout.item,
                new String[]{word.w.COLUMN_NAME_WORD,word.w.COLUMN_NAME_V,word.w.COLUMN_NAME_NOTES,word.w.COLUMN_NAME_EXAMPLE}, new int[]{R.id.word,R.id.v,R.id.notes, R.id.example});
        listview.setAdapter(adapeter);
    }
//连接数据库
    public void setDbHelper(){
        dbHelper = new DatabaseHelpler(this);

    }



//menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //设置对框框属性
                builder.setTitle("帮助")
                        .setMessage("没什么帮助");
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, "普通对话框 取消", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(MainActivity.this, "普通对话框 确定", Toast.LENGTH_SHORT).show();
//                            }
//                        });


                //创建并显示对话框k'j
                builder.create().show();
                break;

        }
        return true;
    }



}