package com.client;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.allinone_final.R;
import com.sina.AccessTokenKeeper;
import com.sina.Constants;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class MySinaClient {
	
	//新浪微博授权
	/** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
    private SsoHandler mSsoHandler;
	/** 微博 Web 授权类，提供登陆等功能  */
    private WeiboAuth mWeiboAuth;
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
    
    private Context context;
    
    private static MySinaClient myClient = null;
    
    public static MySinaClient getInstance(Context cont) {
    	if( myClient == null )
    		myClient = new MySinaClient(cont);
    	return myClient;
    }
    
    private MySinaClient(Context cont) {
    	context = cont;
    	mWeiboAuth = new WeiboAuth(context, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
    }
    
    /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {
        
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
//                updateTokenView(false);
                
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(context, mAccessToken);
                Toast.makeText(context, 
                        R.string.sina_need_auth, Toast.LENGTH_SHORT).show();
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code");
                String message = "Hello";//getString(R.string.app_name);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(context, 
                    R.string.cancel_auth_success, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(context, 
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    
    public void Login() {
		mSsoHandler = new SsoHandler((Activity)context, mWeiboAuth);
        mSsoHandler.authorize(new AuthListener());
		// 或者使用：mWeiboAuth.authorize(new AuthListener(), Weibo.OBTAIN_AUTH_TOKEN);
        
        // 第一次启动本应用，AccessToken 不可用
        mAccessToken = AccessTokenKeeper.readAccessToken(context);
        
        if (mAccessToken != null && mAccessToken.isSessionValid())
        {
        	//登陆成功 跳转
        	//Intent _intent = new Intent(LogIn.this,Main.class);
        	//startActivity(_intent);
        	//Log.v("授权成功", "跳转MAIN");
        	Toast.makeText(context, R.string.logout_sina_success, Toast.LENGTH_LONG).show();
        }	
    }
}
