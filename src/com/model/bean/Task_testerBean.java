package com.model.bean;

public class Task_testerBean{
	String id;
	String name;
	String taskId;
	String userId;
	String createdDate;
	int bugNum;
	
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
	
	public void setTaskId(String taskId){
		this.taskId=taskId;
	}
	public String getTaskId(){
		return taskId;
	}
	
	public void setUserId(String userId){
		this.userId=userId;
	}
	public String getUserId(){
		return userId;
	}
	
	public void setCreatedDate(String createdDate){
		this.createdDate=createdDate;
	}
	public String getCreateDate(){
		return createdDate;
	}
	
	public void setBugNum(int bugNum){
		this.bugNum=bugNum;
	}
	public int getBugNum(){
		return bugNum;
	}
	
}