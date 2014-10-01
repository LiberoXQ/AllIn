package com.activity;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.client.MySinaClient;
import com.client.MyTXClient;
import com.example.allinone_final.R;
import com.myview.MyLinearLayout;
import com.myview.MyLinearLayout.OnFooterRefreshListener;
import com.myview.MyLinearLayout.OnHeaderRefreshListener;

public class SinaNewsActivity extends Activity implements OnClickListener , OnGestureListener , OnHeaderRefreshListener , OnFooterRefreshListener{
	
	ListView LvwTengxunNews;
	Button   BtnNeedOauty ;
	MyLinearLayout mMyLinearLayout;
	
	MySinaClient mySinaClient;
	
	private void InitView()
	{
		BtnNeedOauty = (Button) findViewById(R.id.BtnNeedRenrenOauty);
		LvwTengxunNews = (ListView) findViewById(R.id.LvwRenrenNews);
		BtnNeedOauty.setOnClickListener(this);
		mMyLinearLayout = (MyLinearLayout) findViewById(R.id.main_pull_refresh_view);		
		mMyLinearLayout.setOnHeaderRefreshListener(this);
		mMyLinearLayout.setOnFooterRefreshListener(this);
		mMyLinearLayout.setLastUpdated(new Date().toLocaleString());
		mySinaClient = MySinaClient.getInstance(this);
	}
	
	private void changeView() {
		mMyLinearLayout.setVisibility(View.GONE);
		BtnNeedOauty.setText(R.string.sina_need_auth);
		BtnNeedOauty.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.renn_news_list);
		InitView();
	}
	
	public void onResume() {
		super.onResume();
		changeView();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.BtnNeedRenrenOauty:
				mySinaClient.Login();
				break;
			default:
				break;
		}
	}
	
	
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
	}

	@Override
	public void onHeaderRefresh(MyLinearLayout view) {
		// TODO Auto-generated method stub
	}
}
