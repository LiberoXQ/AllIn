package com.tengxun;

public class Info 
{
	private String text;//微博内容
	private String origtext;//原始内容
	private long count;//微博被转次数
	private long mcount;//点评次数
	private String from;//来源
	private String fromurl;//来源url
	private long id;//微博唯一id
	private String image;//图片url列表
	private Video video;
	private Music music;
	private String name;//发表人帐户名
	private long openid;//用户唯一id，与name相对应
	private String nick;//发表人昵称
	private long self;//是否自已发的的微博，0-不是，1-是
	private long timestamp;//发表时间
	private long type;//微博类型，1-原创发表，2-转载，3-私信，4-回复，5-空回，6-提及，7-评论
	private String head;//发表者头像url
	private String location;//发表者所在地
	private long country_code;//国家码（其他时间线一样）
	private long province_code;//省份码（其他时间线一样）
	private long city_code;//城市码（其他时间线一样）
	private long isvip;//是否微博认证用户，0-不是，1-是
	private String geo;//发表者地理信息
	private long status;//微博状态，0-正常，1-系统删除，2-审核中，3-用户删除，4-根删除
	private String emotionurl;//心情图片url
	private long emotiontype;//心情类型
	private String source;//当type=2时，source即为源tweet
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getOrigtext() {
		return origtext;
	}
	public void setOrigtext(String origtext) {
		this.origtext = origtext;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getMcount() {
		return mcount;
	}
	public void setMcount(long mcount) {
		this.mcount = mcount;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getFromurl() {
		return fromurl;
	}
	public void setFromurl(String fromurl) {
		this.fromurl = fromurl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Music getMusic() {
		return music;
	}
	public void setMusic(Music music) {
		this.music = music;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOpenid() {
		return openid;
	}
	public void setOpenid(long openid) {
		this.openid = openid;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public long getSelf() {
		return self;
	}
	public void setSelf(long self) {
		this.self = self;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getCountry_code() {
		return country_code;
	}
	public void setCountry_code(long country_code) {
		this.country_code = country_code;
	}
	public long getProvince_code() {
		return province_code;
	}
	public void setProvince_code(long province_code) {
		this.province_code = province_code;
	}
	public long getCity_code() {
		return city_code;
	}
	public void setCity_code(long city_code) {
		this.city_code = city_code;
	}
	public long getIsvip() {
		return isvip;
	}
	public void setIsvip(long isvip) {
		this.isvip = isvip;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	public String getEmotionurl() {
		return emotionurl;
	}
	public void setEmotionurl(String emotionurl) {
		this.emotionurl = emotionurl;
	}
	public long getEmotiontype() {
		return emotiontype;
	}
	public void setEmotiontype(long emotiontype) {
		this.emotiontype = emotiontype;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}
