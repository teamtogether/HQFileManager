package com.huaqin.app.hqfilemanager;


import java.util.ArrayList;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ButtonSwitchFragment extends Fragment {
	private final String IMAGE_TYPE = "image/*";
	
	ArrayList<Bitmap> listItems = new ArrayList<Bitmap>();
	Cursor cursor;
	private static final String PROJECTION[] = { "_data", };
	private LayoutInflater mInflater;
	private static final int UPDATE_ADAPTER = 1;
	private GridAdapter simoleadapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
		getAlbum.setType(IMAGE_TYPE);
		Uri originalUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

		cursor = getActivity().getContentResolver().query(originalUri,// 指定缩略图数据库的Uri
				PROJECTION,// 指定所要查询的字段
				null,// 查询条件
				null, // 查询条件中问号对应的值
				null);
		mInflater = LayoutInflater.from(getActivity());
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_ADAPTER:
				simoleadapter.notifyDataSetChanged();
				break;
			}
			super.handleMessage(msg);
		}
	};
	Runnable updateThread = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			cursor.moveToFirst();
			while (cursor.moveToNext()) {

				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
				Bitmap imageBitmap = getBitmap(path);
				listItems.add(imageBitmap);
				handler.sendEmptyMessage(UPDATE_ADAPTER);
			}

		}

		private Bitmap getBitmap(String path) {
			return convertToBitmap(path);
		}

	};

	public Bitmap convertToBitmap(String path) {

		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 设置为ture只获取图片大小
		opts.inJustDecodeBounds = true;
		opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
		// 返回为空
		BitmapFactory.decodeFile(path, opts);
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = (int) 8f;
		Bitmap bitmap = BitmapFactory.decodeFile(path, opts);
		getActivity().getCacheDir();
		return bitmap;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.buttonswitch_fragment,
				container, false);
		simoleadapter = new GridAdapter();
		grid = (GridView) rootview.findViewById(R.id.GridView_picture);
		grid.setAdapter(simoleadapter);
		new Thread(updateThread).start();
		return rootview;

	}

	class GridAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.image, null);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.image_Item);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.image.setImageBitmap(listItems.get(position));
			return convertView;
		}

		// set the imageView using the path of image
		public void setViewImage(ImageView v, String value) {
			try {
				Bitmap bitmap = BitmapFactory.decodeFile(value);
				Bitmap newBit = Bitmap
						.createScaledBitmap(bitmap, 100, 80, true);
				v.setImageBitmap(newBit);
			} catch (NumberFormatException nfe) {
				v.setImageURI(Uri.parse(value));
			}
		}

		@Override
		public int getCount() {
			return listItems != null ? listItems.size() : 0;
		}

		@Override
		public Object getItem(int position) {
			return listItems != null ? listItems.get(position) : null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}

	static class ViewHolder {
		public static ImageView image;
	}
	GridView grid;

}
