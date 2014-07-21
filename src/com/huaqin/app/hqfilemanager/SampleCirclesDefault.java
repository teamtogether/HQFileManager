package com.huaqin.app.hqfilemanager;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

public class SampleCirclesDefault extends FragmentActivity {
	TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    SharedPreferences preferences;
    
  //by liye
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	  requestWindowFeature(Window.FEATURE_NO_TITLE);
    	// by  liye  begin	
		  preferences = getSharedPreferences("count",MODE_WORLD_READABLE);
	        int count = preferences.getInt("count", 0);
	        Boolean guide=preferences.getBoolean("guide", false);
	        //判断程序与第几次运行，如果是第一次运行则跳转到引导页面
	        if (count != 0&&guide) {
	            Intent intent = new Intent();
	            intent.setClass(getApplicationContext(),MainActivity.class);
	            startActivity(intent);
	            finish();
	        }     
	        Editor editor = preferences.edit();
	        //存入数据
	        editor.putInt("count", ++count);
	        //提交修改
	        editor.commit();
	//  by liye  end
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_circles);
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.guide_layout_1, null);
        View view2 = mLi.inflate(R.layout.guide_layout_3, null);
        View view3 = mLi.inflate(R.layout.guide_layout_2, null);
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2); 
        views.add(view3);
        mAdapter = new TestFragmentAdapter(views);
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
     
    }
  public void beginbutton(View e) {
	  preferences = getSharedPreferences("count",MODE_WORLD_READABLE);
      boolean count = preferences.getBoolean("guide", false);
      Editor editor = preferences.edit();
      //存入数据
      editor.putBoolean("guide", true);
      //提交修改
      editor.commit();
	  Intent intent = new Intent();
      intent.setClass(getApplicationContext(),MainActivity.class);
      startActivity(intent);
      finish();
	  
  }
@Override
public void setContentView(View view, LayoutParams params) {
	// TODO Auto-generated method stub
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	super.setContentView(view, params);
}

}