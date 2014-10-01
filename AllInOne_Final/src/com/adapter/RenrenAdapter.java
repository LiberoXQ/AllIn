package com.adapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.client.MyRennClient;
import com.example.allinone_final.R;
import com.renn.rennsdk.param.LikeUGCType;
import com.renren.*;
import com.util.*;
import com.util.AsyncImageLoader.ImageCallback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RenrenAdapter extends BaseAdapter{
	
	/* 构建Adapter 主要在GetView中设置内容和图片显示 */
	
	private ListView listView;
	private ArrayList<RenrenNews> listNews;
	private Context context;
	private LayoutInflater inflater ;
    private AsyncImageLoader asyncImageLoader;
	private MyRennClient myClient;
    
	public RenrenAdapter(Activity activity ,ArrayList<RenrenNews> listNew , ListView view)
	{
		this.context = activity;
		this.listNews = listNew;
		this.listView = view;
		inflater = activity.getLayoutInflater();
		asyncImageLoader = new AsyncImageLoader();
	}	
    
	/* 内部类 用来快捷定位ListView的子控件 */
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
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder = null ;
		// TODO Auto-generated method stub
		
		final RenrenNews feed=listNews.get(position);
		final User sourceUser=feed.getSourceUser();
		Lbs lbs=null;
		Source source=null;
		String imageUrl ;
		imageUrl = feed.getSourceUser().getAvatar().get(0);
	    lbs = feed.getLbs();
		source = feed.getSource();
		
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
//			Toast.makeText(context, sourceUser.id + "   " + feed.getId(), 10).show();
			holder.buttonZan.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
//					Toast.makeText(context, sourceUser.id + "   " + feed.getId(), 10).show();
//					myClient.likeNews(sourceUser.id, LikeUGCType.TYPE_PHOTO, feed.getId());
				}
			});
		}
		else
			holder = (ViewHolder)convertView.getTag();

		holder.textUserName.setText(sourceUser.getName());
		holder.textTime.setText(feed.getTime());
	    holder.imageView.setTag(imageUrl);
		if(lbs!=null)
		{
			holder.textLocation.setText(lbs.getName());
		}
		if(source!=null)
		{
			holder.textTime.setText(holder.textTime.getText().toString());
			holder.textLocation.setText("来自" + source.getText());
		}

		holder.textMessage.setText(feed.getMessage());
		holder.newsLink.removeAllViews();
		switch (feed.getType()) {
			case SHARE_PHOTO:
				holder.line1.setVisibility(View.VISIBLE);
				String Url =  feed.getAttachment().get(0).getOrginalUrl();
				ImageView Ivw1 = new ImageView(context);
			    Ivw1.setTag(Url);
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
//			    AsyncImageLoader2 asyImg3 = new AsyncImageLoader2();  
//				asyImg3.LoadImage(Url, Ivw1);  
				holder.newsLink.addView(Ivw1);
				LayoutParams ps = Ivw1.getLayoutParams();
				ps.width = 500;
				ps.height = 500;
				Ivw1.setLayoutParams(ps);
				break;
			case PUBLISH_ONE_PHOTO:
				holder.line1.setVisibility(View.VISIBLE);
				String Url2 =  feed.getAttachment().get(0).getOrginalUrl();
				ImageView Ivw2 = new ImageView(context);
			    Ivw2.setTag(Url2);
				Drawable cachedImage2 = asyncImageLoader.loadDrawable(context, Url2 , new ImageCallback() {
		            public void imageLoaded(Drawable imageDrawable, String Url) {
		                ImageView imageViewByTag = (ImageView) listView.findViewWithTag(Url);	                
		                if (imageViewByTag != null && imageDrawable != null ) { 
		                	imageViewByTag.setImageDrawable(imageDrawable);
		                }
		            }
		        });
				if (cachedImage2 == null) {
					Ivw2.setImageResource(R.drawable.myhead);
				}else{
					Ivw2.setImageDrawable(cachedImage2);
				}
//				AsyncImageLoader2 asyImg2 = new AsyncImageLoader2();  
//				asyImg2.LoadImage(Url2, Ivw2);  
				holder.newsLink.addView(Ivw2);
				LayoutParams ps2 = Ivw2.getLayoutParams();
				ps2.width = 500;
				ps2.height = 500;
				Ivw2.setLayoutParams(ps2);
				break;
			default:
				break;
		}
//		Drawable cachedImage = asyncImageLoader.loadDrawable(context, imageUrl, new ImageCallback() {
//            public void imageLoaded(Drawable imageDrawable, String imageUrl) {
//                ImageButton imageViewByTag = (ImageButton) listView.findViewWithTag(imageUrl);	                
//
//                if (imageViewByTag != null && imageDrawable != null ) { 
//                    imageViewByTag.setBackgroundDrawable(imageDrawable);
//                }
//            }
//        });
//		if (cachedImage == null) {
//			holder.imageView.setBackgroundResource(R.drawable.myhead);
//		}else{
//			holder.imageView.setBackgroundDrawable(cachedImage);
//		}
//		
		AsyncImageLoader2 asyImg = new AsyncImageLoader2();  
		asyImg.LoadImage(imageUrl, holder.imageView);  
		return convertView;
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
}
