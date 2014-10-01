package com.tengxun;

import android.R.string;

public class Video 
{
	private string picurl;//缩略图
	private string player;//播放器地址
	private string realurl;//视频原地址
	private string shorturl;//视频的短url
	private string title;//视频标题
	
	public string getPicurl() {
		return picurl;
	}
	public void setPicurl(string picurl) {
		this.picurl = picurl;
	}
	public string getPlayer() {
		return player;
	}
	public void setPlayer(string player) {
		this.player = player;
	}
	public string getRealurl() {
		return realurl;
	}
	public void setRealurl(string realurl) {
		this.realurl = realurl;
	}
	public string getShorturl() {
		return shorturl;
	}
	public void setShorturl(string shorturl) {
		this.shorturl = shorturl;
	}
	public string getTitle() {
		return title;
	}
	public void setTitle(string title) {
		this.title = title;
	}
}
