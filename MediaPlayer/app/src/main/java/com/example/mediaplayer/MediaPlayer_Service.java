package com.example.mediaplayer;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.zip.Inflater;

public class MediaPlayer_Service extends Service {
   // MediaPlayer mediaPlayer;
    LocalBinder localBinder = new LocalBinder();


//播放状态
//    boolean station;

//    FloatingActionButton floatingActionButton_play;
//    FloatingActionButton floatingActionButton_lastSong;
//    FloatingActionButton floatingActionButton_nextSong;
//inflator
//    LayoutInflater inflater = (LayoutInflater)getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//    View view = inflater.inflate(R.layout.activity_main, null);

//构造方法  为获取mainactivity中的context   注：这里必须使用空构造方法  否则报错
//    public MediaPlayer_Service(){
//
//    }
//    public MediaPlayer_Service(Context con){
//        context = con;
//        System.out.println("--------------------------------------------------" + context.toString());
//    }
    String nextSong = new String();
    String lastSont = new String();

    MediaPlayer mediaPlayer = new MediaPlayer();
    public class LocalBinder extends Binder{
//        public void set_isPlaying(boolean isPlaying){
//            station = isPlaying;
//        }
        //当前播放音乐
        String current_music = null;


        MediaPlayer_Service getMediaPlayer_service(){
            return MediaPlayer_Service.this;
        }

        public void setCurrent_music(String current_music){
            this.current_music = current_music;
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(localBinder.current_music));
//            if(current_music == null){
//                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.letter);
//            }else{
//                mediaPlayer.pause();
//                mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(localBinder.current_music));
//                localBinder.play();
//            }

        }

        public void ChuShiHua(String current, String lastsong, String nextsong){
            nextSong = nextsong;
            lastSont = lastsong;
            current_music = current;

        }

        public void play(){
            mediaPlayer.start();




        }
        public void pause(){
            mediaPlayer.pause();
        }
        //获取文件总长度
        public long getMusicDuration(){
            return mediaPlayer.getDuration();
        }
        //获取当前进度
        public long getPosition(){
            return mediaPlayer.getCurrentPosition();
        }

        //重新设置播放进度
        public void setPosition(int position){
            mediaPlayer.seekTo(position);
        }
        public boolean getStation(){
            boolean temp = mediaPlayer.isPlaying();
            return temp;
        }

        public void setPlay(){
            if (current_music == null) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.letter);
            } else {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(current_music));
                play();
            }
        }

//        public void nextSong(){
//
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(localBinder.current_music));
//        }
//        public void lastSont(){
//
//            mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(localBinder.current_music));
//        }

    }
//方式2：传递context  失败
//    public void setContext(Context con){
//        context = con;
//        System.out.println("-------------------------------------------------------onCreate" + context);
//    }

//测试失败
//        public void playChoose(){
//            if(mediaPlayer.isPlaying()){
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_play);
//                mediaPlayer.pause();
//            }else{
//                floatingActionButton_play.setImageResource(R.drawable.ic_media_pause);
//                mediaPlayer.start();
//            }
//        }

//测试成功例子
//        public boolean getStation(){
//            return mediaPlayer.isPlaying();
//        }
//        public void play(){
//            mediaPlayer.start();
//        }
//        public void pause(){
//            mediaPlayer.pause();
//        }
//        //获取文件总长度
//        public long getMusicDuration(){
//            return mediaPlayer.getDuration() / 1000;
//        }
//        //获取当前进度
//        public long getPosition(){
//            return mediaPlayer.getCurrentPosition();
//        }
//
//        //重新设置播放进度
//        public void setPosition(int position){
//            mediaPlayer.seekTo(position);
//        }


    @Override
    public void onCreate(){
        super.onCreate();
        System.out.println("------------------------------------------MediaPlayer_Service");

//inflater设置
//        LayoutInflater inflater = LayoutInflater.from(getApplication());
//        View view = inflater.inflate(R.layout.activity_main, null);
//        System.out.println("-------------------------------------------------------onCreate" + view);


//        floatingActionButton_play = view.findViewById(R.id.floatingActionButton_play);
//        floatingActionButton_nextSong = view.findViewById(R.id.floatingActionButton_nextSong);
//        floatingActionButton_lastSong = view.findViewById(R.id.floatingActionButton_lastSong);





//        localBinder.mediaPlayer = new MediaPlayer();
        //先判断当前音乐路径是否为空，若为空设置默认音乐  播放路径中的音乐
//        if(localBinder.current_music == null){
//            mediaPlayer = MediaPlayer.create(this, R.raw.letter);
//        }else{
//            mediaPlayer = MediaPlayer.create(this, Uri.parse(localBinder.current_music));
//        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        LayoutInflater.from()
        //先判断当前音乐路径是否为空，若为空设置默认音乐  播放路径中的音乐
//        if(localBinder.mediaPlayer == null) {

//        localBinder.setPlay();

        System.out.println("------------------------------------------onStartConmmand");
//            if (localBinder.current_music == null) {
//                mediaPlayer = MediaPlayer.create(this, R.raw.letter);
//            } else {
//                mediaPlayer.pause();
//                mediaPlayer = MediaPlayer.create(this, Uri.parse(localBinder.current_music));
////                localBinder.play();
//            }

 //       }
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        super.onDestroy();
    }

}
