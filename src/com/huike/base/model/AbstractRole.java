package com.huike.base.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色实体
 * 
 * @author shadow
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractRole implements Serializable {

	private Integer id; // 编号

	private String name; // 角色名称

	private String expound; // 描述信息

	private Integer status; // 状态 1.正常 2.冻结 3.删除 4.异常
	
	private Date createTime; // 创建时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpound() {
		return expound;
	}

	public void setExpound(String expound) {
		this.expound = expound;
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

}