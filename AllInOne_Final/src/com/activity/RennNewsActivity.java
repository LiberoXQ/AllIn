package com.activity;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.client.MyRennClient;
import com.example.allinone_final.R;
import com.myview.MyLinearLayout;
import com.myview.MyLinearLayout.*;
import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.ListFeedParam;
import com.renren.*;
import com.renren.RenrenNews.FeedType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class RennNewsActivity extends Activity implements OnClickListener , OnGestureListener , OnHeaderRefreshListener , OnFooterRefreshListener{
	
	private MyRennClient myRennClient;
	
	ListView LvwRenrenNews;
	Button   BtnNeedOauty ;
	MyLinearLayout mMyLinearLayout;
	
	/* 初始化View控件 */
	private void InitView()
	{
		BtnNeedOauty = (Button) findViewById(R.id.BtnNeedRenrenOauty);
		LvwRenrenNews = (ListView) findViewById(R.id.LvwRenrenNews);
		BtnNeedOauty.setOnClickListener(this);
		mMyLinearLayout = (MyLinearLayout) findViewById(R.id.main_pull_refresh_view);
		myRennClient = MyRennClient.getInstance(this);
		mMyLinearLayout.setOnHeaderRefreshListener(this);
		mMyLinearLayout.setOnFooterRefreshListener(this);
		mMyLinearLayout.setLastUpdated(new Date().toLocaleString());
	}
	
	/*  更改View的状态  主要为了更改按钮 和 显示新鲜事 */
	private void changeView() {
		if(myRennClient.isLogin()) {
			mMyLinearLayout.setVisibility(View.VISIBLE);
			BtnNeedOauty.setVisibility(View.GONE);
			myRennClient.getNews(LvwRenrenNews,mMyLinearLayout);
		} else {
			mMyLinearLayout.setVisibility(View.GONE);
			BtnNeedOauty.setVisibility(View.VISIBLE);
		}
	}
	
//	/* 将JSon转化为 ArrayList供Adapter使用 */
//	private ArrayList<RenrenNews> jsonToList(JSONArray jsonArray)
//	{
//		JSONObject jsonObject = null;
//		JSONObject temp = null;
//		RenrenNews response = null;
//		User user = null;
//		Lbs lbs = null;
//		Source source = null;
//		
//		ArrayList<RenrenNews> feedList = new ArrayList<RenrenNews>();
//		
//		for(int i=0;i<jsonArray.length();i++)
//		{
//			response = new RenrenNews();
//			user = new User();
//			lbs = new Lbs();
//			source = new Source();
//			try {
//				jsonObject = jsonArray.getJSONObject(i);
//
//				temp = jsonObject.getJSONObject("sourceUser");
//				user.setName(temp.getString("name"));
//				user.setId(temp.getLong("id"));
//				JSONArray array = temp.getJSONArray("avatar");
//				for(int j = 0 ; j < array.length() ; j++ )
//				{
//					JSONObject tempJson = array.getJSONObject(j);
//					user.avatar.add(tempJson.getString("url"));
//				}
//				response.setSourceUser(user);
//
//				response.setTime(jsonObject.getString("time"));
//
//				if( !jsonObject.isNull("lbs"))
//				{
//					temp = jsonObject.getJSONObject("lbs");
//					lbs.setId(temp.getLong("id"));
//					lbs.setPlaceId(temp.getString("placeId"));
//					lbs.setName(temp.getString("name"));
//					lbs.setLongitude(temp.getString("longitude"));
//					lbs.setLatitude(temp.getString("latitude"));
//					response.setLbs(lbs);
//				} else 
//					response.setLbs(null);
//
//				if( !jsonObject.isNull("source"))
//				{
//					temp = jsonObject.getJSONObject("source");
//					source.setText(temp.getString("text"));
//					source.setUrl(temp.getString("url"));
//					response.setSource(source);
//				} else 
//					response.setSource(null);
//				FeedType type = FeedType.ALL;
//				if(jsonObject.getString("type").equals("SHARE_VIDEO"))
//					type = FeedType.SHARE_VIDEO;
//				if(jsonObject.getString("type").equals("UPDATE_STATUS"))
//					type = FeedType.UPDATE_STATUS;
//				if(jsonObject.getString("type").equals("PUBLISH_BLOG"))
//					type = FeedType.PUBLISH_BLOG;
//				if(jsonObject.getString("type").equals("PUBLISH_ONE_PHOTO"))
//					type = FeedType.PUBLISH_ONE_PHOTO;
//				if(jsonObject.getString("type").equals("SHARE_PHOTO"))
//					type = FeedType.SHARE_PHOTO;
//				if(jsonObject.getString("type").equals("SHARE_ALBUM"))
//					type = FeedType.SHARE_ALBUM;
//				if(jsonObject.getString("type").equals("SHARE_BLOG"))
//					type = FeedType.SHARE_BLOG;
//				response.setType(type);
//				
//				if( !jsonObject.isNull("resource") ) {
//					FeedResource resource = new FeedResource();
//					resource.setUrl(jsonObject.getJSONObject("resource").getString("url"));
//					response.setResource(resource);
//				}
//				if( !jsonObject.isNull("attachment")) {
//					ArrayList <FeedAttachment> list = new ArrayList<FeedAttachment>();
//					FeedAttachment attachment = new FeedAttachment();
//					attachment.setOrginalUrl(jsonObject.getJSONArray("attachment").getJSONObject(0).getString("rawImageUrl"));
//					list.add(attachment);
//					response.setAttachment(list);
//				}
//				response.setMessage(jsonObject.getString("message"));
//				response.setId(jsonObject.getLong("id"));
//				response.setMessage(jsonObject.toString() + "\n" + jsonObject.getString("message"));
//				feedList.add(response);
//				
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				 Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
//				e.printStackTrace();
//				break;
//			}
//		}
//		return feedList;
//	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.renn_news_list);
		InitView();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		changeView();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.BtnNeedRenrenOauty:
			myRennClient.Login();
			break;
		default:
			break;
		}
	}
	
	/* 下面的都是下拉和上拉刷新的部分 只需要改最下面两个函数 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onFooterRefresh(MyLinearLayout view) {
		// TODO Auto-generated method stub
		myRennClient.getMoreNews(mMyLinearLayout);
	}

	@Override
	public void onHeaderRefresh(MyLinearLayout view) {
		// TODO Auto-generated method stub
		myRennClient.getNews(LvwRenrenNews, mMyLinearLayout);
	}
}
