package com.activity;

import com.client.MyRennClient;
import com.client.MySinaClient;
import com.client.MyTXClient;
import com.example.allinone_final.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class OauthActivity extends Activity implements OnClickListener{
	
	Button BtnOauthRenn;
	Button BtnOauthTengxun;
	Button BtnOauthSina;
	
	MyRennClient myRennClient ;
	MyTXClient   myTXClient;
	MySinaClient mySinaClient;
	
	private void initView() {
		BtnOauthRenn = (Button)findViewById(R.id.BtnRenrenLog);
		BtnOauthSina = (Button)findViewById(R.id.BtnSinaLog);
		BtnOauthTengxun = (Button)findViewById(R.id.BtnTengxunLog);
		BtnOauthRenn.setOnClickListener(this);
		BtnOauthSina.setOnClickListener(this);
		BtnOauthTengxun.setOnClickListener(this);
		myRennClient = MyRennClient.getInstance(this);
		myTXClient = MyTXClient.getInstance(this);
		mySinaClient = MySinaClient.getInstance(this);
	}
	
	private void changeView() {
		if(myRennClient.isLogin())
			BtnOauthRenn.setText(R.string.renren_cancel_auth);
		else
			BtnOauthRenn.setText(R.string.renren_need_auth);
		if(myTXClient.isLogin())
			BtnOauthTengxun.setText(R.string.tengxun_cancel_auth);
		else
			BtnOauthTengxun.setText(R.string.tengxun_need_auth);
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.oauth_activity);
		initView();
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
			case R.id.BtnRenrenLog:
				myRennClient.changeLogStatus();;
				break;
			case R.id.BtnTengxunLog:
				myTXClient.changeLogStatus();
				break;
			case R.id.BtnSinaLog:
				mySinaClient.Login();
				break;
			default:
				break;
		}
		changeView();
	}
}
