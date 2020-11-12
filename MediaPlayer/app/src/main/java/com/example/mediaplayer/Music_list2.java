package com.example.mediaplayer;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
        import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
        import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.LinkedHashMap;
        import java.util.List;
        import java.util.Map;

public class Music_list2 extends AppCompatActivity {

    //绑定服务
    MediaPlayer_Service.LocalBinder mediaPlayer_localBinder;
;
    ListView ListView_songList;
    SimpleAdapter adapter;
//存储音乐信息
    List<Map<String, Object>> file_info  ;
//共享数据
    ContentResolver contentResolver ;

    final int REQUEST_PERMISSION=0;
//绑定服务需要用到改方法
    Intent intent;

    String nextSong = new String();
    String lastSong = new String();
    //传递歌单
    ArrayList<String> songlist = new ArrayList<>();
    ArrayList<String> pathlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list2);
        ListView_songList = findViewById(R.id.listView_songList);

        //动态申请权限  ------------------------------------------------------------------ 还有些问题
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int hasReadPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            List<String> permission = new ArrayList<>();
            if(hasReadPermission != PackageManager.PERMISSION_GRANTED){
                permission.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if(!permission.isEmpty()){
                //
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
            }
        }
        //绑定服务  为列表点击事件做准备

        intent = new Intent(getApplicationContext(), MediaPlayer_Service.class);
        bindService(intent,  serviceConnection , BIND_AUTO_CREATE);

        contentResolver = getApplication().getContentResolver();
        getMusicData();
        setShow();
//        getMusicData();
        ListView_songList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Music_list2.this);
                final String[] items = {"删除"};
                builder.setTitle("选项");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                file_info.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.create().show();
                return true;
            }
        });

        ListView_songList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                String lasttitle =  file_info.get(position - 1).get("title").toString();
                String nexttitle =  file_info.get(position + 1).get("title").toString();

                String title =  ((TextView)view.findViewById(R.id.tit)).getText().toString();
                String singer = ((TextView)view.findViewById(R.id.editor)).getText().toString();
               // System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++" + lasttitle + "     " + nexttitle);
                String path = new String();
                //显示路径



                //Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
                System.out.println("-------------------------------------------------" + title);
                //查询符合条件的文件
                Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.TITLE + "=?", new String[]{title},null);
//                cursor.moveToNext();
                cursor.moveToNext();
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
////下一曲
//                Cursor c1 = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.TITLE + "=?", new String[]{lasttitle},null);
//                c1.moveToNext();
//                lastSong = c1.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
////上一曲
//                Cursor c2 = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.TITLE + "=?", new String[]{nexttitle},null);
//                c2.moveToNext();
//                nextSong = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

//                System.out.println("-------------------------------------------------" + path +"-------- " +lasttitle + "-------------"+nexttitle);
                mediaPlayer_localBinder.setCurrent_music(path);

//                mediaPlayer_localBinder.nextSong(nexttitle);
//                mediaPlayer_localBinder.lastSont(lasttitle);
//                mediaPlayer_localBinder.ChuShiHua(path,lasttitle,lasttitle);
                //刷新适配器
//                setListShow(position);

                mediaPlayer_localBinder.setPlay();
                adapter.notifyDataSetChanged();

//                startService(intent);

//                mediaPlayer_localBinder.play();
                Intent i = new Intent(Music_list2.this, MainActivity.class);
                i.putExtra("title", title);
                i.putStringArrayListExtra("songlist", songlist);
                i.putStringArrayListExtra("pathlist",pathlist);
                startActivity(i);
            }
        });

    }



   ServiceConnection serviceConnection = new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           mediaPlayer_localBinder = (MediaPlayer_Service.LocalBinder)service;
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
           mediaPlayer_localBinder = null;
       }
   };

//    public void setListShow(int position){
//        ListView_songList.setSelection(position);
//    }


//动态申请权限回调------------------------------------------------确认权限。
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_PERMISSION:{
                for(int i = 0; i < permissions.length; i++){
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        System.out.println("permission -------------------------  permissison granted" + permissions[i]);
                    }
                    else{
                        System.out.println("permission -------------------------  permissison denied" + permissions[i]);
                    }
                }
                break;
            }
            default:{
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }


        }
    }
//界面展示
    public void setShow(){
        adapter = new SimpleAdapter(this, file_info, R.layout.item,new String[]{"title", "artist"}, new int[]{R.id.tit, R.id.editor});
        ListView_songList.setAdapter(adapter);

    }
//访问媒体数据库
    public void getMusicData(){
        file_info = new ArrayList<>();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,null);
        while(cursor.moveToNext()){

            Map<String, Object> map = new HashMap<>();
            //获取路径
            String path= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            //获取名字
            String song = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String musicalbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));

//            System.out.println("------------------------------------------" + cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            //可继续添加信息  暂时做测试
            map.put("artist", artist);
            map.put("title",song);
            file_info.add(map);
            songlist.add(song);
            pathlist.add(path);
        }
//        System.out.println("---------------------------------" + file_info.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saomiao:
                List<Map<String, Object>> tempList = file_info;
                getMusicData();
                setShow();
                AlertDialog.Builder builder = new AlertDialog.Builder(Music_list2.this);
                builder.setMessage("新增歌曲" + (file_info.size() - tempList.size()) + "首");

                builder.show();

                return true;
            case R.id.help:
                AlertDialog.Builder buidler = new AlertDialog.Builder(Music_list2.this);
                buidler.setTitle("帮助");
                buidler.setMessage("没什么帮助");
                buidler.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
