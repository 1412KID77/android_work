package com.example.phonecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button phone ;

    private ListView lv_lxr;
    private Button b_name;
    private ContentResolver cr;
    private List<Map<String, Object>> datalistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得ListView
        lv_lxr = (ListView) findViewById(R.id.lv_lxr);
        //得到访问者
        cr = getContentResolver();
        //定义一个接收联系人姓名和电话号码的集合
        datalistView = new ArrayList<>();
        Uri uri=Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor= cr.query(uri,null,null,null,null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            Uri uriData=Uri.parse("content://com.android.contacts/raw_contacts/"+id+"/data");
            Cursor contactData= cr.query(uriData,null,null,null,null);
            //用来装姓名
            String aa="";
            //用来装号码
            String bb="";
            while(contactData.moveToNext()){
                String type=contactData.getString(contactData.getColumnIndex("mimetype"));
                //如果获取的是vnd.android.cursor.item/phone_v2则是号码
                if(type.equals("vnd.android.cursor.item/phone_v2")){
                    bb=contactData.getString(contactData.getColumnIndex("data1"));
                    //如果获取的是vnd.android.cursor.item/name则是姓名
                }else if(type.equals("vnd.android.cursor.item/name")) {
                    aa=contactData.getString(contactData.getColumnIndex("data1"));
                }
            }
            //将用户名和号码放入Map集合中
            Map<String,Object> map=new HashMap<>();
            map.put("images",aa);
            map.put("titles",bb);
            datalistView.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this, datalistView,R.layout.activity_xs,new String[]{"images","titles"},new int[]{R.id.tv_name,R.id.tv_telephone});
        lv_lxr.setAdapter(adapter);
    }
}