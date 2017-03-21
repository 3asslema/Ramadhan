package com.example.muslim.bag;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;



public class Listen_koran extends Activity implements OnClickListener, OnTouchListener, OnCompletionListener, OnBufferingUpdateListener{
	
	private Button buttonPlayPause;
	private SeekBar seekBarProgress;
	public String editTextSongURL;
	public TextView Sourah_Name,duration;
	
	private static MediaPlayer mediaPlayer;
	public static MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	private int mediaFileLengthInMilliseconds; 
	
	private final Handler handler = new Handler();
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koran);
       
        initView();
    }
   
    /** This method initialise all the views in project*/
    private void initView() {
		buttonPlayPause = (Button)findViewById(R.id.play);
		buttonPlayPause.setOnClickListener(this);
		
		seekBarProgress = (SeekBar)findViewById(R.id.SeekBarTestPlay);		
		seekBarProgress.setMax(99); // It means 100% .0-99
		seekBarProgress.setOnTouchListener(this);
		Sourah_Name=(TextView)findViewById(R.id.name);
		duration=(TextView)findViewById(R.id.duration);
		editTextSongURL = "http://download.quranicaudio.com/quran/maher_256/";
		Bundle extras=this.getIntent().getExtras();
		
		String indice=extras.getString("pos");
		Sourah_Name.setText(extras.getString("soura"));
        int ind=Integer.parseInt(indice)+1;
        if (ind<10)
        	editTextSongURL+="00";
        else if ((ind>=10)&&(ind<100))
        	editTextSongURL+="0";
        indice=Integer.toString(ind)+".mp3";
        editTextSongURL+=indice;
        Log.i("url",editTextSongURL);
		 	
		
		
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnCompletionListener(this);
	}
    private void primarySeekBarProgressUpdater() {
    	seekBarProgress.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100)); // This math construction give a percentage of "was playing"/"song length"
		if (mediaPlayer.isPlaying()) {
			Runnable notification = new Runnable() {
		        public void run() {
		        	primarySeekBarProgressUpdater();
				}
		    };
		    handler.postDelayed(notification,1000);
    	}
    }

	
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.play){
			 //** ImageButton onClick event handler. Method which start/pause mediaplayer playing *//*
			try {
				mediaPlayer.setDataSource(editTextSongURL); // setup song from http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
				mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer. 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL
			
			//duration.setText(((float)(mediaFileLengthInMilliseconds/1000)/60)+" mins");
			if(!mediaPlayer.isPlaying()){
				buttonPlayPause.setText("PAUSE");
				mediaPlayer.start();
				//buttonPlayPause.setImageResource(R.drawable.button_pause);
			}else {
				buttonPlayPause.setText("PLAY");
				mediaPlayer.pause();
				//buttonPlayPause.setImageResource(R.drawable.button_play);
			}
			
			primarySeekBarProgressUpdater();
		}
	}

	
	 
	 

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		buttonPlayPause.setText("PLAY");
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	

			
			
		}
}

	