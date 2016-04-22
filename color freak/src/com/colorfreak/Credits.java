package com.colorfreak;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.widget.TextView;

public class Credits extends Activity implements OnCompletionListener {
	TextView tv1,tv;
	MediaPlayer player;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits);
		tv1=(TextView)findViewById(R.id.credithead);
		tv=(TextView)findViewById(R.id.creditmatter);
		tv.setText(R.string.credit);
		Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ARDELANEY.ttf");
		tv.setTypeface(tf);
		tv.setTextSize(40);
		
		tf=Typeface.createFromAsset(getAssets(),"fonts/dayroman.ttf");
		tv1.setTypeface(tf);
		tv1.setTextSize(20);
		player=MediaPlayer.create(this,R.raw.the_hutch_song);
		player.start();
		player.setOnCompletionListener(this);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		player.stop();
	}
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		player.start();
	}
	

}
