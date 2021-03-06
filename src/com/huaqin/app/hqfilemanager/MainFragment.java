package com.huaqin.app.hqfilemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainFragment extends BaseFragment implements View.OnClickListener{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		view.findViewById(R.id.menu_first).setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.menu_first:
			FileOperatorActivity.startActivity(getActivity(), null);
			break;
		}
	}
}
