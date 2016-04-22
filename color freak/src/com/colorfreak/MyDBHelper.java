package com.colorfreak;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

	public MyDBHelper(Context context) {
		super(context,"hello",null,1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table hscore(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,score integer)");
		db.execSQL("create table thscore(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,score integer)");
		db.execSQL("create table shscore(id INTEGER PRIMARY KEY AUTOINCREMENT,name text,score integer)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
