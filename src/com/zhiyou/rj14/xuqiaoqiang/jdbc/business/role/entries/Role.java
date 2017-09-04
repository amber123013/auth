package com.zhiyou.rj14.xuqiaoqiang.jdbc.business.role.entries;

import java.sql.Date;

public class Role {
	/**
	 * CREATE TABLE `role` (
	  `role_id` int(11) NOT NULL AUTO_INCREMENT,
	  `role_name` varchar(30) DEFAULT NULL,
	  `create_date` datetime DEFAULT NULL,
	  `create_by` int(11) DEFAULT NULL,
	  `update_time` datetime DEFAULT NULL,
	  `update_by` int(11) DEFAULT NULL,
	  `last_update_time` datetime DEFAULT NULL,
	  PRIMARY KEY (`role_id`)
	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1
	 */
	private int roleId;
	private String roleName;
	private Date crateDate;
	private Date updateTime;
	private int updateBy;
	private int createBy;
	private Date lastUpdateTime;
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", crateDate=" + crateDate + ", updateTime=" + updateTime
				+ ", updateBy=" + updateBy + ", lastUpdateTime="
				+ lastUpdateTime + "]";
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCrateDate() {
		return crateDate;
	}
	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
