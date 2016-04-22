package com.colorfreak;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class TimeBomb extends Activity {
	
	EditText time;
	Button start;
	ListView lv;
	ArrayList<String> al;
	MyDBHelper helper;
	SQLiteDatabase db;
	Cursor cr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timebomb);
		time=(EditText)findViewById(R.id.tbtime);
		start=(Button)findViewById(R.id.tbstart);
		lv=(ListView)findViewById(R.id.tblistview);
		al=new ArrayList<String>();
		
		helper=new MyDBHelper(getApplicationContext());
		db=helper.getWritableDatabase();
		View v = null;
		tb(v);
	}
	
	public void tb(View v)
	{
		int no=1,score;
		String name;
		al.clear();
		try{
			cr=db.rawQuery("select * from thscore order by score desc",null);
			if(cr!=null)
			{
				if(cr.moveToFirst())
				{
					do {
						name = cr.getString(cr.getColumnIndex("name"));
						score = cr.getInt(cr.getColumnIndex("score"));
						al.add(no+". "+name+"     "+score);
						no++;
					} while (cr.moveToNext());
				}
			}
		}
		catch (Exception e) 
		{	
		}
		ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,al);
		lv.setAdapter(ad);
	}

	public void start(View v)
	{
		String ssp=time.getText().toString();
		if(ssp.equalsIgnoreCase(""))
		{
			ssp="60";
		}
		int val=Integer.parseInt(ssp);
		Intent i=new Intent(getApplicationContext(),GamePlay.class);
		i.putExtra("time", val);
		i.putExtra("play","tb");
		startActivity(i);
		}
}
