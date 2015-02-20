package com.example.inclass07;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoListAdapter extends ArrayAdapter<Photo> {

	private Context context;
	private int resource;
	private List<Photo> photos;
	private DatabaseManager dbManager;

	public DatabaseManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DatabaseManager dbManager) {
		this.dbManager = dbManager;
	}

	public PhotoListAdapter(Context context, int resource, List<Photo> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.photos = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, parent, false);
		}
		Photo photo = photos.get(position);
		ImageView photoIv = (ImageView) convertView.findViewById(R.id.imageViewPhotoThumb);
		Picasso.with(context).load(photo.getUrl()).into(photoIv);
		TextView tv = (TextView) convertView.findViewById(R.id.textViewPhotoTitle);
		tv.setText(photo.getName());
		Photo tPhoto = dbManager.getPhotoDAO().get(photo.getId());
		ImageView fav = (ImageView) convertView.findViewById(R.id.imageViewFav);
		if (tPhoto != null) {
			fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favorites_yellow));
		} else {
			fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favorites_grey));
		}
		return convertView;
	}
}
