package com.example.tryshowpicture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private ListView mQuestions;
	//宽小于高，
//	private final int[][] pictures = {
//			{R.drawable.photo1,R.drawable.photo2},
//			{R.drawable.photo3},
//			{R.drawable.photo4, R.drawable.photo5, R.drawable.photo6}
//	};
	//图片大，会很卡
//	private final int[][] pictures = {
//			{R.drawable.girl_1,R.drawable.girl_1,R.drawable.girl_1},			
//			{R.drawable.girl_2,R.drawable.girl_2},
//			{R.drawable.girl_3,R.drawable.girl_3},
//			{R.drawable.girl_4},
//			{R.drawable.girl_5},
//			{R.drawable.girl_6,R.drawable.girl_6,R.drawable.girl_6}
//	};
	
	private final int[][] pictures = {
			{R.drawable.autumn_1,R.drawable.autumn_1,R.drawable.autumn_1},
			{R.drawable.autumn_2,R.drawable.autumn_2,R.drawable.autumn_2,R.drawable.autumn_2},
			{R.drawable.autumn_3,R.drawable.autumn_3},
			{R.drawable.autumn_4},
			{R.drawable.autumn_5,R.drawable.autumn_5,R.drawable.autumn_5,R.drawable.autumn_5}
	};
	private LayoutInflater inflater;
	private MyAdapter adapter;
	private Context context;
	final DisplayMetrics dm = new DisplayMetrics();
	int screenWidth;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mQuestions = (ListView)findViewById(R.id.question_list);
		inflater = LayoutInflater.from(this);
		adapter = new MyAdapter();
		mQuestions.setAdapter(adapter);		
		context = this;
		
		//获取屏幕信息
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pictures.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return pictures[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.list_item_image, parent, false);
			}
			ViewHolder holder = (ViewHolder) convertView.getTag();			
			if (holder == null) {
				holder = new ViewHolder();
				holder.image = (ImageView) convertView.findViewById(R.id.image);	
				holder.viewpager = (Gallery)convertView.findViewById(R.id.viewpager);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),  
					pictures[position][0]);
			Log.e("GaoMi","screenWidth = "+screenWidth +" bitmap width = "+bitmap.getWidth());
			if(pictures[position].length>1){
				holder.image.setVisibility(View.GONE);
				holder.viewpager.setVisibility(View.VISIBLE);
/*				ArrayList<ImageView> list = new ArrayList<ImageView>();
				for(int i=0; i<pictures[position].length; i++){
					ImageView iv = new ImageView(context);
					iv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));					
					iv.setImageResource(pictures[position][i]);
					list.add(iv);
				}*/
/*				if(screenWidth < bitmap.getWidth()){
					Gallery.LayoutParams gl = (android.widget.Gallery.LayoutParams) holder.viewpager.getLayoutParams();
					gl.width = (int)(((float)screenWidth/(float)bitmap.getWidth())*bitmap.getHeight());
					holder.viewpager.setLayoutParams(gl);
				}*/
				
				if(screenWidth < bitmap.getWidth()){
					AbsListView.LayoutParams al = (AbsListView.LayoutParams) convertView.getLayoutParams();
					al.height =(int)(((float)screenWidth/(float)bitmap.getWidth())*bitmap.getHeight()); 
					convertView.setLayoutParams(al);
				}					
				holder.viewpager.setAdapter(new ImageAdapter(context, pictures[position]));
			} else {
				holder.image.setVisibility(View.VISIBLE);				
				holder.viewpager.setVisibility(View.GONE);
				//holder.image.setBackgroundResource(pictures[position][0]);
				holder.image.setImageResource(pictures[position][0]);
//				LayoutParams params = holder.image.getLayoutParams();
//				//Drawable drawable  = context.getResources().getDrawable(pictures[position][0]);
//				params.height = bitmap.getHeight();
//				Log.e("GaoMi","height = "+bitmap.getHeight()+" width = "+bitmap.getWidth());
//				holder.image.setLayoutParams(params);
//				AbsListView.LayoutParams p = (android.widget.AbsListView.LayoutParams) convertView.getLayoutParams();
//				p.height = bitmap.getHeight();

/*				AbsListView.LayoutParams lp = new AbsListView.LayoutParams( 
						bitmap.getWidth(), bitmap.getHeight()); */
//	            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
//	            		LinearLayout.LayoutParams.MATCH_PARENT, 70);
//	            convertView.setLayoutParams(lp);
				if(screenWidth < bitmap.getWidth()){
/*					AbsListView.LayoutParams lp = new AbsListView.LayoutParams( 
						LinearLayout.LayoutParams.MATCH_PARENT, 
						(int)(((float)screenWidth/(float)bitmap.getWidth())*bitmap.getHeight())); 
					convertView.setLayoutParams(lp);
*/
					AbsListView.LayoutParams al = (AbsListView.LayoutParams) convertView.getLayoutParams();
					al.height =(int)(((float)screenWidth/(float)bitmap.getWidth())*bitmap.getHeight()); 
					convertView.setLayoutParams(al);
				}				
				
			}
			

			return convertView;
		}	
	}
	class ViewHolder {
		ImageView image;
		Gallery viewpager;
	}
	
	class ImageAdapter extends BaseAdapter {
			private Context mContext;	
			private int[] list;
			public ImageAdapter(Context context, int[] list) {
				this.mContext = context;
				this.list = list;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.length;
			}

			@Override
			public Object getItem(int i) {
				// TODO Auto-generated method stub
				return i;
			}

			@Override
			public long getItemId(int i) {
				// TODO Auto-generated method stub
				return i;
			}
			@Override
			public View getView(int i, View view, ViewGroup viewgroup) {
				view = new ImageView(mContext);		
				((ImageView) view).setScaleType(ScaleType.FIT_START);
				((ImageView) view).setImageResource(list[i]);
				return view;
			}

/*			@Override
			public View getView(int i, View view, ViewGroup viewgroup) {
				view = new ImageView(mContext);
				((ImageView) view).setImageBitmap(ListItem.readBitmap(mContext,
						itemImages[i % itemImages.length]));
				return view;
			}*/

		

		/***
		 * 以最省内存的方式读取本地资源的图片
		 * 
		 * @param context
		 * @param resId
		 * @return
		 */
/*		public static Bitmap readBitmap(Context context, int rid) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			options.inPurgeable = true;
			options.inInputShareable = true;
			// 获取资源图片
			InputStream is = context.getResources().openRawResource(rid);
			return BitmapFactory.decodeStream(is, null, options);
		}*/
	}

/*	class MyPagerAdapter extends PagerAdapter{
		private ArrayList<ImageView> list;
		public MyPagerAdapter(ArrayList<ImageView> list){
			this.list = list;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		public Object instantiateItem(View container, int position) {
			return list.get(position);
		}
		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView(list.get(position));
		}
	}*/
}
