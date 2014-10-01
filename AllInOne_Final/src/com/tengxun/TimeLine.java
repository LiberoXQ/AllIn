package com.tengxun;

import android.R.string;

public class TimeLine 
{
	private long errcode;//返回错误码
	private string msg;//错误信息
	private long ret;//返回值，0-成功，非0-失败
	private Data data;
	private long seqid;//序列号
	
	public long getErrcode() {
		return errcode;
	}
	public void setErrcode(long errcode) {
		this.errcode = errcode;
	}
	public string getMsg() {
		return msg;
	}
	public void setMsg(string msg) {
		this.msg = msg;
	}
	public long getRet() {
		return ret;
	}
	public void setRet(long ret) {
		this.ret = ret;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public long getSeqid() {
		return seqid;
	}
	public void setSeqid(long seqid) {
		this.seqid = seqid;
	}
}
