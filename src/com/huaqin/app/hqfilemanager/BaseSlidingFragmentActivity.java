package com.huaqin.app.hqfilemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
/**
 * 需要SlidingMenu能力，需要实现继承该类
 * @author chaihuasong
 *
 */
public abstract class BaseSlidingFragmentActivity extends SlidingFragmentActivity {
	private static final String TAG = "BaseSlidingFragmentActivity";
	private Context mContext;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		mContext = this;
	}
	
    protected abstract boolean checkIntent(Intent intent);
       
}
