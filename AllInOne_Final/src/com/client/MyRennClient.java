package com.client;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adapter.RenrenAdapter;
import com.example.allinone_final.R;
import com.myview.MyLinearLayout;
import com.renn.rennsdk.RennClient;
import com.renn.rennsdk.RennResponse;
import com.renn.rennsdk.RennClient.LoginListener;
import com.renn.rennsdk.RennExecutor.CallBack;
import com.renn.rennsdk.exception.RennException;
import com.renn.rennsdk.param.ListFeedParam;
import com.renren.*;
import com.renren.RenrenNews.FeedType;

import android.app.Activity;
import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

public class MyRennClient {
	/* 人人客户端 单例模式 */
	private static MyRennClient myRenrenClient = null;
	
	private RennClient rennClient = null ;
	private Context context = null;
	
	private final int PageSize = 20;
	private int Page = 1;
	
	private final String RenrenAppKey = "e884884ac90c4182a426444db12915bf";
	private final String RenrenAppID  = "168802";
	private final String RenrenAppSecretKey = "094de55dc157411e8a5435c6a7c134c5";
	
	RenrenAdapter feedAdapter ;
	ArrayList<RenrenNews> arrayList;
	
	public static MyRennClient getInstance(Context context) {
		if( myRenrenClient == null ) {
			myRenrenClient = new MyRennClient(context);
		}
		return myRenrenClient;
	}
	
	private MyRennClient(final Context context) {
		this.context = context;
		rennClient = RennClient.getInstance(context);
		rennClient.init(RenrenAppID, RenrenAppKey, RenrenAppSecretKey);
		rennClient
				.setScope("read_user_blog read_user_photo read_user_status read_user_feed read_user_album "
						+ "read_user_comment read_user_share publish_blog publish_share "
						+ "send_notification photo_upload status_update create_album "
						+ "publish_comment publish_feed");
		rennClient.setTokenType("bearer");
		rennClient.setLoginListener(new LoginListener() {
			@Override
			public void onLoginSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText( context , R.string.renren_auth_success,
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onLoginCanceled() {
				// TODO Auto-generated method stub
			}
		});		
	}
	
	public boolean isLogin() {
		return rennClient.isLogin();
	}
	
	public void Login() {
		rennClient.login((Activity)context);
	}
	
	public void LogOut() {
		rennClient.logout();
	}
	
	public void changeLogStatus() {
		if( rennClient.isLogin()) {
			rennClient.logout();
			Toast.makeText(context, R.string.logout_renren_success, Toast.LENGTH_SHORT).show();
		} else {
			Login();
		}
	}
	
	public void getNews(final ListView listView,final MyLinearLayout mMyLinearLayout) {
		JSONArray myJson;
		Page = 1;
		ListFeedParam feed = new ListFeedParam();
		feed.setPageNumber( Page++ );
		feed.setPageSize(PageSize);
		try {
			myRenrenClient.rennClient.getRennService().sendAsynRequest( feed , new CallBack() {  
				JSONArray news;
				@Override
                public void onSuccess(RennResponse response) {
					try {
						news = response.getResponseArray();
						arrayList = jsonToList(news);
						feedAdapter = new RenrenAdapter((Activity)context, arrayList, listView);
						listView.setAdapter(feedAdapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			         
					Toast.makeText(context, "获取成功", Toast.LENGTH_SHORT).show();  
					mMyLinearLayout.onHeaderRefreshComplete("上次更新:" + new Date().toLocaleString());
                }             
                @Override
                public void onFailed(String errorCode, String errorMessage) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();                           
                }
			});
		} catch (RennException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void getMoreNews(final MyLinearLayout mMyLinearLayout) {
		ListFeedParam feed = new ListFeedParam();
		feed.setPageNumber(Page++);
		feed.setPageSize(PageSize);			
		if( rennClient != null)
			try {		
					rennClient.getRennService().sendAsynRequest(feed, new CallBack() {              
	                @Override
	                public void onSuccess(RennResponse response) {
	                	JSONArray jsonArray = null;
	                	try {
	                	    ArrayList <RenrenNews> TempList = null ;
							jsonArray = response.getResponseArray();
						    TempList = jsonToList(jsonArray);
						    for( int i = 0 ; i < TempList.size() ; i++)
							   {
						    	    arrayList.add(TempList.get(i));						   
							   }
						    feedAdapter.notifyDataSetChanged();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}    
	                	mMyLinearLayout.onFooterRefreshComplete();
	//	                Toast.makeText(getApplicationContext(), R.string.GetInfoSuccess + feedList.size() , Toast.LENGTH_SHORT).show();  
		                }             
		                @Override
		                public void onFailed(String errorCode, String errorMessage) {
		                    //textView.setText(errorCode+":"+errorMessage);
		                    Toast.makeText(context,R.string.GetInfoFailed , Toast.LENGTH_SHORT).show();                 
		                }
					});
		        }
			catch (RennException e1) {
	            // TODO Auto-generated catch block
	            e1.printStackTrace();
	        }
	}
	
	private ArrayList<RenrenNews> jsonToList(JSONArray jsonArray)
	{
		JSONObject jsonObject = null;
		JSONObject temp = null;
		RenrenNews response = null;
		User user = null;
		Lbs lbs = null;
		Source source = null;
		
		ArrayList<RenrenNews> feedList = new ArrayList<RenrenNews>();
		
		for(int i=0;i<jsonArray.length();i++)
		{
			response = new RenrenNews();
			user = new User();
			lbs = new Lbs();
			source = new Source();
			try {
				jsonObject = jsonArray.getJSONObject(i);

				temp = jsonObject.getJSONObject("sourceUser");
				user.setName(temp.getString("name"));
				user.setId(temp.getLong("id"));
				JSONArray array = temp.getJSONArray("avatar");
				for(int j = 0 ; j < array.length() ; j++ )
				{
					JSONObject tempJson = array.getJSONObject(j);
					user.avatar.add(tempJson.getString("url"));
				}
				response.setSourceUser(user);

				response.setTime(jsonObject.getString("time"));

				if( !jsonObject.isNull("lbs"))
				{
					temp = jsonObject.getJSONObject("lbs");
					lbs.setId(temp.getLong("id"));
					lbs.setPlaceId(temp.getString("placeId"));
					lbs.setName(temp.getString("name"));
					lbs.setLongitude(temp.getString("longitude"));
					lbs.setLatitude(temp.getString("latitude"));
					response.setLbs(lbs);
				} else 
					response.setLbs(null);

				if( !jsonObject.isNull("source"))
				{
					temp = jsonObject.getJSONObject("source");
					source.setText(temp.getString("text"));
					source.setUrl(temp.getString("url"));
					response.setSource(source);
				} else 
					response.setSource(null);
				FeedType type = FeedType.ALL;
				if(jsonObject.getString("type").equals("SHARE_VIDEO"))
					type = FeedType.SHARE_VIDEO;
				if(jsonObject.getString("type").equals("UPDATE_STATUS"))
					type = FeedType.UPDATE_STATUS;
				if(jsonObject.getString("type").equals("PUBLISH_BLOG"))
					type = FeedType.PUBLISH_BLOG;
				if(jsonObject.getString("type").equals("PUBLISH_ONE_PHOTO"))
					type = FeedType.PUBLISH_ONE_PHOTO;
				if(jsonObject.getString("type").equals("SHARE_PHOTO"))
					type = FeedType.SHARE_PHOTO;
				if(jsonObject.getString("type").equals("SHARE_ALBUM"))
					type = FeedType.SHARE_ALBUM;
				if(jsonObject.getString("type").equals("SHARE_BLOG"))
					type = FeedType.SHARE_BLOG;
				response.setType(type);
				
				if( !jsonObject.isNull("resource") ) {
					FeedResource resource = new FeedResource();
					resource.setUrl(jsonObject.getJSONObject("resource").getString("url"));
					response.setResource(resource);
				}
				if( !jsonObject.isNull("attachment")) {
					ArrayList <FeedAttachment> list = new ArrayList<FeedAttachment>();
					FeedAttachment attachment = new FeedAttachment();
					attachment.setOrginalUrl(jsonObject.getJSONArray("attachment").getJSONObject(0).getString("rawImageUrl"));
					list.add(attachment);
					response.setAttachment(list);
				}
				response.setMessage(jsonObject.getString("message"));
				response.setId(jsonObject.getLong("id"));
//				response.setMessage(jsonObject.toString());
				feedList.add(response);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				 Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				break;
			}
		}
		return feedList;
	}
	
}
