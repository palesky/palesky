package com.model.bean;

/*
 * 7/10 第一次测试  by lk
 * steps 详细介绍 该用例实现的步骤
 * 
 * */
public class UsecaseBean {
	String id;
	String usecaseLibId;
	String createdBy;
	String createdDate;
	String steps;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsecaseLibId() {
		return usecaseLibId;
	}
	public void setUsecaseLibId(String usecaseLibId) {
		this.usecaseLibId = usecaseLibId;
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
	
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	
	@Override
	public String toString() {
		return "UsecaseBean [id=" + id + ", usecaseLibId=" + usecaseLibId +  ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", steps=" + steps + "]";
	}
	
	
}
