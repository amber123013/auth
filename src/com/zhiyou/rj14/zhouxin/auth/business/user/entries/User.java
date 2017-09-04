package com.zhiyou.rj14.zhouxin.auth.business.user.entries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private Long userId;
	private String account;
	private String username;
	private String password;
	private String email;
	private String phone;
	private Long createBy;
	private Long lastUpdateBy;
	private Date createTime;
	private Date lastUpdateTime;
	private String createUsername;
	private String lastUpdateUsername;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public String getLastUpdateUsername() {
		return lastUpdateUsername;
	}

	public void setLastUpdateUsername(String lastUpdateUsername) {
		this.lastUpdateUsername = lastUpdateUsername;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", account=" + account + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", createBy=" + createBy + ", lastUpdateBy=" + lastUpdateBy
				+ ", createTime=" + createTime + ", lastUpdateTime=" + lastUpdateTime + "]";
	}

}
