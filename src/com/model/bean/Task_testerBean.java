package com.model.bean;

public class Task_testerBean{
	int id;
	String taskId;
	String userId;
	String createdDate;
	int bugNum;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
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