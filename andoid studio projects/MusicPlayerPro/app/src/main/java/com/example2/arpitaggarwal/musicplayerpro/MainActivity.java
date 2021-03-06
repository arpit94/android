package com.example2.arpitaggarwal.musicplayerpro;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.MediaController;

import com.example2.arpitaggarwal.musicplayerpro.MusicService.MusicBinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends Activity implements MediaController.MediaPlayerControl{

    private ArrayList<Song> songList;
    private ListView songView;
    private MusicService musicSrv;
    private Intent playIntent;
    private boolean musicBound=false;
    private ServiceConnection musicConnection;
    private MusicController controller;
    private boolean paused=false,playbackPaused=false;
    private void setController(){
        //set the controller up
        controller=new MusicController(this);
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrev();
            }
        });
    controller.setMediaPlayer(this);
        controller.setAnchorView(findViewById(R.id.song_list));
        controller.setEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songView=(ListView)findViewById(R.id.song_list);
        songList=new ArrayList<Song>();
        getSongList();
        Collections.sort(songList, new Comparator<Song>() {
            @Override
            public int compare(Song lhs, Song rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());
            }
        });
        SongAdapter songAdt=new SongAdapter(this,songList);
        songView.setAdapter(songAdt);
        //connect to the service
        musicConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MusicService.MusicBinder binder=(MusicService.MusicBinder)service;
                //get service
                musicSrv=binder.getService();
                //pass list
                musicSrv.setList(songList);
                musicBound=true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            musicBound=false;
            }
        };
        setController();
    }

    private void playNext(){
        musicSrv.playNext();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }
    private void playPrev(){
        musicSrv.playPrev();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent=new Intent(this,MusicService.class);
            bindService(playIntent,musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    public void getSongList(){
        //retrieve song info
        ContentResolver musicresolver=getContentResolver();
        Uri musicUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor=musicresolver.query(musicUri,null,null,null,null);
        if(musicCursor!=null&&musicCursor.moveToFirst()){
            //get columns
            int titleColumn=musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn=musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn=musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do{
                long thisId=musicCursor.getLong(idColumn);
                String thisTitle=musicCursor.getString(titleColumn);
                String thisArtist=musicCursor.getString(artistColumn);
                songList.add(new Song(thisId,thisTitle,thisArtist));
            }while(musicCursor.moveToNext());
        }
    }
    public void songPicked(View view)
    {
        musicSrv.setSong(Integer.parseInt(view.getTag().toString()));
        musicSrv.playSong();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_shuffle:  //shuffle:
                musicSrv.setShuffle();
                break;
            case R.id.action_end:
                stopService(playIntent);
                musicSrv=null;
                System.exit(0);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }

    @Override
    public void start() {
    musicSrv.go();
    }

    @Override
    public void pause() {
        playbackPaused=true;
    musicSrv.pausePlayer();
    }

    @Override
    public int getDuration() {
       if (musicSrv!=null && musicBound && musicSrv.isPng())
           return musicSrv.getDur();
        else return 0;
    }

    @Override
    public int getCurrentPosition() {
        if(musicSrv!=null&&musicBound&&musicSrv.isPng())
            return musicSrv.getPosn();
      else  return 0;
    }

    @Override
    public void seekTo(int pos) {
    musicSrv.seek(pos);
    }

    @Override
    public boolean isPlaying() {
        if(musicSrv!=null && musicBound)
            return  musicSrv.isPng();
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        paused=true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(paused){
            setController();
            paused=false;
        }
    }

    @Override
    protected void onStop() {
        controller.hide();
        super.onStop();
    }
}

