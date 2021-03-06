package com.ftfl.myrestaurant.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.myrestaurant.R;
import com.ftfl.myrestaurant.model.RestaurantInformation;

public class RestaurantListAdapter extends ArrayAdapter<RestaurantInformation>{
	private final Activity context;
	private ArrayList<RestaurantInformation> res_info_obj;;

	static class ViewHolder {
		public TextView name,address;
		public ImageView image;
	}

	/*
	 * private ArrayList<String> names; private ArrayList<String> times; private
	 * ArrayList<String> menus;
	 */

	

	public RestaurantListAdapter(Activity context,
			ArrayList<RestaurantInformation> res_info_obj) {
		super(context, R.layout.each_restaurant_chart, res_info_obj);
		this.context = context;
		this.res_info_obj = res_info_obj;
		/*
		 * this.names = names; this.times = times; this.menus = menus;
		 */
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (convertView == null) {
			// LayoutInflater
			// inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.each_restaurant_chart, parent,
					false);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView
					.findViewById(R.id.res_name);
			viewHolder.address = (TextView) rowView
					.findViewById(R.id.res_addresss);
			
			viewHolder.image = (ImageView) rowView
					.findViewById(R.id.restaurantImage);
			
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.name.setText(res_info_obj.get(position).getName());
		holder.address.setText(res_info_obj.get(position).getAddrsss());
		//setPic(holder, position);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 8;

		/*Bitmap bitmap= BitmapFactory.decodeFile(res_info_obj.get(position).getImage(),options);

		  
		       
		    	
				holder.image.setImageBitmap(bitmap);
		    	bitmap.recycle();
		    	*/
		
		
		return rowView;
	}
	

	
}
