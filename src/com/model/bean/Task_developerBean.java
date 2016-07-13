package com.model.bean;

public class Task_developerBean{
	int id;
	String userId;
	String taskId;
	String createdDate;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	
	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	
	public void setTaskId(String taskId){
		this.taskId=taskId;
	}
	public String getTaskId(){
		return taskId;
	}
	
	public void setCreatedDate(String createdDate){
		this.createdDate=createdDate;
	}
	public String getCreatedDate(){
		return createdDate;
	}
}