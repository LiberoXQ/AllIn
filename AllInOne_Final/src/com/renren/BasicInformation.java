package com.renren;

public class BasicInformation{
	enum Sex {
		FEMALE, // ö�� Ů��
		MALE // ö�� ����
	}

	private Sex sex; // �û��Ա�
	private String birthday; // �û����գ���ʽΪ'yyyy-mm-dd'��'y0��-mm-dd'
	private HomeTown homeTown; // ������Ϣ
	
	public BasicInformation() {
		// TODO Auto-generated constructor stub
		homeTown = new HomeTown();
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public HomeTown getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(HomeTown homeTown) {
		this.homeTown = homeTown;
	}
	
}
