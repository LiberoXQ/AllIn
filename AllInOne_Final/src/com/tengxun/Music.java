package com.tengxun;

import android.R.string;

public class Music 
{
	private string author;//演唱者
	private string url;//音频地址
	private string title;//音频名字，歌名
	
	public string getAuthor() {
		return author;
	}
	public void setAuthor(string author) {
		this.author = author;
	}
	public string getUrl() {
		return url;
	}
	public void setUrl(string url) {
		this.url = url;
	}
	public string getTitle() {
		return title;
	}
	public void setTitle(string title) {
		this.title = title;
	}
}
