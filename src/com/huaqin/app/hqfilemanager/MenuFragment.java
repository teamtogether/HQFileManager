package com.huaqin.app.hqfilemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class MenuFragment extends SherlockFragment {
	View mView;
	ListView mListView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.list, null); 
		mListView = (ListView) mView.findViewById(R.id.menu_list);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		adapter.add(new SampleItem(getActivity().getString(R.string.menu_personal_center), R.drawable.ic_menu_personal_center));
		adapter.add(new SampleItem(getActivity().getString(R.string.menu_setting),R.drawable.ic_menu_setting));
		adapter.add(new SampleItem(getActivity().getString(R.string.menu_guide),R.drawable.ic_menu_guide));
		adapter.add(new SampleItem(getActivity().getString(R.string.menu_gprs),R.drawable.ic_menu_gprs));
		adapter.add(new SampleItem(getActivity().getString(R.string.menu_about),R.drawable.ic_menu_about));
		mListView.setAdapter(adapter);
		return mView;
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
