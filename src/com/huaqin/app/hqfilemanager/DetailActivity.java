package com.huaqin.app.hqfilemanager;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.huaqin.app.hqfilemanager.model.Item;

public class DetailActivity extends Activity {

	public final static String TAG = DetailActivity.class.getSimpleName();
	public final static String KEY_LIST = "key_list";
	public final static String KEY_POSITION = "key_position";
	
	ArrayList<Item> list;
	private List<DetailFragment> fragments;
	
	public DetailActivity() {
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_activity);
		
		Intent intent = getIntent();
		list = (ArrayList<Item>)intent.getSerializableExtra(KEY_LIST);
		
		fragments = new ArrayList<DetailFragment>();
		for (Item item : list) {
			fragments.add(new DetailFragment(item));
		}
		
		final ViewPager pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
			@Override
			public int getCount() {
				return list.size();
			}
			
			@Override
			public Fragment getItem(int position) {
//				return new DetailFragment(list.get(position));
				return fragments.get(position);
//				return new LabelListFragment(DummyGenerator.getLabelList());
			}
		});
		pager.setCurrentItem(intent.getIntExtra(KEY_POSITION, 0));
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
}
