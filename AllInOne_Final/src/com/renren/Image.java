package com.renren;

public class Image{
	enum ImageSize {
		MAIN, // ö�� 200pt x 600pt
		TINY, // ö�� 50pt x 50pt
		LARGE, // ö�� 720pt x 720pt
		HEAD // ö�� 100pt x 300pt
	}

	public ImageSize size; // ͼƬ�Ĵ�С
	public String url; // ͼƬ��URL
	
	public ImageSize getSize() {
		return size;
	}
	public void setSize(ImageSize size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
