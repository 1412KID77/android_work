package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,ServiceConnection {

   Button floatingActionButton_play;
   Button floatingActionButton_lastSong;
   Button floatingActionButton_nextSong;
    TextView textView_currentTime;
    TextView textView_totalLength;
    Button button_songList;
    TextView textView_songTitle;
    Switch switch_randomPlay;
    SeekBar seekBar;
// 定时器
    public Timer timer;
  public boolean sui;
//互斥变量，判断是否人为出发进度条
    boolean isSeekbarChangeing;

//    MediaPlayer mediaPlayer;
//    Control_Song control_song;
//调用服务
    MediaPlayer_Service.LocalBinder mediaPlayer_localBinder;
//    MediaPlayer_Service mediaPlayer_service;

//播放状态 用于切换按钮动作 及判断使用服务的相关方法 主要有play 和pause

    Intent intent_all;


    Intent info_fromMusic;
    ArrayList<String> songlist = new ArrayList<>();
    ArrayList<String> pathlist = new ArrayList<>();
    String current_music = new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        floatingActionButton_play = findViewById(R.id.floatingActionButton_play);
        floatingActionButton_nextSong = findViewById(R.id.floatingActionButton_nextSong);
        floatingActionButton_lastSong = findViewById(R.id.floatingActionButton_lastSong);
        button_songList = findViewById(R.id.button_songList);
        textView_songTitle = findViewById(R.id.textView_songTitle);
        switch_randomPlay = findViewById(R.id.switch_randomPlay);
        seekBar = findViewById(R.id.seekbar);
        textView_currentTime = findViewById(R.id.textView_currentTime);
        textView_totalLength = findViewById(R.id.textView_totalLength);

        floatingActionButton_play.setOnClickListener(this);
        floatingActionButton_nextSong.setOnClickListener(this);
        floatingActionButton_lastSong.setOnClickListener(this);
        button_songList.setOnClickListener(this);
        switch_randomPlay.setOnCheckedChangeListener(this);
        seekBar.setOnSeekBarChangeListener(this);

//        mediaPlayer = MediaPlayer.create(this, R.raw.letter);
//传递context  失败
//        mediaPlayer_service = new MediaPlayer_Service();
//        mediaPlayer_service.setContext(getApplication());
//        control_song = new Control_Song(mediaPlayer);

        System.out.println("----------------------------------------------------------onCreate");

//绑定服务
         intent_all = new Intent(this, MediaPlayer_Service.class);
        bindService(intent_all, this, BIND_AUTO_CREATE);
//传递播放状态
        //使用bindservice传递参数，所以取消intent传递
//        intent.putExtra("station", mediaPlayer.isPlaying());
//        System.out.println("-----------------------------------------第二次重启");

       //从Music_list传递给过来的音乐列表和当前播放音乐   每次调用会更新
        info_fromMusic = getIntent();
        songlist = info_fromMusic.getStringArrayListExtra("songlist");
        pathlist = info_fromMusic.getStringArrayListExtra("pathlist");
        current_music = info_fromMusic.getStringExtra("title");
//        if(list != null) {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + list.size());
//        }
        textView_songTitle.setText(current_music);




//        startService(intent_all);

        System.out.println("------------------------------------------------------------------------------------------");

//        if(mediaPlayer_localBinder != null){
//            if(mediaPlayer_localBinder.getStation()){
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
//                station = false;
//            }
//            else {
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
//                station = true;
//            }
//        }
    }
//ServiceConnected


//    @Override
//    protected void onResume() {                                                                                     
//
//        if(mediaPlayer_localBinder != null){
//
//            if(mediaPlayer_localBinder.getStation()){
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
//                station = false;
//            }
//            else {
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
//                station = true;
//            }
//        }else{
//            super.onResume();
//        }
//    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mediaPlayer_localBinder = (MediaPlayer_Service.LocalBinder) service;
        System.out.println("----------------------------onserviceConnencted");
//        setFloatingActionButton_play();
        if(mediaPlayer_localBinder.current_music != null) {
            setSeekBar();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


    public void setSeekBar(){
        /*if(mediaPlayer_localBinder.getStation()){
            floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
        }else{
            floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
        }*/

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isSeekbarChangeing) {
                    seekBar.setProgress((int) mediaPlayer_localBinder.getPosition() / 1000);
                }
                if(sui==false) {
                    String path = new String();
                    if (seekBar.getMax() == seekBar.getProgress()) {
                        for (int i = 0; i < songlist.size(); i++) {
                            if (songlist.get(i).equals(current_music)) {
                                current_music = songlist.get(i + 1);
                                path = pathlist.get(i + 1);
                                break;
                            }
                        }
                        mediaPlayer_localBinder.setCurrent_music(path);
                        mediaPlayer_localBinder.setPlay();

                    }
                }else{
                    int min = 1;
                    int max = pathlist.size()-1;
                    Random random= new Random();
                    int b=random.nextInt(max-min)+min;
                    String path = pathlist.get(b);
                    mediaPlayer_localBinder.setCurrent_music(path);
                    System.out.println(path);
                    mediaPlayer_localBinder.setPlay();
                }


            }
        }, 0, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButton_play:
                // 传递播放状态
//                System.out.println("---------------------------------------------------------------测试" );
  //              if(mediaPlayer_localBinder.mediaPlayer == null) {
//                mediaPlayer_localBinder.setPlay();

                if(current_music != null) {
                  /*  if (mediaPlayer_localBinder.getStation()) {
                        floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
                        mediaPlayer_localBinder.pause();
                    } else {
                        floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
                        mediaPlayer_localBinder.play();
                    }*/
                }else{
                    mediaPlayer_localBinder.setPlay();
                }


              /*  if(mediaPlayer_localBinder.getStation()){
                    floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
                }else{
                    floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
                }*/
                break;
            case R.id.floatingActionButton_nextSong:
//                mediaPlayer_localBinder.nextSong();
                String path = new String();
                if(songlist != null && current_music != null){
                    for(int i = 0; i < songlist.size(); i++){
                        if(songlist.get(i).equals(current_music)){
                             current_music = songlist.get(i + 1);
                             path = pathlist.get(i+1);
                             break;
                        }
                    }
                    mediaPlayer_localBinder.setCurrent_music(path);
                    mediaPlayer_localBinder.setPlay();

                    textView_songTitle.setText(current_music);
                 /*   if(mediaPlayer_localBinder.getStation()){
                        floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
                    }else{
                        floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
                    }*/
                }

                break;
            case R.id.floatingActionButton_lastSong:
//                mediaPlayer_localBinder.lastSont();
                String last = new String();
                if(songlist != null && current_music != null){
                    for(int i = 0; i < songlist.size(); i++){
                        if(songlist.get(i).equals(current_music)){
                            current_music = songlist.get(i - 1);
                            last = pathlist.get(i-1);
                            break;
                        }
                    }
                    mediaPlayer_localBinder.setCurrent_music(last);
                    mediaPlayer_localBinder.setPlay();

                    textView_songTitle.setText(current_music);
                   /* if(mediaPlayer_localBinder.getStation()){
                        floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
                    }else{
                        floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
                    }*/
                }

                break;
            case R.id.button_songList:
                Intent intent = new Intent(MainActivity.this, Music_list2.class);
                startActivity(intent);
                break;
        }
    }
//    public void setFloatingActionButton_play(){
//       System.out.println("---------------------------------------------------------------测试" + mediaPlayer_localBinder.getStation() );
//
//            if (mediaPlayer_localBinder.getStation()) {
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
//            } else {
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        if (!isSeekbarChangeing) {
//                            seekBar.setProgress((int) mediaPlayer_localBinder.getPosition() / 1000);
//                        }
//                    }
//                }, 0, 100);
//            }
//    }

//生命周期测试-------------------------------------------------------------
    @Override
    protected void onStart() {
        super.onStart();
       // setSeekBar();

        System.out.println("----------------------------------------------------------onstart");
 //       setFloatingActionButton_play();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("----------------------------------------------------------onResume");
  //     setFloatingActionButton_play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("----------------------------------------------------------onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("----------------------------------------------------------onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("----------------------------------------------------------onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        setFloatingActionButton_play();
        System.out.println("----------------------------------------------------------onrestart");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            sui=true;
            Toast.makeText(this, "开", Toast.LENGTH_SHORT).show();
        }else{
            sui=false;
            Toast.makeText(this, "关", Toast.LENGTH_SHORT).show();
        }
    }


//解决绑定服务多开问题   ----------------------------------------------------------------------------------------------问题未解决
    public void onBackPressed(){
        Intent setIntent = new Intent(Intent.ACTION_MAIN);  //手机主界面
        setIntent.addCategory(Intent.CATEGORY_HOME);    //当前应用的主界面（MainActivity）   一个intent只能由一个activity  但是能有多个category
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

//seekBar监听器
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //获取音乐总时长
        int song_timeLength = (int) mediaPlayer_localBinder.getMusicDuration() / 1000;
        //获取当前播放位置
        int position = (int) mediaPlayer_localBinder.getPosition() / 1000;
        //开始时间
        int total_min = song_timeLength / 60;
        int total_sec = song_timeLength % 60;
        int current_min = position / 60;
        int current_sec = position % 60;

        seekBar.setMax(song_timeLength);

        textView_totalLength.setText(String.format("%02d:%02d" , total_min,total_sec));
        textView_currentTime.setText(String.format("%02d:%02d",current_min,current_sec));


    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isSeekbarChangeing = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isSeekbarChangeing = false;
        mediaPlayer_localBinder.setPosition(seekBar.getProgress() * 1000);

        int min = (int) ((mediaPlayer_localBinder.getPosition() / 1000) / 60);
        int sec = (int) ((mediaPlayer_localBinder.getPosition() / 1000) % 60);
        textView_currentTime.setText(String.format("%02d:%02d",min, sec));
    }




}