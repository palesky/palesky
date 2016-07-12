package com.model.bean;

public class TmpBean {
	String userid;
	String prod_id;
	String proj_id;
	String dema_id;
	String task_id;
	String bug_id;
	String case_id;
	
	public TmpBean(){}
	
	public void setUserid(String userid){
		this.userid=userid;
	}
	public String getUserid(){
		return userid;
	}
	//------------------------------------------------
	public void setProd_id(String prodid){
		this.prod_id=prodid;
	}
	public String getProd_id(){
		return prod_id;
	}
	//------------------------------------------------
	public void setProj_id(String proj_id){
		this.proj_id=proj_id;
	}
	public String getProj_id(){
		return proj_id;
	}
	//------------------------------------------------
	public void setDema_id(String dema_id){
		this.dema_id=dema_id;
	}
	public String getDema_id(){
		return dema_id;
    }
	//------------------------------------------------
	public void setTask_id(String task_id){
		this.task_id=task_id;
	}
	public String getTask_id(){
		return task_id;
	}
	//-------------------------------------------------
	public void setBug_id(String bug_id){
		this.bug_id=bug_id;
	}
	public String getBug_id(){
		return bug_id;
	}
	//--------------------------------------------------
	public void setCase_id(String case_id){
		this.case_id=case_id;
	}
	public String getCase_id(){
		return case_id;
	}
    //--------------------------------------------------	
}