package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.user.entries;

import java.sql.Date;

public class User {
	private int userId;
	private String account;
	private String userName;
	private Date createDate;
	private int createBy;
	private Date updateTime;
	private int updateBy;
	private String password;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account
				+ ", userName=" + userName + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", updateTime=" + updateTime
				+ ", updateBy=" + updateBy + ", password=" + password + "]";
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
}
