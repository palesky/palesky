package com.model.bean;

public class ProductBean {
	String id;
	String name;
	String status;
	String pro_type;
	String createdBy;
	String createdDate;
	String endDate;
	String explain;
	String confirmedBy;
	String chargeBy;
	int bugNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPro_type() {
		return pro_type;
	}
	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreateDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getConfirmedBy() {
		return confirmedBy;
	}
	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}
	//-----------------------------------------------------
	public String getChargeBy(){
		return chargeBy;
	}
	public void setChargeBy(String chargeBy){
		this.chargeBy=chargeBy;
	}
	
	public int getBugNum(){
		return bugNum;
	}
	public void setBugNum(int num){
		this.bugNum=num;
	}
	//-----------------------------------------------------
	@Override
	public String toString() {
		return "ProductBean [id=" + id + ", name=" + name + ", status=" + status + ", pro_type=" + pro_type
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", endDate=" + endDate + ", explain="
				+ explain + ", confirmedBy=" + confirmedBy + ", chargeBy=" + chargeBy + "]";
	}
	
}
