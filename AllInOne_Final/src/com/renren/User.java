package com.renren;

import java.util.ArrayList;
import java.util.List;

public class User{
	enum EmotionalState {
		INLOVE, // ö�� ������
		OTHER, // ö�� ����
		SINGLE, // ö�� ����
		MARRIED, // ö�� �ѻ�
		UNOBVIOUSLOVE, // ö�� ����
		DIVORCE, // ö�� ����
		ENGAGE, // ö�� ����
		OUTLOVE // ö�� ʧ��
	}

	public Long id; // �û�ID
	public String name; // �û���
	public List<String> avatar; // �û�ͷ���б�
	public Integer star; // �Ƿ�Ϊ�Ǽ��û���ֵ��1����ʾ���ǡ���ֵ��0����ʾ�����ǡ�
	public BasicInformation basicInformation; // �û�������Ϣ
	public List<School> education; // �û�ѧУ��Ϣ
	public List<Work> work; // ������Ϣ
	public List<Like> like; // ϲ��
	public EmotionalState emotionalState; // ����״̬
	
	public User() {
		// TODO Auto-generated constructor stub
		avatar = new ArrayList<String>();
		education = new ArrayList<School>();
		work = new ArrayList<Work>();
		like = new ArrayList<Like>();
		basicInformation = new BasicInformation();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAvatar() {
		return avatar;
	}

	public void setAvatar(List<String> avatar) {
		this.avatar = avatar;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public BasicInformation getBasicInformation() {
		return basicInformation;
	}

	public void setBasicInformation(BasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

	public List<School> getEducation() {
		return education;
	}

	public void setEducation(List<School> education) {
		this.education = education;
	}

	public List<Work> getWork() {
		return work;
	}

	public void setWork(List<Work> work) {
		this.work = work;
	}

	public List<Like> getLike() {
		return like;
	}

	public void setLike(List<Like> like) {
		this.like = like;
	}

	public EmotionalState getEmotionalState() {
		return emotionalState;
	}

	public void setEmotionalState(EmotionalState emotionalState) {
		this.emotionalState = emotionalState;
	}
	
}
