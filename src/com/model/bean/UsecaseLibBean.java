package com.model.bean;

public class UsecaseLibBean {
	
	String id;
	String usecaseLib_type;
	String createdBy;
	String createdDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsecaseLib_type() {
		return usecaseLib_type;
	}
	public void setUsecaseLib_type(String usecaseLib_type) {
		this.usecaseLib_type = usecaseLib_type;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "UsecaseLibBean [id=" + id + ", usecaseLib_type=" + usecaseLib_type + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + "]";
	}
	
}
