package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.userrole.entries;

import java.sql.Date;

public class UserRole {
	private int id;
	private int roleId;
	private int userId;
	private Date crateDate;
	private Date updateTime;
	private int updateBy;
	private int createBy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCrateDate() {
		return crateDate;
	}
	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
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
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

}
