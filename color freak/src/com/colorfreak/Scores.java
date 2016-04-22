package com.colorfreak;

import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Scores extends Activity {
	
	int id,score;
	String name;
	Button tb,suv,all;
	ListView lv;
	ArrayList<String> al;
	MyDBHelper helper;
	SQLiteDatabase db;
	Cursor cr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		tb=(Button)findViewById(R.id.scrtb);
		suv=(Button)findViewById(R.id.scrsuv);
		all=(Button)findViewById(R.id.scrall);
		lv=(ListView)findViewById(R.id.scrlistview);
		al=new ArrayList<String>();
		
		helper=new MyDBHelper(getApplicationContext());
		db=helper.getWritableDatabase();
		View cc = null;
		tb(cc);
	}
	
	public void tb(View v)
	{
		int no=1;
		al.clear();
		try{
			cr=db.rawQuery("select * from thscore order by score desc",null);
			if(cr!=null)
			{
				if(cr.moveToFirst())
				{
					do {
						name=cr.getString(cr.getColumnIndex("name"));
						score=cr.getInt(cr.getColumnIndex("score"));
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
	
	public void suv(View v)
	{
		int no=1;
		al.clear();
		try{
			cr=db.rawQuery("select * from shscore order by score desc",null);
			if(cr!=null)
			{
				if(cr.moveToFirst())
				{
					do {
						name=cr.getString(cr.getColumnIndex("name"));
						score=cr.getInt(cr.getColumnIndex("score"));
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
	
	public void all(View v)
	{
		int no=1;
		al.clear();
		try{
			cr=db.rawQuery("select * from hscore order by score desc",null);
			if(cr!=null)
			{
				if(cr.moveToFirst())
				{
					do {
						name=cr.getString(cr.getColumnIndex("name"));
						score=cr.getInt(cr.getColumnIndex("score"));
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

}
