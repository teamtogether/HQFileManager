package com.huaqin.app.hqfilemanager.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huaqin.app.hqfilemanager.R;
import com.huaqin.app.hqfilemanager.model.Item;

public class ItemListAdapter extends ArrayAdapter<Item> {
	private Activity activity;
	
	public ItemListAdapter(Activity activity, ArrayList<Item> items) {
		//R.layout.item_row
		super(activity, R.layout.item_row, items);
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewWrapper wrapper = null;

		if (view == null) {
			view = activity.getLayoutInflater().inflate(R.layout.item_row, null);
			wrapper = new ViewWrapper(view);
			view.setTag(wrapper);
		} else {
			wrapper = (ViewWrapper)view.getTag();
		}
		
		Item item = getItem(position);
		wrapper.image.setImageResource(item.getDrawable());
		wrapper.label.setText(item.getLabel());
		
		return view;
	}
	
	class ViewWrapper {
		public final ImageView image;
		public final TextView label;
		
		ViewWrapper(View view){
			this.image = (ImageView)view.findViewById(R.id.item_row_img);
			this.label = (TextView)view.findViewById(R.id.item_row_txt);
		}
	}
}
