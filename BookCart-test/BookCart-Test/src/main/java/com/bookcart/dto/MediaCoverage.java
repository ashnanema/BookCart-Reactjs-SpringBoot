package com.bookcart.dto;

public class MediaCoverage {
	int userId;
	int id;
	String title;
	String body;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public MediaCoverage(int userId, int id, String title, String body) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}
	public MediaCoverage() {
		super();
		// TODO Auto-generated constructor stub
	}
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MediaCoverage) {
            return  ((MediaCoverage) obj).id == id ;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.id;
    }
	
	

}
