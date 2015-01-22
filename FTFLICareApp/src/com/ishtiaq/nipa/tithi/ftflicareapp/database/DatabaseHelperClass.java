package com.ishtiaq.nipa.tithi.ftflicareapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {
	   public static final String DATABASE_NAME = "FTFLICareApp.db";
	   public static final String DIET_TABLE_NAME = "diet";
	   public static final String DIET_COLUMN_ID = "id";
	   public static final String DIET_COLUMN_PROFILE_ID = "profileId";
	   public static final String DIET_COLUMN_DATE = "date";
	   public static final String DIET_COLUMN_TIME = "time";
	   public static final String DIET_COLUMN_DAY = "day";
	   public static final String DIET_COLUMN_FEAST_NAME = "dietname";
	   public static final String DIET_COLUMN_MENU = "menu";
	   public static final String DIET_COLUNM_IS_ALARM_SET = "isalarmset";
	  
	   
	  
	  public DatabaseHelperClass(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, 3);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL( "create table diet" +
			      "(id integer primary key,profileId integer, date text,time text,day text,dietname text,menu text,isalarmset integer)");
		 
		 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS diet");
	      onCreate(db);
	}

}
