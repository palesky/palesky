package com.model.bean;

public class Task_developerBean{
	String id;
	String name;
	String userId;
	String taskId;
	String createdDate;
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
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