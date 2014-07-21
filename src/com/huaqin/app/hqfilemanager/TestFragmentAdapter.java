package com.huaqin.app.hqfilemanager;


import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

class TestFragmentAdapter extends PagerAdapter  {
	private ArrayList<View> views;
	private ArrayList<String> titles;
    public TestFragmentAdapter(ArrayList<View> views) {
    	this.views = views;
    	
    }

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	//一定要销毁！！！否则会出错
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.views.size();
	}
}