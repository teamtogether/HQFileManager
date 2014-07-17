package com.huaqin.app.hqfilemanager;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;

import com.huaqin.app.hqfilemanager.adapter.LabelListAdapter;
import com.huaqin.app.hqfilemanager.model.Label;

public class LabelListFragment extends ListFragment {
	
private ArrayList<Label> list;
	private LabelListAdapter adapter;
	
	public LabelListFragment(ArrayList<Label> list) {
		this.list = list;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		setListAdapter(null);
		
		if (adapter == null) {		
			adapter = new LabelListAdapter(getActivity(), list);
		}
		
		setListAdapter(adapter);
	}
	
}
