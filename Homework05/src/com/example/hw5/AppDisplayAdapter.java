/**
 * Ajay Vijayakumaran Nair
 * Ayang
 * Nachiket Doke
 */
package com.example.hw5;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AppDisplayAdapter extends ArrayAdapter<App> {
	private Context context;
	private int resource;
	private List<App> apps;

	public AppDisplayAdapter(Context context, int resource, List<App> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.apps = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resource, parent, false);
		}
		App app = apps.get(position);
		((TextView) convertView.findViewById(R.id.textViewAppName)).setText(app.getTitle());
		((TextView) convertView.findViewById(R.id.textViewDevName)).setText(app.getDevName());
		((TextView) convertView.findViewById(R.id.textViewPrice)).setText(app.getPrice() + "");
		ImageView iv = (ImageView) convertView.findViewById(R.id.imageViewThumb);
		if (app.getAppPhotoLarge() != null) {

			Picasso.with(context).load(app.getAppPhotoLarge()).into(iv);
		} else {
			iv.setImageDrawable(context.getResources().getDrawable(R.drawable.no_img));
		}

		return convertView;
	}
}
