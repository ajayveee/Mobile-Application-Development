package com.example.inclass07;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PhotoDAO {

	private SQLiteDatabase db;

	public PhotoDAO(SQLiteDatabase db) {
		this.db = db;
	}

	public long save(Photo photo) {
		ContentValues values = new ContentValues();
		values.put(PhotoTable.PHOTO_ID, photo.getId());
		values.put(PhotoTable.PHOTO_NAME, photo.getName());
		values.put(PhotoTable.PHOTO_URL, photo.getUrl());
		values.put(PhotoTable.OWNER_NAME, photo.getOwner());

		return db.insert(PhotoTable.TABLE_NAME, null, values);

	}

	/**
	 * 
	 * @param photo
	 * @return true implies currently in favorite, false => not in favorite
	 */
	public boolean updateFavIcon(Photo photo) {
		Photo cPhoto = get(photo.getId());
		if (cPhoto == null) {
			save(photo);
			return true;
		} else {
			delete(photo);
			return false;
		}
	}

	public boolean update(Photo photo) {
		ContentValues values = new ContentValues();
		return db.update(PhotoTable.TABLE_NAME, values, PhotoTable.PHOTO_ID + "=?",
				new String[] { (photo.getName() + "") }) > 0;
	}

	public boolean delete(Photo photo) {
		return db.delete(PhotoTable.TABLE_NAME, PhotoTable.PHOTO_ID + "=?", new String[] { photo.getId() + "" }) > 0;
	}

	public Photo get(long id) {
		Photo photo = null;
		Cursor c = db.query(true, PhotoTable.TABLE_NAME, new String[] { PhotoTable.PHOTO_ID, PhotoTable.PHOTO_NAME,
				PhotoTable.PHOTO_URL, PhotoTable.OWNER_NAME }, PhotoTable.PHOTO_ID + "=?", new String[] { id + "" },
				null, null, null, null, null);
		if (c != null && c.moveToFirst()) {
			photo = buildNoteFromCursor(c);
		}
		return photo;
	}

	public List<Photo> getAll() {
		return null;
	}

	public Photo buildNoteFromCursor(Cursor c) {
		Photo photo = null;
		if (c != null) {
			photo = new Photo();
			photo.setId(c.getLong(0));
			photo.setName(c.getString(1));
			photo.setUrl(c.getString(2));
			photo.setOwner(c.getString(3));
		}
		return photo;
	}

}
