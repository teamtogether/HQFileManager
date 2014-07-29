package com.huaqin.app.hqfilemanager;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ButtonSwitchFragment extends Fragment {
	private final String IMAGE_TYPE = "image/*";

	ArrayList<Bitmap> listItems;
	int[] image_check;// 用于标志哪张图片被选中了
	Cursor cursor;
	private static final String PROJECTION[] = { "_data", };
	private static final String ORDER = "_data ASC";
	private LayoutInflater mInflater;
	private static final int UPDATE_ADAPTER = 1;
	private GridAdapter simoleadapter;
	private Activity mContext;
	private int count;
	private LinearLayout button_linearlayout;
	private List position;
	private Button translate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
		getAlbum.setType(IMAGE_TYPE);
		mContext = getActivity();
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
			cursor = mContext.getContentResolver().query(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,// 指定缩略图数据库的Uri
					PROJECTION,// 指定所要查询的字段
					null,// 查询条件
					null, // 查询条件中问号对应的值
					ORDER);
			count = cursor.getCount();
			image_check = new int[count];
			int i = 1;
			Log.d("liye", "liye count = " + count);
			// listItems.clear();
			while (cursor.moveToNext()) {
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
				Bitmap imageBitmap = getBitmap(path);
				listItems.add(imageBitmap);
				// if (i % 20 == 0 || i == count) {
				handler.sendEmptyMessage(UPDATE_ADAPTER);
				Log.d("liye", "liye count % 20 = " + count % 20 + " count = "
						+ count);
				// }
				i++;
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
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		// 返回为空
		BitmapFactory.decodeFile(path, opts);
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = (int) 8f;
		Bitmap bitmap = BitmapFactory.decodeFile(path, opts);
		// getActivity().getCacheDir();
		return bitmap;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootview = inflater.inflate(R.layout.buttonswitch_fragment,
				container, false);
		listItems = new ArrayList<Bitmap>();
		position = new ArrayList();
		simoleadapter = new GridAdapter();
		button_linearlayout = (LinearLayout) rootview
				.findViewById(R.id.button_Linearlayout);
		grid = (GridView) rootview.findViewById(R.id.GridView_picture);
		grid.setAdapter(simoleadapter);
		translate = (Button) rootview.findViewById(R.id.translate_image);
		new Thread(updateThread).start();
		grid.setOnItemClickListener(simoleadapter);
		return rootview;

	}

	class GridAdapter extends BaseAdapter implements OnItemClickListener {
		ViewHolder holder;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.image, null);
				holder = new ViewHolder(
						(ImageView) convertView.findViewById(R.id.image_Item),
						(ImageView) convertView
								.findViewById(R.id.image_Item1_enter));
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.image.setImageBitmap(listItems.get(position));

			if (image_check[position] == 1) {
				holder.checkBox.setVisibility(View.VISIBLE);
			} else
				holder.checkBox.setVisibility(View.INVISIBLE);

			if (get_arraysum() == 0) {
				// Log.e("liye", get_arraysum()+"----------------------      ");
				button_linearlayout.setVisibility(View.INVISIBLE);
				// 设置按键显示
			} else {
				Log.e("liye", get_arraysum()
						+ "---------------------- 4444444444    ");
				button_linearlayout.setVisibility(View.VISIBLE);
				// 设置按键隐藏
			}
			// get_position();
			// Log.e("liye", "gone" + "------" + position.get(1));
			return convertView;
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
			return position;
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if (image_check[arg2] == 1) {
				// Log.e("liye", "gone" + "------" + image_check[arg2]);
				image_check[arg2] = 0;
				translate.setText("传    输(" + get_arraysum() / 1 + ")");
				simoleadapter.notifyDataSetChanged();
				// position.remov;
				Log.e("liye", "gone" + "------" + position.size());
				// Log.e("liye", "gone" + "------" +
				// image_check[arg2]+"-----=======--------"+get_arraysum());
			} else {
				image_check[arg2] = 1;
				simoleadapter.notifyDataSetChanged();
				position.add(arg2);
				translate.setText("传    输(" + get_arraysum() / 1 + ")");
				Log.e("liye", "gone" + "------" + position.size());
				// Log.e("liye", "------" +
				// image_check[arg2]+"-------------"+get_arraysum());
			}

		}

		int i;

		public int get_arraysum() {
			int sum = 0;
			for (; i < count; i++) {
				sum = sum + image_check[i];
			}
			i = 0;
			return sum;
		}
	}

	class ViewHolder {
		public ImageView image;
		public ImageView checkBox;

		public ViewHolder(ImageView imageview, ImageView checkBox) {
			this.image = imageview;
			this.checkBox = checkBox;
		}
	}

	GridView grid;

	// 用于传输按钮中 获取被点击照片的位置
	public void get_image_position() {
		int i = 0;
		position.removeAll(position);
		for (; i < image_check.length; i++) {
			if (image_check[i] == 1) {
				position.add(i);
			}
		}

	}

}
