package com.renren;

public class FeedAttachment{
	enum FeedAttachmentType {
		LINK, // ö�� ����
		ALBUM, // ö�� ���
		BLOG, // ö�� ��־
		VIDEO, // ö�� ��Ƶ
		STATUS, // ö�� ״̬
		PHOTO // ö�� ͼƬ
	}

	public Long id; // ��������id
	public FeedAttachmentType type; // ������������
	public Long ownerId; // ��������ӵ����id
	public String url; // ��������url
	public String orginalUrl; // ͼƬ��ַ����Ƶ���ŵ�ַ
	public String rawImageUrl; // ͼƬ�Ĵ�ͼ��ַ
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public FeedAttachmentType getType() {
		return type;
	}
	public void setType(FeedAttachmentType type) {
		this.type = type;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrginalUrl() {
		return orginalUrl;
	}
	public void setOrginalUrl(String orginalUrl) {
		this.orginalUrl = orginalUrl;
	}
	public String getRawImageUrl() {
		return rawImageUrl;
	}
	public void setRawImageUrl(String rawImageUrl) {
		this.rawImageUrl = rawImageUrl;
	}
	
}
