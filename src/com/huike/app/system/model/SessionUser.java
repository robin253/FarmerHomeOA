package com.huike.app.system.model;

import java.util.Date;

public class SessionUser {
	private Integer id; // 编号

	private Integer userType; // 用户类型 0：系统管理员，3：会员

	private String name; // 昵称

	private Integer age; // 年龄

	private String headUrl; // 头像地址

	private Integer level; // 优先级 1.最高

	private Integer status; // 状态 1.正常 2.冻结 3.删除 4.异常

	private Date createTime; // 创建时间

	private Integer roleid; // 角色ID

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
}
