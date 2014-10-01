package com.renren;

public class School{
	enum EducationBackground {
		DOCTOR, // ö�� ��ʿ
		COLLEGE, // ö�� ����
		GVY, // ö�� У��
		PRIMARY, // ö�� Сѧ
		OTHER, // ö�� ����
		TEACHER, // ö�� ��ʦ
		MASTER, // ö�� ˶ʿ
		HIGHSCHOOL, // ö�� ����
		TECHNICAL, // ö�� ��ר��У
		JUNIOR, // ö�� ����
		SECRET // ö�� ����
	}

	public String name; // ѧУ����
	public String year; // ��ѧ����
	public EducationBackground educationBackground; // ѧ��
	public String department; // ����רҵ
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public EducationBackground getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(EducationBackground educationBackground) {
		this.educationBackground = educationBackground;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
