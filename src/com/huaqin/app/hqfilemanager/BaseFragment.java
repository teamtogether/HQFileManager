package com.huaqin.app.hqfilemanager;

import android.app.Activity;
import android.content.Intent;

import com.actionbarsherlock.app.SherlockFragment;

public class BaseFragment extends SherlockFragment{
    
    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
   		super.onActivityResult(requestCode, resultCode, data);
   		if (resultCode == Activity.RESULT_OK) {
   		}
   	}
}
