package com.activity;

import com.example.allinone_final.R;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends ActivityGroup implements OnTouchListener , OnClickListener , OnGestureListener{
	
	TextView tvwRenren;
	TextView tvwSina;
	TextView tvwTengxun;
	TextView tvwOauth;
	private ImageView imageView1;
	private LinearLayout container = null;
	
	private void initView() {
		container = (LinearLayout) findViewById(R.id.pvr_user_pager);	
		tvwRenren = (TextView)findViewById(R.id.TvwRenren);
		tvwOauth = (TextView)findViewById(R.id.TvwOauth);
		tvwSina = (TextView)findViewById(R.id.TvwSina);
		tvwTengxun = (TextView)findViewById(R.id.TvwTengxun);
		imageView1 = (ImageView) findViewById(R.id.cursor);      
		tvwRenren.setOnClickListener(this);
		tvwOauth.setOnClickListener(this);
		tvwSina.setOnClickListener(this);
		tvwTengxun.setOnClickListener(this);
		InitImageView();
		OnSelectChange(0);
	}
	
	int CurrentIndex = 0 ;
	int FLING_MIN_DISTANCE = 50 ;
	double FLING_MIN_VELOCITY = 0.2;
	
    private GestureDetector ad_detector;
	
    private void OnSelectChange(int arg0){
		container.removeAllViews();
		switch (arg0) {
			case 0:
				 container.addView(getLocalActivityManager().startActivity(
			             "Module" + arg0,
			             new Intent(MainActivity.this, RennNewsActivity.class)
			                     .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
			             .getDecorView());
				break;
			case 1:
				 container.addView(getLocalActivityManager().startActivity(
			             "Module" + arg0,
			             new Intent(MainActivity.this,  SinaNewsActivity.class)
			                     .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
			             .getDecorView());
				 break;
			case 2:
				 container.addView(getLocalActivityManager().startActivity(
			             "Module" + arg0,
			             new Intent(MainActivity.this, TengxunNewsActivity.class)
			                     .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
			             .getDecorView());
				 break;
			case 3:
				container.addView(getLocalActivityManager().startActivity(
			             "Module" + arg0,
			             new Intent(MainActivity.this,  OauthActivity.class)
			                     .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
			             .getDecorView());
				 break;
			default:
				break;
		}
//		setImageViewWidth(tvwRenren.getWidth());
		InitImageView();
		Animation animation = new TranslateAnimation(tvwRenren.getWidth() * CurrentIndex,
				tvwRenren.getWidth() * arg0, 0, 0);
		CurrentIndex = arg0;
		animation.setFillAfter(true);
		animation.setDuration(300);
		imageView1.startAnimation(animation);
    }
    
	private void setImageViewWidth(int width) {		
		if (width != imageView1.getWidth()) 
		{
			LayoutParams laParams = (LayoutParams) imageView1.getLayoutParams();
			laParams.width = width;		
			imageView1.setLayoutParams(laParams);
		}
	}
    
	private void InitImageView() {
		Matrix matrix = new Matrix();
		matrix.postTranslate(0, 0);
		setImageViewWidth(this.getWindowManager().getDefaultDisplay().getWidth()/ 4);
		imageView1.setImageMatrix(matrix);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		initView();
		ad_detector = new GestureDetector(this);   
		LinearLayout main_layout = (LinearLayout) findViewById(R.id.mainLayout1);
		main_layout.setOnTouchListener(this);
		main_layout.setLongClickable(true);  
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
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
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  float velocityY)  {  
        // TODO Auto-generated method stub  
        if(e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY)  
        {  
            if( CurrentIndex < 3 )
            {
            	OnSelectChange( CurrentIndex + 1 );
            }
        }  
        else 
        	if (e2.getX()-e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) >FLING_MIN_VELOCITY) {   
        		if( CurrentIndex > 0 )
        		{
        			OnSelectChange( CurrentIndex - 1 );
        		}
        }  
        return false;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.TvwRenren:
				OnSelectChange(0);
				break;
			case R.id.TvwSina:
				OnSelectChange(1);
				break;
			case R.id.TvwTengxun:
				OnSelectChange(2);
				break;
			case R.id.TvwOauth:
				OnSelectChange(3);
				break;
			default:
				break;
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return ad_detector.onTouchEvent(event); 
	}
}
