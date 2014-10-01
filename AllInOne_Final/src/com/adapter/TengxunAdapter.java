package com.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.client.MyTXClient;
import com.example.allinone_final.R;
import com.tengxun.Info;
import com.util.AsyncImageLoader;
import com.util.AsyncImageLoader2;
import com.util.AsyncImageLoader.ImageCallback;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TengxunAdapter extends BaseAdapter{

	private ListView listView;

	private Context context;
	private LayoutInflater inflater ;
    private AsyncImageLoader asyncImageLoader;
	private MyTXClient myTXClient; 
	private ArrayList<Info> listNews;
	
	public TengxunAdapter(Context context, ArrayList<Info> arrayList, ListView listView ){
		this.context = context;
		this.listNews= arrayList;
		this.listView = listView;
		inflater = ((Activity)context).getLayoutInflater();
		asyncImageLoader = new AsyncImageLoader();
	}
	
	public static class ViewHolder
	{
		TextView textUserName;
		TextView textTime;
		TextView textLocation;
		TextView textMessage;
		ImageButton imageView;
		LinearLayout line1;
		LinearLayout newsLink;
		ImageButton buttonShare;
		ImageButton buttonZan;
		ImageButton buttonCom;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listNews.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null ;
		// TODO Auto-generated method stub
		
		final Info info = listNews.get(position);
		
		if(convertView==null){
			holder = new ViewHolder();
			convertView= inflater.inflate(R.layout.news_item, null);
			holder.textLocation = (TextView) convertView.findViewById(R.id.TvwUpdateLocation);
			holder.textUserName = (TextView) convertView.findViewById(R.id.TvwUserName);	
			holder.textMessage=(TextView) convertView.findViewById(R.id.TvwNews);
			holder.textTime =(TextView)convertView.findViewById(R.id.TvwUpdateTime);
			holder.imageView = (ImageButton)convertView.findViewById(R.id.ImgBtnUserImage);
			holder.line1 = (LinearLayout)convertView.findViewById(R.id.LltSpLine);
			holder.newsLink = (LinearLayout)convertView.findViewById(R.id.LltNewsLink);
			holder.buttonZan = (ImageButton)convertView.findViewById(R.id.MniIbtnZan);
			convertView.setTag(holder);
			holder.buttonZan.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				}
			});
		}
		else
			holder = (ViewHolder)convertView.getTag();

		holder.textUserName.setText(info.getNick());
		holder.textTime.setText(info.getText());
		holder.textLocation.setText("来自" + info.getLocation());
		holder.textMessage.setText(info.getText() + "\n" + info.getImage());
		holder.textTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(info.getTimestamp() * 1000)));
		AsyncImageLoader2 asyImg = new AsyncImageLoader2();  
		asyImg.LoadImage(info.getHead(), holder.imageView);  
		holder.newsLink.removeAllViews();
		String Url = info.getImage();
		if( Url != null ) {
			holder.line1.setVisibility(View.VISIBLE);
			ImageView Ivw1 = new ImageView(context);
//			AsyncImageLoader2 asyImg2 = new AsyncImageLoader2();  
//			asyImg2.LoadImage(Url, Ivw1); 
		    Ivw1.setTag(Url);
		    Log.v("src", Url);
			Drawable cachedImage = asyncImageLoader.loadDrawable(context, Url , new ImageCallback() {
	            public void imageLoaded(Drawable imageDrawable, String Url) {
	                ImageView imageViewByTag = (ImageView) listView.findViewWithTag(Url);	                
	                if (imageViewByTag != null && imageDrawable != null ) { 
	                	imageViewByTag.setImageDrawable(imageDrawable);
	                }
	            }
	        });
			if (cachedImage == null) {
				Ivw1.setImageResource(R.drawable.myhead);
			}else{
				Ivw1.setImageDrawable(cachedImage);
			}
			holder.newsLink.addView(Ivw1);
//			LayoutParams ps = Ivw1.getLayoutParams();
//			ps.width = 500;
//			ps.height = 500;
//			Ivw1.setLayoutParams(ps);
		}
		return convertView;
	}
}
