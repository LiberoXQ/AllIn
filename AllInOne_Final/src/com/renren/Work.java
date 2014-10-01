package com.renren;

public class Work{
	public String name; // ��˾����
	public String time; // ��ְ����
	public Industry industry; // ��ҵ
	public Job job; // ְλ
	
	public Work() {
		// TODO Auto-generated constructor stub
		industry = new Industry();
		job = new Job();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	
}
