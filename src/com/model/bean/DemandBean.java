package com.model.bean;

/*
 * 增加 bugNum  chargeBy
 * 
 * */
public class DemandBean{
	String id;
	String name;
	String status;
	String createdBy;
	String createdDate;
	String endDate;
	String explain;
	String lastEditedDate;
	String confirmedBy;
	String project_id;
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
	public String getLastEditedDate() {
		return lastEditedDate;
	}
	public void setLastEditedDate(String lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}
	public String getConfirmedBy() {
		return confirmedBy;
	}
	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
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
		return "DemandDao [id=" + id + ", name=" + name + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", endDate=" + endDate + ", explain=" + explain + ", lastEditedDate="
				+ lastEditedDate + ", confirmedBy=" + confirmedBy + ", project_id=" + project_id + "]";
	}

}
