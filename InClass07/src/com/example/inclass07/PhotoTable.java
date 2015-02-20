package com.example.inclass07;

import android.database.sqlite.SQLiteDatabase;

public class PhotoTable {
	static final String TABLE_NAME = "Photos";
	static final String PHOTO_ID = "_id";
	static final String PHOTO_NAME = "name";
	static final String PHOTO_URL = "URL";
	static final String OWNER_NAME = "owner_name";

	static public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + " (");
		sb.append(PHOTO_ID + " integer primary key , ");
		sb.append(PHOTO_NAME + " text not null, ");
		sb.append(PHOTO_URL + " text not null, ");
		sb.append(OWNER_NAME + " text not null); ");
		db.execSQL(sb.toString());
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE_NAME);
		onCreate(db);
	}

}
