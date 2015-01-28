package com.example.myresturant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "myrestaurant.db";
	public static final String RESTAURANT_TABLE_NAME = "restaurant";
	public static final String RESTAURANT_COLUMN_ID = "id";
	public static final String RESTAURANT_COLUMN_NAME = "name";
	public static final String RESTAURANT_COLUMN_ADDRESS = "address";
	public static final String RESTAURANT_COLUMN_LATITUDE = "latitude";
	public static final String RESTAURANT_COLUMN_LONGITUDE = "longitude";
	public static final String RESTAURANT_COLUMN_MENUS = "menus";
	public static final String RESTAURANT_COLUMN_SPECIAL_MENU = "special_menu";
	public static final String RESTAURANT_COLUNM_CLOSE_DAY = "close_day";
	public static final String RESTAURANT_COLUMN_DAILY_OPEN_TIME = "daily_open_time";
	public static final String RESTAURANT_COLUMN_DESCRIPTION = "description";

	public DatabaseHelperClass(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE restaurant"
				+ "(id INTEGER PRIMARY KEY,  name TEXT,address TEXT,latitude TEXT,longitude TEXT,menus TEXT,special_menu TEXT,close_day TEXT,daily_open_time TEXT,description TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS restaurant");
		onCreate(db);
	}

}
