package com.colorfreak;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity {
	
	TextView q,c,w,s;
	EditText name;
	ImageView back,replay;
	int val,iq,ic,iw,is,time,ttime;
	String play,data;
	MyDBHelper helper;
	SQLiteDatabase db;
	SQLiteStatement st;
	Cursor cr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		helper=new MyDBHelper(getApplicationContext());
		db=helper.getWritableDatabase();
		q=(TextView)findViewById(R.id.resq);
		c=(TextView)findViewById(R.id.resc);
		w=(TextView)findViewById(R.id.resw);
		s=(TextView)findViewById(R.id.ress);
		
		name=(EditText)findViewById(R.id.resname);
		back=(ImageView)findViewById(R.id.resback);
		replay=(ImageView)findViewById(R.id.resreplay);
		Intent i=getIntent();
		

		iq=i.getIntExtra("q",0);
		ic=i.getIntExtra("c",0);
		iw=i.getIntExtra("w",0);
		is=i.getIntExtra("score",0);
		time=i.getIntExtra("time",0);
		play=i.getStringExtra("play");
		if(play.equalsIgnoreCase("suv"))
		{
			ttime=i.getIntExtra("ttime",0);
			iq=ic+iw;
			is=(ic*10)-(iw*5)-(ttime/1000);
		}
		calculate();
	}
	
	private void calculate() {
		// TODO Auto-generated method stub
		q.setText(""+iq);
		c.setText(""+ic);
		w.setText(""+iw);
		s.setText(""+is);
		int high=gethigh();
		if(high<=is && is>0)
		{
			MediaPlayer player=MediaPlayer.create(this,R.raw.applause);
			player.start();
			
		}
		
	}

	
	public int gethigh()
	{
		int no=0;
		String que = null;
		if(play.equalsIgnoreCase("tb"))
		{
			que="select * from thscore order by score desc";
		}
		else if(play.equalsIgnoreCase("suv"))
		{
			que="select * from shscore order by score desc";
		}
		
		try{
			cr=db.rawQuery(que,null);
			if(cr!=null)
			{
				if(cr.moveToFirst())
				{
					no=cr.getInt(cr.getColumnIndex("score"));
				}
			}
			else
			{
				no=0;
			}
		}
		catch (Exception e) 
		{	
		}
		return no;
	}

	
	
	
	public void back(View v)
	{
		finish();
	}
	
	public void replay(View V)
	{
		Intent i;
		if(play.equalsIgnoreCase("suv"))
		{
			i=new Intent(getApplicationContext(),Survive.class);
		}
		else{
			i=new Intent(getApplicationContext(),GamePlay.class);
		}
		i.putExtra("play",play);
		i.putExtra("time",time);
		startActivity(i);
		finish();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	if(is>0)
	{
		if(play.equalsIgnoreCase("tb"))
		{
			try{	
				st=db.compileStatement("insert into thscore values(?,?,?)");
				st.bindString(2,name.getText().toString());
				st.bindLong(3,is);
				st.executeInsert();
			  }
			  catch (Exception e) {
				//String s=e.getMessage();
				Toast.makeText(getApplicationContext(),"db error",Toast.LENGTH_SHORT).show();	
			  }
		}
		else if(play.equalsIgnoreCase("suv"))
		{
			try{	
				st=db.compileStatement("insert into shscore values(?,?,?)");
				st.bindString(2,name.getText().toString());
				st.bindLong(3,is);
				st.executeInsert();
			  }
			  catch (Exception e) {
				//String s=e.getMessage();
				Toast.makeText(getApplicationContext(),"db error",Toast.LENGTH_SHORT).show();	
			  }
		}
		
		try{	
			st=db.compileStatement("insert into hscore values(?,?,?)");
			st.bindString(2,name.getText().toString());
			st.bindLong(3,is);
			st.executeInsert();
		  }
		  catch (Exception e) {
			//String s=e.getMessage();
			Toast.makeText(getApplicationContext(),"db error",Toast.LENGTH_SHORT).show();	
		  }
		
	}

	}
}
