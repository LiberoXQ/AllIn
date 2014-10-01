package com.renren;

public class FeedComment{
	public Long id; // ����id
	public String time; // ����ʱ��
	public User author; // ���۵��û����û�������ֻ��id name avatar �ֶ���Ч�������ֶ���Ч
	public String content; // ��������
	
	public FeedComment() {
		// TODO Auto-generated constructor stub
		author = new User();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
