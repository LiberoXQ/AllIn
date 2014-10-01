package com.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adapter.RenrenAdapter;
import com.adapter.TengxunAdapter;
import com.example.allinone_final.R;
import com.tencent.weibo.sdk.android.api.TimeLineAPI;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.component.Authorize;
import com.tencent.weibo.sdk.android.component.GeneralDataShowActivity;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tengxun.Info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;


public class MyTXClient {
	
	/*
	 * 强哥  需要你把腾讯微博的JavaBean
	 * 和 获取关注的新鲜事的JSON拿到
	 *
	 */
	
	private static MyTXClient myTXClient = null;
	
	private AccountModel account = null;
	
	private Context context = null;
	private ListView listView = null;
	
	private String accessToken;// 用户访问令牌
	private TimeLineAPI timeLineAPI;//时间线API
	private HttpCallback mCallBack;//回调函数
	private PopupWindow loadingWindow =  null;
	private String requestFormat = "json";
	
	public MyTXClient(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public static MyTXClient getInstance(Context context) {
		if( myTXClient == null ) {
			myTXClient = new MyTXClient(context);
		}
		return myTXClient;
	}
	
	public boolean isLogin() {
		// TODO Auto-generated method stub
		String accessToken = Util.getSharePersistent(context , "ACCESS_TOKEN");
		return accessToken != null && !accessToken.equals("");
	}

	public void Login() {
		// TODO Auto-generated method stub
		long appid = Long.valueOf(Util.getConfig().getProperty(
				"APP_KEY"));
		String app_secket = Util.getConfig().getProperty("APP_KEY_SEC");
		auth( context , appid , app_secket);
		String accessToken = Util.getSharePersistent(context , "ACCESS_TOKEN");
		account = new AccountModel( accessToken );
	}

	public void LogOut() {
		// TODO Auto-generated method stub
		Util.clearSharePersistent(context);
		Toast.makeText(context, R.string.logout_tengxun_success, Toast.LENGTH_SHORT).show();
	}
	
	public void changeLogStatus() {
		if( isLogin() ) {
			LogOut();
		} else {
			Login();
		}
	}
	
	private static void auth(final Context cont,long appid, String app_secket) 
	{
		final Context context = cont;
		// 注册当前应用的appid和appkeysec，并指定一个OnAuthListener
		// OnAuthListener在授权过程中实施监听
		AuthHelper.register( context , appid, app_secket, new OnAuthListener() 
		{
			// 如果当前设备没有安装腾讯微博客户端，走这里
			@Override
			public void onWeiBoNotInstalled() 
			{
				AuthHelper.unregister(cont);
				Intent i = new Intent( cont , Authorize.class);
				cont.startActivity(i);
			}

			// 如果当前设备没安装指定版本的微博客户端，走这里
			@Override
			public void onWeiboVersionMisMatch() 
			{
				AuthHelper.unregister(cont);
				Intent i = new Intent(cont, Authorize.class);
				cont.startActivity(i);
			}

			// 如果授权失败，走这里
			@Override
			public void onAuthFail(int result, String err) 
			{
				AuthHelper.unregister(cont);
			}

			// 授权成功，走这里
			// 授权成功后，所有的授权信息是存放在WeiboToken对象里面的，可以根据具体的使用场景，将授权信息存放到自己期望的位置，
			// 在这里，存放到了applicationcontext中
			@Override
			public void onAuthPassed(String name, WeiboToken token) 
			{			
				Util.saveSharePersistent(context, "ACCESS_TOKEN",
						token.accessToken);
				Util.saveSharePersistent(context, "EXPIRES_IN",
						String.valueOf(token.expiresIn));
				Util.saveSharePersistent(context, "OPEN_ID", token.openID);
				Util.saveSharePersistent(context, "REFRESH_TOKEN", "");

				Util.saveSharePersistent(context, "CLIENT_ID", Util.getConfig()
						.getProperty("APP_KEY"));
				Util.saveSharePersistent(context, "AUTHORIZETIME",
						String.valueOf(System.currentTimeMillis() / 1000l));
				AuthHelper.unregister(cont);		
				Toast.makeText(context, R.string.login_tengxun_success, Toast.LENGTH_SHORT).show();
			}
		});
		AuthHelper.auth(cont, "");
	}
	
	/* 获取新鲜事 */
	public void getNews(final ListView listView) {
		accessToken = Util.getSharePersistent(context,
				"ACCESS_TOKEN");
		if (accessToken == null || "".equals(accessToken)) {
			Toast.makeText(context, "请先授权",
					Toast.LENGTH_SHORT).show();
			return ;
		} 
			
		mCallBack = new HttpCallback() {
			@Override
			public void onResult(Object object) {
				ModelResult result = (ModelResult) object;
				ArrayList<Info> arrayList = null;
				TengxunAdapter newsAdapter = null;
				if(loadingWindow!=null && loadingWindow.isShowing()){

					loadingWindow.dismiss();
				}
				if(result!=null && result.isSuccess()){
					Toast.makeText(context,
							"获取成功", Toast.LENGTH_SHORT)
							.show();
					JSONObject newsJson = (JSONObject)result.getObj();
					arrayList = ArrayToList(newsJson);
					newsAdapter = new TengxunAdapter(context, arrayList, listView);
					listView.setAdapter(newsAdapter);
				}else{
					Toast.makeText(context,
							"调用失败", Toast.LENGTH_SHORT)
							.show();
				}
				
			}
		};
			
		AccountModel account = new AccountModel(accessToken);
		timeLineAPI = new TimeLineAPI(account);
		timeLineAPI.getHomeTimeLine(context, 0, 0, 30, 0, 0, requestFormat, mCallBack, null, BaseVO.TYPE_JSON);
	}
	
	/* Json 2 Array*/
	private ArrayList<Info> ArrayToList(JSONObject newsJson){
		ArrayList<Info> myNewsInfo = new ArrayList<Info>();
		HashMap hashMap = new HashMap<String, String>();
		try {
			if( newsJson.getInt("ret") == 0) {	
				JSONObject newsData = newsJson.getJSONObject("data");
				Intent i = new Intent(context,GeneralDataShowActivity.class);
				String s = "";      /* 用户名 备注映射 HashMap*/
				JSONObject newsUser = newsData.getJSONObject("user");
				for(Iterator iter = newsUser.keys(); iter.hasNext();) { 
		            String key = (String)iter.next(); 
		            hashMap.put(key,newsUser.get(key));
		        } 
				JSONArray newsInfo = newsData.getJSONArray("info");
				for(int j = 0 ; j < newsInfo.length() ; j++) {
					JSONObject newsObj =  newsInfo.getJSONObject(j);
					Info info = new Info();
		        	info.setHead(newsObj.getString("head") + "/50.jpg");
//		        	info.setText(newsObj.toString());
		        	info.setText(newsObj.getString("text"));
		        	info.setNick(newsObj.getString("nick"));
		        	info.setLocation(newsObj.getString("location"));
		        	info.setTimestamp(newsObj.getLong("timestamp"));
		        	if(!newsObj.isNull("image"))
		        		info.setImage(newsObj.getJSONArray("image").get(0).toString() + "/220.jpg");
		        	myNewsInfo.add(info);
				}		
			} 		
			
		} catch (Exception e) {
			
		}
		return myNewsInfo;
	}
	
}
