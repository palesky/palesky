package com.model.bean;

public class BugBean {
	String id;
	String name;
	String status;
	String bug_type;
	String os;
	String browser;
	String foundBy;
	String foundDate;
	String priority;
	String steps;
	//usecaseId 该bug 是通过该用例测出来的
	//如果usecaseId为空  说明该id是 自主发现的
	String usecaseId;
	String task_testerId;
	String chargeBy;
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id=id;
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
	public String getBug_type() {
		return bug_type;
	}
	public void setBug_type(String bug_type) {
		this.bug_type = bug_type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getFoundBy() {
		return foundBy;
	}
	public void setFoundBy(String fondBy) {
		this.foundBy = fondBy;
	}
	public String getFoundDate() {
		return foundDate;
	}
	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public String getUsecaseId() {
		return usecaseId;
	}
	public void setUsecaseId(String usecaseId) {
		this.usecaseId = usecaseId;
	}
	public String getTask_testerId() {
		return task_testerId;
	}
	public void setTask_testerId(String task_testerId) {
		this.task_testerId = task_testerId;
	}
	public String getChargeBy(){
		return chargeBy;
	}
	public void setChargeBy(String chargeBy){
		this.chargeBy=chargeBy;
	}
	@Override
	public String toString() {
		return "BugBean [id="+ id +" , name=" + name + ", status=" + status + ", bug_type=" + bug_type + ", os=" + os + ", browser="
				+ browser + ", foundBy=" + foundBy + ", foundDate=" + foundDate + ", priority=" + priority + ", steps="
				+ steps + ", usecaseId=" + usecaseId + ", task_testerId=" + task_testerId + "]";
	}
	
}
