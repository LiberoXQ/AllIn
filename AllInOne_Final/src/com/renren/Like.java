package com.renren;

public class Like{
	enum LikeCatagory {
		SPORT, // ö�� �˶�
		MOVIE, // ö�� ��Ӱ
		CARTOON, // ö�� ����
		GAME, // ö�� ��Ϸ
		MUSIC, // ö�� ����
		BOOK, // ö�� �鼮
		INTEREST // ö�� ����
	}

	public LikeCatagory catagory; // ϲ��������
	public String name; // ϲ���Ķ���
	
	public LikeCatagory getCatagory() {
		return catagory;
	}
	public void setCatagory(LikeCatagory catagory) {
		this.catagory = catagory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
