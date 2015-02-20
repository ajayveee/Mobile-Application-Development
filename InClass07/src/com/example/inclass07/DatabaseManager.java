package com.example.inclass07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
	private Context context;
	private DBHelper  dbHelper;
	private SQLiteDatabase db;
	private PhotoDAO photoDAO;
	public DatabaseManager(Context context) {
		super();
		this.context = context;
		this.dbHelper = new DBHelper(context);
		this.db = dbHelper.getWritableDatabase();
		this.photoDAO = new PhotoDAO(db);
	}
	public void close(){
		if(db!=null){
			db.close();
		}
	}
	public PhotoDAO getPhotoDAO() {
		return photoDAO;
	}
	
}
