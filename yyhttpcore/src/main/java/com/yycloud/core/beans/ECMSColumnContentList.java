package com.yycloud.core.beans;

import java.io.Serializable;

public class ECMSColumnContentList implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String smallpic;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSmallpic() {
		return smallpic;
	}

	public void setSmallpic(String smallpic) {
		this.smallpic = smallpic;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
