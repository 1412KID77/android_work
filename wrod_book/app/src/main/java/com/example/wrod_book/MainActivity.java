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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

    FloatingActionButton fbutton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//listview
        listview = (ListView)findViewById(R.id.listview);

//搜索单词
        search_edittext = (EditText)findViewById(R.id.search_word);
        search_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                db = dbHelper.getReadableDatabase();
                String[] project = {
                        word.w.COLUMN_NAME_WORD,
                        word.w.COLUMN_NAME_V,
                        word.w.COLUMN_NAME_NOTES,
                };
                String sortOrder = word.w.COLUMN_NAME_WORD + " DESC";
                String selection = word.w.COLUMN_NAME_WORD + " LIKE ?";
                String[] selectionargs = {"%" + search_edittext.getText().toString() + "%"};
                Cursor c = db.query(word.w.TABLE_NAME, project, selection, selectionargs, null, null, sortOrder);


                List<Map<String, Object>> new_list= new ArrayList<>();
                while (c.moveToNext()){
                    Map<String, Object> new_map = new HashMap<>();
                    new_map.put(word.w.COLUMN_NAME_WORD, c.getString(c.getColumnIndex(word.w.COLUMN_NAME_WORD)));
                    new_map.put(word.w.COLUMN_NAME_V, c.getString(c.getColumnIndex(word.w.COLUMN_NAME_V)));
                    new_map.put(word.w.COLUMN_NAME_NOTES, c.getString(c.getColumnIndex(word.w.COLUMN_NAME_NOTES)));
//                    new_map.put(word.w.COLUMN_NAME_EXAMPLE, c.getString(c.getColumnIndex(word.w.COLUMN_NAME_EXAMPLE)));
                    new_list.add(new_map);
                }
                c.close();


                SimpleAdapter new_adapter = new SimpleAdapter(MainActivity.this, new_list, R.layout.item,
                        new String[]{word.w.COLUMN_NAME_WORD,word.w.COLUMN_NAME_V,word.w.COLUMN_NAME_NOTES}, new int[]{R.id.word,R.id.v,R.id.notes});
                listview.setAdapter(new_adapter);
                return true;
            }
        });


        //链接数据库
        setDbHelper();

//添加按钮
        fbutton = (FloatingActionButton)findViewById(R.id.add_button);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_intent = new Intent(MainActivity.this, add_word.class);
 //               System.out.println("是否异常--------------------------------------------------");
                startActivity(add_intent);

            }
        });


//设置显示界面
        setListView();

//点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, show_details.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

//listview长按事件
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                                Toast.makeText(MainActivity.this, "选择了" + items[which], Toast.LENGTH_LONG).show();
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
                                                    //取得用户输入内容，注意findViewById前面的viewDialog，表示在该view里面进行查找
                                             //       System.out.println("输出22222--------------------------------------------------------"  +  del_input_word.getText().toString());
                                                    String sq="update "+  word.w.TABLE_NAME +" set "+ word.w.COLUMN_NAME_WORD + "=?," + word.w.COLUMN_NAME_V + "=?,"+ word.w.COLUMN_NAME_NOTES +"=? where " + word.w.COLUMN_NAME_WORD +"='"+ del_input_word.getText().toString()+"'";
                                                    db.execSQL(sq, new String[]{re_edit_word.getText().toString(), re_edit_v.getText().toString(), re_edit_notes.getText().toString()});
                                                    //重新展示列表
                                                    setListView();

                                                }
                                            })
                                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });
                                    tempb.create().show();

                                }else{

                                    db = dbHelper.getReadableDatabase();
                                    db.delete(word.w.TABLE_NAME, word.w.COLUMN_NAME_WORD + "=?", new String[]{del_input_word.getText().toString()});
                            //重新展示列表
                                    setListView();
                                }
                            }
                        });
                builder.create().show();
                return true;
            }
        });




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


//, word.w.COLUMN_NAME_EXAMPLE
        Cursor cursor = db.query(word.w.TABLE_NAME, new String[]{word.w.COLUMN_NAME_WORD, word.w.COLUMN_NAME_V, word.w.COLUMN_NAME_NOTES},
                null,null,null,null,null);

        while(cursor.moveToNext()){
            Map<String, Object> map = new HashMap<>();
            map.put(word.w.COLUMN_NAME_WORD, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_WORD)));
            map.put(word.w.COLUMN_NAME_V, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_V)));
            map.put(word.w.COLUMN_NAME_NOTES, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_NOTES)));
//            map.put(word.w.COLUMN_NAME_EXAMPLE, cursor.getString(cursor.getColumnIndex(word.w.COLUMN_NAME_EXAMPLE)));
            lists.add(map);
        }
        cursor.close();


//listview  测试无误
        adapeter = new SimpleAdapter(MainActivity.this, lists, R.layout.item,

                //,word.w.COLUMN_NAME_EXAMPLE    , R.id.example
                new String[]{word.w.COLUMN_NAME_WORD,word.w.COLUMN_NAME_V,word.w.COLUMN_NAME_NOTES}, new int[]{R.id.word,R.id.v,R.id.notes});
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