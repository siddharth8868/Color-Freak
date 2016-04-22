package com.colorfreak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ColorfreaksActivity extends Activity implements OnClickListener {
	
	Button tb,suv,cre;
	TextView htp,hs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tb=(Button)findViewById(R.id.timebomb);
		suv=(Button)findViewById(R.id.survival);
		cre=(Button)findViewById(R.id.credits);
		
		htp=(TextView)findViewById(R.id.mhtp);
		hs=(TextView)findViewById(R.id.mhs);
		htp.setOnClickListener(this);
		hs.setOnClickListener(this);
		
		//Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");  
		//htp.setTypeface(font);
		//hs.setTypeface(font);
	}
	
	
	public void survival(View v){
		Intent i=new Intent(getApplicationContext(),Survive.class);
		i.putExtra("play","suv");
		i.putExtra("time",60);
		startActivity(i);
	}
	public void credits(View v){
		Intent i=new Intent(getApplicationContext(),Credits.class);
		startActivity(i);
	}
	public void timebomb(View v){
		Intent i=new Intent(getApplicationContext(),TimeBomb.class);
		startActivity(i);
	}

	public void onClick(View v) {
		
		if(v.getId()==hs.getId())
		{
			Intent i=new Intent(getApplicationContext(),Scores.class);
			startActivity(i);
		}
		else
		{
			Intent i=new Intent(getApplicationContext(),HowToPlay.class);
			startActivity(i);
		}
		
	}
}