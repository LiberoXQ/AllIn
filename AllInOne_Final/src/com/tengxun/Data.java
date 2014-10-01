package com.tengxun;

public class Data 
{
	private long timestamp;//服务器时间戳，不能用于翻页
	private long hasnext;//0-表示还有微博可拉取，1-已拉取完毕
	private Info info;
	private User user;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public long getHasnext() {
		return hasnext;
	}
	public void setHasnext(long hasnext) {
		this.hasnext = hasnext;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
