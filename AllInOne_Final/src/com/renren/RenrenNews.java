package com.renren;

import java.util.ArrayList;
import java.util.List;

public class RenrenNews {

	public enum FeedType {
		SHARE_VIDEO,   //分享视频
		UPDATE_STATUS, //更新状态̬
		PUBLISH_BLOG,  //发表日志־
		PUBLISH_ONE_PHOTO, //上传单张照片
		SHARE_PHOTO, //分享照片
		SHARE_ALBUM, // 分享相册
		PUBLISH_MORE_PHOTO, // 上传多张照片
		SHARE_LINK, // 分享链接
		SHARE_BLOG, // 分享日志
		ALL         // 全部类型
	}
	
	private Long id; 			//新鲜事的id
	private FeedType type; 		//新鲜事类型
	private String time; 		//新鲜事触发时间
	private User sourceUser; 	//触发新鲜事的用户,用户对象中只有id name avatar 字段有效，其他字段无
	private String message; 	//新鲜事内容
	private String thumbnailUrl; //新鲜事中包含的缩略图
	private FeedResource resource; //新鲜事中包含的资源
	private List<FeedAttachment> attachment; //新鲜事中的附加内容
	private Source source;		 //新鲜事来源
	private Lbs lbs;		 	//lbs信息
	private List<FeedComment> comments; //新鲜事评论列表，只返回第一条及最后一条
	
	public RenrenNews() {
		attachment = new ArrayList<FeedAttachment>();
		comments = new ArrayList<FeedComment>();
		sourceUser = new User();
		resource = new FeedResource();
		source = new Source();
		lbs = new Lbs();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FeedType getType() {
		return type;
	}

	public void setType(FeedType type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getSourceUser() {
		return sourceUser;
	}

	public void setSourceUser(User sourceUser) {
		this.sourceUser = sourceUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public FeedResource getResource() {
		return resource;
	}

	public void setResource(FeedResource resource) {
		this.resource = resource;
	}

	public List<FeedAttachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<FeedAttachment> attachment) {
		this.attachment = attachment;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Lbs getLbs() {
		return lbs;
	}

	public void setLbs(Lbs lbs) {
		this.lbs = lbs;
	}

	public List<FeedComment> getComments() {
		return comments;
	}

	public void setComments(List<FeedComment> comments) {
		this.comments = comments;
	}
}
