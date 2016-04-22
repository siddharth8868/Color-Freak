package com.colorfreak;

import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlay extends Activity implements OnClickListener, OnCompletionListener {

	TextView q,tvwrong,tvcorrect,tvqno,timer,qus,wrg,crg;
	TextView[] ans;
    ArrayList<String> al,bl;
    String play;
    int qs,as,correct=0,wrong=0,qno=0,colno,i,lenght=7,time;
    MediaPlayer mp;
    CountDownTimer counttime;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        mp=MediaPlayer.create(this,R.raw.tiktic);
        mp.start();
		mp.setOnCompletionListener(this);
        
        
        ans= new TextView[7];
        Intent i = getIntent();
        time=i.getIntExtra("time",0);
        play=i.getStringExtra("play");
        if(time!=0)
        {
        time=time*1000;
        }
        else
        {
        	time=60000;
        }
        bl=new ArrayList<String>();
        al=new ArrayList<String>();
        al.add("GREEN");
        al.add("RED");
        al.add("BLUE");
        al.add("BLACK");
        al.add("WHITE");
        al.add("YELLOW");
        al.add("GRAY");
        tvcorrect=(TextView)findViewById(R.id.correct);
        tvqno=(TextView)findViewById(R.id.qno);
        tvwrong=(TextView)findViewById(R.id.wrong);
        qus=(TextView)findViewById(R.id.qno1);
        crg=(TextView)findViewById(R.id.correct1);
        wrg=(TextView)findViewById(R.id.wrong1);
        
        timer=(TextView)findViewById(R.id.timer);
        q=(TextView)findViewById(R.id.question);
        ans[1]=(TextView)findViewById(R.id.ans1);
        ans[2]=(TextView)findViewById(R.id.ans2);
        ans[3]=(TextView)findViewById(R.id.ans3);
        ans[4]=(TextView)findViewById(R.id.ans4);
        ans[5]=(TextView)findViewById(R.id.ans5);
        ans[6]=(TextView)findViewById(R.id.ans6);
        
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/ravie.ttf");
		q.setTypeface(tf);
		//tv.setTextSize(40);
		
		/*tf=Typeface.createFromAsset(getAssets(),"fonts/argos.ttf");
		ans[1].setTypeface(tf);
		ans[2].setTypeface(tf);
		ans[3].setTypeface(tf);
		ans[4].setTypeface(tf);
		ans[5].setTypeface(tf);
		ans[6].setTypeface(tf);
        
		tf=Typeface.createFromAsset(getAssets(),"fonts/alpha.ttf");
		tvcorrect.setTypeface(tf);
		tvqno.setTypeface(tf);
		tvwrong.setTypeface(tf);
		qus.setTypeface(tf);
		crg.setTypeface(tf);
		wrg.setTypeface(tf);
		timer.setTypeface(tf);*/
		
        ans[1].setOnClickListener(this);
        ans[2].setOnClickListener(this);
        ans[3].setOnClickListener(this);
        ans[4].setOnClickListener(this);
        ans[5].setOnClickListener(this);
        ans[6].setOnClickListener(this);
        counttime=new CountDownTimer(time,1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				 timer.setText("Time:"
		                    + String.valueOf(millisUntilFinished / 1000));
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				timer.setText("Time:"+ String.valueOf(0));
				Toast.makeText(getApplicationContext(),"game over",Toast.LENGTH_LONG).show();
				Intent i=new Intent(getApplicationContext(),Result.class);
				qno--;
				i.putExtra("q",qno);
				i.putExtra("c",correct);
				i.putExtra("w",wrong);
				i.putExtra("time",time/1000);
				i.putExtra("play", play);
				int score=(correct*10)-(wrong*5)-(time/1000);
				
				i.putExtra("score",score);
				Toast.makeText(getApplicationContext(),""+score,Toast.LENGTH_LONG).show();
				startActivity(i);
				finish();
			}
		}.start();
        
        
        assign();
    }
	private void assign() {
		// TODO Auto-generated method stub
		ArrayList<String> dub=new ArrayList<String>();
		dub=al;
		qno++;
		tvqno.setText(""+qno);
		Random r = new Random();
		colno=r.nextInt(7);
		i=r.nextInt(6)+1;
		switch (colno) {
case 0:
	q.setTextColor(Color.GREEN);
	ans[i].setText("GREEN");
			
	break;
case 1:
	q.setTextColor(Color.RED);
	ans[i].setText("RED");
			
	break;
case 2:
	q.setTextColor(Color.BLUE);
	ans[i].setText("BLUE");
			
	break;
case 3:
	q.setTextColor(Color.BLACK);
	ans[i].setText("BLACK");
	
	break;

case 4:
	q.setTextColor(Color.WHITE);
	ans[i].setText("WHITE");
	
	break;
case 5:
	q.setTextColor(Color.YELLOW);
	ans[i].setText("YELLOW");
	
	break;
case 6:
	q.setTextColor(Color.DKGRAY);
	ans[i].setText("GRAY");
	break;
		}
		
		dub.clear();
		dub.add("GREEN");
		dub.add("RED");
        dub.add("BLUE");
        dub.add("BLACK");
        dub.add("WHITE");
        dub.add("YELLOW");
        dub.add("GRAY");
		int length=7;
		for(int j=1;j<=6;j++)
		{
			int ran=r.nextInt(length);
			String opcol=dub.get(ran);
			
			if(opcol.equalsIgnoreCase("GREEN"))
			{
				ans[j].setTextColor(Color.GREEN);
			}
			else if(opcol.equalsIgnoreCase("RED"))
			{
				ans[j].setTextColor(Color.RED);
			}
			else if(opcol.equalsIgnoreCase("BLUE"))
			{
				ans[j].setTextColor(Color.BLUE);
			}
			else if(opcol.equalsIgnoreCase("BLACK"))
			{
				ans[j].setTextColor(Color.BLACK);
			}
			else if(opcol.equalsIgnoreCase("WHITE"))
			{
				ans[j].setTextColor(Color.WHITE);
			}
			else if(opcol.equalsIgnoreCase("YELLOW"))
			{
				ans[j].setTextColor(Color.YELLOW);
			}
			else if(opcol.equalsIgnoreCase("GRAY"))
			{
				ans[j].setTextColor(Color.DKGRAY);
			}
			dub.remove(opcol);
			length--;
			
		}
		
		
		
			al.clear();
		 	al.add("GREEN");
	        al.add("RED");
	        al.add("BLUE");
	        al.add("BLACK");
	        al.add("WHITE");
	        al.add("YELLOW");
	        al.add("GRAY");
	        q.setText(al.get(r.nextInt(6)));
		
		//Toast.makeText(getApplicationContext(),""+al.get(0)+al.get(1)+al.get(2)+al.get(3),Toast.LENGTH_LONG).show();

		
		dub=al;
		//Toast.makeText(getApplicationContext(),kka+"b1  "+bl.size(),Toast.LENGTH_LONG).show();

		length=6;
		for(int j=1;j<=6;j++)
		{
			//Toast.makeText(getApplicationContext(),"3  "+bl.size(),Toast.LENGTH_LONG).show();

		try{
			 int ran=r.nextInt(length);	
		   	 String opcol=dub.get(ran);
			 if(i!=j)
			 {
			 ans[j].setText(opcol);
			 }
			 else
			 {
		 		opcol=ans[j].getText().toString();
		 	 }
			 dub.remove(opcol);
			 length--;
			
			}
			catch (Exception e) {
				Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
			}
		}
		
		
		
	//Toast.makeText(getApplicationContext(),alia[0]+alia[1]+alia[2]+alia[3]+alia[4]+alia[5]+alia[6],Toast.LENGTH_LONG).show();
				}
	public void onClick(View v) {
		int num=q.getCurrentTextColor();
		
		String name=convert(num);

		
		switch (v.getId()) {
		case R.id.ans1:
			if(name.equalsIgnoreCase(ans[1].getText().toString()))
			{
				correct++;
				tvcorrect.setText(""+correct);
			}
			else
			{
				//Toast.makeText(getApplicationContext(),""+Color.DKGRAY,Toast.LENGTH_LONG).show();

				wrong++;
				tvwrong.setText(""+wrong);
			}
			
			break;
		case R.id.ans2:
			if(name.equalsIgnoreCase(ans[2].getText().toString()))
			{
				correct++;
				tvcorrect.setText(""+correct);
			}
			else
			{
				wrong++;
				tvwrong.setText(""+wrong);
			}
			break;
		case R.id.ans3:
			if(name.equalsIgnoreCase(ans[3].getText().toString()))
			{
				correct++;
				tvcorrect.setText(""+correct);
			}
			else
			{
				wrong++;
				tvwrong.setText(""+wrong);
			}
			break;
		case R.id.ans4:
			if(name.equalsIgnoreCase(ans[4].getText().toString()))
			{
				correct++;
				tvcorrect.setText(""+correct);
			}
			else
			{
				wrong++;
				tvwrong.setText(""+wrong);
			}
			break;
		case R.id.ans5:
			if(name.equalsIgnoreCase(ans[5].getText().toString()))
			{
				correct++;
				tvcorrect.setText(""+correct);
			}
			else
			{
				wrong++;
				tvwrong.setText(""+wrong);
			}
			break;
		case R.id.ans6:
			if(name.equalsIgnoreCase(ans[6].getText().toString()))
			{

				correct++;
				tvcorrect.setText(""+correct);
			}
			else
			{
				wrong++;
				tvwrong.setText(""+wrong);
			}
			break;
		

		default:
			break;
		}
		assign();

	}
	private String convert(int num) {
		// TODO Auto-generated method stub
		switch (num) {
		case -1:
			return "WHITE";
		case -65536:
			return "RED";
		case -16776961:
			return "BLUE";
		case -16711936:
			return "GREEN";
		case -256:
			return "YELLOW";
		case -16777216:
			return "BLACK";
		case -12303292:
			return "GRAY";
		default:
			break;
		}
		return null;
	}
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mp.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.stop();
	}
}