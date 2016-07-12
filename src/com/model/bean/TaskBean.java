package com.model.bean;

public class TaskBean {
	String id;
	String name;
	String status;
	String createdBy;
	String createdDate; //从系统中读取  不给出获取和设定方法-----还是给一下获取方法吧
	String endDate;
	String explain;
	String lastEditedBy;
	String lastEditedDate;
	String confirmedBy;
	String demand_id;
	String chargeBy;
	int bugNum;
	
	public String getCreatedDate(){
		return createdDate;
	}
	public void setCreatedDate(String createdDate){
		this.createdDate=createdDate;
	}
	//--------------------------------------------------
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//--------------------------------------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//--------------------------------------------------
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//--------------------------------------------------
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	//--------------------------------------------------	
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	//--------------------------------------------------
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	//--------------------------------------------------
	public String getLastEditedBy() {
		return lastEditedBy;
	}
	public void setLastEditedBy(String lastEditedBy) {
		this.lastEditedBy = lastEditedBy;
	}
	//--------------------------------------------------
	public String getLastEditedDate(){
		return lastEditedDate;
	}
	public void setLastEditedDate(String lastEditedDate){
		this.lastEditedDate=lastEditedDate;
	}
	//---------------------------------------------------
	public String getConfirmedBy() {
		return confirmedBy;
	}
	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}
	//---------------------------------------------------
	public String getDemand_id() {
		return demand_id;
	}
	public void setDemand_id(String demand_id) {
		this.demand_id = demand_id;
	}
	//---------------------------------------------------
	public String getChargeBy(){
		return chargeBy;
	}
	public void setChargeBy(String chargeBy){
		this.chargeBy=chargeBy;
	}
	//---------------------------------------------------
	public int getBugNum(){
		return bugNum;
	}
	public void setBugNum(int bugNum){
		this.bugNum=bugNum;
	}
	//---------------------------------------------------
	@Override
	public String toString() {
		return "TaskBean [id=" + id + ", name=" + name + ", status=" + status + ", createdBy=" + createdBy
				+", createdDate= "+createdDate+ ", endDate=" + endDate + ", explain=" + explain + ", lastEditedBy=" + lastEditedBy + ", confirmedBy="
				+ confirmedBy + ", demand_id=" + demand_id + " ,chargeBy"+chargeBy+" ,bugNum"+bugNum+ "]";
	}
	
}
