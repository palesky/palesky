package com.model.bean;

public class ProjectBean {
	String id;
	String name;
	String status;
	String createdBy;
	String createdDate;
	String endDate;
	String explain;
	String team;
	String confirmedBy;
	String prod_id;
	String chargeBy;
	int bugNum;
	
	public String getChargeBy() {
		return chargeBy;
	}

	public void setChargeBy(String chargeBy) {
		this.chargeBy = chargeBy;
	}

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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getConfirmedBy() {
		return confirmedBy;
	}

	public void setConfirmedBy(String confirmedBy) {
		this.confirmedBy = confirmedBy;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
    
	public int getBugNum(){
		return bugNum;
	}
	public void setBugNum(int num){
		this.bugNum=num;
	}
	@Override
	public String toString() {
		return "ProjectBean [id=" + id + ", name=" + name + ", status=" + status + ", createdBy=" + createdBy
				+ ", createDate=" + createdDate + ", endDate=" + endDate + ", explain=" + explain + ", team=" + team
				+ ", confirmedBy=" + confirmedBy + ", prod_id=" + prod_id + "]";
	}
	
}
