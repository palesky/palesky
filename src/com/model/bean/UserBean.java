package com.model.bean;

public class UserBean {
	String id;
	String realname;
	String account;//账户
	String password;
//	String dept;//部门
	String role;//职位
	String gender;//性别
	String email;
	String phone;
	
	String ip;
	String lastLogin;
	int visits;;//总共登录次数
	String privilege;
	String belongTo;
	
	public UserBean(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", realname=" + realname + ", account=" + account + ", password=" + password
				+ ", role=" + role + ", gender=" + gender + ", email=" + email + ", phone=" + phone + ", ip=" + ip
				+ ", lastLogin=" + lastLogin + ", visits=" + visits + ", privilege=" + privilege + ", belongTo="
				+ belongTo + "]";
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	};
	
}
